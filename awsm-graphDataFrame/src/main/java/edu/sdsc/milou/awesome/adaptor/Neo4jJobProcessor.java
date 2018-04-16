package edu.sdsc.milou.awesome.adaptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.neo4j.driver.internal.spi.Logging;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.config.DriverConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sdsc.awesome.milou.SimpleFileLog;
import edu.sdsc.milou.awesome.DataFrame.ZabbixCall;

class neo4jJobProcessor implements Runnable {

	Logger logentry = LoggerFactory.getLogger(neo4jJobProcessor.class);
	Logging th ;

	private List<String> query;
	private Map<String, List<String>> querySet;
	private Map<String, Object> params;
	private Integer size;
	private Driver localDr;
	private String jobId;

	public neo4jJobProcessor(String rjobID, Map<String,List<String>> rquery, Map<String, Object> rparams) {
		// TODO Auto-generated constructor stub
		this.querySet = rquery;
		this.params = rparams;
		this.size = rquery.size();
		this.jobId = rjobID;
	}

	public void run() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();

		SimpleFileLog sfl2 = new SimpleFileLog();
		query = querySet.get("node");
		try {
			this.runQuery(query, params);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//query = querySet.get("edge");
		try {
			this.runQuery(querySet.get("edge"), params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		try {
			sfl2.updateFile("time.csv",Long.toString(elapsedTime)+",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	public Driver BotlConnection(String username, String password, String url) {
		Driver dr = null;
		/*DriverConfiguration configuration = new Configuration().driverConfiguration().setConnectionPoolSize(3500)
				.setCredentials(username, password).setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
				.setURI(url);*/




		boolean hasPassword = password != null && !password.isEmpty();
		AuthToken token = hasPassword ? AuthTokens.basic(username, password) : AuthTokens.none();
		dr = GraphDatabase.driver(url, token,
				Config.build().withMaxSessions(edu.sdsc.milou.awesome.DataFrame.Constatnt.n4jpoolSize).withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig());
		return dr;
	}

	public void runQuery(List<String> query, Map param) throws IOException{





		Driver localDr = BotlConnection(edu.sdsc.milou.awesome.DataFrame.Constatnt.neo4jUser, edu.sdsc.milou.awesome.DataFrame.Constatnt.neo4jPassowrd,
				edu.sdsc.milou.awesome.DataFrame.Constatnt.neo4jServer);

		boolean flag = false;
		Runtime rt = Runtime.getRuntime();
		rt.gc();
		long usedMemory = rt.totalMemory() - rt.freeMemory();
		float safeAlloc = rt.maxMemory();
		SimpleFileLog sfl2 = new SimpleFileLog();
		try {
			sfl2.updateFile("used-memory.csv",Long.toString(usedMemory)+",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		try (Session session = localDr.session())
		{


			for(int i = 0; i < query.size(); i++){
				try ( Transaction tx = session.beginTransaction() )
				{
					tx.run(query.get(i), param);
					tx.success();

				}catch (Exception e) {
					logentry.error(e.getMessage());
					System.out.println(query.get(i));
					System.out.println(e.getLocalizedMessage());
				}

			}
		}catch (Exception e) {
			while(!flag){

			     rt = Runtime.getRuntime();
				rt.gc();
				usedMemory = rt.totalMemory() - rt.freeMemory();
				safeAlloc = rt.maxMemory();


					System.out.println(e.getLocalizedMessage());
					SimpleFileLog sfl = new SimpleFileLog();
					String filename = RandomStringUtils.randomAlphabetic(8)+".cypher";
					for(int i = 0; i < query.size(); i++){
						sfl.updateFile(filename, query.get(i)+",");

					}

					flag = true;
				

			}
		}

		localDr.close();
		ZabbixCall zc = new ZabbixCall();
		zc.getData();
		long usedMemory_end = rt.totalMemory() - rt.freeMemory();
		
		
		int nbThreads =  Thread.getAllStackTraces().keySet().size();
		long avgMem =  usedMemory_end/nbThreads;
		
		
		SimpleFileLog sfl3 = new SimpleFileLog();
		try {
			sfl3.updateFile("avg-mem.csv",Long.toString(avgMem)+","+nbThreads+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		


		/*SimpleFileLog sfl = new SimpleFileLog();
		try {
			sfl.updateFile("size.csv", query.size()+",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}

