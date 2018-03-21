package edu.sdsc.awesome.connector.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JDBCConnection {

    private  Statement stmt;



    final Logger logger = LoggerFactory.getLogger(JDBCConnection.class);


    private String url;
    private String userName;
    private String passWord;
    private String port;
    private Connection connect;


    public JDBCConnection()

    {

    }

    public JDBCConnection(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        try {
            connect = this.connectDB(url, userName, passWord);

        } catch (SQLException e) {
            logger.debug("Check JDBC connection : "+e.getSQLState()+e.getMessage());
        }
    }



    public ResultSet pgSQLQuery(String query) throws SQLException {


        ResultSet rs = null;



        try {
             rs = databaseQuery(query);

        } catch (InterruptedException e) {
            logger.debug(e.getLocalizedMessage() + "Connection Problem Chack the network " + query);
        }


resultSetExt(rs);

        return rs;

    }

    public List resultSetExt(ResultSet rst) throws SQLException {

        ResultSetMetaData rsmd = rst.getMetaData();

        List resultList = new
                ArrayList();
        Map colResult = new HashMap();
        List<Object> elementValue = new ArrayList<Object>();

        Map<String, String> elementType = new HashMap<String, String>();


        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++ ) {
            String name = rsmd.getColumnName(i);
            colResult.put("_name",name);


            System.out.println(rsmd.getColumnTypeName(i));
            Integer nameId = rsmd.getColumnType(i);
            System.out.println(nameId);


            // Do stuff with name
        }

    }


    public ResultSet databaseQuery(String query) throws InterruptedException {

        boolean flag = true;
        int counter = 0;
        ResultSet rs = null;


        do {
            try {
                 stmt = connect.createStatement();

                rs = stmt.executeQuery(query);

                flag = false;


            } catch (SQLException e) {


                logger.debug(e.getLocalizedMessage() + "Error while Executing Query " + query);
                flag = true;
                counter++;

                if(counter==4)
                {
                    flag = false;
                    logger.debug( "Permananely Stopped : Please recover manually " + query);
                }
                else {

                    Thread.sleep(4000);
                    logger.debug( "Wakeup and Retry Number " + counter);
                }

            }
        }while(flag);


  return rs;
    }




    private Connection connectDB( String url,  String userName, String passWord ) throws SQLException {
        Connection conn = null;

            Properties props = new Properties();
            props.setProperty("user",userName);
            props.setProperty("password",passWord);
            //props.setProperty("ssl","true");
            conn = DriverManager.getConnection(url, props);






        return conn;
    }










}
