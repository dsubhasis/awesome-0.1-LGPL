package edu.sdsc.awesome.semijoin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
  
	private String solrBaseUrl = null;
	private String pgHost = null;
	private String username = null; //Postgres username
	private String password = null; //Postgres password
	
	public SemiJoin(String solrBaseUrl, String pgHost, String username, 
			String password) {
		this.solrBaseUrl = solrBaseUrl;
		this.pgHost = pgHost;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Execute Solr query first, then perform a semi-join with Postgres.
	 * @param solrQuery - a standard solr query
	 * @param field - field for result set (the field for semi-join)
	 * @param collection - solr collection
	 * @param joinQuery - a postgres query joining using keys
	 * @param limit - batch size for
	 */
	public void solrToPg(String solrQuery, String field, String collection, 
			String joinQuery, int limit) {
		final Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("q", solrQuery);
		queryParamMap.put("fl", field);
		QueryResponse response;
		try {
			SolrClient client = getSolrClient(this.solrBaseUrl);
			response = client.query(collection, new MapSolrParams(queryParamMap));
			final SolrDocumentList documents = response.getResults();
			ArrayList<String> reSet = new ArrayList<>();
			//TODO: consider divide solr result into segments and/or write to a file
			for(SolrDocument document : documents) {
				reSet.add("'" + document.get(field) + "'");
			}
			Connection conn = this.getConn(this.pgHost, this.username, this.password);
			Statement st = conn.createStatement();
			st.setFetchSize(limit);
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
	
	public void pgToSolr(String pgQuery, String field, String collection, 
			String joinQuery, String fl, int limit) {
		Connection conn = this.getConn(this.pgHost, this.username, this.password);
		Statement st;
		try {
			st = conn.createStatement();
			st.setFetchSize(limit);
			ResultSet rs = st.executeQuery(pgQuery);
			LinkedList<String> keys = new LinkedList<>();
			while(rs.next()) {
				keys.add(rs.getString(1));
			}
			final Map<String, String> queryParamMap = new HashMap<String, String>();
			StringBuilder sb = new StringBuilder();
			sb.append(field);
			//TODO: handle large results
			for(String key : keys) {
				sb.append(key + ' ');
			}
			queryParamMap.put("q", sb.toString());
			queryParamMap.put("fl", fl);
			SolrClient client = getSolrClient(this.solrBaseUrl);
			QueryResponse response = client.query(collection, 
					new MapSolrParams(queryParamMap));
			final SolrDocumentList documents = response.getResults();
			for(SolrDocument doc : documents) {
				System.out.println(doc.get(fl));
			}
		} catch (SQLException | SolrServerException | IOException e) {
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
		SemiJoin sj = new SemiJoin("http://10.128.36.16:8983/solr", 
				"10.128.36.22:5432", args[0], args[1]);
		//sj.solrToPg("segmentedtext:南通市", "filename", "courtcaseraw", 
				//"SELECT parties FROM parsed_data WHERE filename in ", 1000);
		sj.pgToSolr("SELECT filename FROM parsed_data WHERE type = '民事裁定书' "
				+ "LIMIT 5", "filename", "courtcaseraw", "filename:",
				"segmentedtext", 1000);
	}
}