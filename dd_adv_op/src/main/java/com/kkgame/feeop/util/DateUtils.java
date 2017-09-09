package com.kkgame.feeop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	/**
	 * 锟斤拷式锟斤拷锟斤拷锟斤拷时锟斤拷为2000-01-01 00:00:00锟斤拷式
	 * 
	 * @param date
	 * @return
	 */
    public static String formatDateTime(Date date){
    	return format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 锟斤拷式锟斤拷锟斤拷锟斤拷为2000-01-01锟斤拷式
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date){
    	return format(date, "yyyy-MM-dd");
    }
    
    public static String formatYearMonth(Date date) {
    	return format(date, "yyyyMM");
    }

	public static String formatYearMonthDay(Date date){
		return format(date, "yyyyMMdd");
	}

    /**
     * 锟斤拷式锟斤拷锟斤拷锟斤拷时锟斤拷为指锟斤拷锟斤拷式
     * 
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }
    
    public static Date parseDateTime(String str) throws ParseException{
    	return parse(str, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static Date parseDate(String str) throws ParseException{
    	return parse(str, "yyyy-MM-dd");
    }
    
    public static Date parse(String str, String format) throws ParseException{
    	return new SimpleDateFormat(format).parse(str);
    }
    
	public static int getDays(String startDate, String endDate)
	throws Exception
	{
		try{
			Date sd = parseDate(startDate);
			Date ed = parseDate(endDate);
			
			return (int)((ed.getTime()-sd.getTime())/(24*60*60*1000));
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public static String subDay(String date, int num)
	throws Exception
	{
		try{
			Date d = parseDate(date);
			
			Date rd = new Date(d.getTime()+(long)num*24*60*60*1000);
			return formatDate(rd);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public static int getMonths(String startMonth, String endMonth)
	throws Exception
	{
		try{
			int sy = Integer.parseInt(startMonth.substring(0, 4));
			int sm = Integer.parseInt(startMonth.substring(5, 7));
			
			int ey = Integer.parseInt(endMonth.substring(0, 4));
			int em = Integer.parseInt(endMonth.substring(5, 7));
			
			return (ey-sy)*12+em-sm;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public static String subMonth(String month, int num)
	throws Exception
	{
		try{
			int y = Integer.parseInt(month.substring(0, 4));
			int m = Integer.parseInt(month.substring(5, 7));
			
			int rm = m+num;
			int ry = y;
			while(rm<1 || rm>12){
				if(rm>12){
					rm -= 12;
					ry++;
				}
				else if(rm<1){
					rm += 12;
					ry--;
				}
			}
			return ry + "-" + (rm<10?"0":"") + rm;
		}catch(Exception e)
		{
			throw e;
		}
	}
	
	/*
	 * 锟斤拷取锟较革拷锟铰碉拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟斤拷
	 * 
	 * */
	public static String getLastMonthDay(Date date) {
        Calendar cal = Calendar.getInstance();
        //Date date = new Date();
        if(date == null){
        	date = new Date();
        }
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 锟较革拷锟斤拷锟铰凤拷
        // int day1 = cal.getActualMinimum(Calendar.DAY_OF_MONTH);//锟斤拷始锟斤拷锟斤拷
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 锟斤拷锟斤拷锟斤拷锟斤拷
        
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        String endDay = year + "-" + month + "-" + day;
        return endDay;
    }
	
	public static List<String> getDay(String from , String to) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			Date fromDate = df.parse(from);
			Date toDate = df.parse(to);
			Calendar caFrom = Calendar.getInstance();
			caFrom.setTime(fromDate);
			Calendar caTo = Calendar.getInstance();
			caTo.setTime(toDate);
			while(caFrom.before(caTo)) {
				String date = df.format(caFrom.getTime());				
				list.add(date);
				caFrom.add(Calendar.DAY_OF_MONTH, 1);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
