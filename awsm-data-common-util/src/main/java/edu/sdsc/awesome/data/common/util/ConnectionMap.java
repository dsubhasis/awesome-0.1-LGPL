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
