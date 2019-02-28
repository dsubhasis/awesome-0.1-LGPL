package edu.sdsc.awesome.common.connector;

import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public class BaseStoreConnector {

    private JDBCConnection jc;

    public boolean PostgresConnector(Map InputMap, Map outPutMap){

        String url = (String) InputMap.get("url");
        String userName = (String) InputMap.get("userName");
        Integer port = (Integer) InputMap.get("port");
        String password = (String) InputMap.get("password");

        Boolean errorFlag = false;

        try {
            jc = new JDBCConnection(url, userName, password);
            outPutMap.put("connection", jc);
            outPutMap.put("errorFlag", errorFlag);

        } catch (Exception e) {
            errorFlag = false;
            outPutMap.put("errormsg", e.getMessage());

        }




  return true;
    }

    public void PostgresPlanGenerator(String query){
        String explainQuery = "EXPLAIN ANALYZE "+query;
        Map results = null;

        try {
            jc.pqSQLQueryAnalysis(query);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

        System.out.println(results.size());

    }


    public boolean solrConnector(Map InPutMap, Map outPutMap){




        return true;
    }
}
