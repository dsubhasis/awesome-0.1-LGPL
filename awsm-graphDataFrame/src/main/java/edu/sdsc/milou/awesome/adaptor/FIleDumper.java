package edu.sdsc.milou.awesome.adaptor;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class FIleDumper implements Runnable{
	private List<String> query;
	private Map<String, List<String>> querySet;
	private Map<String, Object> params;
	private Integer size;

	public FIleDumper(Map<String,List<String>> rquery)  {
		// TODO Auto-generated constructor stub
		
		String fileNane  = RandomStringUtils.randomAlphabetic(8)+".cypher";
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
