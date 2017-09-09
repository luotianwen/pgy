package com.kokmobi.server.util.ip;
/* Only works with GeoIP Country Edition */
/* For Geoip City Edition, use CityLookup.java */


import java.io.IOException;

class CountryLookup {
    public static void main(String[] args) {
	try {
	    String sep = System.getProperty("file.separator");

	    // Uncomment for windows
	    // String dir = System.getProperty("user.dir");

	    // Uncomment for Linux
	    //String dir = "/usr/local/share/GeoIP";

	    String dbfile = CountryLookup.class.getResource("/GeoIP.dat").toString().substring(5);
	    // You should only call LookupService once, especially if you use
	    // GEOIP_MEMORY_CACHE mode, since the LookupService constructor takes up
	    // resources to load the GeoIP.dat file into memory
	    //LookupService cl = new LookupService(dbfile,LookupService.GEOIP_STANDARD);
	    LookupService cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);

	    System.out.println(cl.getCountry("121.41.120.73").getCode());
	    System.out.println(cl.getCountry("12.25.205.51").getCode());

	    cl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}
