package edu.awesome.sdsc.base.searchTree;

import java.util.Map;

public class InvertedIndex {

    MapIndex m;

    InfoNode n;



    public InvertedIndex(Map propMap) {


        String name = null;
        if(propMap.containsKey("name")){
            name = (String) propMap.get("name");

            if(propMap.containsKey("maxMem")){

            }
        }

    }
}
