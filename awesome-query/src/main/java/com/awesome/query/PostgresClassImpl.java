package com.awesome.query;

import edu.sdsc.awesome.common.connector.BaseStoreConnector;
import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import edu.sdsc.awesome.data.common.util.ConnectionMap;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PostgresClassImpl  {

    private Map InputMap;

    public PostgresClassImpl(Map inputMap) {
        InputMap = inputMap;
    }




    public Map query(String query){

        Map outputMap = new HashMap();
        Map result ;
        BaseStoreConnector bs = new BaseStoreConnector();
        bs.PostgresConnector(InputMap, outputMap);
        bs.PostgresPlanGenerator(query);
        boolean flag = (boolean) outputMap.get("errorFlag");
        if(!flag){
            JDBCConnection jdb = (JDBCConnection) outputMap.get("connection");


            try {
                result = jdb.pgSQLQuery(query);
                outputMap.put("result", result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
return outputMap;

    }



}
