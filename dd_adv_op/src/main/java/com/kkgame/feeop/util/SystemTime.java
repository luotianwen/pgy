package com.kkgame.feeop.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 
 * @author Hong
 * 获取系统时间
 */
public class SystemTime {

	public static String time() {
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);   
        sdf.applyPattern("yyyyMMddHHmmss");   
        String time = sdf.format(System.currentTimeMillis());
		return time;
	}
}
