
package com.kokmobi.server.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class UpYun {
	
	
    public static String getPath(String path,String addr){
    	
    	/*if(null==addr||addr.equals("")){
    		addr="upyun";
    	}
    	if(addr.equals("upyun")){
    		return "?_upt="+toToken(path);
    	}
    	return "?_upt="+toToken(path);
          */
		return  "";
    }
	
	
	private static String UTF8 = "UTF-8";
	public static int time = 4*60*60;
	private static String KEY = "ddlions";
	public void setTime(int second) {
		this.time = second;
	}
	public static String toToken( String path) {
		long eTime = new Date().getTime() / 1000 + time;
		String sign = md5(KEY+"&"+eTime+"&"+path).substring(12, 20) + eTime;
		return sign;
	}
	public String toToken(String key, String path) {
		long eTime = new Date().getTime() / 1000 + time;
		String sign = md5(key+"&"+eTime+"&"+path).substring(12, 20) + eTime;
		return sign;
	}
	
	public static String md5(String strSrc) {
		String result = "";
		byte[] temp = null;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			temp = md5.digest(strSrc.getBytes(UTF8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < temp.length; i++) {
			result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
		}
		return result;
	}
}
