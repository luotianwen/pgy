package com.ddlions.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/5/18
 *          Time: 16:12
 * @author: mm
 * @since 3.0
 */
public class DateUtil {
    public static String getStringMonth() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(c.getTime());
    }
    public static String getStringNextMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(c.getTime());
    }
    public static String getStringUpMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(c.getTime());
    }
    public static int getStringUpMonthDateNum() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMM");
        Date dd = null;
        try {
            dd = sdf0.parse(getStringUpMonth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(dd);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public  static int getMonthDateNum(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date dd = null;
        try {
            dd = sdf0.parse(getStringNextMonth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(dd);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public  static void main(String str[]){
        System.out.println(getStringMonth());
    }

}
