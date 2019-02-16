package Cypher.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.neo4j.register.Register;

import java.util.ArrayList;
import java.util.List;

public class CypherInsert {
    public List<String> Match(List<Node> nodes){
        List<String> createNode = new ArrayList<>();
        for (Node n : nodes) {
            String uuid1 = RandomStringUtils.randomAlphabetic(8);
            List<Property> prop = n.getProp();
            String propString ="{ ";
            for(Property p : prop) {
               String name = p.getName();
               if(p.getType().equalsIgnoreCase("string")){
                 propString = propString + name +": \""+p.getValue().toString()+"\"," ;
               }
            }
            propString = propString + "}";
            String propType = n.getType();
            String query1 = "MERGE ("+uuid1+":"+propType+propString+")";
           createNode.add(query1);
        }
    return createNode;
    }
    public List<String> EdgeMerge(List<Edge> edgeList) {
        List<String> cypherList = new ArrayList<>();
        for (Edge edge : edgeList) {
            String uuid1 = RandomStringUtils.randomAlphabetic(8);
            String uuid2 = RandomStringUtils.randomAlphabetic(8);
            Node startNode = edge.getStartNoed();
            Node endNode = edge.getStartNoed();
            String matchPortion = "";
            if (startNode.getIndetifierType().equalsIgnoreCase("string") & endNode.getIndetifierType().equalsIgnoreCase("string")) {
                String endIdentifier = (String) startNode.getIndetifierValue();
                String startIdentifier = (String) startNode.getIndetifierValue();
                matchPortion = matchNode(uuid1, startNode.getType(), startNode.getIdentifier(), startIdentifier, uuid2, endNode.getType(), endNode.getIdentifier(), endIdentifier);
            } else if (startNode.getIndetifierType().equalsIgnoreCase("Integer") & endNode.getIndetifierType().equalsIgnoreCase("string")) {
                String endIdentifier = (String) startNode.getIndetifierValue();
                Integer startIdentifier = (Integer) startNode.getIndetifierValue();
                matchPortion = matchNode(uuid1, startNode.getType(), startNode.getIdentifier(), startIdentifier, uuid2, endNode.getType(), endNode.getIdentifier(), endIdentifier);

            } else if (startNode.getIndetifierType().equalsIgnoreCase("Integer") & endNode.getIndetifierType().equalsIgnoreCase("Integer")) {
                Integer endIdentifier = (Integer) startNode.getIndetifierValue();
                Integer startIdentifier = (Integer) startNode.getIndetifierValue();
                matchPortion = matchNode(uuid1, startNode.getType(), startNode.getIdentifier(), startIdentifier, uuid2, endNode.getType(), endNode.getIdentifier(), endIdentifier);
            } else if (startNode.getIndetifierType().equalsIgnoreCase("string") & endNode.getIndetifierType().equalsIgnoreCase("Integer")) {
                Integer endIdentifier = (Integer) startNode.getIndetifierValue();
                String startIdentifier = (String) startNode.getIndetifierValue();
                matchPortion = matchNode(uuid1, startNode.getType(), startNode.getIdentifier(), startIdentifier, uuid2, endNode.getType(), endNode.getIdentifier(), endIdentifier);
            }
            String query = matchPortion + merge(uuid1, uuid2, edge);
            cypherList.add(query);
        }
        return cypherList;
    }

    public String merge(String startNodeid, String endNodeid, Edge edge){
        String uuid3 = RandomStringUtils.randomAlphabetic(8);
        List<Property> edgePropertyList = edge.getEdgeProperty();
        String propertyString = " ";
        for(Property edgeProperty : edgePropertyList) {
            String edgePropertyName = edgeProperty.getName();
            if(edgeProperty.getType().equalsIgnoreCase("string")) {
                String edgePropValue = (String)edgeProperty.getValue();
                propertyString = propertyString + uuid3 + "." + edgePropertyName + "="+edgePropValue+",";
            }
            if(edgeProperty.getType().equalsIgnoreCase("Integer")){
                Integer edgePropValue = (Integer) edgeProperty.getValue();
                propertyString = propertyString + uuid3 + "." + edgePropertyName + "=" + edgePropValue;

            }
        }
        String mergeString = "MERGE (" +startNodeid+")-["+uuid3+"]-("+endNodeid+") ON CREATE SET = "+uuid3+"."+propertyString;
        return mergeString;
    }

    public String matchNode(String statId, String startNodeType, String startNodeIdentifierName, String startNodeIdentifier, String endId, String endNodeType, String endNodeIdentifierName, String endNodeIdentifier){
        String matchPortion = "MATCH (" + statId + ":" + startNodeType + "{" +startNodeIdentifierName+ ":\""+startNodeIdentifier+"\"}), (" + statId + ":\" + startNodeType + \"{" +startNodeIdentifierName+ ":\""+endNodeIdentifier+"\"})";
        return matchPortion;
    }


    public String matchNode(String statId, String startNodeType, String startNodeIdentifierName, Integer startNodeIdentifier, String endId, String endNodeType, String endNodeIdentifierName, String endNodeIdentifier){



        String matchPortion = "MATCH (" + statId + ":" + startNodeType + "{" +startNodeIdentifierName+ ":"+startNodeIdentifier+"}), (" + statId + ":\" + startNodeType + \"{" +startNodeIdentifierName+ ":\""+endNodeIdentifier+"\"})";


        return matchPortion;

    }

    public String matchNode(String statId, String startNodeType, String startNodeIdentifierName, Integer startNodeIdentifier, String endId, String endNodeType, String endNodeIdentifierName, Integer endNodeIdentifier){

        String matchPortion = "MATCH (" + statId + ":" + startNodeType + "{" +startNodeIdentifierName+ ":"+startNodeIdentifier+"}), (" + statId + ":\" + startNodeType + \"{" +startNodeIdentifierName+ ":"+endNodeIdentifier+"})";

        return matchPortion;

    }

    public String matchNode(String statId, String startNodeType, String startNodeIdentifierName, String startNodeIdentifier, String endId, String endNodeType, String endNodeIdentifierName, Integer endNodeIdentifier){



        String matchPortion = "MATCH (" + statId + ":" + startNodeType + "{" +startNodeIdentifierName+ ":\""+startNodeIdentifier+"\"}), (" + statId + ":\" + startNodeType + \"{" +startNodeIdentifierName+ ":\""+endNodeIdentifier+"\"})";


        return matchPortion;

    }





}
