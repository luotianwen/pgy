package com.kkgame.feeop.tag;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sun.security.rsa.RSASignature.MD5withRSA;


public class TimeStep {
	int id;
	public static int getDaysBetween (Calendar d1, Calendar d2){
		if (d1.after(d2)){
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
			} 
	        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR); 
	        int y2 = d2.get(Calendar.YEAR); 
	        if (d1.get(Calendar.YEAR) != y2){ 
	            d1 = (Calendar) d1.clone(); 
	            do { 
	                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数 
	                d1.add(Calendar.YEAR, 1); 
	            } while (d1.get(Calendar.YEAR) != y2); 
	        } 
	        return days; 
	    }
	
	public static long getChangDate(Date date, int n) {	
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		df.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static void main(String[] args) {
//		String time1= "2010-05-10";
//		String time2 = "2010-05-13";
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//		Date date1 = null;
//		Date date2 = null;
//		try{
//			date1 = df.parse(time1);
//			date2 = df.parse(time2);
//			System.out.println(date1);
//			System.out.println(date2);
//			
//			Calendar calendar1 = Calendar.getInstance();
//			calendar1.setTime(date1);
//			Calendar calendar2 = Calendar.getInstance();
//			calendar2.setTime(date2);
//			
//			int i = getDaysBetween(calendar1, calendar2);
//			System.out.println(i);
//			
//			long times1 = getChangDate(date1, 0);
//			System.out.println(df.format(times1));
//			long times2 = getChangDate(date1, 1);
//			System.out.println(df.format(times2));
//			long times3 = getChangDate(date1, 2);
//			System.out.println(df.format(times3));
//			long times4 = getChangDate(date1, 3);
//			System.out.println(df.format(times4));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		String time = "KK斗地主"+System.currentTimeMillis();
	}
}
