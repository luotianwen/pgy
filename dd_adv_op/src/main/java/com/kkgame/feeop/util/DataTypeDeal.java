package com.kkgame.feeop.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataTypeDeal {
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrDate(){
		//SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getTime()+""; 
	}

	/**
	 * 将字符串的null改为 ""
	 * @param curValue
	 * @return
	 */
	public static String clearNull(String curValue) {
		if (curValue == null) {
			return "";
		}
		return curValue;
	}

	/**
	 * 当curValue为空时，将curValue = defaultValue
	 * @param curValue
	 * @param defaultValue
	 * @return
	 */
	public static String clearNull(String curValue, String defaultValue) {
		if (curValue == null) {
			return defaultValue;
		}
		return curValue;
	}
	
	/**
	 * 获取当前的年月 yyyyMM
	 * @return
	 */
	public static String getCurYearMonthMinusOne(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return sdf.format(cal.getTime());
	}

	public static String getCurYearMinusOne(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(cal.getTime());
	}
	public static int getCurMonth(){
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.add(Calendar.MONTH, -1);
		return cal.get(Calendar.MONTH)+1;
	}
	public static String getCurMonthStr(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.add(Calendar.MONTH, -1);
		return sdf.format(cal.getTime());
	}
	
	public static String getCurYM(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	public static String getCurY(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	//得到当前月的第一天
	public static String getMonthFirstDay(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return sdf.format(calendar.getTime()); 
	}
	public static String getMonthLastDay(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(calendar.getTime()); 
	}
	

	public static void main(String[] args) throws Exception{
		System.out.println(versionIntToString2(9731)); 

	}

	/**
	 *  2。0版式本转换数值
	 * @param version
	 * @return
	 */
	public static String versionIntToString2(int version){

		int hightOrder = (version & 0XE000)>>13;
		int middleOrder = (version & 0X1F00)>>8;
		int lowerOrder = (version & 0XFF);
		
		return hightOrder + "." + middleOrder + "." + lowerOrder;
	}
	/***
	 * 将version转为int
	 * @param version
	 * @return
	 */
	public static int versionStringToInt2(String strVersion){
		String args[] = strVersion.split("\\.");
		int hightOrder = (Integer.parseInt(args[0]))<<13;
		int middleOrder = (Integer.parseInt(args[1]))<<8;
		int lowerOrder = (Integer.parseInt(args[2]));
		int version = hightOrder |middleOrder|lowerOrder;
		
		return version;
	}

	/**
	 * 将版本号从整型转换为字符串标准格式
	 * @param version
	 * @return
	 */
	public static String versionIntToString(int version) {
		ByteUtils2 bu = new ByteUtils2(ByteUtils2.BYTE_MODE_HH, "GBK");
		byte[] bytes = bu.getBytes(version);
		String ver ="";
		for(int i=0;i<bytes.length;i++) {
			if(i==0) {
				if(bytes[i]!=0) {
					ver += bytes[i]+".";
				}					
			} else if (i==3) {
				ver += bytes[i];
			} else  {
				ver += bytes[i]+".";
			}
		}

		return ver;
	}
	

}
