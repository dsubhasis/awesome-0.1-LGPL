package edu.sdsc.awesome.connector.postgres;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CommonUtil {

public static JsonObject result(ResultSet rst) throws SQLException {
    JsonObjectBuilder jsob = Json.createObjectBuilder();
    ResultSetMetaData rsmd = rst.getMetaData();
    int columnsNumber = rsmd.getColumnCount();
    while(rst.next())
    {
        for(int i = 0; i< columnsNumber; i++){

            System.out.println(rsmd.getColumnName(i));
            System.out.println(rsmd.getColumnClassName(i));
        }

    }

    return jsob.build();
}





}
