package edu.sdsc.awsm.datatype;


import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.schema.Database;
import net.sf.jsqlparser.statement.select.Select;

import java.util.List;

public class SelectElement extends AdilNode {

    private String tableName;
    private List tupleName;
    private List groupBy;
    private List orderby;
    private List join;
    private List distinct;
    private List having;
    private List withElement;
    private Alias aliasName;
    private String schemaName;
    private Database databaseName;

    public List getHaving() {
        return having;
    }

    public void setHaving(List having) {
        this.having = having;
    }

    public Alias getAliasName() {
        return aliasName;
    }

    public void setAliasName(Alias aliasName) {
        this.aliasName = aliasName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public Database getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(Database databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List getTupleName() {
        return tupleName;
    }

    public void setTupleName(List tupleName) {
        this.tupleName = tupleName;
    }

    public List getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List groupBy) {
        this.groupBy = groupBy;
    }

    public List getOrderby() {
        return orderby;
    }

    public void setOrderby(List orderby) {
        this.orderby = orderby;
    }

    public List getJoin() {
        return join;
    }

    public void setJoin(List join) {
        this.join = join;
    }

    public List getDistinct() {
        return distinct;
    }

    public void setDistinct(List distinct) {
        this.distinct = distinct;
    }

    public List getWithElement() {
        return withElement;
    }

    public void setWithElement(List withElement) {
        this.withElement = withElement;
    }

    public SelectElement() {





    }

    public void getTuple(Select statement){



    }


}
