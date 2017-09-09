package com.kokmobi.server.util;

public class BrowserUtil {
	public static int USER_AGENT_ANDROID = 200201;	//安卓
	public static int USER_AGENT_IPHONE = 200202;	//iphone
	public static int USER_AGENT_IPAD = 200203;		//ipad
	public static int USER_AGENT_WEB = 200204;		//web
	
	public static int getAgentType(String agent){//移动终端浏览器版本信息
		
		if(agent.indexOf("Android")>-1){
			return USER_AGENT_ANDROID;
		}
		else if(agent.indexOf("iPhone")>-1) {
			return USER_AGENT_IPHONE;
		}
		else if(agent.indexOf("iPad")>-1) {
			return USER_AGENT_IPAD;
		}
		else {
			return USER_AGENT_WEB;
		}
//	    return {
//
//	        ios: !!agent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
//
//	        android: agent.indexOf('Android') > -1 || agent.indexOf('Linux') > -1, //android终端或者uc浏览器
//
//	        iPhone: agent.indexOf('iPhone') > -1 || agent.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
//
//	        iPad: agent.indexOf('iPad') > -1, //是否iPad
//
//	    }

	}
}
