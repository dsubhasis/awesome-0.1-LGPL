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
public class EdgeTable {

	/**
	 * @return the edgeTable
	 */
	public Map<String, EdgeTableEntry> getEdgeTable() {
		return edgeTable;
	}

	/**
	 * @param edgeTable the edgeTable to set
	 */
	public void setEdgeTable(Map<String, EdgeTableEntry> edgeTable) {
		this.edgeTable = edgeTable;
	}

	private Map<String, EdgeTableEntry> edgeTable;
	

	public void deleteRow(String edgeId) {
		edgeTable.remove(edgeId);
	}

	public EdgeTableEntry getRow(String edgeId) {
		return edgeTable.get(edgeId);
	}
	public boolean containsRow(String edgeId){
		
		return edgeTable.containsKey(edgeId);
	}

	/**
	 * 
	 */
	public EdgeTable() {
		edgeTable = new HashMap<String, EdgeTableEntry>();
	}

	public void insetValue(String edgeId, EdgeTableEntry et) {
		edgeTable.put( edgeId,	 et);
		
	}
	
	public boolean CheckEntry(String edgeId ){
		
		
		if(edgeTable.containsKey(edgeId)){
			return true;
			
		}
		else{
			return false;
		}
	}


	/**
	 * 
	 */

}
