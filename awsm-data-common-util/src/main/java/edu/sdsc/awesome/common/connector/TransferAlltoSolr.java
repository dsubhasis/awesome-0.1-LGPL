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
