package edu.sdsc.awesome.awesomeWeb.sdsc.newspaper;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetLuceneSearchString {


    private JsonObject searchResults;

    public JsonObject getSearchResults() {
        return searchResults;
    }

    private String searchstring;
    private String author;
    private String news;
    private String date;
    private String config;


    public GetLuceneSearchString(String searchstring, String author, String news, String date, String config) {
        this.searchstring = searchstring;
        this.author = author;
        this.news = news;
        this.date = date;
        this.config = config;


        try {
            this.parse(author, news, date, config);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getSearchquery(){


        String query = null;

        return query;
    }
    public void parse(String author, String news, String date, String config)
            throws ParseException{

        Analyzer analyzer = new StandardAnalyzer();
        //String [] a = {"title", "mindate", "maxdate", "author"};
        QueryParser parsedTitle = new QueryParser("title", analyzer);
        QueryParser parsedAuthor = new QueryParser("author", analyzer);
        QueryParser parsedNews = new QueryParser("news", analyzer);
        QueryParser parsedDate = new QueryParser("date", analyzer);

        String[] newsClause = parsedNews.parse(news).toString("news").split(" ");
        String[] authorClause = parsedAuthor.parse(news).toString("author").split(" ");
        String[] titleClause = parsedTitle.parse(news).toString("title").split(" ");
        String[] dateClause = parsedDate.parse(news).toString("date").split(" ");
        Map newsdata = parseLucene(newsClause);
        Map authorData = parseLucene(authorClause);
        Map titleData = parseLucene(titleClause);
        Map datedata = parseLucene(dateClause);



    }



    Map<String, List> parseLucene(String[] data){

        Map output = new HashMap();
        List positive = new ArrayList();
        List negative = new ArrayList();
        List orvalue = new ArrayList();


        for(int i =0 ; i < data.length; i++)
        {
            boolean flag = false;
            if(data[i].startsWith("+"))
            {
                positive.add(data[i].replace("+", ""));
                flag = true;
            }
            if(data[i].startsWith("-"))
            {
                negative.add(data[i].replace("-", ""));
                flag = true;
            }
            if(!flag) {
                orvalue.add(data[i]);
            }
        }



        output.put("AND", positive);
        output.put("NOT", negative);
        output.put("OR", orvalue);

        return output;

    }

    String PGSQLnewsQuery(Map<String, List> output)
    {
        String query = "";

        List<String> positive =  output.get("AND");
        boolean count = true;

        for(int i = 0 ; i < positive.size(); i++)
        {
            if(count){

                query =  query + " "+positive.get(i);
                count = false;

            }
            else{
                query = query+" AND "+positive.get(i);
            }
        }

        List<String> negative = output.get("NOT");

        for(int i = 0 ; i < negative.size(); i++)
        {
            if(count){

                query =  negative.get(i);
                count = false;

            }
            else{
                query = query+" AND "+negative.get(i);
            }
        }



return query;

    }
}
