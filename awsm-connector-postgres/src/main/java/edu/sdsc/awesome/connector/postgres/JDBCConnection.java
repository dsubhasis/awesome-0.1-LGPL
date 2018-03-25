package edu.sdsc.awesome.connector.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JDBCConnection {

    final Logger logger = LoggerFactory.getLogger(JDBCConnection.class);
    private Statement stmt;
    private String url;
    private String userName;
    private String passWord;
    private String port;
    private Connection connect;

    public JDBCConnection(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        try {
            connect = this.connectDB(url, userName, passWord);

        } catch (SQLException e) {
            logger.debug("Check JDBC connection : " + e.getSQLState() + e.getMessage());
        }
    }

    public Map pgSQLQuery(String query) throws SQLException {
        ResultSet rs = null;
        try {
            rs = databaseQuery(query);

        } catch (InterruptedException e) {
            logger.debug(e.getLocalizedMessage() + "Connection Problem Chack the network " + query);
        }
        try {
            rs = databaseQuery(query);

        } catch (InterruptedException e) {
            logger.debug(e.getLocalizedMessage() + "Connection Problem Chack the network " + query);
        }
        return resultSetExt(rs);
    }

    public Map resultSetExt(ResultSet rst) throws SQLException {

        ResultSetMetaData rsmd = rst.getMetaData();
        List resultList = new
                ArrayList();
        List colResult = new ArrayList();
        Map resultSet = new HashMap();
        List<Object> elementValue = new ArrayList<Object>();


        int columnCount = rsmd.getColumnCount();
        Map<String, Integer> elementType = new HashMap<String, Integer>();
        for (int i = 1; i <= columnCount; i++) {
            String name = rsmd.getColumnName(i);
            colResult.add(name);
            //System.out.println(name+rsmd.getColumnType(i)+rsmd.getColumnTypeName(i)+rsmd.getSchemaName(i));
            elementType.put(name, rsmd.getColumnType(i));
        }

        List value = extractValues(rst, elementType, colResult);
        resultSet.put("value", value);
        resultSet.put("type", elementType);
        resultSet.put("tuple", colResult);

        return resultSet;

    }

    public List extractValues(ResultSet rs, Map<String, Integer> elementType, List<String> colName) throws SQLException {

        List valueList = new ArrayList();
        while (rs.next()) {

            Map rowValue = new LinkedHashMap();
            for (String col : colName) {

                Integer type = elementType.get(col);


                if(type == 91)
                {
                    rowValue.put(col, rs.getDate(col));

                }

                if (type == 12) {


                    rowValue.put(col, rs.getString(col));

                }


                if (type == 4) {

                    rowValue.put(col, rs.getInt(col));
                }

                if (type == 2003) {



                    rowValue.put(col, rs.getArray(col).getArray());
                }

            }
            valueList.add(rowValue);

        }

        return valueList;

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

                if (counter == 4) {
                    flag = false;
                    logger.debug("Permananely Stopped : Please recover manually " + query);
                } else {

                    Thread.sleep(4000);
                    logger.debug("Wakeup and Retry Number " + counter);
                }

            }
        } while (flag);


        return rs;
    }


    private Connection connectDB(String url, String userName, String passWord) throws SQLException {
        Connection conn = null;

        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", passWord);
        //props.setProperty("ssl","true");
        conn = DriverManager.getConnection(url, props);


        return conn;
    }


}
