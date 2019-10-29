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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PostGresQuery {


    public static void main(String[] args) {

        System.out.println("Hello World");

        PostGresQuery pgq = new PostGresQuery();
        String query = "SELECT * FROM usnewspaper where news  LIKE '%LA%' AND news LIKE '%shooting%' AND news LIKE '%police officer%';";
         pgq.pgsqlPassThrough(query);
    }


    public Map pgsqlPassThrough(String query){

        Map <String, Object> resultMap = new HashMap<String, Object>();

        ResultSet rst = null;
        JDBCConnection jd = new JDBCConnection(CommonDBUtil.chinaLabURL, CommonDBUtil.chinaLabpgsqlUser,CommonDBUtil.chinaLabpgsqlPassword );

        try {
            resultMap = jd.pgSQLQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // String query = getCountQuery(databaseName);

       return resultMap;
        

    }
}
