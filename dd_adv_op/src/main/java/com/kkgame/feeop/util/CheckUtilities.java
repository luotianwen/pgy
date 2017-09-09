package com.kkgame.feeop.util;
/**
 * 校验工具类
 * @author By RuySing
 */
public class CheckUtilities {

	/**
	 * 是否为空字符串
	 * @param s
	 * @return
	 */
	public static boolean isEmptyString(String s){
		return s == null || "".equals(s.trim()) || "null".equals(s.trim());
	}
	
}
