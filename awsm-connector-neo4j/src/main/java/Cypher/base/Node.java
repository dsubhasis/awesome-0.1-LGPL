package Cypher.base;

import java.util.List;

public class Node {

    private String type;
    private String identifier;
    private Object indetifierValue;
    private String indetifierType;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    private List<Property> prop;

    public List<Property> getProp() {
        return prop;
    }

    public void setProp(List<Property> prop) {
        this.prop = prop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
