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
	public static String getCountry2(String ipAddr) {
		String countryName=null;

		try {
			countryName = IpUtil.getCountryByIpnet(ipAddr);
			System.out.println(countryName);

			countryName = IpUtil.getCountryByIpnet(ipAddr);
			System.out.println(countryName);

			countryName = IpUtil.getCountryByIpnet(ipAddr);
			System.out.println(countryName);
		}catch(Exception ex) {
			ex.printStackTrace();

		}

		return countryName;
	}
	 public static void main(String [] arg){
		 String ip="124.253.246.219";//"5.212.201.198";
		 long begin=System.currentTimeMillis(); 
		 System.out.println(getCountry2(ip));
		 long end=System.currentTimeMillis();
		// System.out.println(end-begin);
		
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
	 }
}
