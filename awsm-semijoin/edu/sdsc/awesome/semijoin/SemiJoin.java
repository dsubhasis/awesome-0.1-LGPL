package edu.sdsc.awesome.semijoin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;

public class SemiJoin {
	static final String DRIVER = "org.postgresql.Driver";
  static final String PREFIX = "jdbc:postgresql://";
  
	private SolrClient client = null;
	
	public SemiJoin(String solrBaseUrl) {
		this.client = this.getSolrClient(solrBaseUrl);
	}
	
	public void search(String searchQuery, String field, String collection, String joinQuery) {
		final Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("q", searchQuery);
		queryParamMap.put("fl", field);
		QueryResponse response;
		try {
			response = client.query(collection, new MapSolrParams(queryParamMap));
			final SolrDocumentList documents = response.getResults();
			ArrayList<String> reSet = new ArrayList<>();
			for(SolrDocument document : documents) {
				reSet.add("'" + document.get(field) + "'");
			}
			Connection conn = this.getConn("10.128.36.22:5432","postgres", "XJrJgb8g");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(joinQuery + "(" + String.join(",", reSet)
			+ ")");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			st.close();
		} catch (SolrServerException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	private SolrClient getSolrClient(String baseUrl) {
		return new HttpSolrClient.Builder(baseUrl)
		    .withConnectionTimeout(10000)
		    .withSocketTimeout(60000)
		    .build();
	}
	
	/**
   * Connect to specified PostgreSQL database
   * @param DBName - database name
   * @param ConnectString - database ip and port
   * @param uid - user name
   * @param password - password
   * @return a valid connection
   */
  private Connection getConn(String ConnectString, String uid,
      String password) {
    Connection conn = null;
    try {
      Class.forName(DRIVER); //Load PostgreSQL JDBC driver
      /* Enter user and password information */
      Properties props = new Properties();
      props.setProperty("user", uid);
      props.setProperty("password", password);
      conn = DriverManager.getConnection(PREFIX + ConnectString , props);
    } catch (ClassNotFoundException | SQLException e) {
    }
    return conn;
  }
	
	public static void main(String[] args) {
		SemiJoin sj = new SemiJoin("http://10.128.36.16:8983/solr");
		sj.search("segmentedtext:��ͨ��", "filename", "courtcaseraw", 
				"SELECT parties FROM parsed_data WHERE filename in ");
	}
}