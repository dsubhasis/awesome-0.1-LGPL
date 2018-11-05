package edu.sdsc.awesome.adil.parser.ParserTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VariableTable {

    private HashMap nameList;

    private HashMap depdendancyList;
    private HashMap definedType;

    public HashMap getNameList() {
        return nameList;
    }

    public void setNameList(HashMap nameList) {
        this.nameList = nameList;
    }



    public HashMap getDepdendancyList() {
        return depdendancyList;
    }

    public void setDepdendancyList(String name, String dep) {
        depdendancyList.put(name, dep);
    }

    public VariableTable() {

        nameList = new HashMap<String, String>();
        definedType = new HashMap<String, String>();
        depdendancyList = new HashMap<String, String>();
    }

    public void addVar(String var, String type, String parent, boolean baseType){

        nameList.put(var, type);
        definedType.put(var, baseType);

        depdendancyList.put(var, parent);

    }

    public boolean checkvar(String name){


        return nameList.containsKey(name);
    }
    public List getVariable(String name){


        List varDetails = new ArrayList();
        varDetails.add(nameList.get(name));
        varDetails.add(depdendancyList.get(name));
        varDetails.add(definedType.get(name));

        return varDetails;

    }
}
