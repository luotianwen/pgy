package com.kkgame.feeop.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
	private static final int BUFFER_SIZE = 16*1024;
	
	/**
	 * ͨ��ʱ�����ļ���
	 * @return
	 */
	public static String getFileNameByTime(){
		SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return ymdhmsFormat.format(new Date());
	}
	
	/**
	 * �ж�Ŀ¼�Ƿ���ڣ��������򴴽�
	 * @param path
	 */
	public static void mkdir(String path) throws Exception{
		File file = new File(path);
		if (!file.exists()){
			file.mkdirs();
		}
	}
	
	/**
	 * ������Ŀ¼���
	 * @param oldDir
	 * @param newDir
	 */
	public static void renameDir(String oldDir, String newDir)throws Exception{
		File file = new File(oldDir);
		file.renameTo(new File(newDir));
	}
	
	/**
	 * ɾ���ļ�
	 * @param filePath
	 */
	public static void deleteFile(String filePath)throws Exception{
		File file = new File(filePath);
		if (file.exists()){
			file.delete();
		}
	}
	
	/**
	 * �ϴ��ļ�
	 * @param src
	 * @param sdt
	 */
	public static void copyFile(File src,File sdt)throws Exception{
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(sdt));
				int fileSize = new Integer(src.length()+"");
				byte[] buffer = new byte[fileSize];
				while (in.read(buffer) > 0){
					out.write(buffer);
				}
				out.flush();
			} finally {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void outPutDesFile(byte[] bs,File sdt)throws Exception{
		try {
			OutputStream out = null;
			try {
				out = new FileOutputStream(sdt);
				out.write(bs);
				out.flush();
			} finally {
				if (null != out)
					out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(File file){ 
		if(file.exists()){
			if(file.isFile()){
				file.delete(); 
			} else if (file.isDirectory()){
				File files[] = file.listFiles();
				for(int i=0;i<files.length;i++){
					deleteFile(files[i]); 
				} 
		    } 
		    file.delete();
		    }else{ 
		    System.out.println("所删除的文件不存在！"+'\n'); 
		   } 
	} 
}
