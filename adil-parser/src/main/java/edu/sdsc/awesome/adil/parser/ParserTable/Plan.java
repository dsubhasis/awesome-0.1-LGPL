/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 */

package edu.sdsc.awesome.adil.parser.ParserTable;



import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

import org.apache.commons.lang.RandomStringUtils;
import org.jgrapht.Graph;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import org.jgrapht.traverse.DepthFirstIterator;

import javax.imageio.ImageIO;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class Plan {
private Long planID;
private Map optimization;

private Map nodeProperties;
private Map edgeProperties;
private VariableTable variables;
private List equivPlans;
private List unitPlans;
public Plan() {
       planID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
       unitPlans = new ArrayList();

       optimization = new HashMap();
       nodeProperties = new HashMap();
       edgeProperties = new HashMap();
       equivPlans = new ArrayList();
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }



    public Map getNodeProperties() {
        return nodeProperties;
    }

    public void setNodeProperties(Map nodeProperties) {
        this.nodeProperties = nodeProperties;
    }

    public Map getEdgeProperties() {
        return edgeProperties;
    }

    public void setEdgeProperties(Map edgeProperties) {
        this.edgeProperties = edgeProperties;
    }

    public VariableTable getVariables() {
        return variables;
    }

    public void setVariables(VariableTable variables) {
        this.variables = variables;
    }

    public List getEquivPlans() {
        return equivPlans;
    }

    public void setEquivPlans(List equivPlans) {
        this.equivPlans = equivPlans;
    }

    public void initPlan(JsonObject js, VariableTable vtable) throws URISyntaxException {
        variables = vtable;
        URI rootNode = new URI("#000");
        URI lastnode = rootNode;

        JsonArray analysisSet = js.getJsonArray("ADIL");
        int size = analysisSet.size();
    for(int i = 0; i < size ; i++) {
        JsonArray analysis = analysisSet.getJsonObject(i).getJsonArray("UnitAnalysis");
        String analysisName = analysisSet.getJsonObject(i).getString("name");

        String schedule = analysisSet.getJsonObject(i).getString("SCHEDULED");


        PlanNodeProperty GlobalPlanProp = new PlanNodeProperty();
        Map pnodeProp = new HashMap();
        pnodeProp.put("name", analysisName);
        pnodeProp.put("schedule", schedule);
        pnodeProp.put("type", "analysis");
        pnodeProp.put("children", analysis.size());
        boolean subFunction = true;
        String subFunctionId;
        //@Ignore These are optional properties

        GlobalPlanProp.setInputNode(false);
        GlobalPlanProp.setOutputNode(true);
        GlobalPlanProp.setNodeProperty(pnodeProp);
        GlobalPlanProp.setOperation("ANALYSIS");




        int sizeana = analysis.size();
        String parentNode = analysisName;
        for (int j = 0; j < sizeana; j++) {

            Map UnitPnodeProp = new HashMap();
            Map UnitEdgeProp = new HashMap();

            JsonObject analysisUnit = analysis.getJsonObject(j);
            Graph unitQueryPlan = new DirectedWeightedMultigraph<>(DefaultEdge.class);
            unitQueryPlan.addVertex(rootNode);

            //Analysis variable name

            String name = analysisUnit.getString("name");
            VariableTableEntry vprop = vtable.getVariableProperties(name);
            int length = 10;
            boolean useLetters = true;
            boolean useNumbers = false;
            String edgeId = RandomStringUtils.random(length, useLetters, useNumbers);
            if (vprop != null) {
                UnitPnodeProp = vprop.getPropertyMap();
            } else {
                subFunction = true;

            }

            if (!nodeProperties.containsKey(name)) {
                UnitPnodeProp.put("name", name);
            } else {
                System.out.println("Variable name : " + name + "is already declared");
            }

            Map unitNodeProp = new HashMap();
            if (analysisUnit.containsKey("ASSN")) {

                String typeAna = "assn";
                Map unitDetails = new HashMap();
                JsonObject statement = analysisUnit.getJsonObject("ASSN");
                if (statement.containsKey("JOIN")) {
                        JsonObject predicates = statement.getJsonObject("JOIN").getJsonObject("PREDICATE");
                        Map joinProp = new HashMap();
                        joinProp.put("predicate", predicates);
                        String tempId = RandomStringUtils.random(length, useLetters, useNumbers);
                        URI jname = new URI("join@");
                        lastnode = jname;
                        unitQueryPlan.addVertex(jname);
                         JsonArray sources = statement.getJsonObject("JOIN").getJsonArray("source");
                    for (int kk = 0; kk < sources.size(); kk++) {
                        Map sourceProp = new HashMap();
                        String s1 = sources.getString(kk);
                        sourceProp.put("name", s1);
                        //Validate and Update Type if Static
                        tempId = RandomStringUtils.random(length, useLetters, useNumbers);
                        URI sname = new URI("ds@" + s1);
                        unitQueryPlan.addVertex(sname);
                        sourceProp.put("vtable", vtable.getVariableProperties(s1));
                        unitNodeProp.put(sname, sourceProp);
                        unitQueryPlan.addEdge(lastnode, sname);
                    }
                if(statement.containsKey("ORDER")){
                    JsonArray orderby = statement.getJsonArray("ORDER");
                    int osize = orderby.size();
                    reStructure(orderby, unitQueryPlan, lastnode, "ORDER");
                }
                    if(statement.containsKey("GROUP")){
                        JsonArray orderby = statement.getJsonArray("GROUP");
                        reStructure(orderby,unitQueryPlan,lastnode,"GROUP");

                    }
                }




                unitQueryPlan.addVertex(lastnode);
                unitQueryPlan.addEdge(rootNode, lastnode);
                unitDetails.put("dag", unitQueryPlan);
                unitDetails.put("prop",unitNodeProp);

                try {
                    dispalyGraph(unitQueryPlan);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                unitPlans.add(unitDetails);

            }
           else if(analysisUnit.containsKey("FORALL")){
               String typeAna = "loop";
               String loopid = analysisUnit.getString("lid");
               UnitPnodeProp.put("type", typeAna);
               UnitPnodeProp.put("uid", loopid);
               UnitPnodeProp.put("inloop", subFunction);
               Plan subplan = new Plan();
               JsonArray subjson = analysisUnit.getJsonObject("FORALL").getJsonArray("in");

               for(int ii = 0; ii < subjson.size(); ii++) {

                   String var = subjson.getJsonObject(ii).getString("name");


               }

           }





       }
        System.out.println();
    }

}
    public Long getPlanID() {
        return planID;
    }
    public Map getOptimization() {
        return optimization;
    }


    private void reStructure(JsonArray orderby, Graph unitQueryPlan, URI lastnode, String type) throws URISyntaxException {

        int osize = orderby.size();
        for(int os = 0; os < osize; os++) {
            String tempId = RandomStringUtils.random(5, true, true);
            URI gname = new URI(type+"@"+orderby.getString(os));

            unitQueryPlan.addVertex(gname);
            unitQueryPlan.addEdge(gname, lastnode, tempId);
            lastnode = gname;

        }

    }




    private static void traverseHrefGraph(Graph hrefGraph, String node) throws URISyntaxException {



        Iterator<URI> iterator = new DepthFirstIterator<>(hrefGraph, new URI(node));
        while (iterator.hasNext()) {
            URI uri = iterator.next();
            System.out.println(uri);
        }
    }
    private void dispalyGraph(Graph g) throws IOException {


        JGraphXAdapter<String, DefaultEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultEdge>(g);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        BufferedImage image =
                mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File("graph.png");
        ImageIO.write(image, "PNG", imgFile);

    }





}
