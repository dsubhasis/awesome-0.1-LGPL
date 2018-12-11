package edu.sdsc.awesome.data.common.util;

import edu.sdsc.awesome.common.connector.BaseStoreConnector;


import java.util.HashMap;
import java.util.Map;

public class ConnectionMap {

  private String className;
  private String ip;
  private String driverClass;

  private Map outputMap;
  private String testClassName;

  public Map createConnetion(Map InputMap){

    BaseStoreConnector baseConnector = new BaseStoreConnector();
    boolean status = false;

    Integer type = (Integer) InputMap.get("type");

   switch (type) {
     case 1:
     {
        status = baseConnector.PostgresConnector(InputMap,  outputMap);

     }
     case 2 :
     {
       status = baseConnector.solrConnector(InputMap, outputMap);
     }

   }

   return outputMap;
  }



}
