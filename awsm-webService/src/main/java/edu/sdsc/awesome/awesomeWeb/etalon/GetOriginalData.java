package edu.sdsc.awesome.awesomeWeb.etalon;

import edu.sdsc.awesome.awesomeWeb.store.CommonDBUtil;
import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOriginalData {
    final Logger logger = LoggerFactory.getLogger(GetOriginalData.class);
    private final String result;
    private final String api = "awesome";
    private final String version = "0.1";

    public GetOriginalData(String databaseName) {


        result = processQuery(databaseName);


    }

    public String processQuery(String databaseName) {

        ResultSet rst = null;

        JDBCConnection jd = new JDBCConnection(CommonDBUtil.chinaLabURL, CommonDBUtil.chinaLabpgsqlUser,CommonDBUtil.chinaLabpgsqlPassword );
        String query = getQuery(databaseName);
        try {
            rst = jd.pgSQLQuery(query);
        } catch (SQLException e) {
            logger.debug(e.getSQLState());
        }

        return resultExtractionCount(rst);

    }


    private static String getQuery(String name) {


        String query = "SELECT orignctdata.id_info->>'nct_id' AS nct_id, orignctdata.location->>'status' AS status, orignctdata.location_countries, etalon_gngm.section, etalon_gngm.utttext, etalon_gngm.candidatematched, etalon_gngm.candidatepreferred  FROM orignctdata INNER JOIN " + name + " ON " + name + ".nct_id = orignctdata.id_info->>'nct_id'  ORDER BY orignctdata.start_date DESC ;";


        System.out.println(query);
        return query;
    }

    public String getResult() {
        return result;
    }

    public String resultExtractionCount(ResultSet rs) {

        int count = 0;

        JsonArrayBuilder jarrya = Json.createArrayBuilder();

        try {
            while(rs.next()){

                JsonObjectBuilder jsresultTemp = Json.createObjectBuilder();
                String nct_id, section, utttext, candidatematched, candidatepreferred, location, location_countries, status;


                try {

                    nct_id = "not available";
                    nct_id = rs.getString("nct_id");
                    if(nct_id == null) {
                        section = "NA";
                    }

                    jsresultTemp.add("nct_id", nct_id);

                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }

                try {

                    status = "not available";
                    status = rs.getString("status");
                    if(status == null) {
                        status = "NA";
                    }

                    jsresultTemp.add("status", status);

                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }


                try {

                    section = "not available";
                    section = rs.getString("section");
                    if(section == null) {
                        section = "NA";
                    }

                    jsresultTemp.add("section", section);

                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }

                try {
                    utttext = "not available";
                    utttext = rs.getString("utttext");
                    if(utttext == null) {
                        utttext = "NA";
                    }
                    jsresultTemp.add("utttext", utttext);

                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }

                try {
                    location = "not available";

                       location = rs.getString("status");

                    if(location == null) {
                           location = "NA";
                       }
                           jsresultTemp.add("location-status", location);


                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }

                try {
                    location_countries = "not available";
                    location_countries = rs.getString("location_countries");

                    if(location_countries == null)
                    {
                        location_countries = "NA";
                    }
                    jsresultTemp.add("location_countries", location_countries);
                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }

                try {
                    candidatematched = rs.getString("candidatematched");
                    if(candidatematched == null) {
                        candidatematched = "NA";
                    }

                    jsresultTemp.add("candidatematched", candidatematched);
                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }


                try {
                    candidatepreferred = rs.getString("candidatepreferred");

                    if(candidatepreferred == null) {
                        candidatepreferred = "NA";
                    }
                    jsresultTemp.add("candidatepreferred", candidatepreferred);
                } catch (SQLException e) {
                    logger.debug(e.getSQLState());
                }


                jarrya.add(jsresultTemp.build());





            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonObjectBuilder jsresult = Json.createObjectBuilder();
        jsresult.add("result", jarrya.build());

        return jsresult.build().toString();

    }

}
