package edu.sdsc.milou.awesome;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;


public class DataRateCalculation {

	public DataRateCalculation() {
		// TODO Auto-generated constructor stub
		
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String query = "";
		
		
		Map param = new HashMap();
		
		
		Driver localDr = BotlConnection(edu.sdsc.milou.awesome.DataFrame.Constatnt.neo4jUser, edu.sdsc.milou.awesome.DataFrame.Constatnt.neo4jPassowrd,
				edu.sdsc.milou.awesome.DataFrame.Constatnt.neo4jServer);
		try (Session session = localDr.session())
		{
			try ( Transaction tx = session.beginTransaction() )
			{
				tx.run(query, param);
				
			}
		}

	}
	public static Driver BotlConnection(String username, String password, String url) {
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

}
