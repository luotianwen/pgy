package com.kokmobi.server.service;

import com.kokmobi.server.bean.AdLanguageInfo;
import com.kokmobi.server.util.BingUtil;

import net.sf.json.JSONObject;

public class TranslateTool extends Thread {
	private AdLanguageInfo info;
	private String to;
	private String key;
	
	public TranslateTool(AdLanguageInfo strs, String to, String key){
		this.info = strs;
		this.to = to;
		this.key = key;
				
	}
	
	public void run() {
		try{
			String[] str = BingUtil.bing(new String[] { info.getAppName(), info.getPushText(), 		
	                info.getIntroduction() }, to);
	      if ((str != null) && (str.length > 0)) {
	      	AdLanguageInfo linfo = new AdLanguageInfo();
	      	linfo.setAppName(str[0]);
	      	linfo.setPushText(str[1]);
	      	linfo.setIntroduction(str[2]);
	      	JSONObject jsobj = JSONObject.fromObject(linfo);
	      	RedisTool.set(this.key, jsobj.toString());
	      	//TODO:set expired time?
//	      	redis.expire(this.key, 60*60*24);
	      }
		}
		catch(Exception e) {
			System.out.println(String.format("can not trans %s to %s", info.getAppName(), this.to));
		}
	}
}
