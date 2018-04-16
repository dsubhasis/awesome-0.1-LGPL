package edu.sdsc.milou.awesome.DataFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.neo4j.cypher.internal.compiler.v2_3.pipes.matching.NodeIdentifier;
import org.w3c.dom.NodeList;

import edu.sdsc.milou.awesome.DataFrameObject.EdgeTable;
import edu.sdsc.milou.awesome.DataFrameObject.EdgeTableEntry;
import edu.sdsc.milou.awesome.DataFrameObject.GraphNodeList;
import edu.sdsc.milou.awesome.DataFrameObject.NodeProperty;

public class GraphCreate {

	NodeProperty nodeProp;
	String NodeName;

	String createNodeStart;

	/**
	 * @return the nodeInclude
	 */
	public List<String> getNodeInclude() {
		return nodeInclude;
	}

	/**
	 * @return the edgeEspression
	 */
	public List<String> getEdgeEspression() {
		return edgeEspression;
	}

	String mathcNode;
	String createedge;
	String createNodeEnd;
	String createNodeSet;
	private List<String> nodeInclude;
	private List<String> edgeEspression;

	/**
	 * 
	 */
	public GraphCreate() {
		nodeInclude = new ArrayList<String>();
		edgeEspression = new ArrayList<String>();
	}

