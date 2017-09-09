package com.kkgame.feeop.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Hong
 * 获取BEAN实例
 */
public class ServiceBeanID {
	
	
	
	public static final String SYSTEM_SERVICE = "systemService";
	public static final String DDL_PRODUCT_SERVICE = "ddlProductService";
	public static final String DDL_CHANNEL_SERVICE = "ddlChannelService";
	public static final String SDK_SERVICE = "mutipleSdkService";
	public static final String APKINFO_SERVICE = "apkInfoService";

	
	public static final String CUSTOMER_SERVICE = "customerService";
	public static final String AGENT_SERVICE = "agentService";
	public static final String ADVER_SERVICE = "adverService";
	public static final String ADV_SERVICE = "advService";
	public static final String ADVLINKMAN_SERVICE = "advLinkmanService";

	public static final String PROMOTIONSERVICE_SERVICE = "promotionService";
	public static final String PROMOTIONCUSTOMERSERVICE_SERVICE = "promotionCustomerService";
		
	public static final String ROLE_SERVICE = "roleService"	;
	
	public static final String PRODUCT_SERVICE = "productService";

	public static final String OPERATOR_SERVICE = "operatorService";
		
	private static Map<String, Object> serviceMap = new HashMap<String, Object>();
	
	private static ApplicationContext ctx = null;
	
	public static Object getServiceObject(String beanID) {
		Object service = serviceMap.get(beanID);
		if (null == service) {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext-*.xml");
			}
			service = ctx.getBean(beanID);
			serviceMap.put(beanID, service);
		}
		return service;
	}
}
