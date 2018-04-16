/**
 * 
 */
package edu.sdsc.milou.awesome.DataFrameObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author subhasis
 *
 */
public class ItemList {

	private Map<String, GraphNodeList> nodeList;
	private Map<String, GraphEdgeList> edgeList;

	/**
	 * 
	 */
	public ItemList() {
		
		nodeList = new HashMap();
		edgeList = new HashMap();
		
	}
	public void insertNode(String nodeId, GraphNodeList gnl)
	{
		
		nodeList.put(nodeId, gnl);
	}
	public void insertEdge(String edgeId, GraphEdgeList gnl)
	{
		
		edgeList.put(edgeId, gnl);
	}
	
	public GraphNodeList getNodeList(String nodeId)
	{
		return nodeList.get(nodeId);
	}
	public GraphEdgeList getEdgeList(String edgeId)
	{
		return edgeList.get(edgeId);
	}
	
	
}
