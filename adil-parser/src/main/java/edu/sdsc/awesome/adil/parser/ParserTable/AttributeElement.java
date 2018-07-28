package edu.sdsc.awesome.adil.parser.ParserTable;

public class AttributeElement {

    private String name;
    private String attributeType;

    public AttributeElement(String name, String attributeType, boolean store) {
        this.name = name;
        this.attributeType = attributeType;
        this.store = store;
    }

    private boolean store;
}
