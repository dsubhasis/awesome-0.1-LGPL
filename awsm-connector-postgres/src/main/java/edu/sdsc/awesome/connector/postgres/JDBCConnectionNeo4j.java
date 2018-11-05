package edu.sdsc.awesome.connector.postgres;

import edu.sdsc.awesome.connector.neo4j.ConnectNeo4j;
import org.apache.commons.cli.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JDBCConnectionNeo4j {

    /* Format String for query */
    public static String CREATE_FORMAT = "CREATE (n:";
    /* Database connection */
    private static JDBCConnection client;

    public JDBCConnectionNeo4j(String url, String username, String password) {
        try {
            client = new JDBCConnection(url, username, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public String[] createQuery(ArrayList valuesList, HashMap elementTypeList, ArrayList colResultList, String category) {
        /* separate filters */
        String[] queries = new String[valuesList.size()];
        String filters = "";
        for (int i = 0; i < colResultList.size(); i++) {
            if(i == colResultList.size() - 1) {
                filters += colResultList.get(i) + ",";
                filters += colResultList.get(i) + "Type";
            } else {
                filters += colResultList.get(i) + ",";
                filters += colResultList.get(i) + "Type,";
            }
        }

        /* separate values */
        String[] keys = filters.split(",");
        for(int j = 0; j < valuesList.size(); j++) {
            LinkedHashMap values = (LinkedHashMap) (valuesList.get(j));
            String[] objects = new String[keys.length];
            for (int k = 0; k < keys.length; k++) {
                if(k%2 == 1) {
                    objects[k] = elementTypeList.get(keys[k - 1]) + "";
                } else {
                    String text = "" + (values.get(keys[k]));
                    if(text != null) {
                        objects[k] = (text).replace("\"", "\\\"");
                    }
                }
            }
            String query = CREATE_FORMAT + category + " {";
            int iterator = 0;
            /* map filters with values */
            for(String key:keys) {
                query += key + ":";
                query += "\"" + objects[iterator] + "\"";
                if(iterator != objects.length - 1) {
                    query += ", ";
                }
                iterator++;
            }
            query += "})";
            /* add new query */
            queries[j] = query;
        }
        return queries;
    }

    public static void main(String[] args) {
        /* parse postgresql arguments */
        Options options = new Options();
        Option url = new Option("u", "url", true, "Postgresql URL Link");
        url.setRequired(true);
        options.addOption(url);
        Option password = new Option("p", "password", true, "Postgresql password");
        password.setRequired(true);
        options.addOption(password);
        Option username = new Option("pu", "username", true, "Postgresql username");
        username.setRequired(true);
        options.addOption(username);
        Option query = new Option("q", "query", true, "Postgresql query");
        query.setRequired(true);
        options.addOption(query);

        /* parse neo4j arguments */
        Option bolt = new Option("b", "bolt", true, "Neo4j Bolt Connector");
        bolt.setRequired(true);
        options.addOption(bolt);
        Option neo4jUsername = new Option("nu", "neo4jusername", true, "Neo4j Username");
        neo4jUsername.setRequired(true);
        options.addOption(neo4jUsername);
        Option neo4jPassword = new Option("np", "neo4jpassword", true, "Neo4j Password");
        neo4jPassword.setRequired(true);
        options.addOption(neo4jPassword);
        Option nodeLabel = new Option("l", "label", true, "Node Label Classification");
        nodeLabel.setRequired(false);
        options.addOption(nodeLabel);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        String urlPath = "";
        String usernamePath = "";
        String passwordPath = "";
        String queryPath = "";

        String boltPath = "";
        String neo4jusernamePath = "";
        String neo4jpasswordPath = "";
        String nodeLabelPath = "";

        /* set arguments to strings */
        try {
            cmd = parser.parse(options, args);
            urlPath = cmd.getOptionValue("url");
            usernamePath = cmd.getOptionValue("username");
            passwordPath = cmd.getOptionValue("password");
            queryPath = cmd.getOptionValue("query");
            boltPath = cmd.getOptionValue("bolt");
            neo4jusernamePath = cmd.getOptionValue("neo4jusername");
            neo4jpasswordPath = cmd.getOptionValue("neo4jpassword");
            nodeLabelPath = cmd.getOptionValue("label");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        /* create connection to neo4j database */
        JDBCConnectionNeo4j connection = new JDBCConnectionNeo4j(urlPath, usernamePath, passwordPath);
        Map queryResults = new HashMap();

        /* get query results */
        try {
            queryResults = client.pgSQLQuery(queryPath);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        /* set default label if missing argument */
        if(nodeLabelPath == null) {
            nodeLabelPath = "Node";
        }

        /* separate query results */
        ArrayList valuesList = (ArrayList) (queryResults.get("value"));
        HashMap elementTypeList = (HashMap) (queryResults.get("type"));
        ArrayList colResultList = (ArrayList) (queryResults.get("tuple"));

        /* create neo4j connection */
        ConnectNeo4j command = new ConnectNeo4j(boltPath, neo4jusernamePath, neo4jpasswordPath);
        /* create neo4j queries */
        String[] queries = connection.createQuery(valuesList, elementTypeList, colResultList, nodeLabelPath);
        /* run queries */
        for(String neo4jquery:queries) {
            System.out.println("\nQuery: " + neo4jquery);
            command.parseCommand(neo4jquery);
        }
        command.close();

    }

}
