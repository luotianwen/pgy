package com.kokmobi.server.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;



public class IpUtil {
	
	public static String getCoutry(String ip)
	{
		String country = null;
		try{
			
			country = getCountryBySeeker(ip);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(country == null || "".equals(country)){
			country = getCountryByIpnet(ip);
		}
		IpGeo iPSeeker=IpGeo.getInstance();
		country = iPSeeker.getCountry(ip);
		return country;
	}
	
	public static String getCountryBySeeker(String ip) {
		IPSeeker  iPSeeker  = IPSeeker.getInstance();
		return iPSeeker.getCountry(ip);
	}
	
	public static String getCountryByIpnet(String ip) {
		Ipipnet	ipipnet=Ipipnet.getInstance();
		return ipipnet.getCountry(ip);		
	}
	public static String getCountryByIpGeo(String ip) {
		IpGeo iPSeeker=IpGeo.getInstance();
		return iPSeeker.getCountry(ip);
	}
	
	 public static void main(String [] arg){
		 String ip="104.238.32.36";//"5.212.201.198";
		 long begin=System.currentTimeMillis(); 
		 System.out.println(getCoutry(ip)); 
		 long end=System.currentTimeMillis();
		 System.out.println(end-begin);
		
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
	 }
}
