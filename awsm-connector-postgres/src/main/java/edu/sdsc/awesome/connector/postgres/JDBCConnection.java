package edu.sdsc.awesome.connector.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

public class JDBCConnection {

    private  Statement stmt;
    private Connection connect;


    final Logger logger = LoggerFactory.getLogger(JDBCConnection.class);


    private String url;
    private String userName;
    private String passWord;
    private String port;

    public JDBCConnection()

    {

    }

    public JDBCConnection(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;

    }


    public void renewConnection()
    {

    }
    public ResultSet pgSQLQuery(String query) throws SQLException {


        ResultSet rs = null;

        connect = this.connectDB(url,userName,passWord);

        try {
             rs = databaseQuery(query);

        } catch (InterruptedException e) {
            logger.debug(e.getLocalizedMessage() + "Connection Problem Chack the network " + query);
        }


 return rs;

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
           url = "jdbc:postgresql://10.128.37.10/postgres";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","eTalon2018#");
            //props.setProperty("ssl","true");
            conn = DriverManager.getConnection(url, props);






        return conn;
    }








}
