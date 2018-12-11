package com.awesome.query;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class PostgresClassImplTest extends TestCase {

    public void testQuery() {

       String user = "postgres";
       String password = "postgres";
       String url = "jdbc:postgresql://10.128.6.136";
       Integer port = 5432;
       Map inputMap = new HashMap();
       inputMap.put("userName", user);
       inputMap.put("password", password);
       inputMap.put("url", url);

       PostgresClassImpl pgl = new PostgresClassImpl(inputMap);
       String query = "Select * from test;";
       Map output = pgl.query(query);
       assertEquals(10, output.get("num"));

    }
}