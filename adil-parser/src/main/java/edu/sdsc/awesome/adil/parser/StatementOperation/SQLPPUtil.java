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
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.sdsc.awesome.adil.parser.StatementOperation.ParserUtil.getOperatorCapabilty;
import static edu.sdsc.awesome.adil.parser.StatementOperation.ParserUtil.getStoreCapabilty;

public class SQLPPUtil {


   // public static void printName(){
 //       System.out.println("name");
   // }



    public static JsonObjectBuilder handelSelectStatement(JsonObjectBuilder selectObject, String srcName, boolean srcRenameFlag, boolean selectRenameFlag, boolean unnestFlag,
                                                   boolean groupByFlag, boolean expectFlag, boolean limitFlag, boolean aggrFlag, Map valueObject) throws SQLException {

        JsonObjectBuilder t_job = Json.createObjectBuilder();
        JsonObjectBuilder groupBy = Json.createObjectBuilder();
        JsonArrayBuilder t_store = Json.createArrayBuilder();
        t_store.add("PQSQL");
        t_store.add("ADB");
        String srcTableName = (String) valueObject.get("SRCNAME");
        t_job.add("Source",(String) valueObject.get("SRCNAME"));
        List vars = (List)valueObject.get("SelectVar");
        Map orgRenameMap = new HashMap();
        orgRenameMap= (Map) valueObject.get("SelectMap");
        JsonArrayBuilder t_temp = Json.createArrayBuilder();
        JsonArrayBuilder g_temp = Json.createArrayBuilder();
        for(int i = 0; i < vars.size(); i++)
        {
            String tempVar = vars.get(i).toString();
            t_temp.add(tempVar);
        }
        t_job.add("TUPLE",t_temp.build());
        /*List store = getStoreCapabilty("SRC-SCHEMA", srcTableName );

        String schemaType = "Dependent";
        //String depenDenet

        for (int i = 0; i< store.size(); i++)
        {
        t_store.add((String)store.get(i));
        }*/

        if(groupByFlag)
        {

            List groups = (List)valueObject.get("groupBy");

            for(int i = 0; i < groups.size(); i++)
            {
                String groupName = (String)groups.get(i);
                g_temp.add(groupName);
            }

            groupBy.add("GROUPNAME",(String)valueObject.get("groupName") );
            groupBy.add("GROUPOF",g_temp.build());
            t_job.add("GROUPBY",groupBy.build());
        }

         t_job.add("STOREMODEL",t_store.build());


         if(selectRenameFlag){
             List rename = (List)valueObject.get("SelectVar");

         }



        if(limitFlag){

            t_job.add("LIMIT", Integer.valueOf((String) valueObject.get("limitValue")));
            t_job.add("STORE", false);



        }
        if(unnestFlag)
        {

       //     t_job.add("UNNEST", (String) getOperatorCapabilty("name","unnest", "sqlpp" ));

            t_job.add("UNNEST", "SQLPP");
            t_job.add("UNNESTIMPL", "ADB");
            t_job.add("UNNESTName", (String) valueObject.get("UNNESTName"));
            t_job.add("UNNESTReName", (String) valueObject.get("UNNESTReName"));




        }
        if(groupByFlag)
        {
            //t_job.add("")
        }



selectObject.add("Select", t_job);


        return selectObject;







    }




}
