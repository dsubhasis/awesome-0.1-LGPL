package edu.sdsc.awesome.neo4j.awsmneo4jclient.postgresSQL;

import edu.sdsc.awesome.connector.postgres.JDBCConnection;
import jdk.nashorn.internal.parser.JSONParser;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGstatusUpdate {

    public JsonObject dbstats(String schema){

        JsonObjectBuilder js = Json.createObjectBuilder();

        JDBCConnection jd = new JDBCConnection("jdbc:postgresql://localhost:5432", "postgres", "postgres" );
        String query = edu.sdsc.awesome.neo4j.awsmneo4jclient.postgresSQL.query.viewQuery(schema);

        JsonObject rsm = js.build();

        try {
            rsm = jd.pgSQLQueryJson(query);
        } catch (SQLException e) {
            e.getErrorCode();
        }





        return rsm;
    }
}
