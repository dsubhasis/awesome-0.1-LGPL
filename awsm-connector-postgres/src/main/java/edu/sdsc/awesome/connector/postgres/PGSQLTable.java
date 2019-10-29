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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PGSQLTable {

    private String tableName;
    private String schemaname;

    private List<PGSQLTuple> pgtupleList;
    private List<PGSQLIndex> pgindexList;

    public void insert(Map resultSet, Map resultSetIndex){

        this.populateTable(resultSet);
        List<Map> indexValue = (List) resultSetIndex.get("value");
        for(Map unitValue : indexValue )
        {
            PGSQLIndex pgindex = new PGSQLIndex();
            indexTable(unitValue, pgindex);
            pgindexList.add(pgindex);
        }
    }
    public PGSQLTable(String tableName) {
        this.tableName = tableName;
        PGSQLIndex pgindex = new PGSQLIndex();
        pgtupleList = new ArrayList();
        pgindexList = new ArrayList();
        
    }



    public void indexTable(Map indexValue, PGSQLIndex pgindex){

        String indexdef = (String) indexValue.get("indexdef");
        String indexName = (String) indexValue.get("indexname");
        pgindex.setName(indexName);
        parseIndex(indexdef, pgindex);

    }
    public void parseIndex(String defIndex, PGSQLIndex pgindex){
        String[] indexType = defIndex.split("USING");
        String[] temp1 = indexType[1].split(" \\(");
        String temp2 = temp1[1].replace(")","");
        String[] field = temp2.split(",");
        pgindex.setIndexType(temp1[0]);
        pgindex.setIndexedField(field);
    }

    public void populateTable(Map resultSet){
        List<Map> value = (List) resultSet.get("value");
        for (Map unitValue : value) {
            PGSQLTuple pgtuple = new PGSQLTuple();
            pgtuple = new PGSQLTuple();
            pgtuple.Setall((String) unitValue.get("column_name"), (String) unitValue.get("udt_name"), (String) (unitValue.get("is_nullable")), (Integer) unitValue.get("character_maximum_length"), (String) unitValue.get("data_type"), (String) unitValue.get("date_type_precession"));
            pgtupleList.add(pgtuple);
            
        }










    }


}
