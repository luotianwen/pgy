package com.kkgame.pay.stat.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	private static Log logger = LogFactory.getLog(FileUtil.class);
	
	private static final int BUFFER_SIZE = 16*1024;

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
	
	/**
	 * 判断目录是否存在，如果不存在则创建
	 * @param path
	 */
	public static void mkdir(String path) throws Exception{
		File file = new File(path);
		if (!file.exists()){
			file.mkdirs();
		}
	}

	/**
	 * 根据文件生成字节流，方便其它资源读取
	 * @param objFile
	 * @return
	 */
	public static byte[] readFileStream(File objFile){
		InputStream in = null;
		try {
			
			try {
				in = new BufferedInputStream(new FileInputStream(objFile),BUFFER_SIZE);
				int fileSize = new Integer(objFile.length()+"");
				byte[] buffer = new byte[fileSize];
				while (in.read(buffer) > 0){
				}
				return buffer;
			} finally {
				if (null != in)
					in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 删除文件
	 * @param filePath
	 */
	public static void deleteFile(String filePath)throws Exception{
		File file = new File(filePath);
		if (file.exists()){
			file.delete();
		}
	}
	
	/**
	 * 上传文件
	 * @param src
	 * @param sdt
	 */
	public static void copyFile(File src,File sdt)throws Exception{
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(sdt),BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0){
					out.write(buffer);
				}
			} finally {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			}
		} catch (Exception e) {
			logger.error("FileUtil copyFile error:",e);
		}
	}
}
