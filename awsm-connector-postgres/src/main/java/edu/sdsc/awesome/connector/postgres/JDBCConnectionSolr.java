package edu.sdsc.awesome.connector.postgres;


import edu.sdsc.awesome.connector.solr.ConnectSolr;
import org.apache.commons.cli.*;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;

public class JDBCConnectionSolr {

    public static void main(String[] args) {
        /* parse postgresql arguments */
        Options options = new Options();
        Option url = new Option("u", "url", true, "Postgresql URL Link");
        url.setRequired(true);
        options.addOption(url);
        Option password = new Option("p", "password", true, "Postgresql password");
        password.setRequired(true);
        options.addOption(password);
        Option username = new Option("n", "username", true, "Postgresql username");
        username.setRequired(true);
        options.addOption(username);
        Option query = new Option("q", "query", true, "Postgresql query");
        query.setRequired(true);
        options.addOption(query);

        /* parse solr arguments */
        Option solrURL = new Option("su", "solrurl", true, "Solr URL");
        solrURL.setRequired(true);
        options.addOption(solrURL);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        String urlPath = "";
        String usernamePath = "";
        String passwordPath = "";
        String queryPath = "";
        String sUrlPath = "";

        /* set arguments to strings */
        try {
            cmd = parser.parse(options, args);
            urlPath = cmd.getOptionValue("url");
            usernamePath = cmd.getOptionValue("username");
            passwordPath = cmd.getOptionValue("password");
            queryPath = cmd.getOptionValue("query");
            sUrlPath = cmd.getOptionValue("solrurl");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        /* create connection to postgresql database */
        JDBCConnection connection = null;
        try {
            connection = new JDBCConnection(urlPath, usernamePath, passwordPath);
        } catch (Exception e) {
            System.exit(1);
        }

        /* collect query results */
        Map queryResults = new HashMap();
        try {
            queryResults = connection.pgSQLQuery(queryPath);
        } catch (SQLException e) {
            System.exit(1);
        }

        /* separate query results */
        ArrayList valuesList = (ArrayList) (queryResults.get("value"));
        HashMap elementTypeList = (HashMap) (queryResults.get("type"));
        ArrayList colResultList = (ArrayList) (queryResults.get("tuple"));

        /* separate filters/keys */
        ConnectSolr client = new ConnectSolr(sUrlPath);
        String[] keys = new String[2*colResultList.size()];
        for (int i = 0; i < 2*colResultList.size(); i++) {
            if(i%2 == 1) {
                keys[i] =  colResultList.get(i/2) + "Type";
            } else {
                keys[i] = (String)(colResultList.get(i/2));
            }
        }

        /* separating values/objects */
        int count = 0;
        for(int j = 0; j < valuesList.size(); j++) {
            LinkedHashMap values = (LinkedHashMap) (valuesList.get(j));
            String[] objects = new String[keys.length];
            for (int k = 0; k < values.size(); k++) {
                if(k%2 == 1) {
                    objects[k] = "" + (elementTypeList.get(keys[k - 1]));
                } else {
                    objects[k] = "" + (values.get(keys[k]));
                }
            }
            try {
                client.addDocuments(keys, objects);
                count++;
            } catch (SolrServerException e) {
                System.exit(1);
            } catch (IOException e) {
                System.exit(1);
            }
        }

        /* print out number of documents committed  */
        System.out.printf("\nPrinted out %d document(s)\n", count);

    }
}