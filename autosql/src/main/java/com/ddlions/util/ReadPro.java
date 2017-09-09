package com.ddlions.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/5/18
 *          Time: 12:14
 * @author: mm
 * @since 3.0
 */
public class ReadPro {
    private static Map<String,String> pros=new HashMap();

    private static Properties    getPro(){
        InputStream inputStream3 = ReadPro.class.getClassLoader().getResourceAsStream("db_pkig_pool.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream3);
            inputStream3.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return p;
    }

    public static String getValue(String key){
        String value="";
        if(pros.containsKey(key)){
            value=pros.get(key);
        }else{
            value= getPro().getProperty(key);
            pros.put(key,value);
        }

        return value;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
             System.out.println("ip:" + getValue("hz_url")  );
        }

    }
}
