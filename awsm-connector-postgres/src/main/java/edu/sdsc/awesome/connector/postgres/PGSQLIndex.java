package edu.sdsc.awesome.connector.postgres;


import java.util.List;

public class PGSQLIndex {

    public List<String> getIndexedField() {
        return indexedField;
    }

    public String getIndexType() {
        return indexType;
    }

    public String getIndexdef() {
        return indexdef;
    }

    public void setIndexdef(String indexdef) {
        this.indexdef = indexdef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    private List<String> indexedField;
    private String indexdef;
    private String name;
    private String schemaName;
    private String indexType;











}
