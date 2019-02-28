package edu.sdsc.awesome.adil.parser;


import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import edu.sdsc.Cypher.Cypher;
import edu.sdsc.Cypher.SimpleNode;
import edu.sdsc.SQLPP.SQLPP;
import edu.sdsc.adil.Adil;
import edu.sdsc.adil.ParseException;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.deparser.StatementDeParser;
import org.apache.commons.cli.*;


import javax.json.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static edu.sdsc.awesome.adil.parser.StatementOperation.ParserUtil.*;

public class AdilFirstPass {

    private String data;
    private Reader analysisScript;

    public AdilFirstPass() {


    }

    public void printdata(String x) {

        //System.out.println("Print" + x);

    }

    public JsonObject generateAWESOMEPlan(Reader analysisScript, float time) {

        JsonObjectBuilder js = Json.createObjectBuilder();

        Adil p = new Adil(analysisScript);
        try {
            js = p.ADILStatement(js);
            //System.out.println(js.build().toString());


        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        return js.build();

    }


    public JsonObject generateAWESOMEPlan(Reader analysisScript) {
        this.analysisScript = analysisScript;

        JsonObjectBuilder js = Json.createObjectBuilder();

        Adil p = new Adil(analysisScript);
        try {
            js = p.ADILStatement(js);
            //System.out.println(js.build().toString());


        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        return js.build();

    }


    public static void main(String[] args) throws SQLException, IOException {


        AdilFirstPass as = new AdilFirstPass();

        Options options = new Options();
        Statement stmt;


        Option input = new Option("i", "input", true, "input file path");

        input.setRequired(true);

        options.addOption(input);

        try {


            stmt = CCJSqlParserUtil.parse("Select * from test ");

            System.out.println(stmt);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }


        CommandLineParser parser = new DefaultParser();

        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;


        final CommandLine commandLine;
        String inputDir = null;
        try {
            commandLine = parser.parse(options, args);
            inputDir = commandLine.getOptionValue("i");
        } catch (org.apache.commons.cli.ParseException e) {

            System.out.println("IO exceptions reading files " + e.getMessage());
        }

        Path path = Paths.get(inputDir);
        String stringFromFile = java.nio.file.Files.lines(path).collect(
                Collectors.joining());



/*


        String url = "jdbc:sqlite:/home/subhasis/IdeaProjects/adil/test.db";
        //String url = "jdbc:sqlite:";
        Connection con = null;
        con = DriverManager.getConnection(url);
        Statement stm =  con.createStatement();
        String sqlDesc = "CREATE TABLE IF NOT EXISTS DecisionTable (\n"
                + " Id INTEGER PRIMARY KEY,\n"
                + "	ElementName string ,\n"
                + "	ElementType string ,\n"
                + "	StorageName string ,\n"
                + "	StorageType string ,\n"
                + "	IndexName string ,\n"
                + "	IndexType string ,\n"
                + "	Memoize boolean ,\n"
                + "	IsTemorary boolean, \n"
                + "	planId INTEGER \n"
                + ");";
        String sqlAttribute =  "CREATE TABLE IF NOT EXISTS AttributeTable (\n"
                + " Id INTEGER PRIMARY KEY,\n"
                + "	AttributeName string ,\n"
                + "	StoreType string ,\n"
                + "	StoreName string ,\n"
                + "	IndexType string ,\n"
                + "	Memoize boolean ,\n"
                + "	IsTemorary boolean, \n"
                + "	planId INTEGER \n"
                + ");";

        String palnTable =  "CREATE TABLE IF NOT EXISTS planTable (\n"
                + " Id INTEGER PRIMARY KEY,\n"
                + "	plan Text ,\n"
                + "	ISFinal boolean, \n"
                + "	planId INTEGER \n"
                + ");";

        String capabaility = "CREATE TABLE `capabityTable` (\n"
	                           +"id	INTEGER PRIMARY KEY,\n"
	                           +"capabilit TEXT,\n"
	                           +"modelName TEXT,\n"
	                           +"storeName INTEGER \n"
                               + ");";

        stm.execute(sqlDesc);
        stm.execute(sqlAttribute);
        stm.execute(palnTable);
        stm.close();*/
        Reader sr = new StringReader(stringFromFile);

       /* sr = new StringReader( "  create ENV SocialData; CREATE DATASOURCE USNEWS TYPE NEWS (lang, eng),(tokenizer, snlp),(FilePath, default);CREATE DATASOURCE USPOLITICS TYPE TWEET (credential, abcTweet),(streaming, true),(path, default);  CREATE CONNECTION FROM DATASOURCE USPOLITICS to DefaultTweetSchema EXECUTE every 30M;  CREATE CONNECTION FROM DATASOURCE USNEWS to DefaultNEWS EXECUTE every 24H; create analysis a1 as ( " +
                "v := CONSTRUCTGRAPH( \" create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2) ; \" ), " +
                "annotatedTweets0 :=annotate( Tweet.text ) in USPoliticsTweets with Senate, House, Leadership, Places where wc > a  STORE TYPE DEFAULT, " +
                "annotatedNews :=annotate( news.title , news.content ) IN USNews WITH  Senate, House, Leadership, Places  where catagory == politics AND name == NewYorkTimes  AND name == LATimes AND name == WAJOURNAL STORE TYPE Default," +
                " BotTweets := EXECUTESQLPP( \" WITH  hfQueries as (select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ) ; select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ;\")" +
                " , PROPERTYGRAPH  AdilNode := CREATE VIEW(  COLLECTION :=  EXECUTESQLPP( \" SELECT ht name htgroup count(h) from hfQueries NOT IN (SELECT  x from BotTweets  Unnest b.htdoc  x  WHERE   u.doc <  100 );  \"  ), VIEW := CONSTRUCTGRAPH(\" create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2);\"))," +
                "ConversationByGroups[] :=  communityDetection(Conversations, kcore), TermDocumentMatrx := ComputerTermDocumentMatrix(ConversationByGroups, param), TweetConversationTopics[] := computeTopicModel(TermDocumentMatrix, param), concatinatedNews :=concatNews(title, content), NEWSTermDocumentMatrx := ComputerTermDocumentMatrix(ConversationByGroups, param), NewsConversationTopics := computeTopicModel(NEWSTermDocumentMatrx, param) STORE AS TEMPORALRELATION ON News2.reportDate AS DATE, Xreport := REPORT ComputeDifference(TweetConversationTopics, NewsConversationTopics) AS JSON  STORE WEEKLY )execute every 12H; ");
*/

        //PROPERTYGRAPH  AdilNode := CREATE VIEW( COLLECTION :=  EXECUTESQLPP( " SELECT  ht name htgroup count(h) from hfQueries NOT IN ( select  x from BotTweets  Unnest b.htdoc  x  WHERE   u.doc <  100  ); " ), VIEW := CONSTRUCTGRAPH("create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2);" ) PARTITION BY getDate(x.tt) AS XX )


        // "annotate(news.title t, news.content c) in USNews with Senate, House, Leadership, Places  where news.category = 'politics' and  news.newspaper.name in ['New York Times', 'LA Times', 'Wall Street Journal', ...]"
        //sr = new StringReader("  create analysis a1 as(PROPERTYGRAPH  AdilNode := CREATE VIEW( COLLECTION :=  EXECUTESQLPP( \" SELECT  ht name htgroup count(h) from hfQueries NOT IN ( select  x from BotTweets  Unnest b.htdoc  x  WHERE   u.doc <  100  ); \" ), VIEW := CONSTRUCTGRAPH(\"create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2);\" ) PARTITION BY getDate(x.tt) AS XX ) );");

        //sr = new StringReader("create analysis a1 as (  BotTweets := EXECUTESQLPP( \" WITH  hfQueries as (select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ) ; select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ;\") ); ;");
        //Reader sr = new StringReader("x := CONSTRUCTGRAPH(\"create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2);\" ) PARTITION BY getDate(x.tt) AS XX");
        //Reader sr = new StringReader("create (u:User {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})");

        //Reader sr = new StringReader("match (n:Tweet) merge (m)-[:hasHashTag]->( u:User  {uname: unnest( Collection.Tweet.Entities.userMentions.id) } )");
        JsonObjectBuilder js = Json.createObjectBuilder();
        //sr = new StringReader(" \"  SELECT  ht name htgroup count(h) from hfQueries NOT IN ( select  x from BotTweets  Unnest b.htdoc  x  WHERE   u.doc <  100  );  \" ");
        //sr = new StringReader(" \"  select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ; \" ");
        // sr = new StringReader(" \"  WITH  hfQueries as (select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ) ; select m.id as TweetID e.text as hashtag m.user.name  AS name  from annotatedTweets.witness m  unnest m.entities.hashtag e ;\" ");
        //sr = new StringReader(" analysis[][] := CONS( a, aa) STORE AS TEMPORALRELATION on dat.ib as date; ");
        //sr = new StringReader("x := FILTER( x.id where x( AP, x, > )   ");
        //with hfQueries as ( select m.id as TweetID, e.text as hashtag, m.user.name  name  from annotatedTweets.witness m  unnest m.entities.hashtag e)

        //sr = new StringReader(" \" create CONSTRAINT ON (t:Tweet) ASSERT t.id IS UNIQUE ;  create CONSTRAINT ON (t:User) ASSERT u.id IS UNIQUE ; create (u:User { id: Collection.Tweet.User.id, name: Collection.Tweet.User.name})-[:created]->(n:Tweet { id: Collection.Tweet.TweetID, TweetDate: Collection.Tweet.TweetDate, Text: Collection.Tweet.Text } );  create (u:x {id: Collection.Tweet.User.id, name: Collection.Tweet.User.name}) ; match (n:Tweet) merge (n)-[:hasHashTag]->( h:HashTag  {tag: unnest(Collection.Tweet.Entities.HashTags)}); match (h1)  merge (h1)-[:cooccursWith]->(h2); \"");

      /*  Cypher chp = new Cypher(sr);

        try {
            JsonObjectBuilder p = chp.Expression(js);
            System.out.println(p.build().toString());
            //p.dump(">");


        } catch (edu.sdsc.Cypher.ParseException e) {
            e.printStackTrace();
        } */


   /*     SQLPP sqp = new SQLPP(sr);
        try {

            js = sqp.Expression(js);

            System.out.println(js.build().toString());

        } catch (edu.sdsc.SQLPP.ParseException e) {
            e.printStackTrace();
        }
*/


        Adil p = new Adil(sr);
        try {
            js = p.ADILStatement(js);


        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        JsonObject temp = js.build();

       System.out.println(temp.toString());

        JsonObjectBuilder finalObject = Json.createObjectBuilder();
        finalObject.add("FirstPass", temp);
        JsonObjectBuilder variableSecondPass = Json.createObjectBuilder();
        JsonObjectBuilder schemaSecondPass = Json.createObjectBuilder();
        JsonObjectBuilder storeSecondPass = Json.createObjectBuilder();
        JsonObject lObject;
        JsonObject ltempDecision = temp.getJsonObject("INFO").getJsonObject("decision");
        JsonObject ltempvariable = temp.getJsonObject("INFO").getJsonObject("Variable");

     Set keys = ltempDecision.keySet();


        for (Object key: keys)
        {
                 System.out.println(key.toString());



        }




        if (!temp.getJsonArray("ADIL").isEmpty()) {

            JsonObjectBuilder adilObject = Json.createObjectBuilder();

            JsonArray plan = temp.getJsonArray("ADIL");


            for (int i = 0; i < plan.size(); i++) {
                lObject = plan.getJsonObject(i);


                if (lObject.containsKey("UnitAnalysis"))

                {
                    //System.out.println(lObject.getJsonArray("UnitAnalysis").toString());

                    JsonArray tempArray = lObject.getJsonArray("UnitAnalysis");
                    JsonArrayBuilder unitanalysis = Json.createArrayBuilder();


                    for (int j = 0; j < tempArray.size(); j++) {


                        //Inside UnitAnalysis
                        JsonObject anObject = tempArray.getJsonObject(j);
                        JsonObjectBuilder analysisObject = Json.createObjectBuilder();
                        String avariablename = "x";
                        String avariableWitnessType = "x";
                        String witneseStore = "x";

                        if (anObject.containsKey("AnalysisVariable")) {
                            avariablename = anObject.getString("AnalysisVariable");
                        }


                        if (anObject.containsKey("plan")) {

                            //State of Annotation

                            if (anObject.getJsonObject("plan").containsKey("annotation")) {

                                JsonObject realization = as.annotationSecondPass(anObject, variableSecondPass, schemaSecondPass, storeSecondPass, ltempvariable, avariablename);

                                analysisObject.add("plan", anObject.getJsonObject("plan"));
                                analysisObject.add("realization",realization);
                                analysisObject.add("analysisvariable",avariablename);
                                System.out.println(analysisObject.build().toString());


                            }

                            if (anObject.getJsonObject("plan").containsKey("filter")){

                                JsonObject variable = variableSecondPass.build();
                                JsonObject schema = schemaSecondPass.build();
                                JsonObject store = storeSecondPass.build();

                                variableSecondPass = Json.createObjectBuilder(variable);
                                schemaSecondPass = Json.createObjectBuilder(schema);
                                storeSecondPass = Json.createObjectBuilder(store);


                                JsonObject realization = as.FilterSecondPass(anObject,variable, schema, store, ltempvariable, avariablename, variableSecondPass, schemaSecondPass, storeSecondPass );
                                analysisObject.add("plan", anObject.getJsonObject("plan"));
                                analysisObject.add("realization",realization);
                                analysisObject.add("analysisvariable",avariablename);


                            }

                            if(anObject.getJsonObject("plan").containsKey("operation")){

                                anObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("FUNCTION");


                                if(anObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("component").getJsonObject("FUNCTION").getString("NAME").equalsIgnoreCase("project"))
                                {
                                    JsonObject variable = variableSecondPass.build();
                                    JsonObject schema = schemaSecondPass.build();
                                    JsonObject store = storeSecondPass.build();
                                    variableSecondPass = Json.createObjectBuilder(variable);
                                    schemaSecondPass = Json.createObjectBuilder(schema);
                                    storeSecondPass = Json.createObjectBuilder(store);
                                    JsonObject realization = as.ProjectFuction(anObject, variable, schema, store, ltempvariable, avariablename, variableSecondPass, schemaSecondPass, storeSecondPass);
                                    analysisObject.add("plan", anObject.getJsonObject("plan"));
                                    analysisObject.add("realization",realization);
                                    analysisObject.add("analysisvariable",avariablename);


                                }
                                else if (!anObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("component").getJsonObject("FUNCTION").getString("NAME").equalsIgnoreCase("project")){

                                    JsonObject variable = variableSecondPass.build();
                                    JsonObject schema = schemaSecondPass.build();
                                    JsonObject store = storeSecondPass.build();
                                    variableSecondPass = Json.createObjectBuilder(variable);
                                    schemaSecondPass = Json.createObjectBuilder(schema);
                                    storeSecondPass = Json.createObjectBuilder(store);
                                    JsonObject realization = as.generalFunction(anObject, variable, schema, store, ltempvariable, avariablename, variableSecondPass, schemaSecondPass, storeSecondPass);

                                    //analysisObject.add("plan", anObject.getJsonObject("plan"));
                                    analysisObject.add("realization",realization);
                                    analysisObject.add("analysisvariable",avariablename);
                                }



                            }


                        }
                        unitanalysis.add(analysisObject.build());
                    }

                    adilObject.add("UnitAnalysis",unitanalysis.build() );
                }


            }

            finalObject.add("SecondPass",adilObject.build());

        }


        System.out.println(finalObject.build());
    }
    public JsonObject generalFunction(JsonObject proObject, JsonObject variable, JsonObject schema, JsonObject store, JsonObject variableFirst, String avariablename,
                                     JsonObjectBuilder variableSecondPass, JsonObjectBuilder schemaSecondPass, JsonObjectBuilder storeSecondPass)
    {
        JsonObjectBuilder projectObject = Json.createObjectBuilder();

        if(proObject.getJsonObject("plan").containsValue("Type")) {

            String type = proObject.getJsonObject("plan").getString("Type");


            if (type.equalsIgnoreCase("array")) {
                Integer size = proObject.getJsonObject("plan").getInt("ArraySize");

            }
        }

        String field = proObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("component").getJsonObject("FUNCTION").getString("NAME");

        JsonArray info = proObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("component").getJsonObject("FUNCTION").getJsonArray("info");

        boolean Store = proObject.getJsonObject("plan").getJsonObject("operation").getBoolean("STORE");
        boolean temporal = proObject.getJsonObject("plan").getJsonObject("operation").getBoolean("Temporal");
        if(Store)
        {

            String outputSchema = null;
            for (int i = 0; i < info.size(); i++)
            {
                outputSchema = info.getJsonObject(i).getString("outputSchema");

            }


           // String outputSchema = proObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("component").getJsonObject("FUNCTION").getString("outputschema");

            if(outputSchema.equalsIgnoreCase("list<propertygraph>"))
            {
                projectObject.add("GRAPH","neo4j");
                projectObject.add("List(GRAPH)","PGSQL");
                projectObject.add("Index", "List");
            }
            if(outputSchema.equalsIgnoreCase("(matrix<int, string, int>, matrix<int, int, float>, matrix<int, int, float>)"))
            {
                projectObject.add("TopicMatrix","PGSQL");


            }
            if(outputSchema.equalsIgnoreCase("matrix<int, string, int>"))
            {
                projectObject.add("Matrix","PGSQL");

            }
            if(temporal)
            {


                String time = proObject.getJsonObject("plan").getJsonObject("operation").getString("AS");
                projectObject.add("Index", time);
                String temfield = proObject.getJsonObject("plan").getJsonObject("operation").getString("Field");
                String temtype = proObject.getJsonObject("plan").getJsonObject("operation").getString("TemporalType");
                projectObject.add("TEMPORAL-FIELD", temfield);


                if(temtype.equalsIgnoreCase("relational"))
                {
                    projectObject.add("TEMPORAL-INDEX", "PGSQL");
                    projectObject.add("OUTPUT","PGSQL");
                }
                if(temtype.equalsIgnoreCase("graph"))
                {
                    projectObject.add("TEMPORAL-INDEX", "PGSQL");
                    projectObject.add("OUTPUT","neo4j");
                }

                //String teporal

               // Sting temporalField =
            }

        }




        return projectObject.build();
    }

    public JsonObject ProjectFuction(JsonObject proObject, JsonObject variable, JsonObject schema, JsonObject store, JsonObject variableFirst, String avariablename,
                                     JsonObjectBuilder variableSecondPass, JsonObjectBuilder schemaSecondPass, JsonObjectBuilder storeSecondPass)
    {
        JsonObjectBuilder projectObject = Json.createObjectBuilder();

        String field = proObject.getJsonObject("plan").getJsonObject("operation").getJsonObject("component").getJsonObject("FUNCTION").getJsonObject("arguments").getString("param");

        String type = variable.getString(field);

        projectObject.add("OutputType", type);
        if(proObject.getJsonObject("plan").getJsonObject("operation").getBoolean("STORE"))
        {
            projectObject.add("STORE", store.getString(field) );
        }
        else if(!proObject.getJsonObject("plan").getJsonObject("operation").getBoolean("STORE"))
        {
            projectObject.add("STORE", "AWESOME:IN-MEMORY" );
        }

        projectObject.add("SCHEMA", schema.getJsonArray(field) );

        variableSecondPass.add(avariablename,type );
        schemaSecondPass.add(avariablename, schema.getJsonArray(field));
        storeSecondPass.add(avariablename,store.getString(field) );



return projectObject.build();
    }

    public JsonObject FilterSecondPass(JsonObject filterObject, JsonObject variable, JsonObject schema, JsonObject store, JsonObject variableFirst, String avariablename,
                                       JsonObjectBuilder variableSecondPass, JsonObjectBuilder schemaSecondPass, JsonObjectBuilder storeSecondPass  ){

        JsonObjectBuilder filterVariable = Json.createObjectBuilder();
        AdilFirstPass as = new AdilFirstPass();

        String type = null;
        String fieldName = null;
        //JsonObject variable = variableSecondPass.build();
        JsonObject sc = null;
        //JsonObject schema = schemaSecondPass.build();
        //JsonObject store = storeSecondPass.build();
        Integer cost = 0;

        if(filterObject.getJsonObject("plan").getJsonObject("filter").containsKey("condition"))
        {
            fieldName = filterObject.getJsonObject("plan").getJsonObject("filter").getString("field");
            type = variable.getString(fieldName);
            sc = schema.getJsonObject(fieldName);
            filterVariable.add("OutputType", type);
            filterVariable.add("OutputSchema", sc);
        }
        if(filterObject.getJsonObject("plan").getJsonObject("filter").getJsonObject("condition").containsKey("WHERE"))
        {
            JsonArray function = filterObject.getJsonObject("plan").getJsonObject("filter").getJsonObject("condition").getJsonArray("WHERE");

            for(int i = 0; i < function.size();i++)
            {
                JsonObject functionObject = function.getJsonObject(i);
                if(functionObject.containsKey("firstfunction"))
                {
                   cost =  as.calclateFunctionCost(functionObject.getJsonObject("firstfunction"),store.getString(fieldName), cost);
                }
            }
            if (cost > 5) {
                filterVariable.add("COMPUTE:POLICY", "STORE");
                filterVariable.add("COMPUTE:PROVIDER", store.getString(fieldName));


            } else {

                filterVariable.add("COMPUTE:POLICY", "STREAM");
                filterVariable.add("COMPUTE:POLICY", "AWESOME");
            }


            //System.out.println("");
        }



        variableSecondPass.add(avariablename,type );
        schemaSecondPass.add(avariablename, sc);
        storeSecondPass.add(avariablename,store.getString(fieldName) );



        return filterVariable.build();

    }


    public Integer calclateFunctionCost(JsonObject expression, String witnessmodel, Integer cost) {

        JsonArray arrayObject;

        arrayObject = expression.getJsonObject("FUNCTION").getJsonArray("info");

        for (int i = 0; i < arrayObject.size(); i++) {
            JsonObject temp = arrayObject.getJsonObject(i);

            if (temp.getString("provider").equalsIgnoreCase(witnessmodel)) {
                cost = cost + temp.getInt("cost");
            }
        }

        if (expression.getJsonObject("FUNCTION").getJsonObject("arguments").containsKey("f()")) {
            JsonObject recObject = expression.getJsonObject("FUNCTION").getJsonObject("arguments").getJsonObject("f()");


            cost = calclateFunctionCost(recObject, witnessmodel, cost);
        }
        return cost;
    }


    public boolean LibrarySerarch(JsonArray adil, String searchName) {

        JsonObject lObject;

        JsonArray ltemp = null;
        for (int i = 0; i < adil.size(); i++) {
            lObject = adil.getJsonObject(i);

            if (lObject.containsKey("IMPORT")) {


                ltemp = lObject.getJsonObject("IMPORT").getJsonObject("System").getJsonArray("info");
                for (int k = 0; k < ltemp.size(); k++) {

                    String lname = ltemp.get(k).asJsonObject().getString("name");
                    if (lname.equals(searchName)) {
                        return true;

                    }
                }

            }

        }


        return false;
    }

    public String serachDataSource(JsonObject variable, String name) {

        String output = name;
        String fieldName = null;

        do {
            output = variable.getString(name);
            fieldName = name;
            name = output;
        } while (!output.equals("DataSource"));


        return fieldName;

    }

    public List<String> getProviderName(String name, String tableName, String value) throws SQLException {


        List<String> pName = new ArrayList<String>();
        ResultSet rs = getResult(name, tableName, "\"" + value + "\"");
        while (rs.next()) {
            pName.add(rs.getString("type"));
        }

        return pName;
    }


    public JsonObject annotationSecondPass(JsonObject anObject, JsonObjectBuilder variableSecondPass, JsonObjectBuilder schemaSecondPass,
                                           JsonObjectBuilder storeSecondPass, JsonObject FisrtPasspvariable, String avariablename) throws SQLException {


        String avariableWitnessType = null;
        String witneseStore = null;
        String source = null;
        AdilFirstPass as = new AdilFirstPass();

        JsonObjectBuilder annotObject = Json.createObjectBuilder();
        JsonArrayBuilder annotArray = Json.createArrayBuilder();
        JsonArrayBuilder unionArray = Json.createArrayBuilder();
        JsonArray annotTupels = null;
        int countflat = 0;
        int countNestedOBJ = 0;
        int countNestedArray = 0;

        //System.out.println(anObject.toString());

        if(anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getBoolean("memoize"))
        {
            source = anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getJsonObject("MEMOSRC").getString("SCHEMA");
            annotTupels = anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getJsonObject("MEMOSRC").getJsonObject("SELECT").getJsonArray("PROJECT");
        }

        if (anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").containsKey("UNION")) {
            //start of union operation
            JsonObjectBuilder unionObject = Json.createObjectBuilder();


            JsonArray annotationfunction = anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getJsonArray("UNION");
            JsonObjectBuilder witnessannotTupel = Json.createObjectBuilder();
            JsonObjectBuilder matchannotTupel = Json.createObjectBuilder();

            for (int n = 0; n < annotationfunction.size(); n++) {
                JsonObject annotInfo = annotationfunction.getJsonObject(n);

                if(!anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getBoolean("memoize")) {

                    source = annotInfo.getJsonObject("SOURCE").getString("SCHEMA");
                    annotTupels = annotInfo.getJsonObject("SOURCE").getJsonArray("PROJECT");

                }

                String ldataSrc = as.serachDataSource(FisrtPasspvariable, source);
                JsonObjectBuilder srcdataDetails = Json.createObjectBuilder();

                srcdataDetails = CheckDBStorage(srcdataDetails, ldataSrc);
                JsonObject srcdataDetailsJSON = srcdataDetails.build();
                String derivedSrc = srcdataDetailsJSON.getString("source");

                String witnessfieldname = null;

                //Determine JSON
                JsonArray witnessObject = anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getJsonObject("WITNESS").getJsonArray("PROJECT");

                for(int q = 0; q < witnessObject.size(); q++) {

                    witnessfieldname = witnessObject.getJsonObject(q).getString("name");

                    if(!witnessfieldname.equalsIgnoreCase("*")) {

                        String witnessSRC = witnessObject.getJsonObject(q).getString("source");

                        String[] fieldNameArray = witnessfieldname.split("\\.");

                        String replacedtuplefieldname = witnessfieldname.replace(fieldNameArray[0], derivedSrc);

                       // String replacedtuplefieldname = witnessfieldname.replace(witnessSRC, derivedSrc);

                        JsonObjectBuilder witnesstupleInfo = Json.createObjectBuilder();


                        JsonObject witnessinfojson = fieldSchemaCheck(witnesstupleInfo, replacedtuplefieldname);

                        if (witnessinfojson.getString("model").equals("flat")) {
                            ++countflat;
                        }
                        if (witnessinfojson.getString("model").equals("nested")) {
                            ++countNestedOBJ;
                        }

                    }


                }

                if (countNestedOBJ > countflat) {
                    witnessannotTupel.add("witness", "semistructured");

                    variableSecondPass.add(avariablename , "semistructured");

                    avariableWitnessType = "semistructured";

                    if (as.getProviderName("name", "provider", "semistructured").size() <= 1) {
                        witnessannotTupel.add("witness-store", as.getProviderName("name", "provider", "semistructured").get(0));
                        witneseStore = as.getProviderName("name", "provider", "semistructured").get(0);

                    }


                    //witnessannotTupel.add("MatchTuple", annotTupels);


                    //Witness Store
                    //JsonObject witnessJson = fieldSchemaCheck()



                    witnessannotTupel.add("match", "semistructured");
                    variableSecondPass.add(avariablename + ".match", "semistructured");
                    if (as.getProviderName("name", "provider", "semistructured").size() <= 1) {
                        witnessannotTupel.add("witness-store", as.getProviderName("name", "provider", "semistructured").get(0));
                        witneseStore = as.getProviderName("name", "provider", "semistructured").get(0);
                    }
                }

                if (countNestedOBJ == countflat) {
                    witnessannotTupel.add("witness", "semistructured");
                    variableSecondPass.add(avariablename , "semistructured");
                    avariableWitnessType = "semistructured";
                    if (as.getProviderName("name", "provider", "semistructured").size() <= 1) {
                        witnessannotTupel.add("witness-store", as.getProviderName("name", "provider", "semistructured").get(0));
                        witneseStore = as.getProviderName("name", "provider", "semistructured").get(0);
                    }
                    witnessannotTupel.add("match", "relational");
                    variableSecondPass.add(avariablename + ".match", "relational");
                    if (as.getProviderName("name", "provider", "relational").size() <= 1) {
                        witnessannotTupel.add("match", as.getProviderName("name", "provider", "relational").get(0));

                    }

                }

                if (countNestedOBJ == 0) {
                    witnessannotTupel.add("witness", "relational");
                    variableSecondPass.add(avariablename, "relational");
                    avariableWitnessType = "relational";
                    if (as.getProviderName("name", "provider", "relational").size() <= 1) {
                        witnessannotTupel.add("witness-store", as.getProviderName("name", "provider", "relational").get(0));
                        witneseStore = as.getProviderName("name", "provider", "relational").get(0);
                    }
                    witnessannotTupel.add("match", "relational");
                    variableSecondPass.add(avariablename + ".match", "relational");
                    if (as.getProviderName("name", "provider", "relational").size() <= 1) {
                        witnessannotTupel.add("match", as.getProviderName("name", "provider", "relational").get(0));
                    }
                }


                if(witnessfieldname.equalsIgnoreCase("*"))
                {

                    String type = null;

                    witnessObject = checkAllDBelement(derivedSrc,"schematable", "schemaname" );
                }


                //Determine Output schema


                String type = "";


                // System.out.println(srcdataDetailsJSON.toString());

                if(!anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getBoolean("memoize")) {
                    annotTupels = annotInfo.getJsonObject("SOURCE").getJsonArray("PROJECT");
                }


                JsonObjectBuilder typeannotTupel = Json.createObjectBuilder();
                JsonArrayBuilder tupleInfoArray = Json.createArrayBuilder();



                for (int at = 0; at < annotTupels.size(); at++) {
                    int flag = 0;
                    JsonObject tempAnnotTuple = annotTupels.getJsonObject(at);

                    String tuplefieldname = tempAnnotTuple.getString("field");
                    String replacedtuplefieldname = tuplefieldname.replace(source, derivedSrc);

                    JsonObjectBuilder tupleInfo = Json.createObjectBuilder();


                    JsonObject tinfojson = fieldSchemaCheck(tupleInfo, replacedtuplefieldname);

                    if (tinfojson.getString("model").equals("flat")) {
                        ++countflat;
                    }
                    if (tinfojson.getString("model").equals("nested")) {
                        ++countNestedOBJ;
                    }

                    tupleInfoArray.add(tinfojson);
                    //typeannotTupel.add("InputType", tupleInfoArray.build());

                    //

                    int cost = 0;


                    JsonArray whereClause = null;

                    /*if (!anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getBoolean("memoize")) {
                        whereClause = anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getJsonObject("MEMOSRC").getJsonObject("SELECT").getJsonArray("WHERE");


                    for (int wc = 0; wc < whereClause.size(); wc++) {

                        JsonObject expression = whereClause.getJsonObject(wc);
                        if (expression.containsKey("firstfunction")) {
                            cost = 0;
                            cost = as.calclateFunctionCost(expression.getJsonObject("firstfunction"), witneseStore, cost);

                            typeannotTupel.add("FirstOperatorFunctionCost", cost);

                            typeannotTupel.add("FirstFunctionName", expression.getJsonObject("firstfunction").getJsonObject("FUNCTION").getString("NAME"));

                            if (cost > 5) {
                                typeannotTupel.add("COMPUTE:POLICY", "STORE");
                                typeannotTupel.add("COMPUTE:PROVIDER", witneseStore);


                            } else {

                                typeannotTupel.add("COMPUTE:POLICY", "STREAM");
                                typeannotTupel.add("COMPUTE:PROVIDER", "AWESOME");
                            }
                            typeannotTupel.add("function", expression.getJsonObject("firstfunction"));

                        }

                    }
                    annotArray.add(typeannotTupel.build());

                }*/

                }


                //annotObject.add("realization", typeannotTupel.build());
                //Inside each union

                unionArray.add(annotArray.build());
            }


            JsonArray whereClause = null;

            if (anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getBoolean("memoize")) {
                JsonObjectBuilder typeannotTupel = Json.createObjectBuilder();
                whereClause = anObject.getJsonObject("plan").getJsonObject("annotation").getJsonObject("Operation").getJsonObject("MEMOSRC").getJsonObject("SELECT").getJsonArray("WHERE");


                for (int wc = 0; wc < whereClause.size(); wc++) {

                    JsonObject expression = whereClause.getJsonObject(wc);
                    if (expression.containsKey("firstfunction")) {
                       int cost = 0;
                        cost = as.calclateFunctionCost(expression.getJsonObject("firstfunction"), witneseStore, cost);

                        typeannotTupel.add("FirstOperatorFunctionCost", cost);

                        typeannotTupel.add("FirstFunctionName", expression.getJsonObject("firstfunction").getJsonObject("FUNCTION").getString("NAME"));

                        if (cost > 5) {
                            typeannotTupel.add("COMPUTE:POLICY", "STORE");
                            typeannotTupel.add("COMPUTE:PROVIDER", witneseStore);


                        } else {

                            typeannotTupel.add("COMPUTE:POLICY", "STREAM");
                            typeannotTupel.add("COMPUTE:PROVIDER", "AWESOME");
                        }
                        typeannotTupel.add("function", expression.getJsonObject("firstfunction"));

                    }

                }
                annotArray.add(typeannotTupel.build());

            }




            JsonObject wittuple = witnessannotTupel.build();

            schemaSecondPass.add(avariablename , witnessannotTupel);
            storeSecondPass.add(avariablename , witneseStore);
            annotObject.add("Storage",wittuple);
            annotObject.add("Memoize",annotArray.build());
            //annotObject.add("UNION", unionArray.build());
        }

        return annotObject.build();
        // end of annotation
    }


}



        /* if (countNestedOBJ > countflat) {
                        typeannotTupel.add("witness", "semistructured");

                        variableSecondPass.add(avariablename + ".witness", "semistructured");

                        avariableWitnessType = "semistructured";

                        if (as.getProviderName("name", "provider", "semistructured").size() <= 1) {
                            typeannotTupel.add("witness-store", as.getProviderName("name", "provider", "semistructured").get(0));
                            witneseStore = as.getProviderName("name", "provider", "semistructured").get(0);

                        }

                        typeannotTupel.add("match", "semistructured");
                        variableSecondPass.add(avariablename + ".match", "semistructured");
                        if (as.getProviderName("name", "provider", "semistructured").size() <= 1) {
                            typeannotTupel.add("witness-store", as.getProviderName("name", "provider", "semistructured").get(0));
                            witneseStore = as.getProviderName("name", "provider", "semistructured").get(0);
                        }
                    }
                    if (countNestedOBJ == countflat) {
                        typeannotTupel.add("witness", "semistructured");
                        variableSecondPass.add(avariablename + ".witness", "semistructured");
                        avariableWitnessType = "semistructured";
                        if (as.getProviderName("name", "provider", "semistructured").size() <= 1) {
                            typeannotTupel.add("witness-store", as.getProviderName("name", "provider", "semistructured").get(0));
                            witneseStore = as.getProviderName("name", "provider", "semistructured").get(0);
                        }
                        typeannotTupel.add("match", "relational");
                        variableSecondPass.add(avariablename + ".match", "relational");
                        if (as.getProviderName("name", "provider", "relational").size() <= 1) {
                            typeannotTupel.add("match", as.getProviderName("name", "provider", "relational").get(0));

                        }

                    }
                    if (countNestedOBJ == 0) {
                        typeannotTupel.add("witness", "relational");
                        variableSecondPass.add(avariablename + ".witness", "relational");
                        avariableWitnessType = "relational";
                        if (as.getProviderName("name", "provider", "relational").size() <= 1) {
                            typeannotTupel.add("witness-store", as.getProviderName("name", "provider", "relational").get(0));
                            witneseStore = as.getProviderName("name", "provider", "relational").get(0);
                        }
                        typeannotTupel.add("match", "relational");
                        variableSecondPass.add(avariablename + ".match", "relational");
                        if (as.getProviderName("name", "provider", "relational").size() <= 1) {
                            typeannotTupel.add("match", as.getProviderName("name", "provider", "relational").get(0));
                        }
                    }
                    schemaSecondPass.add(avariablename + ".witness", annotTupels);
                    storeSecondPass.add(avariablename+ ".witness", witneseStore);
                    */






