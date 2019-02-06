package edu.sdsc.awesome.connector.postgres;

import java.util.Map;

public class PGSQLTable {

    private String tableName;
    private String schemaname;
    private PGSQLIndex pgindex;
    private PGSQLTuple pgtuple;

    public void insert(Map resultSet, Map resultSetIndex){







    }


    public PGSQLTable(String tableName) {
        this.tableName = tableName;
        pgindex = new PGSQLIndex();
        pgtuple = new PGSQLTuple();
    }
}
