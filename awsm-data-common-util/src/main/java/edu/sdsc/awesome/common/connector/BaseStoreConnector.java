package edu.sdsc.awesome.common.connector;

import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import org.eclipse.collections.impl.bimap.mutable.HashBiMap;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class BaseStoreConnector {

    public boolean PostgresConnector(Map InputMap, Map outPutMap){



        String url = (String) InputMap.get("url");
        String userName = (String) InputMap.get("userName");
        Integer port = (Integer) InputMap.get("port");
        String password = (String) InputMap.get("password");

        Boolean errorFlag = true;
        JDBCConnection jc;
        try {
            jc = new JDBCConnection(userName, url, password);
            outPutMap.put("connection", jc);
        } catch (Exception e) {
            errorFlag = false;
            outPutMap.put("errormsg", e.getMessage());

        }

        outPutMap.put("errorFlag", errorFlag);


  return true;
    }


    public boolean solrConnector(Map InPutMap, Map outPutMap){


        return true;
    }
}
