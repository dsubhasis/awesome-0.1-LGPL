package com.awesome.query;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresClassImplTest extends TestCase {

    public void testQuery() {

       String user = "postgres";
       String password = "Sdsc2018#";
       String url = "jdbc:postgresql://10.128.6.136";
       Integer port = 5432;
       Map inputMap = new HashMap();
       inputMap.put("userName", user);
       inputMap.put("password", password);
       inputMap.put("url", url);

       PostgresClassImpl pgl = new PostgresClassImpl(inputMap);
       String query = "Select count(*) from senate;";
       Map output = pgl.query(query);
       Map result = (Map) output.get("result");

      List<String> fieldName = (List) result.get("tuple");
      List<Map> values = (List) result.get("value");

     for(Map value : values) {
         for (String ignored : fieldName) {
             int count = (Integer) value.get(ignored);
             assertEquals(100, count);
         }
     }


    }
}