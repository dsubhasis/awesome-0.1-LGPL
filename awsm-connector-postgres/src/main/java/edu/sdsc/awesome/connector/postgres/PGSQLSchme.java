package edu.sdsc.awesome.connector.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PGSQLSchme {

    final Logger logger = LoggerFactory.getLogger(PGSQLSchme.class);

    private JDBCConnection jd;




    public void checkTableName(List<String> tableName) throws SQLException {

        Map resultMapTable, resultMapIndex, resultMapType;
        List tableList = new ArrayList();

      for(String table  : tableName) {

            String query = "select column_name, data_type, udt_name,  is_nullable, character_maximum_length, data_type from INFORMATION_SCHEMA.COLUMNS where table_name = \'"+table+"\'";
            resultMapTable =  jd.pgSQLQuery(query);
            PGSQLTable pgt = new PGSQLTable(table);
            String query2 ="SELECT indexname, indexdef FROM pg_indexes where tablename =  \'"+table+"\'";
            resultMapIndex = jd.pgSQLQuery(query2);
            pgt.insert(resultMapTable, resultMapIndex);
            //pgt.insert(resultMapTable, jd.pgSQLQuery(query2));
            tableList.add(pgt);

           // List tuple = resultMap.get('map')''

      }

    }
    public PGSQLSchme() {
        jd = new JDBCConnection(temp.pgurl, temp.pguser, temp.pgpassword);
    }
    public boolean tableExist(String tableName) throws SQLException {
        String query ="SELECT EXISTS (SELECT 1 from information_schema.tables where table_name  = \'"+tableName+"\');";
        Map resultMap =  jd.pgSQLQuery(query);
        List value = (List) resultMap.get("value");
        Map unitValue = (Map) value.get(0);

        boolean exists = (boolean) unitValue.get("exists");





        return exists;


    }


}
