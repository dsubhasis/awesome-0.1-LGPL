package edu.sdsc.awesome.awesomeWeb.awsmnode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetStatData implements GetNeo4jData {


    @Override
    public String getStats() {
        return null;
    }

    public BufferedReader getDataFromRemote(String inputString, String host) throws IOException {


        URL url = null;
        String urlString = "http://"+host+"/RESTfulExample/json/product/post";
        try {
            url = new URL("http://localhost:8080/RESTfulExample/json/product/post");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setDoOutput(true);
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Content-Type", "application/json");

        String input = inputString;

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        return br;
    }
}
