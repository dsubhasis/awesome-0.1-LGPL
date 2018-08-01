package edu.sdsc.awesome.adil.parser.StatementOperation;

import edu.sdsc.awesome.common.connector.CommonDBUtil;
import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CommonValidation {

    final Logger logger = LoggerFactory.getLogger(CommonValidation.class);



    public void connectDB(){

    }

    public Map processQuery(String databaseName) {

        ResultSet rst = null;
        Map rsm = null;

        JDBCConnection jd = new JDBCConnection(Util.userName, Util.database, Util.password );
        String query = getQuery(databaseName);
        try {
            rsm  = jd.pgSQLQuery(query);
        } catch (SQLException e) {
            logger.debug(e.getSQLState());
        }

        return rsm;

    }

    private String getQuery(String databaseName) {

        String query = "Select * from ";
        return query;
    }


}
