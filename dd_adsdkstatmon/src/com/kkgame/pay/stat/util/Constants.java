package com.kkgame.pay.stat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Constants {
	public static boolean ISRUNIFRAMEACTIVE = false;
	public static boolean ISRUNIFRAME = false;
    public static boolean ISRUNSUBLINK = false;
	public static boolean ISRUNSUB = false;
	public static boolean ISRUN=false;
	public static boolean ISRUNXL=false;
	public static boolean ISRUNXMXL=false;
	public static boolean ISRUNHY=false;
	public static boolean ISRUNXF=false;
	public static boolean ISRUNJS=false;
	public static boolean ISRUNZS=false;
	public static boolean ISRUNDJ=false;
	public static boolean ISRUNXZ=false;
	public static boolean ISRUNAZ=false;
	public static boolean ISRUNJH=false;
	public static boolean ISRUNLINK=false;
	public static boolean ISRUNDSSDK=false;
	public static boolean ISRUNOFFERSDK = false;
	public static Map<String, Integer> CITY_MAPS = new Hashtable();
	public static Map<String, Integer> CITY_MAP = new Hashtable();
	public static final int SDK = 600400;
	public static final int LEAD = 600403;
	public static final int SINK = 600404;
	public static final int SILENCE = 600405;
	//public static final String REDIS_HOST ="192.168.222.7:2181,192.168.222.50:2181,192.168.222.59:2181,192.168.222.6:2181,192.168.222.5";
	public static final String REDIS_HOST ="192.168.222.4";
	public static final String REDIS_PORT ="19000";
	public static final int RECORD_COUNT =10;
	public static final int SQL_COUNT =10;
	public static final String ZK_PORT ="2181";
	public static final String ZK_PROXY_DIR = "/zk/codis/db_ddsdk/proxy";
	public static final String USER_INFO_DAY ="user_info_day";
	public static final String USER_INFO_PROJECT_STAT ="user_info_project_stat";
	public static final String USER_INFO_VERSION_STAT ="user_info_version_stat";
	public static final String USER_INFO_PROJECT_VERSION_STAT ="user_info_project_version_stat";
	//用户活跃数据
	public static final String USER_ACTIVE_STAT ="user_active_stat";
	public static final String USER_ACTIVE_REGISTER_STAT ="user_active_register_stat";
	public static final String USER_ACTIVE_PROJECT_STAT ="user_active_project_stat";
	public static final String USER_ACTIVE_VERSION_STAT ="user_active_version_stat";
	public static final String USER_ACTIVE_PROJECT_VERSION_STAT ="user_active_project_version_stat";
	
	public static final String AD_DATA_LINK_STAT ="adData_link_stat";

	
	//展示数据
	public static final String AD_DATA_SEND_AD_DETAIL_USER = "adData_send_detail";
	public static final String AD_DATA_SEND_PROJECT_DETAIL_USER = "adData_send_project_detail";
	public static final String AD_DATA_SEND_VERSION_DETAIL_USER = "adData_send_version_detail";
	public static final String AD_DATA_SEND_AD_STAT_USERS = "adData_send_ad_stat_users";
	public static final String AD_DATA_SEND_AD_STAT_TIMES = "adData_send_ad_stat_times";
	public static final String AD_DATA_SEND_PROJECT_STAT_USERS = "adData_send_project_stat_users";
	public static final String AD_DATA_SEND_PROJECT_STAT_TIMES = "adData_send_project_stat_times";
	public static final String AD_DATA_SEND_VERSION_STAT_USERS = "adData_send_version_stat_users";
	public static final String AD_DATA_SEND_VERSION_STAT_TIMES = "adData_send_version_stat_times";

	public static final String AD_DATA_SEND_SUCC_AD_DETAIL_USER = "adData_sendSucc_detail";
	public static final String AD_DATA_SEND_SUCC_PROJECT_DETAIL_USER = "adData_sendSucc_project_detail";
	public static final String AD_DATA_SEND_SUCC_VERSION_DETAIL_USER = "adData_sendSucc_version_detail";
	public static final String AD_DATA_SEND_SUCC_AD_STAT_USERS = "adData_sendSucc_ad_stat_users";
	public static final String AD_DATA_SEND_SUCC_AD_STAT_TIMES = "adData_sendSucc_ad_stat_times";
	public static final String AD_DATA_SEND_SUCC_PROJECT_STAT_USERS = "adData_sendSucc_project_stat_users";
	public static final String AD_DATA_SEND_SUCC_PROJECT_STAT_TIMES = "adData_sendSucc_project_stat_times";
	public static final String AD_DATA_SEND_SUCC_VERSION_STAT_USERS = "adData_sendSucc_version_stat_users";
	public static final String AD_DATA_SEND_SUCC_VERSION_STAT_TIMES = "adData_sendSucc_version_stat_times";

	public static final String AD_DATA_SHOW_AD_DETAIL_USER = "adData_show_detail";
	public static final String AD_DATA_SHOW_PROJECT_DETAIL_USER = "adData_show_project_detail";
	public static final String AD_DATA_SHOW_VERSION_DETAIL_USER = "adData_show_version_detail";
	public static final String AD_DATA_SHOW_AD_STAT_USERS = "adData_show_ad_stat_users";
	public static final String AD_DATA_SHOW_AD_STAT_TIMES = "adData_show_ad_stat_times";
	public static final String AD_DATA_SHOW_PROJECT_STAT_USERS = "adData_show_project_stat_users";
	public static final String AD_DATA_SHOW_PROJECT_STAT_TIMES = "adData_show_project_stat_times";
	public static final String AD_DATA_SHOW_VERSION_STAT_USERS = "adData_show_version_stat_users";
	public static final String AD_DATA_SHOW_VERSION_STAT_TIMES = "adData_show_version_stat_times";

	public static final String AD_DATA_CLICK_AD_DETAIL_USER = "adData_click_detail";
	public static final String AD_DATA_CLICK_PROJECT_DETAIL_USER = "adData_click_project_detail";
	public static final String AD_DATA_CLICK_VERSION_DETAIL_USER = "adData_click_version_detail";
	public static final String AD_DATA_CLICK_AD_STAT_USERS = "adData_click_ad_stat_users";
	public static final String AD_DATA_CLICK_AD_STAT_TIMES = "adData_click_ad_stat_times";
	public static final String AD_DATA_CLICK_PROJECT_STAT_USERS = "adData_click_project_stat_users";
	public static final String AD_DATA_CLICK_PROJECT_STAT_TIMES = "adData_click_project_stat_times";
	public static final String AD_DATA_CLICK_VERSION_STAT_USERS = "adData_click_version_stat_users";
	public static final String AD_DATA_CLICK_VERSION_STAT_TIMES = "adData_click_version_stat_times";

	public static final String AD_DATA_DOWNLOAD_AD_DETAIL_USER = "adData_download_detail";
	public static final String AD_DATA_DOWNLOAD_PROJECT_DETAIL_USER = "adData_download_project_detail";
	public static final String AD_DATA_DOWNLOAD_VERSION_DETAIL_USER = "adData_download_version_detail";
	public static final String AD_DATA_DOWNLOAD_AD_STAT_USERS = "adData_download_ad_stat_users";
	public static final String AD_DATA_DOWNLOAD_AD_STAT_TIMES = "adData_download_ad_stat_times";
	public static final String AD_DATA_DOWNLOAD_PROJECT_STAT_USERS = "adData_download_project_stat_users";
	public static final String AD_DATA_DOWNLOAD_PROJECT_STAT_TIMES = "adData_download_project_stat_times";
	public static final String AD_DATA_DOWNLOAD_VERSION_STAT_USERS = "adData_download_version_stat_users";
	public static final String AD_DATA_DOWNLOAD_VERSION_STAT_TIMES = "adData_download_version_stat_times";
	
	public static final String AD_DATA_INSTALLED_AD_DETAIL_USER = "adData_installed_detail";
	public static final String AD_DATA_INSTALLED_PROJECT_DETAIL_USER = "adData_installed_project_detail";
	public static final String AD_DATA_INSTALLED_VERSION_DETAIL_USER = "adData_installed_version_detail";
	public static final String AD_DATA_INSTALLED_AD_STAT_USERS = "adData_installed_ad_stat_users";
	public static final String AD_DATA_INSTALLED_AD_STAT_TIMES = "adData_installed_ad_stat_times";
	public static final String AD_DATA_INSTALLED_PROJECT_STAT_USERS = "adData_installed_project_stat_users";
	public static final String AD_DATA_INSTALLED_PROJECT_STAT_TIMES = "adData_installed_project_stat_times";
	public static final String AD_DATA_INSTALLED_VERSION_STAT_USERS = "adData_installed_version_stat_users";
	public static final String AD_DATA_INSTALLED_VERSION_STAT_TIMES = "adData_installed_version_stat_times";

	public static final String AD_DATA_ACTIVATE_AD_DETAIL_USER = "adData_activate_detail";
	public static final String AD_DATA_ACTIVATE_PROJECT_DETAIL_USER = "adData_activate_project_detail";
	public static final String AD_DATA_ACTIVATE_VERSION_DETAIL_USER = "adData_activate_version_detail";
	public static final String AD_DATA_ACTIVATE_AD_STAT_USERS = "adData_activate_ad_stat_users";
	public static final String AD_DATA_ACTIVATE_AD_STAT_TIMES = "adData_activate_ad_stat_times";
	public static final String AD_DATA_ACTIVATE_PROJECT_STAT_USERS = "adData_activate_project_stat_users";
	public static final String AD_DATA_ACTIVATE_PROJECT_STAT_TIMES = "adData_activate_project_stat_times";
	public static final String AD_DATA_ACTIVATE_VERSION_STAT_USERS = "adData_activate_version_stat_users";
	public static final String AD_DATA_ACTIVATE_VERSION_STAT_TIMES = "adData_activate_version_stat_times";
	
	public static List<Integer> AD_TYPE_LIST = new ArrayList<Integer>();
	public static Map<Integer,Integer> AD_TYPE_MAP = new HashMap<Integer,Integer>();
	public static Map<Integer,Integer> LINK_TYPE_MAP = new HashMap<Integer,Integer>();
	public static List<Integer> TYPE_LIST = new ArrayList<Integer>();
	public static List<Integer> LINK_TYPE_LIST = new ArrayList<Integer>();
	public static Map<Integer, String> TYPE_USER_MAP = new HashMap<Integer, String>();
	public static Map<Integer, String> TYPE_ACTIVE_MAP = new HashMap<Integer, String>();
	public static Map<Integer, String> TYPE_PROJECT_ACTIVE_MAP = new HashMap<Integer, String>();

	static {
		TYPE_LIST.add(600400);//sdk
		TYPE_LIST.add(600403);//引导
		TYPE_LIST.add(600404);//下沉
		TYPE_LIST.add(600405);//静默
		TYPE_LIST.add(600406);//链接
		TYPE_LIST.add(600407);//海外线下

		AD_TYPE_LIST.add(600500);//push
		AD_TYPE_LIST.add(600506);//插屏
		AD_TYPE_LIST.add(600508);//下沉
		AD_TYPE_LIST.add(600509);//引导
		AD_TYPE_LIST.add(600501);//静默
		
		LINK_TYPE_LIST.add(1);
		LINK_TYPE_LIST.add(2);
		LINK_TYPE_LIST.add(3);
		LINK_TYPE_LIST.add(4);
		
		
		AD_TYPE_MAP.put(600500, 1);
		AD_TYPE_MAP.put(600506, 2);
		AD_TYPE_MAP.put(600508, 3);
		AD_TYPE_MAP.put(600509, 4);
		AD_TYPE_MAP.put(600501, 5);
		
		LINK_TYPE_MAP.put(1, 1);
		LINK_TYPE_MAP.put(2, 2);
		LINK_TYPE_MAP.put(3, 3);
		LINK_TYPE_MAP.put(4, 4);
		
		TYPE_USER_MAP.put(600400, "sdk_users_");
		TYPE_USER_MAP.put(600403, "guide_users_");
		TYPE_USER_MAP.put(600404, "apk_users_");
		TYPE_USER_MAP.put(600405, "slient_users_");
		TYPE_USER_MAP.put(600406, "websdk_users_");
		TYPE_USER_MAP.put(600407, "offline_users_");

		TYPE_PROJECT_ACTIVE_MAP.put(600400, "sdk_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600403, "guide_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600404, "apk_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600405, "slient_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600406, "websdk_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600407, "offline_users_cooid_");

		TYPE_ACTIVE_MAP.put(600400, "sdk_actusers_");
		TYPE_ACTIVE_MAP.put(600403, "guide_actusers_");
		TYPE_ACTIVE_MAP.put(600404, "apk_actusers_");
		TYPE_ACTIVE_MAP.put(600405, "slient_actusers_");
		TYPE_ACTIVE_MAP.put(600406, "websdk_actusers_");
		TYPE_ACTIVE_MAP.put(600407, "offline_actusers_");
	}
	
	public static void main(String[] args) {
		String s = "10790";
		System.out.println(s);
	}
}
