package com.kokmobi.server.service;

import java.util.Map;

import com.kokmobi.server.util.HttpUtils;

public class HttpGetTool extends Thread {
	
	private String url;
	private Map<String, String> paras;
	
	public HttpGetTool(String url, Map<String, String> para) {
		this.url = url;
		this.paras = para;
	}
	
	public void run() {
		String r = HttpUtils.sendGet(this.url, this.paras);		
		System.out.println(String.format("sale to ddl:%s,result:%s", url, r));
	}
}
