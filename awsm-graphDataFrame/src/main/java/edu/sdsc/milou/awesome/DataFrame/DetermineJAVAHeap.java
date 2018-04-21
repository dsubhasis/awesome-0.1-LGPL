/**
 * 
 */
package edu.sdsc.milou.awesome.DataFrame;

/**
 * @author subhasis
 *
 */
public class DetermineJAVAHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DetermineJAVAHeap dh = new DetermineJAVAHeap();
		dh.getRuntimeMemeory();

	}
	
	public void getRuntimeMemeory(){
		
		Runtime rt = Runtime.getRuntime();
		System.out.println(rt.freeMemory());
		System.out.println(rt.maxMemory());
		System.out.println(rt.totalMemory());
	}

}
