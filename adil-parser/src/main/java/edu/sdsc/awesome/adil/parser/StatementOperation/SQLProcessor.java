package edu.sdsc.awesome.adil.parser.StatementOperation;

import edu.sdsc.awsm.datatype.SelectElement;
import edu.sdsc.awsm.datatype.WithElement;
import edu.sdsc.awsm.datatype.AdilNode;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/*

 */

public class SQLProcessor {

    final Logger logger = LoggerFactory.getLogger(SQLProcessor.class);




    public AdilNode SelectStatement(Select sqlSelectStatement){

        // Check with select

             SelectElement selectList = new SelectElement();


             List<AdilNode> withList = new ArrayList();

       //Process With element First

        if(sqlSelectStatement.getWithItemsList().size()>0)
        {
            List stmtList = sqlSelectStatement.getWithItemsList();
            for(int i =0; i < stmtList.size(); i++){
                AdilNode selectWith = new SelectElement();
                WithElement wth = new WithElement();
                WithItem wtm = (WithItem) stmtList.get(i);
                System.out.println("Test"+wtm.toString());
                SelectBody inSelect = (SelectBody) wtm.getSelectBody();
                if(inSelect instanceof PlainSelect)
                {
                    PlainSelect pls = (PlainSelect) inSelect;
                    selectWith = plainSelect(pls);
                }

              withList.add(selectWith);
            }



        }




            PlainSelect pls = (PlainSelect) sqlSelectStatement.getSelectBody();

            selectList = plainSelect(pls);
            selectList.setWithElement(withList);







return selectList;
    }
    SelectElement  plainSelect(PlainSelect plainSelect){

        SelectElement select = new SelectElement();

        try {
            List<Join> joinList = plainSelect.getJoins();
            select.setJoin(joinList);
        } catch (Exception e) {
            System.out.println("Joint Clause Not Found ");
        }
        Distinct distinct =  plainSelect.getDistinct();
        List<Expression> columGroupBy =  plainSelect.getGroupByColumnReferences();
        Limit limitClause = plainSelect.getLimit();
        List colum = (List) plainSelect.getHaving();
        Table ft = (Table) plainSelect.getFromItem();

        List tableName = new ArrayList();

        List selectItems = plainSelect.getSelectItems();

        try {
            select.setDistinct(distinct.getOnSelectItems());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            select.setOrderby(plainSelect.getOrderByElements());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            select.setJoin(select.getJoin());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // select.setTableName(plainSelect.getS);
        try {
            select.setTupleName(selectItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            select.setTableName(ft.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            select.setHaving(colum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            select.setAliasName(ft.getAlias());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            select.setSchemaName(ft.getSchemaName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            select.setDatabaseName(ft.getDatabase());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return select;





    }
}
