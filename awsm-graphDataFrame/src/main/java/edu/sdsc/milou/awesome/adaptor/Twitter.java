/**
 * 
 */
package edu.sdsc.milou.awesome.adaptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sdsc.awesome.milou.SimpleFileLog;
import edu.sdsc.milou.awesome.DataFrameObject.EdgeTable;
import edu.sdsc.milou.awesome.DataFrameObject.GraphNodeList;
import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author subhasis
 *
 */
public class Twitter {

	private List tweetList;
	private Integer listSize;
	private Integer outList;
	private EdgeTable globalEdgeTable;
	private Map<String, GraphNodeList> globalNodeList;
	private List<String> edgeList;
	

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Options options = new Options();
		Option input = new Option("b", "input", true, "input file path");
		input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);
        final Integer tempBuff = Integer.parseInt(commandLine.getOptionValue("b"));
        
		Twitter tw = new Twitter();
		tw.getTweet(tempBuff);

	}

	/**
	 * 
	 */
	public Twitter() {

		tweetList = new ArrayList();
		globalEdgeTable = new EdgeTable();
		globalNodeList = new HashMap<String, GraphNodeList>();
		edgeList = new ArrayList();

	}

	public void getTweet(Integer TweetListSize) {

		final Logger logentry = LoggerFactory.getLogger(Twitter.class);
		// GraphConnector graphNode = new GraphConnector();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		// cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("");
		cb.setOAuthConsumerSecret("");
		cb.setOAuthAccessToken("");
		cb.setOAuthAccessTokenSecret("");
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {
			final long startTime = System.currentTimeMillis();
			public void onStatus(Status st) {
				
				System.out.println(" Text Found : " + st.getText());
				// TODO Auto-generated method stub
				if (st.getHashtagEntities().length != 0) {
					//System.out.println(" Text Found : " + st.getText());
					HashtagEntity[] hte = st.getHashtagEntities();
					User us = st.getUser();
					//System.out.println(" User: " + us.getName());
					tweetList.add(st);
					
					if(tweetList.size() > TweetListSize){
						
						Runtime rt = Runtime.getRuntime();
						rt.gc();
						long usedMemory = rt.totalMemory() - rt.freeMemory();
						
						SimpleFileLog sfl = new SimpleFileLog();
						try {
							sfl.updateFile("memory.csv", Long.toString(usedMemory)+",");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						ModelTransferInit mdt = new ModelTransferInit(tweetList, globalEdgeTable, globalNodeList, edgeList);
						tweetList.clear();
						final long duration = System.currentTimeMillis() - startTime;
						try {
							sfl.updateFile("timedatarate.csv", Long.toString(usedMemory)+",");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Thread t1 = new Thread(mdt);
						t1.start();
					}
					/*if (tweetList.size() > 3) {
						ModelTransfer md = new ModelTransfer(tweetList, globalEdgeTable, globalNodeList,
								edgeList);
						
						md.extractData();
						tweetList.clear();
						System.out.println(edgeList.size());
						if (edgeList.size() > 5) {
							GraphCreate gc = new GraphCreate();
							gc.setNode(edgeList, globalEdgeTable, globalNodeList);
							List s = gc.getEdgeEspression();
							List t = gc.getNodeInclude();
							edgeList.clear();
						}
					}*/
					
				}
			}
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
			}
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
			}
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
			}
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
			}
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
			}
		};
		FilterQuery fq = new FilterQuery();
		
		String keywords[] = { "donald trump", "hillary clinton", "Neil Gorsuch", "Nate Silver","Erika Moen", "Jared Kushner", "Shailagh Murray","Kristie Canegallo",
				"Steve Bannon", "John McCain", "Bob Casey", "Dianne Feinstein", "Eric Brakey", "Dick Durbin", "Mitch McConnell", "John Cornyn", "John Thune", "John Barrasso",
				"Roy Blunt", "Cory Gardner", "Mike Lee", "Mike Pence", "Orrin Hatch", "Chuck Schumer", "Dick Durbin", "Patty Murray", "Debbie Stabenow", "Mark Warner", "Elizabeth Warren",
				"Amy Klobuchar", "Bernie Sanders", "Joe Manchin", "Tammy Baldwin", "Chris Van Hollen", "Jeff Merkley", "Patrick Leahy", "Richard Shelby", "Luther Strange", 
				"Lisa Murkowski", "Dan Sullivan", "Jeff Flake", "John Boozman", "Tom Cotton", "Dianne Feinstein", "Kamala Harris", "Michael Bennet", "Cory Gardner", 
				"Richard Blumenthal", "Tom Carper", "Chris Coons", "Bill Nelson", "Marco Rubio", "Johnny Isakson", "David Perdue", "Brian Schatz", "Mazie Hirono", "Mike Crapo", 
				"Jim Risch", "Dick Durbin", "Tammy Duckworth", "Joe Donnelly", "Joni Ernst", "Pat Roberts", "Jerry Moran", "Mitch McConnell", "Rand Paul", "Bill Cassidy", 
				"John N. Kennedy", "Susan Collins", "Angus King", "Ben Cardin", "Chris Van Hollen", "Elizabeth Warren", "Ed Markey", "Debbie Stabenow", "Gary Peters",
				"Amy Klobuchar", "Al Franken", "Thad Cochran", "Roger Wicker", "Claire McCaskill", "Roy Blunt", "Jon Tester", "Steve Daines", "Deb Fischer", "Joseph Dunford", "Paul J. Selva", 
				"Thomas D. Waldhauser", "Joseph L. Votel", "Curtis M. Scaparrotti", "Lori J. Robinson", "Harry B. Harris, Jr.", " Kurt W. Tidd", "Raymond A. Thomas III", "John E. Hyten", " Darren W. McDew"
				, "Rex Tillerson", "Steve Mnuchin", "James Mattis", "Jeff Sessions", "Ryan Zinke", "Mike Young", "Wilbur Ross", "Ed Hugler", "Ben Carson", "Elaine Chao",
				"Rick Perry", "Betsy DeVos", "David Shulkin", "John F. Kelly", "Reince Priebus", "Stephen Vaughn", "Dan Coats", "Nikki Haley", "Mick Mulvaney", "Mike Pompeo",
				"Scott Pruitt", "Linda McMahon", "James Comey", "Devin Nunes", "Paul Ryan", "Adam Schiff" };
		

		//String keywords[] = { "Football cup","FA Cup", "The Football Association","Danny Welbeck", "housewives", "de oliveira" };
		// logentry.info("Fetching Data from Tweet with fllowing filters : ");
		System.out.println("Fetching Data from The Twiter Stream by using following filters: : ");

		System.out.println(Arrays.toString(keywords));

		fq.track(keywords);
		//fq.language(new String[]{""});
		twitterStream.addListener(listener);

		twitterStream.filter(fq);
		// twitterStream.sample();

	}

}
