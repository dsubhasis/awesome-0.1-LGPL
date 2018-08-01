package edu.sdsc.awesome.connector.solr;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrDocumentList;
import org.apache.commons.cli.*;
import org.apache.solr.common.SolrInputDocument;

public class ConnectSolr {

    private static HttpSolrClient solr;

    private static void addDocuments( String name ,String filtersPath, String objectsPath) throws IOException,
            SolrServerException{
        if(name == null || filtersPath == null || objectsPath == null) {
            System.err.println("Missing document filters, objects, or name");
            System.exit(1);
        }
        String[] filters = filtersPath.split(",");
        String[] objects = objectsPath.split(",");
        if(filters.length != objects.length) {
            System.err.println("Not enough filters and/or objects to make pairs");
            System.exit(1);
        }
        else {
            SolrInputDocument doc = new SolrInputDocument();
            for(int i = 0; i < filters.length; i++) {
                doc.addField(filters[i], objects[i]);
            }
            doc.addField("name", name);
            solr.add(doc);
        }
        solr.commit();
        System.out.println("Successful Commit of Document : " + name);
    }

    private static void doQuery( String requestPath, String queryPath, String fieldsPath,
                                         String filter, Gson gson ) {

        SolrQuery query = new SolrQuery();

        if(requestPath != null) {
            query.setRequestHandler(requestPath);
        }

        if(queryPath != null) {
            query.set("q", queryPath);
        } else {
            System.err.println("Missing Query Argument");
            System.exit(1);
        }

        if(fieldsPath != null) {
            String[] fields = fieldsPath.split(",");
            for (String field : fields) {
                query.addField(field);
            }
        }

        query.setStart(0);
        QueryResponse response;

        if(filter != null) {
            query.addFilterQuery(filter);
        }
        try {
            response = solr.query(query);
            SolrDocumentList results = response.getResults();
            String res = gson.toJson(results);
            System.out.println(res);
        } catch(SolrServerException e) {
            System.err.println("SolrServerException: Unable to connect to Solr Server");
            System.exit(1);
        } catch(IOException e) {
            System.err.println("IOException: Unable to parse responses");
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        Options options = new Options();
        Option url = new Option("u", "url", true, "Solr HTML Link");
        url.setRequired(true);
        options.addOption(url);
        Option request = new Option("r", "request", true, "Solr Request Handler");
        request.setRequired(false);
        options.addOption(request);
        Option query =  new Option("q", "query", true, "Solr Query" );
        query.setRequired(false);
        options.addOption(query);
        Option fields = new Option("fl", "fields", true, "Solr Query Fields");
        fields.setRequired(false);
        options.addOption(fields);
        Option filters = new Option("ft", "filters", true, "Solr Query Filters");
        filters.setRequired(false);
        options.addOption(filters);

        Option documents = new Option("d", "documents", false, "Add Solr Documents");
        documents.setRequired(false);
        options.addOption(documents);
        Option filtersdocument = new Option("fd", "filtersdocument", true, "Solr Document Filters");
        filtersdocument.setRequired(false);
        options.addOption(filtersdocument);
        Option objectsdocument = new Option("od", "objectsdocument", true, "Solr Document Objects");
        objectsdocument.setRequired(false);
        options.addOption(objectsdocument);
        Option namedocument = new Option("nd", "namedocument", true, "Solr Document Name");
        namedocument.setRequired(false);
        options.addOption(namedocument);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        String urlPath = "";
        String requestPath = "";
        String queryPath = "";
        String fieldsPath = "";
        String filtersPath = "";
        String filtersdocumentPath = "";
        String objectsdocumentPath = "";
        String namedocumentPath = "";
        boolean documentsFlag = false;

        /* parse values */
        try {
            cmd = parser.parse(options, args);
            urlPath = cmd.getOptionValue("url");
            requestPath = cmd.getOptionValue("request");
            queryPath = cmd.getOptionValue("query");
            fieldsPath = cmd.getOptionValue("fields");
            filtersPath = cmd.getOptionValue("filters");
            filtersdocumentPath = cmd.getOptionValue("filtersdocument");
            objectsdocumentPath = cmd.getOptionValue("objectsdocument");
            namedocumentPath = cmd.getOptionValue("namedocument");
            documentsFlag = cmd.hasOption("documents");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        solr = new HttpSolrClient.Builder(urlPath).build();

        /* check if adding documents or searching documents */
        if(documentsFlag) {
            try {
                addDocuments(namedocumentPath, filtersdocumentPath, objectsdocumentPath);
            }
            catch(SolrServerException e) {
                System.err.println("SolrServerException: Unable to connect to Solr Server");
                System.exit(1);
            }
            catch(IOException e) {
                System.err.println("IOException: Unable to parse responses");
                System.exit(1);
            }
        }
        else {
            solr.setParser(new XMLResponseParser());
            Gson gson= new GsonBuilder().setPrettyPrinting().create();

            if(filtersPath != null) {
                String[] filtersArr = filtersPath.split(",");
                for(String filter : filtersArr) {
                    doQuery(requestPath, queryPath, fieldsPath, filter, gson);
                }
            }
        }
    }
}
