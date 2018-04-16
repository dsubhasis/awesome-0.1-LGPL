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
public class EdgeProperty {
	
	
	/**
	 * @return the property
	 */
	public Map getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(Map property) {
		this.property = property;
	}

	private String edgeId;
	private Map property;
	/**
	 * 
	 */
	public EdgeProperty() {
		
		property = new HashMap();
	}
	/**
	 * @return the edgeId
	 */
	public String getEdgeId() {
		return edgeId;
	}
	/**
	 * @param edgeId the edgeId to set
	 */
	public void setEdgeId(String edgeId) {
		this.edgeId = edgeId;
	} 
	
public Map addProperty(String name, String value){
		
		property.put(name, value);
		return property;
		
	}

	public Map addProperty(String name, Integer value){
		
		property.put(name, value);
		return property;
		
	}

	public Map addProperty(String name, long value){
		
		property.put(name, value);
		return property;
		
	}
	
	
	
	
	

}
