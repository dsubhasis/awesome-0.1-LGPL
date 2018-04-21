/**
 * 
 */
package edu.sdsc.milou.awesome.adaptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sleepycat.je.utilint.Timestamp;

import edu.sdsc.awesome.milou.SimpleFileLog;
import edu.sdsc.milou.awesome.DataFrame.GraphCreate;
import edu.sdsc.milou.awesome.DataFrameObject.EdgeTable;
import edu.sdsc.milou.awesome.DataFrameObject.GraphNodeList;

/**
 * @author subhasis
 *
 */
public class ModelTransferInit implements Runnable {

	/**
	 * @param args
	 */
	private List tweetList;
	private EdgeTable globalEdgeTable;
	private Map<String, GraphNodeList> globalNodeList;
	private List<String> edgeList;
	private List<String> nodeCypher;
	private List<String> edgeCypher;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();
		ModelTransfer md = new ModelTransfer(tweetList, globalEdgeTable, globalNodeList,
				edgeList);
		SimpleFileLog sfl = new SimpleFileLog();
		
		md.extractData();
			GraphCreate gc = new GraphCreate();
			gc.setNode(edgeList, globalEdgeTable, globalNodeList);
			nodeCypher.addAll(gc.getNodeInclude());
			edgeCypher.addAll(gc.getEdgeEspression());
			Map query = new HashMap();
			query.put("node", nodeCypher);
			query.put("edge", edgeCypher);
			
			try {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Integer size = nodeCypher.size() + edgeCypher.size();
				sfl.updateFile("dataRaw.csv", timestamp.toString()+","+size+"\n");
				//sfl.updateFile("nodeDetails.csv", timestamp.toString()+","+nodeCypher.size()+" , "+" "+edgeCypher.size()+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map rparams = new HashMap<>();
			String rjobID = UUID.randomUUID().toString();
			neo4jJobProcessor njb = new neo4jJobProcessor(rjobID, query, rparams);
			Thread th = new Thread(njb);
			njb.run();
			
			
			
			
			
			
			
		
		
	}

	/**
	 * 
	 */
	

	/**
	 * @return the nodeCypher
	 */
	public List<String> getNodeCypher() {
		return nodeCypher;
	}

	/**
	 * @param tweetList
	 * @param globalEdgeTable
	 * @param globalNodeList
	 * @param edgeList
	 */
	public ModelTransferInit(List rtweetList, EdgeTable globalEdgeTable, Map<String, GraphNodeList> globalNodeList,
			List<String> redgeList) {
		tweetList = new ArrayList();
		tweetList.addAll(rtweetList);
		
		
		this.globalEdgeTable = globalEdgeTable;
		this.globalNodeList = globalNodeList;
		edgeList = new ArrayList<String>();
		nodeCypher = new ArrayList();
		edgeCypher = new ArrayList();
		
		//edgeList.addAll(redgeList);
		
	}

	/**
	 * @return the edgeCypher
	 */
	public List<String> getEdgeCypher() {
		return edgeCypher;
	}

}
