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

        BaseStoreConnector bs = new BaseStoreConnector();
        bs.PostgresConnector(InputMap, outputMap);

        boolean flag = (boolean) outputMap.get("errorFlag");
        if(!flag){

            JDBCConnection jdb = (JDBCConnection) outputMap.get("connection");

            try {
                jdb.pgSQLQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


return outputMap;

    }



}
