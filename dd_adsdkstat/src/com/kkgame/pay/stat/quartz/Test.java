package com.kkgame.pay.stat.quartz;

import com.kkgame.pay.stat.util.Constants;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;

import java.util.Map;

public class Test {
	private static JedisResourcePool jedisPool;

	static {
		StringBuffer hostPort = new StringBuffer();
		hostPort.append(Constants.REDIS_HOST).append(":").append(Constants.ZK_PORT);
		jedisPool = RoundRobinJedisPool.create().curatorClient(hostPort.toString(), 30000)
				.zkProxyDir(Constants.ZK_PROXY_DIR).build();
	}

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		// List<String> dayList = CalendarFormat.getymdList("20150922",
		// "20151031");
		// for(String day:dayList) {
		// sb.append("a_cphc_").append(day).append(" ");
		// }
		// List<String> dayList = CalendarFormat.getymdList("20151001",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_feeback_list_activate_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_feeback_list_click_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_feeback_list_download_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_feeback_list_installed_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_feeback_list_show_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_feeback_pkg_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("adv_sents_list_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("apk_actusers_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(int i=0;i<=9;i++) {
		// for(String day:dayList) {
		// sb.append("sdk_actusers_").append(i).append("_").append(day).append("
		// ");
		// }
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(String day:dayList) {
		// sb.append("apk_users_").append(day).append(" ");
		// }
		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(String day:dayList) {
		// sb.append("sdk_users_").append(day).append(" ");
		// }

		// List<String> dayList = CalendarFormat.getymdList("20150831",
		// "20151031");
		// for(String day:dayList) {
		// sb.append("apk_users_cooid_").append(day).append(" ");
		// }

 		/*List<String> dayList = ad_send_data
	 		for (String day : dayList) {
	 			sb.append("sdk_users_cooid_").append(day).append(" ");
	 	}
 		System.out.println(sb.toString());
		System.out.println(1);*/
		/*try (Jedis jedis = jedisPool.getResource()) {
			 List<String>set=jedis.lrange("ad_send_data", 0, -1);
			//jedis.lrange("ad_send_data", 0, -1);
			System.out.println(set.size());
			 for (String object : set) {
				System.out.println(object);
			}  
		}*/


	 }
}
