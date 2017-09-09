package com.kokmobi.server.util;

import java.io.IOException;

import com.kokmobi.server.util.ip.LookupService;


public class IpGeo {
    private static final String dbfile = IpGeo.class.getResource("/GeoIP.dat").toString().substring(5);

    private static LookupService cl;

    private static class SingletonHolder {
        private static final IpGeo INSTANCE = new IpGeo();
    }

    public static IpGeo getInstance() {
        try {
            if (cl == null)
                cl = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return SingletonHolder.INSTANCE;
    }

    public String getCountry(String ip) {
        return cl.getCountry(ip).getCode();
    }
}
