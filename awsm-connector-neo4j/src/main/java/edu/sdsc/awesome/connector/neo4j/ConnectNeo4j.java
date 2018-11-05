package edu.sdsc.awesome.connector.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import org.apache.commons.cli.*;

import static org.neo4j.driver.v1.Values.parameters;

public class ConnectNeo4j {

    private Driver driver;
    /* sets up bolt connector and login */
    public ConnectNeo4j(String bolt, String user, String password) {
        try {
            driver = GraphDatabase.driver(bolt, AuthTokens.basic(user, password));
        } catch(Exception e) {
            System.err.println("Server Connection Failed");
            System.exit(1);
        }
    }

    /* closes driver after use */
    public void close() {
        driver.close();
    }

    /* parses command */
    public void parseCommand( final String query ) {
        try(Session session = driver.session()) {
            String result = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    StatementResult result = tx.run(query);
                    tx.success();
                    return "Success";
                }
            });
            System.out.println( result );
        }
        catch(Exception e) {
            System.err.println("Query Failed to Process");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Options options = new Options();
        Option bolt = new Option("b", "bolt", true, "Neo4j Bolt Connector");
        bolt.setRequired(true);
        options.addOption(bolt);

        Option username = new Option("u", "username", true, "Neo4j Username");
        username.setRequired(true);
        options.addOption(username);

        Option password = new Option("p", "password", true, "Neo4j Password");
        password.setRequired(true);
        options.addOption(password);

        Option query = new Option("q", "query", true, "Neo4j Query");
        query.setRequired(true);
        options.addOption(query);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        String boltPath = "";
        String usernamePath = "";
        String passwordPath = "";
        String queryPath = "";

        try {
            cmd = parser.parse(options, args);
            boltPath = cmd.getOptionValue("bolt");
            usernamePath = cmd.getOptionValue("username");
            passwordPath = cmd.getOptionValue("password");
            queryPath = cmd.getOptionValue("query");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        /* Create Client and Run Command */
        ConnectNeo4j command = new ConnectNeo4j(boltPath, usernamePath, passwordPath);
        command.parseCommand( queryPath );
        command.close();

    }

}