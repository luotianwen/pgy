package com.kkgame.hz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataTypeDeal {

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
		System.out.println(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return sdf.format(calendar.getTime()); 
	}
	public static String getMonthLastDay(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(calendar.getTime()); 
	}
	

//	public static void main(String[] args) throws Exception{
//		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
//		  java.util.Date date= myFormatter.parse("2011-11-14 18:12:12");  
//		  Calendar cal = Calendar.getInstance();
//		  long  day=(cal.getTime().getTime()-date.getTime())/(24*60*60*1000); 
//		  System.out.println(day);
//
//	}
	public static void main(String[] args) {
		String productIds = "2,3,4,5,";
		int productIdsLength = productIds.length();
		System.out.println(productIds.substring(0, productIds.length()-1));
	}
}
