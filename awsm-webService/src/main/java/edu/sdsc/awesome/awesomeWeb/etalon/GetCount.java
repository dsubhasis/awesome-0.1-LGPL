package edu.sdsc.awesome.awesomeWeb.etalon;

import edu.sdsc.awesome.awesomeWeb.store.CommonDBUtil;
import edu.sdsc.awesome.connector.postgres.CommonUtil;
import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public class GetCount {

    final Logger logger = LoggerFactory.getLogger(GetCount.class);


    private final String resultSet;


    public String getResultSet() {
        return resultSet;
    }

    public GetCount(String databaseName) {


        resultSet = processQuery(databaseName);

    }
    public String processQuery(String databaseName){
        ResultSet rst = null;
        JDBCConnection jd = new JDBCConnection(CommonDBUtil.chinaLabURL, CommonDBUtil.chinaLabpgsqlUser,CommonDBUtil.chinaLabpgsqlPassword );
        String query = getCountQuery(databaseName);
        try {
             rst = jd.pgSQLQuery(query);
        } catch (SQLException e) {
            logger.debug(e.getSQLState());
        }
        return resultExtractionCount(rst);

    }


    private static String getCountQuery(String name){

        String query = "SELECT count(*) from "+name+" ;";


        return query;
    }

    public String resultExtractionCount(ResultSet rs){

        int count = 0;

        try {
            CommonUtil.result(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while(rs.next()){


                count = rs.getInt("count");
                System.out.println(count);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonObjectBuilder jsresult = Json.createObjectBuilder();
        jsresult.add("count", count);

        return jsresult.build().toString();

    }


}
