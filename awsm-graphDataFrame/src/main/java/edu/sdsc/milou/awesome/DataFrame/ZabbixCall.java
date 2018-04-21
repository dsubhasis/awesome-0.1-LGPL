package edu.sdsc.milou.awesome.DataFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.json.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import edu.sdsc.awesome.milou.SimpleFileLog;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;

public class ZabbixCall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ZabbixCall ac = new ZabbixCall();
		ac.getData();
	}

	public void getData() {
		String url = "http://10.128.5.178/zabbix/api_jsonrpc.php";
		DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
		zabbixApi.init();
		boolean login = zabbixApi.login("Admin", "newpass");
		System.err.println("login:" + login);
		String host = "neo4j";
		JSONObject filter = new JSONObject();

		filter.put("host", "neo4j");
		//filter.put(, value)
		//List a = new ArrayList();
		
		//Request getRequest = RequestBuilder.newBuilder().method("host.get").paramEntry("filter", filter).build();
		Map search = new HashMap();
		search.put("key_", "system.cpu.util[,user]");
		//search.put("itemid", "23687");
		Map flter = new HashMap();
		//Request getRequest = RequestBuilder.newBuilder().method("history.get").paramEntry("output", "shorten").paramEntry("hostid", "10105").paramEntry("search", search)
				//.paramEntry("filter", filter).build();
		
		Request getRequest = RequestBuilder.newBuilder().method("history.get").paramEntry("output", "extend").paramEntry("history", 0).paramEntry("itemids", "23296").paramEntry("sortorder", "DESC").paramEntry("limit", 10).build();

		JSONObject getResponse = zabbixApi.call(getRequest);
		System.err.println(getResponse);
		
		 System.out.println(getResponse.getJSONArray("result").toString());
		 JSONArray jsonarray = new JSONArray(getResponse.getJSONArray("result"));
		 
		 
		//Iterator size = jsr.iterator();
		List values = new ArrayList();
		 for (int i = 0; i < jsonarray.length(); i++) {
			 org.json.JSONObject jsonobject = jsonarray.getJSONObject(i);
			 String value = jsonobject.getString("value");
			 values.add(value);
			 SimpleFileLog sfl = new SimpleFileLog();
			 try {
				sfl.updateFile("performance.csv", value+",");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		

	}

}
