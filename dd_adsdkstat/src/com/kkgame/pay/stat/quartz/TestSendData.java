package com.kkgame.pay.stat.quartz;

import com.kkgame.pay.stat.util.HttpUtils;

public class TestSendData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String POST_URL="http://192.168.199.172:8082/newservice/newopenOrSale.action?coo_id=1234567&imei=imeii&sdk=5&channelId=123ss&type=1&xc_coo_id=0&xmodel=m1note&xversion=5.1&ximsi=460026029668041&xinternet=1&xoperator=CMCC&xwidth=1080&xheight=1920";
		for (int i = 1; i <13; i++) {
			String resp = HttpUtils.sentPost(POST_URL.replaceAll("imeii", i+"b776adq1"),"&sdkversion=8");
			System.out.println(resp);
		}
	}

}
