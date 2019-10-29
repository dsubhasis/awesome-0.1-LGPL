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


