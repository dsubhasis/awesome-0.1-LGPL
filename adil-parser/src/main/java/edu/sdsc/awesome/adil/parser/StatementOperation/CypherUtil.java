package edu.sdsc.awesome.adil.parser.StatementOperation;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class CypherUtil {


    public static JsonObjectBuilder handleCypheNode(JsonObjectBuilder orginaObject){


        JsonObjectBuilder jb = Json.createObjectBuilder();



        return orginaObject;



    }

    public static JsonObjectBuilder handleMatchStatement(JsonObjectBuilder jObject, String match, JsonObjectBuilder mergeStmt ){


        JsonObjectBuilder temp = Json.createObjectBuilder();
        temp.add("Operation", mergeStmt.build());
        temp.add("Variables", match);

        jObject.add("Match", temp.build());

      return jObject;
    }
   public static JsonObjectBuilder  handleCypherNode(JsonObjectBuilder  jObject,JsonObjectBuilder jb, String node){

        JsonObjectBuilder temp = Json.createObjectBuilder();
        temp.add("name", node);
        temp.add("properties", jb.build());
        temp.add("type", "AdilNode");

     jObject.add(node, temp.build());


        return jObject;
   }
    public static JsonObjectBuilder handleNodeProperty(JsonObjectBuilder jObject,  String image, String unnestName, boolean flag ){

        JsonObjectBuilder temp = Json.createObjectBuilder();
        temp.add("variable", image);

       if(flag)
       {
           temp.add("unnest", flag);
           temp.add("src", unnestName);
       }
        if(!flag)
        {
            temp.add("unnest", flag);
            temp.add("src", unnestName);
        }



       jObject.add(image , temp.build());
       return jObject;
    }
}
