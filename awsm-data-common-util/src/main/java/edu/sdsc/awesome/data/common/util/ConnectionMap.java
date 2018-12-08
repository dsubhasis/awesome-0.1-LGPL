package edu.sdsc.awesome.data.common.util;

import edu.sdsc.awesome.common.connector.BaseStoreConnector;
import org.eclipse.collections.impl.bimap.mutable.HashBiMap;

import java.util.Map;

public class ConnectionMap {

  private String className;
  private String ip;
  private String driverClass;
  private Map InputMap;
  private Map OutputMap;
  private String testClassName;

  public void createConnetion(Map InputMap){

    BaseStoreConnector baseConnector = new BaseStoreConnector();
    boolean status = false;
    Map connetionMap = new HashBiMap();
    Integer type = (Integer) InputMap.get("type");

   switch (type) {
     case 1:
     {
        status = baseConnector.PostgresConnector(InputMap, connetionMap);

     }
     case 2 :
     {
       status = baseConnector.solrConnector(InputMap, connetionMap);
     }

   }
  }



}
