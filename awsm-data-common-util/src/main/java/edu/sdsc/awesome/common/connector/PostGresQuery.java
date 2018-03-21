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
            rst = jd.pgSQLQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // String query = getCountQuery(databaseName);

       return resultMap;
        

    }
}
