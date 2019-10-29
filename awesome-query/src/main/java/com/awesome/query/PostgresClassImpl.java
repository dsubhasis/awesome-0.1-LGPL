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
