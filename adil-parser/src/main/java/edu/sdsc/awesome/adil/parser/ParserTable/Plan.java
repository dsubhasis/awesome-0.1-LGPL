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


import org.apache.commons.lang.RandomStringUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.*;

public class Plan {
private Long planID;
private Map optimization;
private Graph planDAG;
private Map nodeProperties;
private Map edgeProperties;
private VariableTable variables;
private List equivPlans;
public Plan() {
       planID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
       planDAG = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
       optimization = new HashMap();
       nodeProperties = new HashMap();
       edgeProperties = new HashMap();
       equivPlans = new ArrayList();
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }

    public void setPlanDAG(Graph planDAG) {
        this.planDAG = planDAG;
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

    public void initPlan(JsonObject js, VariableTable vtable){
        variables = vtable;
        String rootNode = "#000";
        planDAG.addVertex(rootNode);

        JsonArray analysisSet = js.getJsonArray("ADIL");
        int size = analysisSet.size();
    for(int i = 0; i < size ; i++){
        JsonArray analysis = analysisSet.getJsonObject(i).getJsonArray("UnitAnalysis");
        String analysisName = analysisSet.getJsonObject(i).getString("name");
        planDAG.addVertex(analysisName);
        planDAG.addEdge(rootNode,analysisName,"#000");
        String schedule = analysisSet.getJsonObject(i).getString("SCHEDULED");
        PlanNodeProperty pnode = new PlanNodeProperty();
        Map pnodeProp = new HashMap();
        pnodeProp.put("name", analysisName);
        pnodeProp.put("schedule",schedule);
        pnodeProp.put("type", "analysis");
        pnodeProp.put("children",analysis.size());
        boolean subFunction = true;
        String subFunctionId ;
        pnode.setInputNode(false);
        pnode.setOutputNode(true);
        pnode.setNodeProperty(pnodeProp);
        pnode.setOperation("ANALYSIS");
        nodeProperties.put(analysisName, pnode);

        int sizeana = analysis.size();
        String parentNode = analysisName;
        for(int j = 0; j < sizeana ; j++)
        {
            PlanNodeProperty UnitPnode = new PlanNodeProperty();
            Map UnitPnodeProp = new HashMap();
            Map UnitEdgeProp = new HashMap();

            JsonObject analysisUnit = analysis.getJsonObject(j);
            String name = analysisUnit.getString("name");
            VariableTableEntry vprop = vtable.getVariableProperties(name);
            int length = 10;
            boolean useLetters = true;
            boolean useNumbers = false;
            String edgeId = RandomStringUtils.random(length, useLetters, useNumbers);
           if(vprop != null ) {
               UnitPnodeProp = vprop.getPropertyMap();
           }
           else{
               subFunction =  true;
               subFunctionId = analysisUnit.getString("lid");


           }
            if(!nodeProperties.containsKey(name)) {
                UnitPnodeProp.put("name", name);
            }
            else {
                System.out.println("Variable name : "+name+"is already declared");
            }
           if(analysisUnit.containsKey("ASSN"))
           {
               String typeAna = "assn";

               UnitPnodeProp.put("type", typeAna);
               UnitEdgeProp.put("type", typeAna);

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

                   String var = subjson.getJsonObject(ii).getString("id");


               }



           }

           nodeProperties.put(name,UnitPnodeProp);
           planDAG.addVertex(name);
           planDAG.addEdge(parentNode, name, edgeId);
           parentNode = name;


       }
        System.out.println();
    }
    System.out.println("Test");
}
    public Long getPlanID() {
        return planID;
    }
    public Map getOptimization() {
        return optimization;
    }


    public Graph getPlanDAG() {
        return planDAG;
    }


}
