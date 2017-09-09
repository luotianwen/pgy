package com.kkgame.feeop.base;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

public class PkigConstants {
	public static ServletContext PKIG_WEB_CONTEXT = null;

	/**
	 * 分页-每页显示条数
	 */
	public static final int PAGE_SIZE = 50;
	/**
	 * 分页-页数
	 */
	public static final int PAGE_NUM = 1;
	/**
	 * 分页-每页显示的条数list
	 */
	public static final String PAGE_SIZE_LIST = "15,20,35,50,100";
	/**
	 * 项目根目录
	 */
	public static String PKIG_WEB_ROOT = "";
	
	
	public final static int SETTLE_METHOD_WEEK=1;// 周结
	public final static int SETTLE_METHOD_MONTH=2;//月结
	
	public final static String ROOT_PATH = "RootPath";
	
	 public final static String SESSION_USER = "SESSION_USER";
	 public final static String ROLE_CUSTOMER = "ROLE_CUSTOMER";
	 
	 public static Map<String, Object> ONLINE_USER = new HashMap<String, Object>();
	 
	 public static final String RESPONSE_ERROR = "-1";
	 
	 /*************************客户常量**********************************/
		/**
	     * 客户状态-新建
	     */		
		public static final int CUSTOMER_STATUS_NEW = 0;
		/**
	     * 客户状态-待审
	     */		
		public static final int CUSTOMER_STATUS_WAIT_CONFIRM = 1;
		/**
	     * 客户状态-拓展中
	     */		
		public static final int CUSTOMER_STATUS_DEVING = 2;
		/**
	     * 客户状态-审核不通过
	     */		
		public static final int CUSTOMER_STATUS_NO_PASS = 3;
		/**
	     * 客户状态-签约
	     */		
		public static final int CUSTOMER_STATUS_SIGN_UP = 4;
		/**
	     * 客户状态-代理放弃
	     */		
		public static final int CUSTOMER_STATUS_ABANDON = 5;
		
		/**
	     * 客户状态-收回拓展权
	     */		
		public static final int CUSTOMER_STATUS_CALL_BACK = 6;
		/**
	     * 客户状态-已过期
	     */		
		public static final int CUSTOMER_STATUS_OVERDUE = 7;
		/**
	     * 客户状态-延期
	     */		  
		public static final int CUSTOMER_STATUS_DEFERRED = 8;
		/**
	     * 客户延期状态-同意延期
	     */		  
		public static final int CUSTOMERS_DEFERRED_AGREE = 80;
		/**
	     * 客户延期状态-不同意延期
	     */		  
		public static final int CUSTOMER_DEFERRED_DISAGREE = 81;
		/**
	     * 客户状态-闲置
	     */		  
		public static final int CUSTOMER_STATUS_IDLE = 9;
		
		/**
	     * 客户状态-移交
	     */		  
		public static final int CUSTOMER_STATUS_DEVOLVE = 10;
		/**
	     * 客户移交状态-同意移交
	     */		  
		public static final int CUSTOMER_DEVOLVE_AGREE = 100;
		
		/**
	     * 客户移交状态-不同意移交
	     */		  
		public static final int CUSTOMER_DEVOLVE_DISAGREE = 101;
		
		/**
	     * 客户移交状态-取消移交
	     */		  
		public static final int CUSTOMER_DEVOLVE_CANCEL = 102;
		

		/**
	     * 客户状态-所有状态
	     * 查询状态
	     */		
		public static final int CUSTOMER_STATUS_ALL = 99;

	   public static final String POST_URL ="http://192.168.222.3:8585/inter/UserRegister!data.action";
	    public static final String APK_CDN = "http://apk.google8cn.com/upload/apk/";
	   // public static final String APK_CDN = "http://192.168.199.172:8082";
	//DDL转发服务从.4转到.3
	    public static final String DDL_FORWORD = "http://192.168.222.4:8480/210.jsp?coo_id=";
	   // public static final String DDL_FORWORD = "http://104.250.137.162:8480/210.jsp?coo_id=";
}
