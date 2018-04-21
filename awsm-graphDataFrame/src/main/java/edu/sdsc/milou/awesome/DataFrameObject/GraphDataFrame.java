/**
 * 
 */
package edu.sdsc.milou.awesome.DataFrameObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author subhasis
 *
 */
public class GraphDataFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public GraphDataFrame() {

		// globalEdgeTable = new EdgeTable();
		// globalNodeList = new HashMap<String, GraphNodeList>();
		time = new Timestamp(System.currentTimeMillis());
		totalEdge = 0;
		totalNode = 0;
	}

	// private Map <String, GraphNodeList> globalNodeList;

	private Integer totalNode;
	private Integer totalEdge;
	private Timestamp time;

	// private EdgeTable globalEdgeTable;

	public boolean insertRowBatch(List<Map> row, Map<String, GraphNodeList> globalNodeList, EdgeTable globalEdgeTable,
			List<String> edgeList) {

		for (Map<?, ?> data : row) {
			String entry = (String) data.get("edgeid");
			String idsNode = data.get("firstNode").toString();
			String ideNode = data.get("secondNode").toString();
			NodeProperty sNode = (NodeProperty) data.get("firstNodeProperty");
			NodeProperty eNode = (NodeProperty) data.get("secondNodeProperty");
			EdgeProperty eprop = (EdgeProperty) data.get("edgeProperty");
			String nameEdge = (String) data.get("edgeName");
			
			if (!globalEdgeTable.CheckEntry(entry)) {

				if (globalNodeList.containsKey(ideNode) & globalNodeList.containsKey(idsNode)) {

					if (!globalNodeList.get(ideNode).contains(ideNode, idsNode)
							| !globalNodeList.get(idsNode).contains(idsNode, ideNode)) {

						this.addRow(entry, sNode, idsNode, eNode, ideNode, nameEdge, eprop, globalNodeList,
								globalEdgeTable);
						edgeList.add(entry);
						GraphNodeList lgraphNode = globalNodeList.get(ideNode);
						lgraphNode.insertNode(idsNode, entry, ideNode);
						globalNodeList.put(ideNode, lgraphNode);

						GraphNodeList sgraphNode = globalNodeList.get(idsNode);
						sgraphNode.insertNode(ideNode, entry, idsNode);
						globalNodeList.put(idsNode, sgraphNode);
					} else if (globalNodeList.get(ideNode).contains(ideNode, idsNode)
							& globalNodeList.get(idsNode).contains(idsNode, ideNode)) {
						
						
						
                        if(!idsNode.equals(ideNode)){
						System.out.println("Collision : " + idsNode + " " + ideNode);
                        }


					}

				} else if (!globalNodeList.containsKey(ideNode) & !globalNodeList.containsKey(idsNode)) {
					this.addRow(entry, sNode, idsNode, eNode, ideNode, nameEdge, eprop, globalNodeList,
							globalEdgeTable);
					edgeList.add(entry);
					GraphNodeList sgarphNode = new GraphNodeList();
					sgarphNode.insertNode(idsNode, entry, ideNode);
					globalNodeList.put(ideNode, sgarphNode);

					GraphNodeList lgraphNode = new GraphNodeList();
					lgraphNode.insertNode(ideNode, entry, idsNode);
					globalNodeList.put(idsNode, lgraphNode);

				} else if (!globalNodeList.containsKey(idsNode) & globalNodeList.containsKey(ideNode)) {

					this.addRow(entry, sNode, idsNode, eNode, ideNode, nameEdge, eprop, globalNodeList,
							globalEdgeTable);
					edgeList.add(entry);
					GraphNodeList sgarphNode = new GraphNodeList();
					sgarphNode.insertNode(ideNode, entry, idsNode);
					globalNodeList.put(idsNode, sgarphNode);

					GraphNodeList lgraphNode = globalNodeList.get(ideNode);
					lgraphNode.insertNode(idsNode, entry, idsNode);
					//globalNodeList.put(ideNode, lgraphNode);

				} else if (!globalNodeList.containsKey(ideNode) & globalNodeList.containsKey(idsNode)) {

					this.addRow(entry, sNode, idsNode, eNode, ideNode, nameEdge, eprop, globalNodeList,
							globalEdgeTable);
					
					edgeList.add(entry);
					
					
					GraphNodeList sgarphNode = new GraphNodeList();
					sgarphNode.insertNode(idsNode, entry, ideNode);
					globalNodeList.put(ideNode, sgarphNode);

					GraphNodeList lgraphNode = globalNodeList.get(idsNode);
					lgraphNode.insertNode(ideNode, entry, idsNode);
					//globalNodeList.put(idsNode, lgraphNode);

				}
			} else {
				//System.out.println("Error : Please recover ");
			}
		}
		return false;
	}

	private void addRow(String edgeId, NodeProperty startNodeProperty, String startNodeID, NodeProperty endNodeProperty,
			String endNodeId, String edgerelation, EdgeProperty egdeProperty, Map<String, GraphNodeList> globalNodeList,
			EdgeTable globalEdgeTable) {
		if (!globalNodeList.containsKey(edgeId)) {
			EdgeTableEntry entry = new EdgeTableEntry();
			entry.insert(egdeProperty, startNodeID, startNodeProperty, edgerelation, endNodeId, endNodeProperty);
			globalEdgeTable.insetValue(edgeId, entry);
			//this.updateNodeList(startNodeID, endNodeId, edgeId, globalNodeList, globalEdgeTable);
		} else {
			//System.out.println("Duplicate Entry " + edgeId);
		}
	}

	/*public void updateNodeList(String sNodeId, String endNodeId, String edgeId,
			Map<String, GraphNodeList> globalNodeList, EdgeTable globalEdgeTable) {

		if (!globalNodeList.containsKey(sNodeId)) {
			GraphNodeList endNode = new GraphNodeList();
			endNode.insertNode(sNodeId, edgeId, endNodeId);
		} else if (!globalNodeList.containsKey(endNodeId)) {
			GraphNodeList startNode = new GraphNodeList();
			
			startNode.setEdgeId(edgeId);
			startNode.insertNode(endNodeId, edgeId, sNodeId);
			globalNodeList.put(endNodeId, startNode);
		} else if (globalNodeList.containsKey(sNodeId)) {

			GraphNodeList startNode = globalNodeList.get(sNodeId);
			startNode.setEdgeId(edgeId);
			startNode.insertNode(endNodeId, edgeId, sNodeId);

		} else if (globalNodeList.containsKey(endNodeId)) {

			GraphNodeList startNode = globalNodeList.get(endNodeId);
			startNode.setEdgeId(edgeId);
			startNode.insertNode(sNodeId, edgeId, endNodeId);

		}

	}*/

	public void mergeNode() {

	}

	public void dumpDataFrame() {

	}

	public void restoreDataFrame() {

	}

	public Map<?, ?> updateNodeList(Map<?, ?> gnodelist, long StrtNode, long endNode) {

		return gnodelist;

	}

	public NodeProperty createNodeProperty(NodeProperty nodeP, String name, String value) {
		nodeP.addProperty(name, value);
		return nodeP;
	}

	public NodeProperty createNodeProperty(NodeProperty nodeP, String name, double value) {
		nodeP.addProperty(name, value);
		return nodeP;
	}

	public NodeProperty createNodeProperty(NodeProperty nodeP, String name, long value) {
		nodeP.addProperty(name, value);
		return nodeP;
	}

	public EdgeProperty createEdgeProperty(EdgeProperty edgeP, String name, String value) {
		edgeP.addProperty(name, value);
		return edgeP;
	}

	public EdgeProperty createEdgeProperty(EdgeProperty edgeP, String name, long value) {
		edgeP.addProperty(name, value);
		return edgeP;
	}

	public EdgeProperty createEdgeProperty(EdgeProperty edgeP, String name, Integer value) {
		edgeP.addProperty(name, value);
		return edgeP;
	}

}
