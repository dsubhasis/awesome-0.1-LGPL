package Cypher.base;

import java.util.List;

public class Edge {

    private Node startNoed;
    private Node endNode;
    private String type;
    private boolean directed;
    private List<Property> edgeProperty;
    private String identifier;
    private Object indetifierValue;
    private String indetifierType;

    public Node getStartNoed() {
        return startNoed;
    }

    public void setStartNoed(Node startNoed) {
        this.startNoed = startNoed;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public List<Property> getEdgeProperty() {
        return edgeProperty;
    }

    public void setEdgeProperty(List<Property> edgeProperty) {
        this.edgeProperty = edgeProperty;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Object getIndetifierValue() {
        return indetifierValue;
    }

    public void setIndetifierValue(Object indetifierValue) {
        this.indetifierValue = indetifierValue;
    }

    public String getIndetifierType() {
        return indetifierType;
    }

    public void setIndetifierType(String indetifierType) {
        this.indetifierType = indetifierType;
    }
}
