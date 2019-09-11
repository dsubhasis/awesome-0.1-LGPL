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
