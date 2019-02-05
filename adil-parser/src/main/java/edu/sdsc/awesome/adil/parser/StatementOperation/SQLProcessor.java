package edu.sdsc.awesome.adil.parser.StatementOperation;

import edu.sdsc.awesome.adil.parser.adil.parser.sql.SelectElement;
import edu.sdsc.awesome.adil.parser.adil.parser.sql.WithElement;
import edu.sdsc.awesome.connector.postgres.PGSQLSchme;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/*

 */

public class SQLProcessor {

    final Logger logger = LoggerFactory.getLogger(SQLProcessor.class);




    public void SelectStatement(Select sqlSelectStatement){

        // Check with select



        if(sqlSelectStatement.getWithItemsList().size()>0)
        {
            List stmtList = sqlSelectStatement.getWithItemsList();
            for(int i =0; i < stmtList.size(); i++){
                WithElement wth = new WithElement();
                WithItem wtm = (WithItem) stmtList.get(i);
                System.out.println("Test"+wtm.toString());
                SelectBody inSelect = (SelectBody) wtm.getSelectBody();
                if(inSelect instanceof PlainSelect)
                {
                    PlainSelect pls = (PlainSelect) inSelect;
                    plainSelect(pls);
                }
            }

        }

    }
    void plainSelect(PlainSelect plainSelect){

        try {
            List<Join> joinList = plainSelect.getJoins();
        } catch (Exception e) {
            System.out.println("Joint Clause Not Found ");
        }
        Distinct distinct =  plainSelect.getDistinct();
        List<Expression> columGroupBy =  plainSelect.getGroupByColumnReferences();
        Limit limitClause = plainSelect.getLimit();
        Expression colum = plainSelect.getHaving();
        FromItem ft = plainSelect.getFromItem();
        List selectItems = plainSelect.getSelectItems();

        SelectElement select = new SelectElement();

        //select.setDistinct(distinct);









    }
}
