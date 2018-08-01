package edu.sdsc.awesome.adil.parser.ParserTable;

import java.util.HashMap;
import java.util.Map;

public class Table {
    private Map<String, AttributeElement> attributeTable;
    private Map<String, DecisionElement> decisionTable;

    public Table() {
        attributeTable = new HashMap<String, AttributeElement>();
        decisionTable = new HashMap<String, DecisionElement>();
    }

    public void addAttribute(String name, String attributeType, boolean store)
    {
        AttributeElement at = new AttributeElement(name, attributeType, store);
        attributeTable.put(name,at);
    }

    public boolean checkAttribute(String name){
        return attributeTable.containsKey(name);
    }

    public boolean delAttribute(String name){
        boolean flag =false;
        if(attributeTable.containsKey(name))
        {
            attributeTable.remove(name);
            flag = true;
        }
        return flag;
    }

    public DecisionElement getDecisionElement(String name){

        return decisionTable.get(name);

    }



}
