package edu.sdsc.awesome.sdsc.modellib;

import javax.json.JsonObjectBuilder;
import java.util.Map;

public class Cypher {


   private Map propertyMap;
   private String name;

    public Cypher(Map propertyMap, String name) {
        this.propertyMap = propertyMap;
        this.name = name;
        System.out.println("Hi");


    }
}