	public void setNode(List<String> edgeId, EdgeTable globalEdgeTable, Map<String, GraphNodeList> globalNodeList) {

		String uid = RandomStringUtils.randomAlphabetic(8);
		createNodeStart = " ";
		createNodeEnd = " ";
		createNodeSet = " ";
		List duplicate = new ArrayList();

		for (String edge : edgeId) {
			uid = RandomStringUtils.randomAlphabetic(8);
			EdgeTableEntry ete = globalEdgeTable.getRow(edge);
			// globalEdgeTable.deleteRow(edge);
			Double lattitude = (double) 0;
			List matchList = new ArrayList();
			List Statement = new ArrayList();
			// edgeInclude.add(edge);

			String startnode = ete.getStartNodeId().toString();
			String endNode = ete.getEndNodeId().toString();
			String edgeLink = ete.getRetationType();
			List<String> edgeChain = new ArrayList();
			// this.findLinkChain(edgeChain, globalNodeList, globalEdgeTable,
			// ete);
			// globalEdgeTable.deleteRow(edge);
			if (ete.getStartNodeProperty().getProperty().get("type").equals("user")
					& !duplicate.contains(ete.getStartNodeId())) {
				// if(duplicate.contains(o))
				createNodeStart = "MERGE ( " + uid + ":user {" + "uid : " + ete.getStartNodeId() + ", "
						+ "follower_count :" + ete.getStartNodeProperty().getProperty().get("follower_count") + ", "
						+  "screen_name :" + "\""+ ete.getStartNodeProperty().getProperty().get("screen_name") + "\""+", "
						+ "friendcount: " + ete.getStartNodeProperty().getProperty().get("friends_count") + " } ) "
						+ "ON CREATE SET " + " " + uid + ".created = "
						+ ete.getStartNodeProperty().getProperty().get("created") + " SET " + " " + uid + ".lastused = "
						+ ete.getStartNodeProperty().getProperty().get("lasteused");
				// System.out.println(createNodeStart);
				duplicate.add(ete.getStartNodeId());
				nodeInclude.add(createNodeStart);
				uid = RandomStringUtils.randomAlphabetic(8);
			}
			if (ete.getStartNodeProperty().getProperty().get("type").equals("tweet")
					& !duplicate.contains(ete.getStartNodeId())) {

				uid = RandomStringUtils.randomAlphabetic(8);
				createNodeStart = " MERGE ( " + uid + ":tweet {" + "tid: " + ete.getStartNodeId() + ", " + "text :" 
						+ "\""+ ete.getEndNodeProperty().getProperty().get("text") + "\"" + ", " + "lang :"
						+ "\""+ ete.getStartNodeProperty().getProperty().get("lang") + "\"" + ", " + "latitude : "
						+ lattitude + " } ) ON CREATE SET  " + uid + ".timestamp = "
						+ ete.getStartNodeProperty().getProperty().get("created") + " SET " + uid + ".lastused = "
						+ ete.getStartNodeProperty().getProperty().get("lasteused");
				duplicate.add(ete.getStartNodeId());
				nodeInclude.add(createNodeStart);
				// System.out.println(createNodeStart);
			}
			if (ete.getStartNodeProperty().getProperty().get("type").equals("hahstag")
					& !duplicate.contains(ete.getStartNodeId())) {

				uid = RandomStringUtils.randomAlphabetic(8);

				createNodeStart = " MERGE ( " + uid + ":hashtag {" + "name :" + ete.getEndNodeId()
						+ " } )  ON CREATE SET  " + " " + uid + ".created = "
						+ ete.getStartNodeProperty().getProperty().get("created") + " SET " + uid + ".lastused = "
						+ ete.getStartNodeProperty().getProperty().get("lasteused");
				duplicate.add(ete.getStartNodeId());
				nodeInclude.add(createNodeStart);
				System.out.println(createNodeStart);
			}
			uid = RandomStringUtils.randomAlphabetic(8);
			try {
				if (ete.getEndNodeProperty().getProperty().get("type").equals("user")
						& !duplicate.contains(ete.getEndNodeId())) {
					uid = RandomStringUtils.randomAlphabetic(8);
					createNodeStart = " MERGE ( " + uid + ":user {" + "uid : " + ete.getEndNodeId() + ", "
							+ "follower_count :" + ete.getEndNodeProperty().getProperty().get("follower_count") + ", "
							+ "screen_name :" + "\""+ ete.getStartNodeProperty().getProperty().get("screen_name") + "\""+ "," + "friendcount: " + ete.getEndNodeProperty().getProperty().get("friends_count") + " } ) "
							+ "ON CREATE SET " + " " + uid + ".created = "
							+ ete.getEndNodeProperty().getProperty().get("created") + " SET " + " " + uid + ".lastused = "
							+ ete.getEndNodeProperty().getProperty().get("lasteused");
					duplicate.add(ete.getEndNodeId());
					nodeInclude.add(createNodeStart);
					// System.out.println(createNodeStart);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			uid = RandomStringUtils.randomAlphabetic(8);
		
			try {
				if (ete.getEndNodeProperty().getProperty().get("type").equals("tweet")
						& !duplicate.contains(ete.getEndNodeId())) {

					createNodeStart = " MERGE ( " + uid + ":tweet {" + "tid: " + ete.getEndNodeId() + ", " +  "text :" 
							+ "\""+ ete.getEndNodeProperty().getProperty().get("text") + "\"" + ", " + "lang :" 
							+ "\""+ ete.getEndNodeProperty().getProperty().get("lang") + "\"" + ", " + "latitude : " + lattitude
							+ " } ) ON CREATE SET  " + uid + ".timestamp = "
							+ ete.getEndNodeProperty().getProperty().get("created") + " SET " + uid + ".lastused = "
							+ ete.getEndNodeProperty().getProperty().get("lasteused");
					duplicate.add(ete.getEndNodeId());
					nodeInclude.add(createNodeStart);
					// System.out.println(createNodeStart);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			try {
				if (ete.getEndNodeProperty().getProperty().get("type").equals("hashtag")
						& !duplicate.contains(ete.getEndNodeId())) {

					uid = RandomStringUtils.randomAlphabetic(8);
					createNodeStart = " MERGE ( " + uid + ":hashtag {" + "name: \"" + ete.getEndNodeId()
							+ "\" } )  ON CREATE SET  " + " " + uid + ".created = "
							+ ete.getEndNodeProperty().getProperty().get("created") + " SET " + uid + ".lastused = "
							+ ete.getEndNodeProperty().getProperty().get("lasteused");
					duplicate.add(ete.getEndNodeId());
					nodeInclude.add(createNodeStart);
					// System.out.println(createNodeStart);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getLocalizedMessage());
			}

		}
		for (String edge : edgeId) {

			if (globalEdgeTable.containsRow(edge)) {
				// System.out.println(globalEdgeTable.getEdgeTable().size());
				Map nodeList = new HashMap();
				Map IdList = new HashMap();

				EdgeTableEntry ete = globalEdgeTable.getRow(edge);
				globalEdgeTable.deleteRow(edge);
				Double lattitude = (double) 0;
				List matchList = new ArrayList();
				List Statement = new ArrayList();
				// edgeInclude.add(edge);

				List<String> edgeChain = new ArrayList();
				List<String> edgeProperty = new ArrayList<String>();
				// this.findLinkChain(edgeChain, edgeProperty, globalNodeList,
				// globalEdgeTable, ete);
				String startNode = ete.getStartNodeId();
				String endNode = ete.getEndNodeId();
				String edgeLink = ete.getRetationType();
				String expression = "";
				String eid = "chainCenter";

				String suid = null;
				String euid = null;

				if (!IdList.containsKey(startNode)) {
					suid = RandomStringUtils.randomAlphabetic(8);
					IdList.put(startNode, suid);
				} else if (IdList.containsKey(startNode)) {
					suid = (String) IdList.get(startNode);
				}
				if (!IdList.containsKey(endNode)) {
					euid = RandomStringUtils.randomAlphabetic(8);
					IdList.put(endNode, euid);
				} else if (IdList.containsKey(endNode)) {
					euid = (String) IdList.get(endNode);
				}
				String seed = "(" + suid + ")-" + "[ " + eid + " : " + edgeLink + "]-" + "(" + euid + ")";
				String startType = (String) ete.getStartNodeProperty().getProperty().get("type");
				String endType = (String) ete.getEndNodeProperty().getProperty().get("type");

				String endId = edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(endType);
				String startId = edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(startType);

				String mstartNode = edu.sdsc.milou.awesome.DataFrame.Util.formatString(startType, startNode);
				String mendNode = edu.sdsc.milou.awesome.DataFrame.Util.formatString(endType, endNode);

				String starMquery = "MATCH (" + suid + ":" + startType + "{" + startId + " : " + mstartNode + "})";
				String endMquery = "MATCH (" + euid + ":" + endType + "{" + endId + " : " + mendNode + "})";

				if (!nodeList.containsKey(startNode)) {
					nodeList.put(startNode, starMquery);
				}
				if (!nodeList.containsKey(endNode)) {
					nodeList.put(endNode, endMquery);
				}
				String expressionEnd = this.expressionStartNode(ete, startNode, endNode, globalNodeList, globalEdgeTable,
						expression, edgeLink, nodeList, startType, endType, IdList, true);
				String expressionStart = this.expressionEndNode(ete, startNode, endNode, globalNodeList, globalEdgeTable, "",
						edgeLink, nodeList, startType, endType, IdList, true);
				expression = expressionStart + seed + expressionEnd;
				String matchExp = "";
				Iterator it = nodeList.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					uid = RandomStringUtils.randomAlphabetic(8);
					matchExp = matchExp + pair.getValue() + " ";
				}
				// System.out.println(matchExp + " MERGE " + expression);
				String finalEdgeExp = matchExp + " MERGE " + expression;
				//System.out.println(finalEdgeExp);
				edgeEspression.add(finalEdgeExp);
			}
		}

	}

	/**
	 * 
	 */

	public String replacelast(String line, String replace) {
		StringBuilder b = new StringBuilder(line);
		b.replace(line.lastIndexOf(","), line.lastIndexOf(",") + 1, replace);
		line = b.toString();
		return line;
	}

	public String findLinkChain(List<String> expressionList, List<String> edgeProperty,
			Map<String, GraphNodeList> globalNodeList, EdgeTable globalEdgeTable, EdgeTableEntry ete) {
		String startnode = ete.getStartNodeId().toString();
		String endNode = ete.getEndNodeId().toString();
		String edgeLink = ete.getRetationType();
		String eid = RandomStringUtils.randomAlphabetic(8);

		String expression = " (" + startnode + ")-" + "[ " + eid + " : " + edgeLink + "]-" + "(" + endNode + ")";
		String setProperty = " " + eid + ".lastused = timestamp(), " + eid + ".created = timestamp()";
		expressionList.add(expression);
		List<String> startNodeEdgeList = globalNodeList.get(startnode).getAllEdges(startnode);
		boolean findedge = true;
		for (String stEdge : startNodeEdgeList) {
			if (globalEdgeTable.CheckEntry(stEdge)) {
				EdgeTableEntry etafirst = globalEdgeTable.getRow(stEdge);
				globalEdgeTable.deleteRow(stEdge);
				this.findLinkChain(expressionList, edgeProperty, globalNodeList, globalEdgeTable, etafirst);
				// globalEdgeTable.deleteRow(stEdge);
			}
		}
		List<String> endqNodeEdgeList = globalNodeList.get(endNode).getAllEdges(endNode);
		for (String endEdge : endqNodeEdgeList) {
			if (globalEdgeTable.CheckEntry(endEdge)) {
				EdgeTableEntry etafirst = globalEdgeTable.getRow(endEdge);
				globalEdgeTable.deleteRow(endEdge);
				this.findLinkChain(expressionList, edgeProperty, globalNodeList, globalEdgeTable, etafirst);
			}
		}
		return expression;
	}

	public String expressionStartNode( EdgeTableEntry etafirst, String startNode, String endNode, Map<String, GraphNodeList> globalNodeList,
			EdgeTable globalEdgeTable, String expression, String edgeLink, Map nodeList, String startType, String endType, Map IdList, boolean startExp) {

		if (nodeList.size() < edu.sdsc.milou.awesome.DataFrame.Constatnt.nodeMatchConstrain) {

			

			boolean flag = true;
			String eid = RandomStringUtils.randomAlphabetic(8);

			String suid = null;
			String euid = null;

			if (!IdList.containsKey(startNode)) {
				suid = RandomStringUtils.randomAlphabetic(8);
				IdList.put(startNode, suid);
			} else if (IdList.containsKey(startNode)) {
				suid = (String) IdList.get(startNode);
			}
			if (!IdList.containsKey(endNode)) {
				euid = RandomStringUtils.randomAlphabetic(8);
				IdList.put(endNode, euid);
			} else if (IdList.containsKey(endNode)) {
				euid = (String) IdList.get(endNode);
			}

			if (startExp) {
				// expression = expression + "(" + startNode + ")-" + "[ " + eid
				// + "
				// : " + edgeLink + "]-" + "(" + endNode
				// + ")";
			} else if (!startExp) {
				expression = expression + "-" + "[ " + eid + " : " + edgeLink + "]-" + "(" + euid + ")";
			}

			//String endType = (String) etafirst.getEndNodeProperty().getProperty().get("type");

			String endId = edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(endType);
			String mendNode = edu.sdsc.milou.awesome.DataFrame.Util.formatString(endType, endNode);

			String endMquery = "MATCH (" + euid + ":" + endType + "{" + endId + " : " + mendNode + "})";
			if (!nodeList.containsKey(endNode)) {
				nodeList.put(endNode, endMquery);
			}

			// System.out.println(" \n" + expression);
			List<String> NodeEdgeList = globalNodeList.get(endNode).getAllEdges(endNode);
			int i = 0;
			if (!NodeEdgeList.isEmpty()) {
				flag = true;
				int size = NodeEdgeList.size();
				while (flag & i < (size - 1)) {
					// System.out.println(" \n" + i + "\t" + size);
					if (globalEdgeTable.CheckEntry(NodeEdgeList.get(i))) {
						etafirst = globalEdgeTable.getRow(NodeEdgeList.get(i));
						globalEdgeTable.deleteRow(NodeEdgeList.get(i));
						flag = false;
					} else {

						i++;
						flag = true;
					}
				}
			}
			String lstartNode = null;
			String lendNode = null;
			if (!flag) {
				if (endNode.equals(etafirst.getStartNodeId())) {
					lstartNode = etafirst.getStartNodeId();
					lendNode = etafirst.getEndNodeId();
					endType = (String) etafirst.getEndNodeProperty().getProperty().get("type");
					startType = (String) etafirst.getStartNodeProperty().getProperty().get("type");

					if (!IdList.containsKey(lstartNode)) {
						suid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lstartNode, suid);
					} else if (IdList.containsKey(lstartNode)) {
						suid = (String) IdList.get(lstartNode);
					}
					if (!IdList.containsKey(lendNode)) {
						euid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lendNode, euid);
					} else if (IdList.containsKey(lendNode)) {
						euid = (String) IdList.get(lendNode);
					}

					/*
					 * String startType = (String)
					 * etafirst.getStartNodeProperty().getProperty().get("type")
					 * ;
					 * 
					 * String endType = (String)
					 * etafirst.getEndNodeProperty().getProperty().get("type");
					 * 
					 * String endId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * endType); String startId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * startType);
					 * 
					 * String mstartNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * startType, lstartNode); String mendNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * endType, lendNode);
					 * 
					 * String starMquery = "MATCH (" + suid + ":" + startType +
					 * "{" + startId + " : " + mstartNode + "})"; String
					 * endMquery = "MATCH (" + euid + ":" + endType + "{" +
					 * endId + " : " + mendNode + "})";
					 * 
					 * if (!nodeList.containsKey(lstartNode)) {
					 * nodeList.put(lstartNode, starMquery); } if
					 * (!nodeList.containsKey(lendNode)) {
					 * nodeList.put(lendNode, endMquery); }
					 */
				} else if (endNode.equals(etafirst.getEndNodeId())) {

					lendNode = etafirst.getStartNodeId();
					lstartNode = etafirst.getEndNodeId();
					startType = (String) etafirst.getEndNodeProperty().getProperty().get("type");
					endType = (String) etafirst.getStartNodeProperty().getProperty().get("type");


					if (!IdList.containsKey(lstartNode)) {
						suid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lstartNode, suid);
					} else if (IdList.containsKey(lstartNode)) {
						suid = (String) IdList.get(lstartNode);
					}
					if (!IdList.containsKey(lendNode)) {
						euid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lendNode, euid);
					} else if (IdList.containsKey(lendNode)) {
						euid = (String) IdList.get(lendNode);
					}

					/*
					 * String endType = (String)
					 * etafirst.getStartNodeProperty().getProperty().get("type")
					 * ; String startType = (String)
					 * etafirst.getEndNodeProperty().getProperty().get("type");
					 * 
					 * String endId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * endType); String startId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * startType);
					 * 
					 * String mstartNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * startType, lstartNode); String mendNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * endType, lendNode);
					 * 
					 * String starMquery = "MATCH (" + suid + ":" + startType +
					 * "{" + startId + " : " + mstartNode + "})"; String
					 * endMquery = "MATCH (" + euid + ":" + endType + "{" +
					 * endId + " : " + mendNode + "})";
					 * 
					 * if (!nodeList.containsKey(lstartNode)) {
					 * nodeList.put(lstartNode, starMquery); } if
					 * (!nodeList.containsKey(lendNode)) {
					 * nodeList.put(lendNode, endMquery); }
					 */
				}

				String ledgeLink = etafirst.getRetationType();
				if (ledgeLink.equals("hashtagusedIn")) {
					// System.out.println("Gotc");
				}
				String localExp = this.expressionStartNode(etafirst, lstartNode, lendNode, globalNodeList, globalEdgeTable,
						expression, ledgeLink, nodeList, startType, endType, IdList , false);
				// System.out.println(localExp + "\t" );
				expression = localExp;

			}
		}

