package com.kkgame.feeop.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFormat {
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

	public static final int NAME_TYPE_FULLNAME = 0;
	public static final int NAME_TYPE_ABBRNAME = 1;

	private CalendarFormat() {
	}
	
	/**
	 * MM/dd/yyyy格式数据转化为时间
	 * @param dateMDY
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTime(String dateMDY) throws ParseException {
		SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
		return mdyFormat.parse(dateMDY);
	}
	
	/**
	 * yyyyMMdd格式数据转化为时间
	 * @param dateYYYYMMDD
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTimeYYYYMMDD(String dateYYYYMMDD) throws ParseException {
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		return yyyyMMddFormat.parse(dateYYYYMMDD);
	}

	/**
	 * MM/dd/yyyy或自定义格式数据转化为时间
	 * @param dateString
	 * @param sformat
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTime(String dateString, SimpleDateFormat sformat) throws ParseException {
		SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
		return (null == sformat) ? mdyFormat.parse(dateString) : sformat.parse(dateString);
	}

	/**
	 * yyyyMMdd格式数据转化为long型time
	 * @param dateYYYYMMDD
	 * @return
	 * @throws ParseException
	 */
	public static long getLongTimeYYYYMMDD(String dateYYYYMMDD) throws ParseException {
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		return yyyyMMddFormat.parse(dateYYYYMMDD).getTime();
	}
	
	/**
	 * MM/dd/yyyy格式数据转化为long时间
	 * @param dateMDY
	 * @return
	 * @throws ParseException
	 */
	public static long getLongTime(String dateMDY) throws ParseException {
		SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
		return mdyFormat.parse(dateMDY).getTime();
	}
	
	/**
	 * MM/dd/yyyy格式数据转化为long型time
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public static long getLongTime(String dateString, SimpleDateFormat sformat) throws ParseException {
		SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
		return (null == sformat) ? mdyFormat.parse(dateString).getTime() : sformat.parse(dateString).getTime();
	}

	/**
	 * yyyy-MM-dd HH:mm:ss型String
	 * @param clickTime
	 * @return
	 */
	public static String getDateString(long clickTime) {
		SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ymdhmsFormat.format(clickTime);
	}
	
	/**
	 * yyyy-MM-dd Strng
	 * @param clickTime
	 * @return
	 */
	public static String getDateStringYMD(long clickTime) {
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		return ymdFormat.format(clickTime);
	}

	public static String getDateString(long clickTime, SimpleDateFormat sformat) {
		SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (null == sformat) ? ymdhmsFormat.format(clickTime) : sformat.format(clickTime);
	}

	/**
	 * this time is YYYY:month:day:0:0:0
	 */ 
	public static long getNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 上个月第0秒
	 * @return
	 */
	public static long getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * yyyy-MM-dd hh:mm:ss 当前时间格式化String
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * yyyyMMdd：当前日期格式化String
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * yyyyMMdd：当前日期格式化String
	 * @return
	 */
	public static String getCurrentYmdDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 前一天
	 * @return
	 */
	public static String getYesterday() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(c.getTime());
	}

	/**
	 * 月份 long 型
	 * @param n
	 * @return
	 */
	public static long getMonth(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 推后几个月
	 * @param date
	 * @param n
	 * @return
	 */
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
	
	/**
	 * 周
	 * @param date
	 * @return
	 */
	public static String getWeekNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setMinimalDaysInFirstWeek(4);
		if(calendar.get(Calendar.WEEK_OF_YEAR)<10) {
			return "0"+calendar.get(Calendar.WEEK_OF_YEAR);
		}
		return ""+calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 周
	 * @param date
	 * @return
	 */
	public static int getWeekNumInt(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setMinimalDaysInFirstWeek(4);
		return calendar.get(Calendar.WEEK_OF_YEAR);
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

	public static String getStringNextMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
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
		SimpleDateFormat ymFormat = new SimpleDateFormat("yyyyMM");
		String to = CalendarFormat.getDateString(System.currentTimeMillis(), ymFormat);
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
	/**
	 * 获得周一的时间
	 * @param date
	 * @return
	 */
	public static Calendar genMonday(String date)
	 {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Date d = null;
		  try
		  {
			  d = format.parse(date);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(d);
		  //关于DAY_OF_WEEK的说明
		  //Field number for get and set indicating 
		  //the day of the week. This field takes values 
		  //SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, 
		  //and SATURDAY
		  cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		  return cal;
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
	 * 获取当月第一天
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(c.getTime());
	}
	
	public static String getLastSevenDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -6);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(c.getTime());
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
	
	/**
	 * 获取yyyyWeek
	 * @param dateString
	 * @param fromFormat
	 * @return
	 */
	public static String getYearWeek(String dateString,String fromFormat) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(fromFormat);
			return dateString.substring(0, 4)+getWeekNum(df.parse(dateString));
		} catch (Exception e) {
			
		}
		return null;
	}

	public static List<String> getyyyyMMddList(String fromDateString,String endDateString) {
		List<String> list = null;
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fromDate = ymdFormat.parse(fromDateString);
			Date endDate = ymdFormat.parse(endDateString);
			Calendar fromCa = Calendar.getInstance();
			fromCa.setTime(fromDate);
			Calendar endCa = Calendar.getInstance();
			endCa.setTime(endDate);
			list = new ArrayList<String>();
			while(fromCa.before(endCa)||fromCa.equals(endCa)) {
				list.add(ymdFormat.format(fromCa.getTime()));
				fromCa.add(Calendar.DAY_OF_MONTH, 1);
			}
			return list;			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static List<String> getYearWeekList(String fromDateString,String endDateString) {
		List<String> list = null;
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {			
			Date fromDate = ymdFormat.parse(fromDateString);
			Date endDate = ymdFormat.parse(endDateString);
			Calendar fromCa = Calendar.getInstance();
			fromCa.setTime(fromDate);
			Calendar endCa = Calendar.getInstance();
			endCa.setTime(endDate);
			endCa.add(Calendar.DAY_OF_MONTH, 7-endCa.get(Calendar.DAY_OF_WEEK));
			list = new ArrayList<String>();
			while(fromCa.before(endCa)||fromCa.equals(endCa)) {
				list.add(CalendarFormat.getYearWeek(ymdFormat.format(fromCa.getTime()), "yyyy-MM-dd"));
				fromCa.add(Calendar.DAY_OF_MONTH, 7);
			}
			return list;			
		} catch (Exception e) {
		}		
		return null;
	}
	

	public static List<String> getDayHour(String date) {
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			for(int i=0;i<24;i++) {
				list.add(date+" "+i);
			}
			return list;	
		} catch (Exception e) {
		}		
		return null;
	}
	
	public static List<String> getDayLastThirtyMin(String date) {
		List<String> list = null;
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat ymdhmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			list = new ArrayList<String>();
			Calendar ca = Calendar.getInstance();
			Date datetime = ymdhmFormat.parse(date);
			Date firsttime = ymdFormat.parse(date);
			ca.setTime(datetime);			
			ca.add(Calendar.MINUTE, -29);
			for(int i=1;i<=30;i++) {
				if(firsttime.before(ca.getTime())||firsttime.equals(ca.getTime())) {
					list.add(ymdhmFormat.format(ca.getTime()));					
				}			
				ca.add(Calendar.MINUTE, 1);
			}
			return list;	
		} catch (Exception e) {
		}		
		return null;
	}
	
	public static List<String> getYearMonthList(String fromDateString,String endDateString) {
		List<String> list = null;
		SimpleDateFormat y_mFormat = new SimpleDateFormat("yyyy-MM");
		try {
			SimpleDateFormat ymFormat = new SimpleDateFormat("yyyyMM");
			Date fromDate = y_mFormat.parse(fromDateString);
			Date endDate = y_mFormat.parse(endDateString);
			Calendar fromCa = Calendar.getInstance();
			fromCa.setTime(fromDate);
			Calendar endCa = Calendar.getInstance();
			endCa.setTime(endDate);
			list = new ArrayList<String>();
			while(fromCa.before(endCa)||fromCa.equals(endCa)) {
				list.add(ymFormat.format(fromCa.getTime()));
				fromCa.add(Calendar.MONTH, 1);
			}
			return list;			
		} catch (Exception e) {
		}		
		return null;
	}

	public static int getMonths(Date date1, Date date2){  
		int iMonth = 0;  
		int flag = 0;  
		try{  
			Calendar objCalendarDate1 = Calendar.getInstance();  
			objCalendarDate1.setTime(date1);  
	
			Calendar objCalendarDate2 = Calendar.getInstance();  
			objCalendarDate2.setTime(date2);  
	 
			if (objCalendarDate2.equals(objCalendarDate1))  
				return 0;  
			if (objCalendarDate1.after(objCalendarDate2)){  
				Calendar temp = objCalendarDate1;  
				objCalendarDate1 = objCalendarDate2;  
				objCalendarDate2 = temp;  
			}  
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))  
				flag = 1;  

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))  
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))  
							* 12 + objCalendarDate2.get(Calendar.MONTH) - flag)  
						- objCalendarDate1.get(Calendar.MONTH);  
			else  
				iMonth = objCalendarDate2.get(Calendar.MONTH)  
							- objCalendarDate1.get(Calendar.MONTH) - flag;  

			} catch (Exception e){  
				e.printStackTrace();  
			}  
		 return iMonth;  
		} 
	
	
	public static int getMonth(Date date1, Date date2){  
		int iMonth = 0;  
		int flag = 0;  
		try{  
			Calendar objCalendarDate1 = Calendar.getInstance();  
			objCalendarDate1.setTime(date1);  
	
			Calendar objCalendarDate2 = Calendar.getInstance();  
			objCalendarDate2.setTime(date2);  
	 
			if (objCalendarDate2.equals(objCalendarDate1))  
				return 0;  
			if (objCalendarDate1.after(objCalendarDate2)){  
				Calendar temp = objCalendarDate1;  
				objCalendarDate1 = objCalendarDate2;  
				objCalendarDate2 = temp;  
			}  
			
			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))  
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))  
							* 12 + objCalendarDate2.get(Calendar.MONTH) )  
							- objCalendarDate1.get(Calendar.MONTH) +1;  
			else  
				iMonth = objCalendarDate2.get(Calendar.MONTH)  
							- objCalendarDate1.get(Calendar.MONTH) +1;  

			} catch (Exception e){  
				e.printStackTrace();  
			}  
		 return iMonth;  
	}
	
	/**
	 * 获取月
	 * @param i
	 * @return
	 */
	public static String getYM(int i) {
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat ymFormat = new SimpleDateFormat("yyyyMM");
			ca.add(Calendar.DAY_OF_MONTH, -i);
			return ymFormat.format(ca.getTime());
		} catch (Exception e) {
		}
		return null;
	}
	

	/**
	 * 获取月
	 * @param i
	 * @return
	 */
	public static String getYMAddMonth(int i) {
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat ymFormat = new SimpleDateFormat("yyyyMM");
			ca.add(Calendar.MONTH, -i);
			return ymFormat.format(ca.getTime());
		} catch (Exception e) {
		}
		return null;
	}
	

	/**
	 * 获取日月天
	 * @param
	 * @return
	 */
	public static String getYmd(int i) {
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar ca = Calendar.getInstance();			
			ca.add(Calendar.DAY_OF_MONTH, -i);
			return ymdFormat.format(ca.getTime());
		} catch (Exception e) {
		}
		return null;
	}
		
	public static String getyyyyMMdd(int i) {
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Calendar ca = Calendar.getInstance();			
			ca.add(Calendar.DAY_OF_MONTH, -i);
			return yyyyMMddFormat.format(ca.getTime());
		} catch (Exception e) {
		}
		return null;
	}
	
	public static String getyyyyMMddAdd(String date,int i) {
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar ca = Calendar.getInstance();	
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			ca.setTime(df.parse(date));
			ca.add(Calendar.DAY_OF_MONTH, -i);
			return ymdFormat.format(ca.getTime());
		} catch (Exception e) {
		}
		return null;
	}
	
	public static int getTimeStep(String date,String compareDate) {
		SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!"".equals(date)&&!"".equals(compareDate)) {
			try {
				Date date1 = ymdhmsFormat.parse(date);
				Date date2 = ymdhmsFormat.parse(compareDate);
				int disTime = (int)((date2.getTime()-date1.getTime()))/1000/60;
				return disTime;
			} catch (Exception e) {
				return 0;
			}
			
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws ParseException {
		//System.out.println(getMonth(new Date(2010,11,12),new Date(2010,1,1)));
		
		//List list = CalendarFormat.getDayLastThirtyMin("2011-01-01 00:32");
		System.out.println(getTimeStep("2015-04-30 00:00:00","2015-04-30 00:32:00"));
	}
}