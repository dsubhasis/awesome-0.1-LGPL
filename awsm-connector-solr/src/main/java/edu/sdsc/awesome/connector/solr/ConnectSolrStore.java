package edu.sdsc.awesome.connector.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConnectSolrStore {


    private List<SolrInputDocument> ASMSorlDocumentList;
    private Map<String, String> schema;
    private List<Map> dataSet;
    private String serverConfig;



    public ConnectSolrStore(Map<String, String> schema,  String serverConfig) {
        this.schema = schema;
        this.dataSet = dataSet;
        this.serverConfig = serverConfig;

    }


    public void documentPush(List<Map> dataSet)
            {

        ASMSorlDocumentList = new ArrayList<SolrInputDocument>();
        SolrInputDocument sld;

        for (int i =0 ; i < dataSet.size(); i++)
        {

            sld = new SolrInputDocument();
            Map obj = dataSet.get(i);

            Iterator it = schema.entrySet().iterator();

            while(it.hasNext())
            {
                Map.Entry pair = (Map.Entry)it.next();

                String key = (String)pair.getKey();
                String value = (String)pair.getValue();

                sld.addField(value, obj.get(key));

            }

            ASMSorlDocumentList.add(sld);


        }
        this.solrUpdateCommitDocument(serverConfig,ASMSorlDocumentList);

    }
    void solrUpdateCommitDocument (String solrServer, List<SolrInputDocument> solrDocument)  {


            SolrClient solr = new HttpSolrClient.Builder(solrServer).build();
            try {
                solr.add(solrDocument);
                solr.commit();



solr.close();

            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




    }



}


