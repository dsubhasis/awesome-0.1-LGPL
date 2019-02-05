package edu.sdsc.awesome.adil.parser.adil.parser.sql;

import net.sf.jsqlparser.statement.select.Select;

import java.util.List;

public class SelectElement {

    private List tableName;
    private List tupleName;
    private List groupBy;
    private List orderby;
    private List join;
    private List distinct;
    private WithElement withElement;


    public List getTableName() {
        return tableName;
    }

    public void setTableName(List tableName) {
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

    public WithElement getWithElement() {
        return withElement;
    }

    public void setWithElement(WithElement withElement) {
        this.withElement = withElement;
    }

    public SelectElement() {





    }

    public void getTuple(Select statement){



    }


}
