package com.kkgame.hz.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFormat {
	public static final SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat ymFormat = new SimpleDateFormat("yyyyMM");
	public static final SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat dmyFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat dmFormat = new SimpleDateFormat("dd/MM");
	public static final SimpleDateFormat mmddFormat = new SimpleDateFormat("MM-dd");
	public static final SimpleDateFormat HHFormat = new SimpleDateFormat("HH");
	public static final SimpleDateFormat MMddFormat = new SimpleDateFormat("MM dd");
	public static final SimpleDateFormat MMFormat = new SimpleDateFormat("MM");
	public static final SimpleDateFormat ddFormat = new SimpleDateFormat("dd");
	public static final SimpleDateFormat MMyyyyFormat = new SimpleDateFormat("MM yyyy");
	public static final SimpleDateFormat yyyyFormat = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat yyMMddFormat = new SimpleDateFormat("yyMMdd");
	public static final SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");

	public static final int NAME_TYPE_FULLNAME = 0;
	public static final int NAME_TYPE_ABBRNAME = 1;

	private CalendarFormat() {
	}

	public static Date getDateTime(String dateMDY) throws ParseException {
		return mdyFormat.parse(dateMDY);
	}
	
	public static Date getDateTimeYYYYMMDD(String dateYYYYMMDD) throws ParseException {
		return yyyyMMddFormat.parse(dateYYYYMMDD);
	}

	public static Date getDateTime(String dateString, SimpleDateFormat sformat) throws ParseException {
		return (null == sformat) ? mdyFormat.parse(dateString) : sformat.parse(dateString);
	}

	public static long getLongTimeYYYYMMDD(String dateYYYYMMDD) throws ParseException {
		return yyyyMMddFormat.parse(dateYYYYMMDD).getTime();
	}
	
	public static long getLongTime(String dateMDY) throws ParseException {
		return mdyFormat.parse(dateMDY).getTime();
	}

	public static long getLongTime(String dateString, SimpleDateFormat sformat) throws ParseException {
		return (null == sformat) ? mdyFormat.parse(dateString).getTime() : sformat.parse(dateString).getTime();
	}

	public static String getDateString(long clickTime) {
		return ymdhmsFormat.format(clickTime);
	}
	public static String getDateStringYMD(long clickTime) {
		return ymdFormat.format(clickTime);
	}

	public static String getDateString(long clickTime, SimpleDateFormat sformat) {
		return (null == sformat) ? ymdhmsFormat.format(clickTime) : sformat.format(clickTime);
	}

	// this time is YYYY:month:day:0:0:0
	public static long getNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	public static long getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static String getCurrentDateTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	public static String getCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(new Date());
	}

	public static String getYesterday() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(c.getTime());
	}

	
	public static long getMonth(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static long getMonth(Date date,int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static int getWeekNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(calendar.WEEK_OF_YEAR);
	}
	
	public static String getStringCurrentMonth() {
		String currentMonth = getCurrentDate().substring(0, 6);
		return currentMonth;
	}

	public static String getStringLastMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		return simpleDateFormat.format(c.getTime());
	}

	public static String getFirstDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(c.getTime());
	}
	
	public static long getChangDate(int n) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static long getChangDate(Date date, int n) {		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	public static long getThisYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	public static String getWeekName(int week) {
		switch (week) {
		case (1):
			return "Sun";
		case (2):
			return "Mon";
		case (3):
			return "Tue";
		case (4):
			return "Wed";
		case (5):
			return "Thu";
		case (6):
			return "Fri";
		case (7):
			return "Sat";
		default:
			return "Sun";
		}
	}

	public static String getWeekFullName(int week) {
		switch (week) {
		case (1):
			return "Sunday";
		case (2):
			return "Monday";
		case (3):
			return "Tuesday";
		case (4):
			return "Wednesday";
		case (5):
			return "Thursday";
		case (6):
			return "Friday";
		case (7):
			return "Saturday";
		default:
			return "Sunday";
		}
	}
	
	public static String getWeekCnName(int week) {
		switch (week) {
		case (1):
			return "星期一";
		case (2):
			return "星期二";
		case (3):
			return "星期三";
		case (4):
			return "星期四";
		case (5):
			return "星期五";
		case (6):
			return "星期六";
		case (7):
			return "星期日";
		default:
			return "星期日";
		}
	}

	public static String getMonthName(int month) {
		switch (month) {
		case (1):
			return "Jan";
		case (2):
			return "Feb";
		case (3):
			return "Mar";
		case (4):
			return "Apr";
		case (5):
			return "May";
		case (6):
			return "Jun";
		case (7):
			return "Jul";
		case (8):
			return "Aug";
		case (9):
			return "Sep";
		case (10):
			return "Oct";
		case (11):
			return "Nov";
		case (12):
			return "Dec";
		default:
			return "Jan";
		}
	}

	public static String getMonthForFullName(int month) {
		switch (month) {
		case (1):
			return "JANUARY";
		case (2):
			return "FEBRUARY";
		case (3):
			return "MARCH";
		case (4):
			return "APRIL";
		case (5):
			return "MAY";
		case (6):
			return "JUNE";
		case (7):
			return "JULY";
		case (8):
			return "AUGUST";
		case (9):
			return "SEPTEMBER";
		case (10):
			return "OCTOBER";
		case (11):
			return "NOVEMBER";
		case (12):
			return "DECEMBER";
		default:
			return "JANUARY";
		}
	}

	public static String getMonthName(String strMonth, int nameType) {
		int month;
		try {
			month = Integer.parseInt(strMonth.substring(4, 6));
		} catch (Exception e) {
			month = 1;
		}
		String strName = "";
		if (NAME_TYPE_ABBRNAME == nameType) {
			strName = getMonthName(month);
		} else if (NAME_TYPE_FULLNAME == nameType) {
			strName = getMonthForFullName(month);
		}
		return strName;
	}

	public static String getFullTime(int time) {
		return (time < 10) ? "0" + time : String.valueOf(time);
	}

	private static List<String> getList() {
		List<String> aList = new ArrayList<String>();
		String to = CalendarFormat.getDateString(System.currentTimeMillis(), CalendarFormat.ymFormat);
		int intTo = Integer.parseInt(to);
		String from = "200712";
		int intFrom = Integer.parseInt(from);
		// in desc order
		for (int i = intTo; i >= intFrom; i--) {
			if (i % 100 > 12 || i % 100 == 0) {
				continue;
			}
			aList.add(i + "");
		}
		return aList;
	}
	
	
	// 获得一个时间对应的周一的时间
	public static long getMonday(String date){
	 long dateInt = 0;
	    try {
		dateInt = getLongTimeYYYYMMDD(date);
		 int dayOfWeek = getDayOfWeek(date)-1;
		 dateInt = dateInt - dayOfWeek*24*60*60*1000;
	     } catch (ParseException e) {
		 e.printStackTrace();
	    }	
	 return dateInt;
	}
	
	// 获得一个时间对应的周日的时间
	public static long getSunday(String date){
		long dateInt = 0;
		try {
			dateInt = getLongTimeYYYYMMDD(date);
			int dayOfWeek = 7-getDayOfWeek(date);
			 dateInt = dateInt + dayOfWeek*24*60*60*1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	 return dateInt;
	}
	
	public static String getWeek( String date){
		String week = getWeekCnName(getDayOfWeek(date));	
		return week;
	}
	
	// 获得一个时间是本周的的几天，星期一：1 星期天：7
	private static Integer getDayOfWeek( String date){
	 // 获得一个日期是一个星期中的第几天，星期日：0 星期六：6
	 int DateOfWeek = 0;
	    try {
		  DateOfWeek = getDateTimeYYYYMMDD(date).getDay();
		   if(DateOfWeek == 0){
			  DateOfWeek = 7;
			 }
	    } catch (Exception e) {
		e.printStackTrace();
	    }	
	 return DateOfWeek;
	}
	

	public final static long INTERVALHOUR = 1000 * 60 * 60;
	public final static long INTERVALDAY = INTERVALHOUR * 24;
	public final static long INTERVALWEEK = INTERVALDAY * 7;
	public final static long INTERVALMONTH = INTERVALDAY * 31;
	
	
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYY_MM = "yyyy-MM";
	public final static String YYYYMM = "yyyyMM";
	
	/**
	 * 通过相应格式获得时间
	 * @author lotteLiu
	 * @param format
	 * @return
	 */
	public static String getCurrentFormatDate(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		return sdf.format(c.getTime());
	}
	
	/**
	 * 将时间由原来的格式转换为另一种时间格式
	 * @param formatDate
	 * @param fromFormat
	 * @param toFormat
	 * @return
	 */
	public static String switchFormatDate(String formatDate, String fromFormat, String toFormat) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
			Date date = sdf.parse(formatDate);
			sdf = new SimpleDateFormat(toFormat);
			return sdf.format(date);
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public static String getYM(int i) {
		try {
			Calendar ca = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("yyyyMM");
			ca.add(Calendar.DAY_OF_MONTH, -i);
			return df.format(ca.getTime());
		} catch (Exception e) {
		}
		return null;
	}

	public static void main1(String[] args) throws ParseException {
		//System.out.println(switchFormatDate("2009-08-01", "yyyy-MM-dd", "yyyyMM"));
	//	System.out.println(switchFormatDate("2009-08-01", "yyyy-MM-dd", "yyyyMMdd"));
		//System.out.println("得到的月份："+getDateString(CalendarFormat.getMonth(CalendarFormat.getDateTimeYYYYMMDD("20090801"),-1)));
	   // System.out.println(ymFormat.parse("200801"));
	    String month = "200801";
        int n = 0;
        while(Integer.parseInt(month) < Integer.parseInt(CalendarFormat.ymFormat.format(new Date()))){   
            month = CalendarFormat.ymFormat.format(CalendarFormat.getMonth(new Date(CalendarFormat.ymFormat.parse(month).getTime()), n));
            System.out.println(Integer.parseInt(month));
            n++;
        }
	    
	}

	public static void main(String[] args) {
		// String ymd = getDateString(System.currentTimeMillis(),
		// yyyyMMddFormat);
		// System.out.println(ymd);
		// List<String> aList = getList();
		// StringBuilder sb = new StringBuilder();
		// for (String month : aList) {
		// sb.append("<option value=\"" + month + "\">");
		// sb.append(month);
		// sb.append("</option>");
		// }
		// System.out.println(sb.toString());
//		CalendarFormat.getChangDate(-10);
//		System.out.println("时间：" + ymdhmsFormat.format(CalendarFormat.getChangDate(-10)));
		Date date = new Date();
		System.out.println( date.getDate());
		CalendarFormat.getFirstDayOfMonth();
	
		
	}
	
	

}