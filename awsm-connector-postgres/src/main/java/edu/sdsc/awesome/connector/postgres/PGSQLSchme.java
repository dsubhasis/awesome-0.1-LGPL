package edu.sdsc.awesome.connector.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PGSQLSchme {

    final Logger logger = LoggerFactory.getLogger(PGSQLSchme.class);



    public void checkTableName(List<String> tableName) throws SQLException {


        JDBCConnection jd = new JDBCConnection(temp.pgurl, temp.pguser, temp.pgpassword);


        for(String table  : tableName) {

            String query = "select column_name, data_type, character_maximum_length from INFORMATION_SCHEMA.COLUMNS where table_name ="+table;

            Map resultMap =  jd.pgSQLQuery(query);


        }





    }
}
