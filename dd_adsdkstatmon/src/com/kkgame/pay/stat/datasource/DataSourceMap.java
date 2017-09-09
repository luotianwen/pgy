package com.kkgame.pay.stat.datasource;

import java.util.HashMap;
import java.util.Map;

public class DataSourceMap {

    public static final String KOK_DATA= "kok_data"; 
    public static final String KOK_FEE= "kok_fee"; 
    
    public static Map<String, Integer> serverMap ;
    static {
    	serverMap = new HashMap<String, Integer>();
    	serverMap.put(KOK_DATA, 1);    	
    	serverMap.put(KOK_FEE, 2);    	
    };
}