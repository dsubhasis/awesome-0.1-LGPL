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
