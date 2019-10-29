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

package edu.sdsc.awesome.adil.parser.StatementOperation;

import com.awesome.query.CommonSlice.PGSQL.BasicSlice;
import edu.sdsc.Cypher.Cypher;
import edu.sdsc.SQLPP.SQLPP;
import edu.sdsc.awesome.connector.postgres.PGSQLSchme;
import edu.sdsc.awsm.datatype.AdilNode;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ParserUtil {

    static final Logger logvalue = LoggerFactory.getLogger(ParserUtil.class);

    static boolean validateTuple(String s, String fieldName, String tableName) throws SQLException {

        Connection con = ParserUtil.dbConnection();
        int count = 0;

        boolean flag = false;
        String query = "Select count(*) from " + tableName + " where " + fieldName + " = " + s;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            count = rs.getInt("count");
            if (count > 0) {
                flag = true;
            }
        }
        return flag;
    }

    public static JsonArray checkAllDBelement(String match, String table, String fieldName) {
        JsonArrayBuilder variable = Json.createArrayBuilder();
        String name;


        try {
            ResultSet rs = getResult(fieldName, table, "\"" + match + "\"");
            while (rs.next()) {
                JsonObjectBuilder temp = Json.createObjectBuilder();

                name = rs.getString("name");
                temp.add("name", name);
                temp.add("source", rs.getString("name"));
                variable.add(temp);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return variable.build();

    }

    public static JsonObject fieldSchemaCheck(JsonObjectBuilder jObject, String field) {
        String type = null, path = null, model = null;

        String[] partofField = field.split("\\.");
        try {
            ResultSet rs = getResult("name", "schematable", "\"" + partofField[1] + "\"");

            while (rs.next()) {

                type = rs.getString("type");
                path = rs.getString("path");


                String[] pathComponent = path.split("\\.");

                int size = pathComponent.length;
                if (size > 0) {

                    if ((pathComponent[size - 1]).equals("*")) {
                        model = "nested";

                    } else if ((pathComponent[size - 1]).equals("+")) {
                        model = "nested";

                    } else {
                        model = "flat";

                    }
                } else {
                    model = "flat";

                }
                jObject.add("model", model);
                jObject.add("DataType", type);
                jObject.add("path", path);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jObject.build();


    }


    public static JsonObjectBuilder CheckDBStorage(JsonObjectBuilder dbObject, String match) {


        JsonArrayBuilder store = Json.createArrayBuilder();

        String name;

        try {
            ResultSet rs = getResult("name", "storageType", "\"" + match + "\"");
            while (rs.next()) {
                name = rs.getString("type");


                rs = getResult("name", "provider", "\"" + name + "\"");
                while (rs.next()) {
                    String storename = rs.getString("type");
                    store.add(storename);
                }

                dbObject.add(name, store.build());
                dbObject.add("source", match);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbObject;
    }

    public static JsonObjectBuilder ImportLibraryDBCheck(JsonObjectBuilder retrunObject, String fromClause, JsonArray name) {

        JsonArrayBuilder jproject = Json.createArrayBuilder();

        for (int i = 0; i < name.size(); i++) {
            try {
                ResultSet rs = getResult("name", "libraryentry", name.get(i).toString());
                while (rs.next()) {
                    JsonObjectBuilder jObject = Json.createObjectBuilder();
                    String lname = rs.getString("name");
                    String lpath = rs.getString("path");
                    Integer lsize = rs.getInt("size");
                    String lcomputeClass = rs.getString("computeClass");
                    String type = rs.getString("type");
                    jObject.add("name", lname);
                    jObject.add("VerifiedPath", lpath);
                    jObject.add("size", lsize);
                    jObject.add("ComputeClass", lcomputeClass);
                    jObject.add("type", type);
                    jproject.add(jObject.build());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        retrunObject.add("LOAD", jproject.build());
        return retrunObject;
    }
    public static ResultSet getResult(String fieldName, String table, String value) throws SQLException {

        String name = "*";

        String query = "Select " + name + " from " + table + " where " + fieldName + " = " + value + " ";
       // System.out.println(query);
        Connection con = ParserUtil.dbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);


        return rs;

    }

    public static ResultSet getResult(String colname, String fieldName, String table, String value) throws SQLException {

        String name = "colname";

        String query = "Select " + name + " from " + table + " where " + fieldName + " = " + value;
        //System.out.println(query);
        Connection con = ParserUtil.dbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);


        return rs;

    }


    static int insertTuple(String query) {
        Connection con = ParserUtil.dbConnection();
        int count = 0;
        boolean flag = false;

        Statement st = null;
        try {
            st = con.createStatement();
            st.execute(query);
            st.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }

        return 0;
    }


    static Connection dbConnection() {

        Properties prop = new Properties();
        InputStream input = null;
        String url = null;
        //String url = "jdbc:sqlite:/home/subhasis/IdeaProjects/adil/test.db";


        try {
            input = new FileInputStream("adil-parser/config.properties");
            ;
            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            url = prop.getProperty("db.path");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //String url = "jdbc:sqlite::memory";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Internal Database Connection error");
        }
        return con;
    }

    public static List getStoreCapabilty(String fieldName, String value) throws SQLException {
        List name = new ArrayList();
        String query = "Select capability from StoreCapabilityTable where " + fieldName + " = " + value;
        Connection con = ParserUtil.dbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            name.add(rs.getString("capabitity"));
        }

        return name;

    }

    public static String getOperatorCapabilty(String fieldName, String orgModel, String value) throws SQLException {

        String name = null;

        String query = "Select name from OperatorCapabityTable where " + fieldName + " = " + value;
        Connection con = ParserUtil.dbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            name = rs.getString("name");
        }

        return name;

    }

    public static Integer generateUniqueID() {
        AtomicInteger counter = new AtomicInteger();
        return counter.intValue();
    }

    public static JsonObjectBuilder variableType(JsonObjectBuilder type, String src, String variable, JsonObjectBuilder decesion) {


        String[] temp = variable.split("\\.");

        String fieldType = temp[0];
        String modifiedField = variable.replace(temp[0], src);

        type.add("Type", fieldType);
        type.add("field", modifiedField);
        decesion.add(modifiedField, false);

        return type;


    }

    public static JsonObject getProjectionObject(JsonObject jSlection, List<String> projection, String src, JsonObjectBuilder variable, JsonObjectBuilder decision, JsonObjectBuilder schema, JsonObjectBuilder type) {

        JsonObjectBuilder jwhere = Json.createObjectBuilder(jSlection);
        JsonObjectBuilder jobject = Json.createObjectBuilder();


        JsonArrayBuilder jproject = Json.createArrayBuilder();

        JsonObjectBuilder projectionVar = Json.createObjectBuilder();
        jobject.add("SCHEMA", src);
        jobject.add("verified", false);


        for (int i = 0; i < projection.size(); i++) {


            String prjvar = projection.get(i);
            String[] temp = prjvar.split("\\.");
            schema.add(src, false);

            projectionVar.add("tuple", prjvar);
            if (temp.length > 1) {
                projectionVar.add("Type", temp[0]);
                String replacestring = prjvar.replace(temp[0], src);
                projectionVar.add("field", replacestring);


                decision.add(replacestring, false);
                type.add(replacestring, false);


                jproject.add(projectionVar.build());

            }


        }


        jwhere.add("PROJECT", jproject.build());

        jobject.add("SELECT", jwhere.build());


        return jobject.build();

    }

    public static JsonObjectBuilder functionCheck(JsonObjectBuilder fObject, String functionName) {

        ResultSet rs = null;
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        try {
            rs = getResult("name", "functionsignaturetable", "\"" + functionName + "\"");

            JsonArrayBuilder array = Json.createArrayBuilder();


            while (rs.next()) {

                String lname = rs.getString("name");
                String loutput = rs.getString("output");
                String linput = rs.getString("inputtype");
                String lprovider = rs.getString("provider");
                Integer lcost = rs.getInt("cost");

                jObject.add("name", lname);
                jObject.add("inputSchema", linput);
                jObject.add("outputSchema", loutput);
                jObject.add("provider", lprovider);
                jObject.add("cost", lcost);
                array.add(jObject.build());

            }


            jObject.add("info", array.build());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jObject;

    }

    public static JsonObjectBuilder getAnnotateJSONPLAN(JsonObjectBuilder jobject, List<String> dictionary, List<String> witness, JsonObject jSlection, List<String> projection, String src, JsonObjectBuilder variable, JsonObjectBuilder desision, JsonObjectBuilder schema, JsonObjectBuilder type) throws Exception {

        //JsonObjectBuilder jobject =Json.createObjectBuilder();
        JsonObject source = getProjectionObject(jSlection, projection, src, variable, desision, schema, type);
        JsonObjectBuilder dbcheck = Json.createObjectBuilder();

        JsonArrayBuilder jarray = Json.createArrayBuilder();
        JsonArrayBuilder jwitness = Json.createArrayBuilder();
        for (int w = 0; w < witness.size(); w++) {

            JsonObjectBuilder witneestemp = Json.createObjectBuilder();
            witneestemp.add("source", src);
            witneestemp.add("name", witness.get(w));
            jwitness.add(witneestemp.build());
        }

        JsonObjectBuilder witnessProject = Json.createObjectBuilder();
        if (witness.size() == 0) {
            JsonObjectBuilder witneestemp = Json.createObjectBuilder();
            witneestemp.add("source", src);
            witneestemp.add("name", "*");

            jwitness.add(witneestemp.build());
        }
        witnessProject.add("PROJECT", jwitness.build());
        jobject.add("WITNESS", witnessProject.build());
        if (dictionary.size() > 1) {
            jobject.add("memoize", true);
            jobject.add("MEMOSRC", source);
            for (int i = 0; i < dictionary.size(); i++) {
                JsonObjectBuilder tempJobject = Json.createObjectBuilder();
                ResultSet rs = getResult("name", "libraryentry", "\"" + dictionary.get(i) + "\"");
                while (rs.next()) {
                    String lname = rs.getString("name");
                    String lpath = rs.getString("path");
                    Integer lsize = rs.getInt("size");
                    String lcomputeClass = rs.getString("computeClass");
                    dbcheck.add("name", lname);
                    dbcheck.add("VerifiedPath", lpath);
                    dbcheck.add("size", lsize);
                    dbcheck.add("ComputeClass", lcomputeClass);
                    dbcheck.add("ImportOptimized", false);
                    tempJobject.add("info", dbcheck.build());
                }
                tempJobject.add("SOURCE", "memoize");

                jarray.add(tempJobject.build());
            }
            jobject.add("UNION", jarray.build());


        } else {
            jobject.add("ANNOTATE", dictionary.get(0));
            jobject.add("SOURCE", source);


        }

        //System.out.println(jobject.build().toString());

        return jobject;

    }

    public static JsonObjectBuilder handleCypherQuery(String name, JsonObjectBuilder js, JsonObjectBuilder partition, boolean partitionflag) {


        //Reader sr = new StringReader(" \" create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2); \"");

        Reader sr = new StringReader(name);
        Cypher chp = new Cypher(sr);
        JsonObjectBuilder p = Json.createObjectBuilder();

        try {
            p = chp.Expression(p);
            //System.out.println(p.build().toString());
            //p.dump(">");


        } catch (edu.sdsc.Cypher.ParseException e) {
            e.printStackTrace();
        }


        js.add("PLAN", p.build());
        js.add("Executable", name);
        js.add("PARTTION-STATUS", partitionflag);
        if (partitionflag) {
            js.add("partition", partition.build());
        }

        //System.out.println(js.build().toString());
        return js;
    }


    public static JsonObjectBuilder handleSQLQuery(String name, JsonObjectBuilder js) throws edu.sdsc.SQLPP.ParseException {
        JsonObjectBuilder p = Json.createObjectBuilder();
        SQLProcessor sql = new SQLProcessor();
        net.sf.jsqlparser.statement.Statement stmt;
        String lname = name.replaceFirst("\"", "");
        name = lname.substring(0, name.length() - 2);
        Reader sr = new StringReader(name);
        SQLPP sqp = new SQLPP(sr);
        try {
            //System.out.println(name);

            stmt = CCJSqlParserUtil.parse(name);


            if (stmt instanceof Select) {
                PGSQLSchme pgql = new PGSQLSchme();
                Select selectStmt = (Select) stmt;
                String whereClause = "a=3 AND b=4 AND c=5 AND d>5 AND x<10";
                Expression expr = CCJSqlParserUtil.parseCondExpression(whereClause);
                PlainSelect ps = (PlainSelect) stmt;

                TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                List<String> tableList = tablesNamesFinder.getTableList(selectStmt);

                /*
                Validate tables with the databases
                 */

                boolean tableExist = true;
                for (String tname : tableList) {

                    try {
                        if (!pgql.tableExist(tname)) {
                            tableExist = false;
                            logvalue.info("table not found");
                            break;
                        }

                    } catch (SQLException e) {
                        logvalue.info("Debug problem");
                    }
                }
                //Check table
                List tableDetails;
                AdilNode SelectElement;
                if (tableExist) {

                    try {
                        tableDetails = pgql.TableStats(tableList);
                        SelectElement = sql.SelectStatement(selectStmt);
                        //System.out.println("Statement parsed ");
                        BasicSlice bs = new BasicSlice(tableDetails, SelectElement);

                    } catch (SQLException e) {
                    }


                }

            }


            //System.out.println(stmt);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

        //System.out.println(js.build().toString());


        js.add("PLAN", p.build());
        js.add("EXECUTABLE", name);
        return js;
    }

public static JsonObject validateFunction(String name) throws SQLException {

        JsonObjectBuilder object = Json.createObjectBuilder();

        String query = "Select * from functionsignaturetable WHERE name = \""+name+"\"";
        //System.out.println(query);
        Connection con = ParserUtil.dbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
    while (rs.next()) {
        name = rs.getString("name");
        String output = rs.getString("output");
        String optclass = rs.getString("optclass");
        String input = rs.getString("inputtype");
        String vslicefld = rs.getString("vslicefld");

        object.add("name", name);
        object.add("output", output);
        //object.add("optclass", optclass);
        object.add("input", input);
        //object.add("vslicefld", vslicefld);



    }
    return object.build();

}
    public JsonObjectBuilder handleawsmfunction(JsonObjectBuilder jobject) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JsonObjectBuilder p = Json.createObjectBuilder();
        JsonObject stmt = jobject.build();
        Map property = new HashMap();
        //Get the type
        System.out.println(stmt.toString());
        String type = stmt.getString("input");
        String className = null;
        try {
            ResultSet rs = getResult("name", "classTable", "\"" + type + "\"");
            while (rs.next()) {
                className = rs.getString("class");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.runClassLib(property, className);
        return p;
    }

    public void runClassLib(Map property, String ClassName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class myClass = Class.forName(ClassName);
        Constructor<?> constructor = myClass.getConstructor(Map.class, String.class);
        Object object = constructor.newInstance(property, ClassName);
    }
    public void runClassLib(JsonObject property, String ClassName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class myClass = Class.forName(ClassName);
        Constructor<?> constructor = myClass.getConstructor(JsonObject.class, String.class);
        Object object = constructor.newInstance(property, ClassName);
    }
}
