package com.kokmobi.server.test;

import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.service.impl.RedistServiceImpl;

public class RedisTool {
	
	private static RedistServiceImpl redis = new RedistServiceImpl();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		redis.initPool();
		
//		String key = String.format(Constants.KEY_USER_INFO, Constants.SDKSTYLE_SDK, "556888888553");
//		
//		String resp = redis.get(key);	//是否注册
//		System.out.println(String.format("%s %s", key, resp));
//		
 		String ukey = Constants.KEY_USER_LIST_TOSAVE;
 		String strObj = "{\"cdate\":{\"date\":8,\"day\":2,\"hours\":18,\"minutes\":17,\"month\":11,\"seconds\":34,\"time\":1449569854182,\"timezoneOffset\":-480,\"year\":115},\"channelid\":\"123ss\",\"coo_id\":\"1234567\",\"countryLevel\":4,\"dataType\":0,\"imei\":\"556888888553\",\"ipaddr\":\"127.0.0.1\",\"scoo_id\":\"1234567\",\"sdate\":{\"date\":8,\"day\":2,\"hours\":18,\"minutes\":17,\"month\":11,\"seconds\":34,\"time\":1449569854182,\"timezoneOffset\":-480,\"year\":115},\"sdk\":2,\"sdkStyle\":600400,\"sdkversion\":\"2\",\"type\":\"1\",\"xc_coo_id\":\"0\",\"xcou\":-1,\"xdate\":{\"date\":8,\"day\":2,\"hours\":18,\"minutes\":17,\"month\":11,\"seconds\":34,\"time\":1449569854182,\"timezoneOffset\":-480,\"year\":115},\"ximsi\":\"460026029668041\",\"xinternet\":\"1\",\"xmodel\":\"m1note\",\"xoperator\":\"CMCC\",\"xversion\":\"5.1\"}";
 		//long uresp = redis.lpush(ukey, strObj) ;
 		redis.del(ukey);
 		//System.out.println(String.format("%s %s", ukey, uresp));
		
		
		String lastKey = String.format(Constants.KEY_AD_LAST_SENT, "549959959133", Constants.SDKSTYLE_SDK, "1234567");
		redis.del(lastKey);
		 // redis.lpush(lastKey, strObj) ;
		//String lastresp = redis.get(lastKey);	//
		//System.out.println(String.format("%s %s", lastKey, lastresp));
//		
//		String rkey = String.format(Constants.KEY_AD_SENTIST, "656888888553", Constants.ADTYPE_PUSH, Constants.SDKSTYLE_SDK);
//		String rresp = redis.get(rkey);	//
//		System.out.println(String.format("%s %s", rkey, rresp));
	}

}
