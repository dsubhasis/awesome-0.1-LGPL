/**
 * 
 */
package edu.sdsc.milou.awesome.DataFrameObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author subhasis
 *
 */
public class NodeProperty {
	
	
	
	private String type;
	private Map property;
	
	
	 
	
	/**
	 * @param property
	 */
	public NodeProperty() {
		property = new HashMap();
	}

	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

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


	public Map addProperty(String name, String value){
		property.put(name, value);
		return property;
	}

	public Map addProperty(String name, double value){
		property.put(name, value);
		return property;
	}

	public Map addProperty(String name, long value){
		property.put(name, value);
		return property;
	}
	
public Map unpateNodePropery(String name , String value){
	property.remove(name);
	property.put(name, value);
	return property;
	}
public Map unpateNodePropery(String name , long value){
	property.remove(name);
	property.put(name, value);
	return property;
	}
	
	

}
