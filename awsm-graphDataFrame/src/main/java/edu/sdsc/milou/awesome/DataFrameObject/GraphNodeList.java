/**
 * 
 */
package edu.sdsc.milou.awesome.DataFrameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author subhasis
 *
 */
public class GraphNodeList {
	private String nodeId;
	private List edgeIdList;
	// private List<Map<String, String>> listNodeId;
	private Map<String, Map> NodeIndex;

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the edgeId
	 */

	/**
	 * @param edgeId
	 *            the edgeId to set
	 */

	public void insertNode(String snodeId, String edge, String endNodeId) {
		if (!NodeIndex.containsKey(endNodeId)) {

			Map nodeEdge = new HashMap();
			nodeEdge.put("node", snodeId);
			edgeIdList = new ArrayList();
			edgeIdList.add(edge);
			nodeEdge.put("edge", edgeIdList);

			NodeIndex.put(endNodeId, nodeEdge);
		} else {

			Map nodeEdge = NodeIndex.get(endNodeId);

			if (!nodeEdge.containsKey(snodeId)) {
				nodeEdge.put("node", snodeId);
				edgeIdList = new ArrayList();
				edgeIdList.add(edge);
				nodeEdge.put("edge", edgeIdList);
				for(int i = 0; i < edgeIdList.size(); i++)
				{
				
				//System.out.print(edgeIdList.get(i));
				//System.out.println("end List");
				}

			} else {
				List tempEdgeList = (List) nodeEdge.get("edge");
				tempEdgeList.add(edge);
				nodeEdge.put("edge", tempEdgeList);
			}

		}
	}

	
	

	/**
	 * @param nodeId
	 * @param edgeIdList
	 * @param nodeIndex
	 */
	public GraphNodeList(String nodeId, List edgeIdList, Map<String, Map> nodeIndex) {
		this.nodeId = nodeId;
		this.edgeIdList = edgeIdList;
		NodeIndex = nodeIndex;
	}

	public boolean contains(String nodeId, String adjNode) {
		List<Map<String, String>> listNodeId = new ArrayList();

		boolean value = NodeIndex.containsKey(adjNode);
		return value;
	}

	/*
	 * public String getConnetingEdge(String iNode, String cNode) {
	 * 
	 * List<Map<String, String>> listNodeId = new ArrayList(); String edge =
	 * null; listNodeId = NodeIndex.get(iNode); Map<String, String> nodeEdge;
	 * for (int i = 0; i < listNodeId.size(); i++) {
	 * 
	 * nodeEdge = listNodeId.get(i); if (nodeEdge.containsKey(cNode)) {
	 * 
	 * edge = nodeEdge.get(cNode); return edge; } }
	 * 
	 * return edge; }
	 */

	public List getAllConnectingNodes(String startNode) {
		List<Map<String, String>> listNodeId = new ArrayList();
		List<String> nodes = new ArrayList();
		for (int i = 0; i < listNodeId.size(); i++) {
			nodes.add(listNodeId.get(i).get("nodes"));
		}

		return nodes;

	}

	public List getAllEdges(String startNode) {
		List<Map<String, String>> listNodeId = new ArrayList();
		List<String> edges = new ArrayList();
		edges = (List<String>) NodeIndex.get(startNode).get("edge");
		
		
		return edges;
	}

	/**
	 * 
	 */
	public GraphNodeList() {
		NodeIndex = new HashMap();

	}

}
