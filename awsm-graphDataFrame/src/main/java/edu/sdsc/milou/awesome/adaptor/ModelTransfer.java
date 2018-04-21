/**
 * 
 */
package edu.sdsc.milou.awesome.adaptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.sdsc.awesome.milou.adaptor.AsterixDBTweetObject;
import edu.sdsc.milou.awesome.DataFrameObject.EdgeProperty;
import edu.sdsc.milou.awesome.DataFrameObject.EdgeTable;
import edu.sdsc.milou.awesome.DataFrameObject.GraphDataFrame;
import edu.sdsc.milou.awesome.DataFrameObject.GraphNodeList;
import edu.sdsc.milou.awesome.DataFrameObject.NodeProperty;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserMentionEntity;

/**
 * @author subhasis
 *
 */
public class ModelTransfer {

	private List<Status> tweetList;
	private User us;
	private Status st;
	private HashMap<Integer, String> QueryQueue;
	private GraphDataFrame dataFrame;
	private NodeProperty nodeP;
	private List rowEntry;
	private EdgeTable globalEdgeTable;
	private Map<String, GraphNodeList> globalNodeList;
	private List<String> edgeList;

	/**
	 * @param tweetList
	 * @param us
	 */
	public ModelTransfer(List rtweetList, EdgeTable lglobalEdgeTable, Map<String, GraphNodeList> lglobalNodeList , List<String> ledgeList) {
		tweetList = rtweetList;
		dataFrame = new GraphDataFrame();
		rowEntry = new ArrayList();
		this.globalEdgeTable = lglobalEdgeTable;
		this.globalNodeList = lglobalNodeList;
		this.edgeList = ledgeList;
	}
	public List extractData() {
		for (Status tweet : tweetList) {
			st = tweet;
			us = st.getUser();
			UUID uniqueID = UUID.randomUUID();
			EdgeProperty edgeP = new EdgeProperty();
			dataFrame.createEdgeProperty(edgeP, "time", st.getCreatedAt().getTime() );
			dataFrame.createEdgeProperty(edgeP, "lastused", st.getCreatedAt().getTime() );


			NodeProperty UsernodeP = new NodeProperty();

			dataFrame.createNodeProperty(UsernodeP, "uid", us.getId());
			dataFrame.createNodeProperty(UsernodeP, "count", 0);
			dataFrame.createNodeProperty(UsernodeP, "created", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(UsernodeP, "screen_name", us.getScreenName());
			dataFrame.createNodeProperty(UsernodeP, "lasteused", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(UsernodeP, "follower_count", ((Integer) us.getFollowersCount()));
			dataFrame.createNodeProperty(UsernodeP, "friends_count", ((Integer) us.getFriendsCount()));
			dataFrame.createNodeProperty(UsernodeP, "favourits_count", ((Integer) us.getFavouritesCount()));
			dataFrame.createNodeProperty(UsernodeP, "type", "user");
			

			NodeProperty tweetnodeP = new NodeProperty();

			dataFrame.createNodeProperty(tweetnodeP, "tid", st.getId());
			dataFrame.createNodeProperty(tweetnodeP, "count", 0);
			dataFrame.createNodeProperty(tweetnodeP, "created", st.getCreatedAt().getTime());
			dataFrame.createNodeProperty(tweetnodeP, "lasteused", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(tweetnodeP, "text", st.getText().replaceAll("\"", " "));
			if(st.getGeoLocation()!=null)
			{
				dataFrame.createNodeProperty(tweetnodeP, "latitude", st.getGeoLocation().getLatitude());
				dataFrame.createNodeProperty(tweetnodeP, "longitude", st.getGeoLocation().getLongitude());
			}
			dataFrame.createNodeProperty(tweetnodeP, "lang", st.getLang());
			dataFrame.createNodeProperty(tweetnodeP, "orphan", 1);
			dataFrame.createNodeProperty(tweetnodeP, "type", "tweet");
			Map row = this.createRowelement(us.getId(), UsernodeP, st.getId(), tweetnodeP, "TweetedBy", edgeP);
			rowEntry.add(row);

			HashtagEntity[] hte = st.getHashtagEntities();

			NodeProperty HashTagNode = new NodeProperty();
			if(st.getHashtagEntities().length > 0)
			{
			dataFrame.createNodeProperty(HashTagNode, "created", st.getCreatedAt().getTime());
			dataFrame.createNodeProperty(HashTagNode, "count", 0);
			dataFrame.createNodeProperty(HashTagNode, "lasteused", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(HashTagNode, "type", "hashtag");
			NodeProperty connectingHahsTag = new NodeProperty();
			connectingHahsTag = HashTagNode;
			
			
			for (int i = 0; i < hte.length; i++) {

				dataFrame.createNodeProperty(HashTagNode, "name", hte[i].getText());
				row = this.createRowelement(us.getId(), UsernodeP, hte[i].getText(), HashTagNode, "HashTagUsedBy");
				rowEntry.add(row);
				row = this.createRowelement(st.getId(), tweetnodeP, hte[i].getText(), HashTagNode, "HashTagUsedIn");
				rowEntry.add(row);

				for (int j = i + 1; j < hte.length; j++) {

					connectingHahsTag.addProperty("name", hte[j].getText());

					row = this.createRowelement(hte[i].getText(), HashTagNode, hte[j].getText(), connectingHahsTag,
							"hashTagComenation");
					rowEntry.add(row);

				}

			}
			
			}
			NodeProperty retweetNode = new NodeProperty();
			try {
				if(!st.getRetweetedStatus().getText().isEmpty()){
				dataFrame.createNodeProperty(retweetNode, "created", st.getRetweetedStatus().getCreatedAt().getTime());
				dataFrame.createNodeProperty(retweetNode, "lasteused", st.getRetweetedStatus().getCreatedAt().getTime());
				dataFrame.createNodeProperty(retweetNode, "tid", st.getRetweetedStatus().getId());
				dataFrame.createNodeProperty(retweetNode, "count", st.getRetweetedStatus().getRetweetCount());
				dataFrame.createNodeProperty(retweetNode, "orphan", 1);
				dataFrame.createNodeProperty(retweetNode, "text", st.getRetweetedStatus().getText().replaceAll("\"", " "));
				dataFrame.createNodeProperty(retweetNode, "lang", st.getRetweetedStatus().getLang());
				dataFrame.createNodeProperty(retweetNode, "type", "tweet");
				row = this.createRowelement(us.getId(), UsernodeP, st.getRetweetedStatus().getId(), retweetNode , "Retweeted", edgeP);
				rowEntry.add(row);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Retweeted Status : "+e.getMessage());
				
			}

			NodeProperty mentionedNode = new NodeProperty();
			UserMentionEntity[] umt = st.getUserMentionEntities();
			if(st.getUserMentionEntities().length > 0){

			dataFrame.createNodeProperty(mentionedNode, "created", st.getCreatedAt().getTime());
			
			dataFrame.createNodeProperty(mentionedNode, "lasteused", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(mentionedNode, "type", "user");
			}

			NodeProperty inReplyTweetNode = new NodeProperty();
			if(st.getInReplyToStatusId() > 0){

			dataFrame.createNodeProperty(inReplyTweetNode, "created", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(inReplyTweetNode, "lasteused", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(inReplyTweetNode, "tid", st.getInReplyToStatusId());
			dataFrame.createNodeProperty(inReplyTweetNode, "text", " ");
			dataFrame.createNodeProperty(inReplyTweetNode, "count", 0);
			dataFrame.createNodeProperty(inReplyTweetNode, "orphan", 1);
			dataFrame.createNodeProperty(inReplyTweetNode, "lang", st.getLang());
			dataFrame.createNodeProperty(inReplyTweetNode, "type", "tweet");
			}

			NodeProperty inReplyUserNode = new NodeProperty();
			
			if(st.getInReplyToUserId() > 0){

			dataFrame.createNodeProperty(inReplyUserNode, "created", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(inReplyUserNode, "lasteused", us.getCreatedAt().getTime());
			dataFrame.createNodeProperty(inReplyUserNode, "uid", st.getInReplyToUserId());
			dataFrame.createNodeProperty(inReplyUserNode, "screen_name", st.getInReplyToScreenName());
			dataFrame.createNodeProperty(inReplyUserNode, "follower_count", 0);
			dataFrame.createNodeProperty(inReplyUserNode, "friends_count", 0);
			dataFrame.createNodeProperty(inReplyUserNode, "favourits_count", 0);
			dataFrame.createNodeProperty(inReplyUserNode, "count", 0);
			dataFrame.createNodeProperty(inReplyUserNode, "orphan", 1);
			dataFrame.createNodeProperty(inReplyUserNode, "type", "user");
			}

			if(st.getInReplyToUserId() > 0)
			{
				
				row = this.createRowelement(us.getId(), UsernodeP, st.getInReplyToUserId(), inReplyUserNode , "InReplytoUser", edgeP);
				rowEntry.add(row);
				row = this.createRowelement(us.getId(), UsernodeP, st.getId(), tweetnodeP , "TweetedBy", edgeP);
				rowEntry.add(row);
			}	
				
			if(st.getInReplyToStatusId() > 0)
			{
               row = this.createRowelement(st.getId(), tweetnodeP, st.getInReplyToStatusId(), inReplyTweetNode , "InReplytoTweet", edgeP);
				
				rowEntry.add(row);
			}
				
			


			if(st.getUserMentionEntities() != null){
				for(int i = 0 ; i < umt.length ; i++){
					mentionedNode.addProperty("uid",  umt[i].getId());
					mentionedNode.addProperty("screen_name",  umt[i].getScreenName());
					if(umt[i].getScreenName().equals(us.getScreenName())){
						
					//System.out.println(us.getScreenName());
				}
					row = row = this.createRowelement(us.getId(), UsernodeP, umt[i].getId(), mentionedNode , "Mentioned", edgeP);
					rowEntry.add(row);

				}
			}
		}
		//System.out.println(" ");
		dataFrame.insertRowBatch(rowEntry, globalNodeList, globalEdgeTable, edgeList);
		return rowEntry;
	}

	public Map createRowelement(long firsthasttagName, NodeProperty firsthasttagProperty, long secondhasttagName,
			NodeProperty secondhasttahProperty, String edge, EdgeProperty edp) {

		Map row = new HashMap();
		UUID uniqueID = UUID.randomUUID();
		
		row.put("edgeid", uniqueID.toString());
		row.put("firstNode", firsthasttagName);
		row.put("firstNodeProperty", firsthasttagProperty);
		row.put("secondNode", secondhasttagName);
		row.put("secondNodeProperty", secondhasttahProperty);
		row.put("edgeName", edge);
		row.put("edgeProperty", edp);
		return row;

	}

	public Map createRowelement(String firsthasttagName, NodeProperty firsthasttagProperty, long secondhasttagName,
			NodeProperty secondhasttahProperty, String edge) {

		Map row = new HashMap();
		UUID uniqueID = UUID.randomUUID();

		row.put("edgeid", uniqueID.toString());

		row.put("firstNode", firsthasttagName);
		row.put("firstNodeProperty", firsthasttagProperty);
		row.put("secondNode", secondhasttagName);
		row.put("secondNodeProperty", secondhasttahProperty);
		row.put("edgeName", edge);

		return row;

	}

	public Map createRowelement(long firsthasttagName, NodeProperty firsthasttagProperty, String secondhasttagName,
			NodeProperty secondhasttahProperty, String edge) {

		Map row = new HashMap();
		UUID uniqueID = UUID.randomUUID();

		row.put("edgeid", uniqueID.toString());
		row.put("firstNode", firsthasttagName);
		row.put("firstNodeProperty", firsthasttagProperty);
		row.put("secondNode", secondhasttagName);
		row.put("secondNodeProperty", secondhasttahProperty);
		row.put("edgeName", edge);
		return row;

	}

	public Map createRowelement(String firsthasttagName, NodeProperty firsthasttagProperty, String secondhasttagName,
			NodeProperty secondhasttahProperty, String edge) {

		Map row = new HashMap();
		UUID uniqueID = UUID.randomUUID();

		row.put("edgeid", uniqueID.toString());
		row.put("firstNode", firsthasttagName);
		row.put("firstNodeProperty", firsthasttagProperty);
		row.put("secondNode", secondhasttagName);
		row.put("secondNodeProperty", secondhasttahProperty);
		row.put("edgeName", edge);

		return row;

	}
	


}
