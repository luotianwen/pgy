package com.kkgame.feeop.util;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;

/**
 * @author RuySing By 2011-1-19
 */
public class StringUtilities {

	/**
	 * 将集合元素中的对象的主键值拼装成字符串
	 * @param list
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("unchecked")
	public static String joinObjectPK(List list) throws Exception{
		StringBuilder sb = new StringBuilder();
		if(null != list && list.size() >0){
			for(Object o : list){
				Field[] fields = o.getClass().getDeclaredFields();
				if(null != fields && fields.length > 0){
					for(Field f : fields){
						f.setAccessible(true);
						if(null != f.getAnnotation(Id.class)){
							sb.append(f.get(o)).append(",");
							continue;
						}
					}
				}
			}
		}
		if(sb.toString().length() > 0){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
}
