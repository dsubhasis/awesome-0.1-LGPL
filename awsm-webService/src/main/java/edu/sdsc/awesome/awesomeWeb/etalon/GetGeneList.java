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

public class GetGeneList {
    public String getResult() {
        return result;
    }

    private final String result;
    private final String api = "awesome";
    private final String version = "0.1";

    public GetGeneList(String query, String databaseName) {


        result = processQuery(databaseName, query);


    }
    public String processQuery(String databaseName, String geneList){
        ResultSet rst = null;
        Map rsm ;
        Map elementList = parseString(geneList);
        JDBCConnection jd = new JDBCConnection(CommonDBUtil.chinaLabURL, CommonDBUtil.chinaLabpgsqlUser,CommonDBUtil.chinaLabpgsqlPassword );
        String query = getQuery(databaseName, elementList);

        try {
            rsm = jd.pgSQLQuery(query);
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return resultExtractionCount(rst);

    }

    public Map parseString(String queryString){


         String[] pipeSepText = queryString.split("\\|");
         //String[] text = queryString.split("\\|");
        Map<String, List> elementList = new HashMap();
        List LikeSerachList = new ArrayList();
        List searchList = new ArrayList();
        String lelement;


        for(int i = 0; i < pipeSepText.length; i++)
        {
            if(pipeSepText[i].matches("(.*).\\*")) {
                lelement = pipeSepText[i].replace("*", "%");
                LikeSerachList.add(lelement);

                System.out.println(pipeSepText[i]);
            }

            else if(pipeSepText[i].matches("\\*.(.*)")) {
                lelement = pipeSepText[i].replace("*", "%");
                LikeSerachList.add(lelement);
                System.out.println(pipeSepText[i]);
            }
            else {

                searchList.add(pipeSepText[i]);

            }

        }
        elementList.put("likeSearch",LikeSerachList);
        elementList.put("search", searchList);

        return elementList;
    }


    private static String getQuery(String name, Map<String , List> elementList){

        String wherePredicate = null;
        boolean whereFlag = true;
        String query = null;

        List likeSearch = elementList.get("likeSearch");
        List search = elementList.get("search");


        if(search.size()>0|likeSearch.size()>0){

            for(int i = 0; i < search.size();i++)
            {
                if(whereFlag) {
                    wherePredicate = " WHERE upper(candidatematched) = " + "upper(\'"+search.get(i)+"\')";
                    whereFlag = false;
                }
                else{
                    wherePredicate = wherePredicate + " OR upper(candidatematched) = " + "upper(\'"+search.get(i)+"\')";
                }
            }

            for(int i = 0; i < likeSearch.size();i++)
            {
                if(whereFlag) {
                    wherePredicate = " WHERE upper(candidatematched) LIKE " + "upper(\'"+likeSearch.get(i)+"\')";
                    whereFlag = false;
                }
                else{
                    wherePredicate = wherePredicate + " OR upper(candidatematched) LIKE " + "upper(\'"+likeSearch.get(i)+"\')";
                }
            }

            query = "SELECT nct_id, section, utttext, candidatematched, candidatepreferred from "+name+ wherePredicate +" ;";
        }
        else {


            query = "SELECT nct_id, section, utttext, candidatematched, candidatepreferred from " + name + wherePredicate + " ;";
        }




        System.out.println(query);
        return query;
    }

    public String resultExtractionCount(ResultSet rs){

        int count = 0;
        JsonArrayBuilder jarrya = Json.createArrayBuilder();

        try {
            while(rs.next()){

                JsonObjectBuilder jsresultTemp = Json.createObjectBuilder();
                String section, utttext, candidatematched, candidatepreferred;

                section = rs.getString("section");
                utttext = rs.getString("utttext");
                candidatematched = rs.getString("candidatematched");
                candidatepreferred = rs.getString("candidatepreferred");

                jsresultTemp.add("candidatepreferred", candidatepreferred);
                jsresultTemp.add("candidatematched", candidatematched);
                jsresultTemp.add("section", section);
                jsresultTemp.add("utttext", utttext);

                jarrya.add(jsresultTemp.build());


                System.out.println(count);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonObjectBuilder jsresult = Json.createObjectBuilder();
        jsresult.add("result", jarrya.build());

        return jsresult.build().toString();

    }



}
