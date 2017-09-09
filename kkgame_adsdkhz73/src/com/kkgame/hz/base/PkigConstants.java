package com.kkgame.hz.base;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class PkigConstants {

	public static ServletContext KKFUN_WEB_CONTEXT = null;
	
	/**
	 * 产品包文件保存的路径
	 */
	public final static String PACKAGE_PROJECT_SAVE_PATH = "D:/project/";
	
	public final static String DOCUMENT_PROJECT_SAVE_PATH = "D:/project/";
	
	/**
	 * 电子结算报表模板
	 */
	public static String EXCEL_TEPLATE_PATH = "";
    public static final String REPORT_TEMPLATE_PROJECT ="project.xls";

	/**
	 * 运营平台地址
	 */
	public static final String URL_PROJECT ="http://ad.cloudmob.mobi/inter/Inter!data.action";
	/**
	 * DDL平台创建产品链接
	 */
     public static final String URL_DDL_PROJECT ="http://104.250.137.162:8280/product.jsp";
	/**
	 * DDL平台创建渠道链接
	 */
	public static final String URL_DDL_CUSTOMER ="http://104.250.137.162:8280/channel.jsp";
	/**
	 * 老SDK平台创建项目地址
	 */
	public static final String URL_SDK_PROJECT ="http://104.250.137.194:8581/product.jsp";
	/*************************财务常量**********************************/
	/**
	 * 公司固定税率
	 * */
	public static final double KKFUN_RATE=5.5;
	/**
	 * 移动税率
	 */
	public static final double YD_RATE=15;
	/**
	 * 联通税率
	 */
	public static final double LT_RATE=30;
	
	
	/**
     * 分页-每页显示条数
     */	
	public final static int PAGE_SIZE = 30;
	/**
     * 分页-页数
     */
	public final static int PAGE_NUM = 1;
	/**
     * 分页-每页显示条数list
     */
	public final static String PAGE_SIZE_LIST = "15,20,30,50,100";
	/**
     * 项目根目录
     */
	public static String KKFUN_WEB_ROOT = "";

	
	/*************************系统角色常量**********************************/
	
	/**
     * 用户类型-超级管理员
     */
	public static final String USER_TYPE_SUPER = "SP";
	/**
     * 用户类型-商务助理
     */	
	public static final String USER_TYPE_MANAGER = "MG";
	/**
     * 用户类型-代理商
     */	
	public static final String USER_TYPE_AGENT = "AG";
	/**
     * 用户类型-商务拓展人员
     */	
	public static final String USER_TYPE_BD = "BD";
	/**
     * 用户类型-客户
     */	
	public static final String USER_TYPE_CUSTOMER = "CM";
	/**
     * 用户类型-商务协助人员
     */	
	public static final String USER_TYPE_BH = "BH";
	/**
     * 用户类型-CP
     */	
	public static final String USER_TYPE_CP = "CP";
	/**
     * 用户类型-技术人员
     */	
	public static final String USER_TYPE_TECHNICIAN ="TC";
	/**
     * 用户类型-测试人员
     */	
	public static final String USER_TYPE_TESTER="TS";
	
	public static final String USER_TYPE_MIDDLER="MD";	
	
	   /**
     * 用户类型-彩票部
     */ 
    public static final String USER_TYPE_LOTTERY_DIVISION="LD";
    /**
     * 用户类型-产品部
     */ 
    public static final String USER_TYPE_PRODUCT="PD";
 
    /**
     * 用户类型-财务部
     */ 
    public static final String USER_TYPE_FINANCIAL_DEPARTMENT="FD";
    
    /**
     * 用户类型-运营中心
     */ 
    public static final String USER_TYPE_OPERATIONS_CENTER="OC";
    /**
     * 用户类型-单机游戏部
     */    
    public static final String USER_TYPE_SINGLE_GAME="SG";

    /**
     * 用户类型-新极地(权限：可以查看快乐风客户信息)
     */
    public static final String USER_TYPE_XJD="XJ";
    
    /**
     * 用户类型-其他公司测试人员
     * 权限：销量记录、MO清单查询、MT清单查询
     */
    public static final String USER_TYPE_OT="OT";
    
    /**
     * 技术值班人员
     */
    public static final String USER_TYPE_DT="DT";
    
	
	/*************************session角色保存常量**********************************/	
	/**
     * PORTALUSER SESSION 
     */
	public final static String SESSION_PORTALUSER = "SESSION_PORTALUSER";
	/**
     * 代理商 SESSION 
     */
	public static final String SESSION_AGENT = "SESSION_AGENT";
	/**
     * 商务拓展人员 SESSION 
     */
	public static final String SESSION_BD = "SESSION_BD";
	/**
     * 客户 SESSION 
     */
	public static final String SESSION_CUSTOMER = "SESSION_CUSTOMER";
	/**
     * 商务协助人员 SESSION 
     */
	public static final String SESSION_BH = "SESSION_BH";

	/*************************用户账号状态常量****************************/
	/**
     * 用户状态-正常
     */	
	public static final int USER_STATUS_NORMAL = 0;
	/**
     * 用户状态-受限
     */		
	public static final int USER_STATUS_CONFINE = 1;
	/**
     * 用户状态-锁定
     */		
	public static final int USER_STATUS_LOCKED = 2;

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
	
	/*************************项目常量**********************************/	
	/**
     * 项目状态-新建
     */	
	public static final int STATUS_NEW = 0;
	/**
     * 项目状态-待技术审核
     */		
	public static final int PROJECT_STATUS_APPLY_PACKAGE = 1;
	/**
     * 项目状态-参数错误错误
     */		
	public static final int PROJECT_STATUS_PARAMS_ERROR = 2;
	/**
     * 项目状态-待出包
     */	
	public static final int PROJECT_STATUS_BUILDING = 3;
	/**
     * 项目状态-移植跟进
     */	
	public static final int PROJECT_STATUS_EMBEDING = 4;
	/**
     * 项目状态-待样机测试
     */		
	public static final int PROJECT_STATUS_TESTING = 5;
	/**
     * 项目状态-测试通过
     */		
	public static final int PROJECT_STATUS_TEST_PASS = 6;
	
	public final static Map projectStatus = new LinkedHashMap<Integer, String>();   
    static {
        projectStatus.put(0, "新建");
        projectStatus.put(1, "待审");
        projectStatus.put(2, "参数错误");
        projectStatus.put(3, "待出包");
        projectStatus.put(4, "移植跟进");
        projectStatus.put(5, "样机测试");
        projectStatus.put(6, "测试通过");
        projectStatus.put(12, "技术放弃");
        projectStatus.put(13, "商务放弃");        
    }
	
	
	/*************************项目价格审核状态常量**********************************/
	/**
     * 项目价格审核状态-未提交审核
     */	 
 	public static final int PROJECT_VERIFIONSTATUS_NEW = 0;
	/**
     * 项目价格审核状态-待审核
     */	
	public static final int PROJECT_VERIFIONSTATUS_WAIT_CONFIRM = 1;
	/**
     * 项目价格审核状态-初审通过的
     */	
	public static final int PROJECT_VERIFIONSTATUS_FIRST_TRIAL = 2;
	/**
     * 项目价格审核状态-审核通过
     */	
	public static final int PROJECT_VERIFIONSTATUS_CONFIRM = 3;
	
	
	
	/*************************产品常量**********************************/
	/**
     * 产品是否关闭内置-否
     * 
     */	
	public static final int PRODUCT_ACTIVE = 0;

	
	/*************************项目合作模式常量**********************************/
	public static final int PROJECT_SALE_FEE_SIGN_OPEN = 3;
	/**
     * 合作模式
     * 分成
     */
	public static final int PROJECT_COOPERATEMODE_FEE = 1;
	/**
     * 合作模式
     * 销量
     */
	public static final int PROJECT_COOPERATEMODE_SALE = 2;
	


    /************************* 测试手机号 **********************************/
	public final static Map mobile = new LinkedHashMap<String, String>();	 

	
    public static final int PROJECT_DT_PRODUCT = 1;
    

    public final static Map shBd = new LinkedHashMap<Integer, String>();   

	
    public static final int DIVIDE_STATUS_SUCCESS = 0;
    public static final int DIVIDE_STATUS_LOGIN = 1;
    public static final int DIVIDE_STATUS_SYSTEM = 2;   
    
    public static final String BUSINESS_DEVELOPER = "SESSION_BUSINESS_DEVELOPER";
}
