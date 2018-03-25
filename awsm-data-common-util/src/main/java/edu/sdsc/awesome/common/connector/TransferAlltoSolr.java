package edu.sdsc.awesome.common.connector;

import edu.sdsc.awesome.connector.solr.ConnectSolrStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferAlltoSolr {


    public static void main(String[] args) {

        System.out.println("Hello World");

        PostGresQuery pgq = new PostGresQuery();

        int i = 2005325;
        int j = 2015324;

        while(i< 3000000) {
            String query = "SELECT * FROM usnewspaper" + " where "+ "id > "+i+" AND id < "+ j+" ;";
            System.out.println(query);
            Map resultMap = pgq.pgsqlPassThrough(query);

            Map mappingList = new HashMap();

            mappingList.put("news", "newsContent");
            mappingList.put("title", "newsHeadline");
            mappingList.put("id", "newsId");
           // mappingList.put("author", "authors");
            mappingList.put("src", "source");
            mappingList.put("url", "newsurl");
            mappingList.put("publishdate", "newsDate");

            ConnectSolrStore css = new ConnectSolrStore(mappingList, "http://10.128.6.148:8983/solr/usnewspaper/");
            css.documentPush((List<Map>) resultMap.get("value"));
            i = j;
            j = j + 10000;

        }


    }

}