		return expression;
	}

	public String expressionEndNode(EdgeTableEntry etafirst, String startNode, String endNode, Map<String, GraphNodeList> globalNodeList,
			EdgeTable globalEdgeTable, String expression, String edgeLink, Map nodeList, String startType, String endType, Map IdList, boolean startExp) {
		if (nodeList.size() < edu.sdsc.milou.awesome.DataFrame.Constatnt.nodeMatchConstrain) {
			//EdgeTableEntry etafirst = null;
			boolean flag = true;
			String eid = RandomStringUtils.randomAlphabetic(8);
			String suid = null;
			String euid = null;

			if (!IdList.containsKey(startNode)) {
				suid = RandomStringUtils.randomAlphabetic(8);
				IdList.put(startNode, suid);
			} else if (IdList.containsKey(startNode)) {
				suid = (String) IdList.get(startNode);
			}
			if (!IdList.containsKey(endNode)) {
				euid = RandomStringUtils.randomAlphabetic(8);
				IdList.put(endNode, euid);
			} else if (IdList.containsKey(endNode)) {
				euid = (String) IdList.get(endNode);
			}

			if (startExp) {
				// expression = "(" + startNode + ")-" + "[ " + eid + " : " +
				// edgeLink + "]-" + "(" + endNode + ")"
				// + expression;
			} else if (!startExp) {
				expression = "(" + suid + ")-" + "[ " + eid + " : " + edgeLink + "]-" + expression;
			}
			//String startType = (String) etafirst.getStartNodeProperty().getProperty().get("type");
			String startId = edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(startType);
			String mstartNode = edu.sdsc.milou.awesome.DataFrame.Util.formatString(startType, startNode);
			String starMquery = "MATCH (" + suid + ":" + startType + "{" + startId + " : " + mstartNode + "})";

			if (!nodeList.containsKey(startNode)) {
				nodeList.put(startNode, starMquery);
			}
			List<String> NodeEdgeList = globalNodeList.get(startNode).getAllEdges(startNode);
			int i = 0;
			if (!NodeEdgeList.isEmpty()) {
				flag = true;
				int size = NodeEdgeList.size();
				while (flag & i < (size)) {
					// System.out.println(" \n" + i + "\t" + size);
					// System.out.println(NodeEdgeList.get(i));
					String index = NodeEdgeList.get(i);
					if (globalEdgeTable.CheckEntry(index)) {
						etafirst = globalEdgeTable.getRow(NodeEdgeList.get(i));
						globalEdgeTable.deleteRow(NodeEdgeList.get(i));
						flag = false;
					} else {
						i++;
						flag = true;
					}
				}
			}
			String lstartNode = null;
			String lendNode = null;
			if (!flag) {
				if (startNode.equals(etafirst.getEndNodeId())) {
					lstartNode = etafirst.getStartNodeId();
					lendNode = etafirst.getEndNodeId();
					endType = (String) etafirst.getEndNodeProperty().getProperty().get("type");
					startType = (String) etafirst.getStartNodeProperty().getProperty().get("type");

					if (!IdList.containsKey(lstartNode)) {
						suid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lstartNode, suid);
					} else if (IdList.containsKey(lstartNode)) {
						suid = (String) IdList.get(lstartNode);
					}
					if (!IdList.containsKey(lendNode)) {
						euid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lendNode, euid);
					} else if (IdList.containsKey(lendNode)) {
						euid = (String) IdList.get(lendNode);
					}

					/*
					 * String startType = (String)
					 * etafirst.getStartNodeProperty().getProperty().get("type")
					 * ;
					 * 
					 * String endType = (String)
					 * etafirst.getEndNodeProperty().getProperty().get("type");
					 * 
					 * String endId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * endType);
					 * 
					 * String mendNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * endType, lendNode);
					 * 
					 * String startId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * startType);
					 * 
					 * String mstartNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * startType, lstartNode); String mendNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * endType, lendNode);
					 * 
					 * String starMquery = "MATCH (" + suid + ":" + startType +
					 * "{" + startId + " : " + mstartNode + "})"; String
					 * endMquery = "MATCH (" + euid + ":" + endType + "{" +
					 * endId + " : " + mendNode + "})";
					 * 
					 * if (!nodeList.containsKey(lendNode)) {
					 * nodeList.put(lendNode, endMquery); }
					 */
				} else if (startNode.equals(etafirst.getStartNodeId())) {

					lendNode = etafirst.getStartNodeId();
					lstartNode = etafirst.getEndNodeId();
					startType = (String) etafirst.getEndNodeProperty().getProperty().get("type");
					endType = (String) etafirst.getStartNodeProperty().getProperty().get("type");

					if (!IdList.containsKey(lstartNode)) {
						suid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lstartNode, suid);
					} else if (IdList.containsKey(lstartNode)) {
						suid = (String) IdList.get(lstartNode);
					}
					if (!IdList.containsKey(lendNode)) {
						euid = RandomStringUtils.randomAlphabetic(8);
						IdList.put(lendNode, euid);
					} else if (IdList.containsKey(lendNode)) {
						euid = (String) IdList.get(lendNode);
					}

					/*
					 * String endType = (String)
					 * etafirst.getStartNodeProperty().getProperty().get("type")
					 * ; String startType = (String)
					 * etafirst.getEndNodeProperty().getProperty().get("type");
					 * 
					 * String endId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * endType); String startId =
					 * edu.sdsc.milou.awesome.DataFrame.Util.findPrimaryKey(
					 * startType);
					 * 
					 * String mstartNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * startType, lstartNode); String mendNode =
					 * edu.sdsc.milou.awesome.DataFrame.Util.formatString(
					 * endType, lendNode);
					 * 
					 * String starMquery = "MATCH (" + suid + ":" + startType +
					 * "{" + startId + " : " + mstartNode + "})"; String
					 * endMquery = "MATCH (" + euid + ":" + endType + "{" +
					 * endId + " : " + mendNode + "})";
					 * 
					 * if (!nodeList.containsKey(lstartNode)) {
					 * nodeList.put(lstartNode, starMquery); } if
					 * (!nodeList.containsKey(lendNode)) {
					 * nodeList.put(lendNode, endMquery); }
					 */
				}
				String ledgeLink = etafirst.getRetationType();

				String localExp = this.expressionEndNode(etafirst, lstartNode, lendNode, globalNodeList, globalEdgeTable,
						expression, ledgeLink, nodeList, startType, endType, IdList, false);
				// System.out.println(localExp + "\t");
				expression = localExp;

			}
		}
		return expression;
	}
}
