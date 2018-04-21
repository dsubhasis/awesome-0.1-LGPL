/**
 * 
 */
package edu.sdsc.milou.awesome.DataFrameObject;

/**
 * @author subhasis
 *
 */
public class EdgeTableEntry {
	/**
	 * @return the startNodeId
	 */
	public String getStartNodeId() {
		return startNodeId;
	}
	/**
	 * @param startNodeId the startNodeId to set
	 */
	public void setStartNodeId(String startNodeId) {
		this.startNodeId = startNodeId;
	}
	/**
	 * @return the endNodeId
	 */
	public String getEndNodeId() {
		return endNodeId;
	}
	/**
	 * @param endNodeId the endNodeId to set
	 */
	public void setEndNodeId(String endNodeId) {
		this.endNodeId = endNodeId;
	}
	/**
	 * @param edgeProperty
	 * @param startNodeId
	 * @param startNodeProperty
	 * @param retationType
	 * @param endNodeId
	 * @param endNodeProperty
	 */
	public void insert(EdgeProperty edgeProperty, String startNodeId, NodeProperty startNodeProperty,
			String retationType, String endNodeId, NodeProperty endNodeProperty) {
		this.edgeProperty = edgeProperty;
		this.startNodeId = startNodeId;
		this.startNodeProperty = startNodeProperty;
		this.retationType = retationType;
		this.endNodeId = endNodeId;
		this.endNodeProperty = endNodeProperty;
	}
	private EdgeProperty edgeProperty;
	private String startNodeId;
	private NodeProperty startNodeProperty;
	private String retationType;
	private String endNodeId;
	private NodeProperty endNodeProperty;
	/**
	 * @return the edgeProperty
	 */
	public EdgeProperty getEdgeProperty() {
		return edgeProperty;
	}
	/**
	 * @param edgeProperty the edgeProperty to set
	 */
	public void setEdgeProperty(EdgeProperty edgeProperty) {
		this.edgeProperty = edgeProperty;
	}
		/**
	 * @return the startNodeProperty
	 */
	public NodeProperty getStartNodeProperty() {
		return startNodeProperty;
	}
	/**
	 * @param startNodeProperty the startNodeProperty to set
	 */
	public void setStartNodeProperty(NodeProperty startNodeProperty) {
		this.startNodeProperty = startNodeProperty;
	}
	/**
	 * @return the retationType
	 */
	public String getRetationType() {
		return retationType;
	}
	/**
	 * @param retationType the retationType to set
	 */
	public void setRetationType(String retationType) {
		this.retationType = retationType;
	}
	
	/**
	 * @return the endNodeProperty
	 */
	public NodeProperty getEndNodeProperty() {
		return endNodeProperty;
	}
	/**
	 * @param endNodeProperty the endNodeProperty to set
	 */
	public void setEndNodeProperty(NodeProperty endNodeProperty) {
		this.endNodeProperty = endNodeProperty;
	}
}
