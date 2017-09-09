package com.kkgame.pay.stat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Constants {
	public static Map<String, Integer> CITY_MAPS = new Hashtable();
	public static Map<String, Integer> CITY_MAP = new Hashtable();
	public static final int SDK = 600400;
	public static final int LEAD = 600403;
	public static final int SINK = 600404;
	public static final int SILENCE = 600405;
	//public static final String REDIS_HOST ="192.168.0.103";
	public static final String REDIS_HOST ="192.168.222.4";
	public static final String REDIS_PORT ="19000";
	public static final int RECORD_COUNT =2000;
	public static final int SQL_COUNT =5000;
	public static final String ZK_PORT ="2181";
	public static final String ZK_PROXY_DIR = "/zk/codis/db_ddsdk/proxy";
	
	
	public static final String USER_INFO_STAT ="user_info_stat";
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
	public static final String AD_DATA_LINK_UV ="adData_link_uv";
	public static final String AD_DATA_DSSDK_STAT ="adData_dssdk_stat";

	public static final String AD_DATA_OFFERSDK_SHOW = "adData_offersdk_show";
	public static final String AD_DATA_OFFERSDK_CLICK = "adData_offersdk_click";
	public static final String AD_TOTAL_OFFERSDK_INCOME = "pay_offersdk_total";

	public static final String AD_TOTAL_SUB_ACTIVE = "ad_total_active";
	public static final String OFFERSDK_TOTAL_ACTIVE = "offersdk_total_active";
	public static final String AD_TOTAL_SUB_INCOME = "pay_ad_total";
	public static final String COO_TOTAL_SUB_STAT = "coo_total_active";
	public static final String AD_TOTAL_SUB_STAT = "adData_sub_adId";
	public static final String AD_CLICK_DAY = "ad_click_day";
	public static final String COO_CLICK_DAY = "coo_click_day";
	public static final String AD_ACTIVE_DAY = "ad_active_day";
	public static final String COO_ACTIVE_DAY = "coo_active_day";
	public static final String AD_PAY_DAY = "ad_pay_day";
	public static final String OFFERSDK_CLICK_DAY = "offersdk_click_ad_stat_times";
	public static final String OFFERSDK_SHOW_DAY = "offersdk_show_ad_stat_times";
	public static final String OFFERSDK_SHOW_STAT_USERS = "offersdk_show_ad_stat_users";
	public static final String OFFERSDK_ACTIVE_DAY = "offersdk_active_day";
	public static final String OFFERSDK_PAY_DAY = "offersdk_pay_day";
	public static final String AD_TOTAL_OFFERSDK_STAT = "adData_offerSdk_adId";
	//展示数据
	public static final String AD_DATA_SEND_AD_DETAIL_USER = "adData_send_detail";
	public static final String AD_DATA_SEND_PROJECT_DETAIL_USER = "adData_send_project_detail";
	public static final String AD_DATA_SEND_VERSION_DETAIL_USER = "adData_send_version_detail";
	public static final String AD_DATA_SEND_AD_STAT_USERS = "adData_send_ad_stat_users";
	public static final String AD_DATA_SEND_AD_STAT_TIMES = "adData_send_ad_stat_times";
	public static final String OFFERSDK_SEND_AD_STAT_USERS = "offersdk_send_ad_stat_users";
	public static final String OFFERSDK_SEND_AD_STAT_TIMES = "offersdk_send_ad_stat_times";
	public static final String AD_DATA_SEND_PROJECT_STAT_USERS = "adData_send_project_stat_users";
	public static final String AD_DATA_SEND_PROJECT_STAT_TIMES = "adData_send_project_stat_times";
	public static final String AD_DATA_SEND_VERSION_STAT_USERS = "adData_send_version_stat_users";
	public static final String AD_DATA_SEND_VERSION_STAT_TIMES = "adData_send_version_stat_times";

	public static final String AD_DATA_SEND_SUCC_AD_DETAIL_USER = "adData_sendSucc_detail";
	public static final String AD_DATA_SEND_SUCC_PROJECT_DETAIL_USER = "adData_sendSucc_project_detail";
	public static final String AD_DATA_SEND_SUCC_VERSION_DETAIL_USER = "adData_sendSucc_version_detail";
	public static final String OFFERSDK_SEND_SUCC_AD_STAT_USERS = "adData_sendSucc_ad_stat_users";
	public static final String OFFERSDK_SEND_SUCC_AD_STAT_TIMES = "adData_sendSucc_ad_stat_times";
	public static final String AD_DATA_SEND_SUCC_AD_STAT_USERS = "offersdk_sendSucc_ad_stat_users";
	public static final String AD_DATA_SEND_SUCC_AD_STAT_TIMES = "offersdk_sendSucc_ad_stat_times";
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
	public static List<Integer> OFFER_TYPE_LIST = new ArrayList<Integer>();
	public static Map<Integer, String> TYPE_USER_MAP = new HashMap<Integer, String>();
	public static Map<Integer, String> TYPE_ACTIVE_MAP = new HashMap<Integer, String>();
	public static Map<Integer, String> TYPE_PROJECT_ACTIVE_MAP = new HashMap<Integer, String>();
	public static List<Integer> DS_TYPE_LIST = new ArrayList<Integer>();


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

		OFFER_TYPE_LIST.add(600601);
		OFFER_TYPE_LIST.add(600602);
		OFFER_TYPE_LIST.add(600603);
		OFFER_TYPE_LIST.add(600604);
		OFFER_TYPE_LIST.add(600605);
		OFFER_TYPE_LIST.add(600606);
		OFFER_TYPE_LIST.add(600607);
		OFFER_TYPE_LIST.add(600608);

		DS_TYPE_LIST.add(1);
		DS_TYPE_LIST.add(2);
		DS_TYPE_LIST.add(3);
		DS_TYPE_LIST.add(4);
		DS_TYPE_LIST.add(5);
		AD_TYPE_MAP.put(600500, 1);
		AD_TYPE_MAP.put(600506, 2);
		AD_TYPE_MAP.put(600508, 3);
		AD_TYPE_MAP.put(600509, 4);
		AD_TYPE_MAP.put(600501, 5);
		
		LINK_TYPE_MAP.put(1, 1);
		LINK_TYPE_MAP.put(2, 2);
		LINK_TYPE_MAP.put(3, 3);
		LINK_TYPE_MAP.put(4, 4);
		LINK_TYPE_MAP.put(5, 5);
		TYPE_USER_MAP.put(600400, "sdk_users_");
		TYPE_USER_MAP.put(600403, "guide_users_");
		TYPE_USER_MAP.put(600404, "apk_users_");
		TYPE_USER_MAP.put(600405, "slient_users_");


		TYPE_PROJECT_ACTIVE_MAP.put(600400, "sdk_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600403, "guide_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600404, "apk_users_cooid_");
		TYPE_PROJECT_ACTIVE_MAP.put(600405, "slient_users_cooid_");


		TYPE_ACTIVE_MAP.put(600400, "sdk_actusers_");
		TYPE_ACTIVE_MAP.put(600403, "guide_actusers_");
		TYPE_ACTIVE_MAP.put(600404, "apk_actusers_");
		TYPE_ACTIVE_MAP.put(600405, "slient_actusers_");

	}
	
	public static void main(String[] args) {
		String s = "10790";
		System.out.println(s);
	}
}
