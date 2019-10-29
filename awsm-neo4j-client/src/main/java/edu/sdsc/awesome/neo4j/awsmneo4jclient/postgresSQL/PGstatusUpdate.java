/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 */

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
