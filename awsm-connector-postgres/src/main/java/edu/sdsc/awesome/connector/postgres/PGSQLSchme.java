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






    public List TableStats(List<String> tableName) throws SQLException {

        Map resultMapTable, resultMapIndex, resultMapType, resultAwsmStat;
        List tableList = new ArrayList();

      for(String table  : tableName) {

            String query = "select  pg_relation_size(\'"+table+"\') as size, column_name, data_type, udt_name,  is_nullable, character_maximum_length, data_type from INFORMATION_SCHEMA.COLUMNS where table_name = \'"+table+"\'";
            resultMapTable =  jd.pgSQLQuery(query);
            PGSQLTable pgt = new PGSQLTable(table);
            String query2 ="SELECT indexname, indexdef FROM pg_indexes where tablename =  \'"+table+"\'";
            resultMapIndex = jd.pgSQLQuery(query2);
            pgt.insert(resultMapTable, resultMapIndex);

            //SELECT
          //    pg_size_pretty (pg_relation_size('actor'));






            //pgt.insert(resultMapTable, jd.pgSQLQuery(query2));
            tableList.add(pgt);

           // List tuple = resultMap.get('map')''

      }

      return tableList;

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
