package com.kkgame.feeop.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {

	public static byte[] getBytesFromFile(File file) {   
        byte[] ret = null; 
        
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {   
            if (file!=null && file.exists()) {   
	            
	            in = new FileInputStream(file);
	            out = new ByteArrayOutputStream();
	            
	            byte[] buf = new byte[4096];  
	            
	            int len = 0;   
	            while ((len = in.read(buf)) != -1) { 
	                out.write(buf, 0, len);
	            }
	            
	            out.flush();
	            
	            ret = out.toByteArray(); 
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        finally{
        	try{out.close();}catch(Exception e){};
        	try{in.close();}catch(Exception e){};
        }
        
        return ret;   
    }
	
}
