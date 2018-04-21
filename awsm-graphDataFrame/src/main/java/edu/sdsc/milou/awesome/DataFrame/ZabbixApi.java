package edu.sdsc.milou.awesome.DataFrame;

import com.alibaba.fastjson.JSONObject;

import io.github.hengyunabc.zabbix.api.Request;

public interface ZabbixApi {

	void init();

	void destroy();

	String apiVersion();

	JSONObject call(Request request);

	boolean login(String user, String password);
}