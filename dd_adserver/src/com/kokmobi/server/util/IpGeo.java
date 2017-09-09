package com.kokmobi.server.util;

import java.io.IOException;

import com.kokmobi.server.util.ip.LookupService;



public class IpGeo {
	 private static final String dbfile = IpGeo.class.getResource("/GeoIP.dat").toString().substring(5);
	 private static IpGeo instance = new IpGeo();
	 private  static LookupService cl;
	
	 public static IpGeo getInstance(){		  
		 try{
			 cl= new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);
		 }catch (IOException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return instance;
	 }
	  
	 public String getCountry(String ip)
	 {
		 return cl.getCountry(ip).getCode();
	 }
}
