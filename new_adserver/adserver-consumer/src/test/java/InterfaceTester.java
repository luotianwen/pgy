public class InterfaceTester extends Thread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InterfaceTester t1 = new InterfaceTester(2);
		t1.start();
		
		InterfaceTester t2 = new InterfaceTester(2);
		t2.start();
		
		InterfaceTester t3 = new InterfaceTester(2);
		t3.start();
		InterfaceTester t4 = new InterfaceTester(2);
		t4.start();
		InterfaceTester t5 = new InterfaceTester(2);
		t5.start();
		InterfaceTester t6 = new InterfaceTester(2);
		t6.start();
		InterfaceTester t7 = new InterfaceTester(2);
		t7.start();
		InterfaceTester t8 = new InterfaceTester(2);
		t8.start();
		InterfaceTester t9 = new InterfaceTester(2);
		t9.start();
		InterfaceTester t10 = new InterfaceTester(2);
		t10.start();
		
		InterfaceTester t11 = new InterfaceTester(1);
		t11.start();
		InterfaceTester t12 = new InterfaceTester(1);
		t12.start();
		InterfaceTester t13 = new InterfaceTester(1);
		t13.start();
		InterfaceTester t14 = new InterfaceTester(1);
		t14.start();
		InterfaceTester t15 = new InterfaceTester(1);
		t15.start();
		InterfaceTester t16 = new InterfaceTester(1);
		t16.start();
		InterfaceTester t17 = new InterfaceTester(1);
		t17.start();
		InterfaceTester t18 = new InterfaceTester(1);
		t18.start();
		InterfaceTester t19 = new InterfaceTester(1);
		t19.start();
		InterfaceTester t20 = new InterfaceTester(1);
		t20.start();
		InterfaceTester t21 = new InterfaceTester(1);
		t21.start();
		InterfaceTester t22 = new InterfaceTester(1);
		t22.start();
		InterfaceTester t23 = new InterfaceTester(1);
		t23.start();
		InterfaceTester t24 = new InterfaceTester(1);
		t24.start();
		InterfaceTester t25 = new InterfaceTester(1);
		t25.start();
		InterfaceTester t26 = new InterfaceTester(1);
		t26.start();
		InterfaceTester t27 = new InterfaceTester(1);
		t27.start();
		InterfaceTester t28 = new InterfaceTester(1);
		t28.start();
		InterfaceTester t29 = new InterfaceTester(1);
		t29.start();
		InterfaceTester t30 = new InterfaceTester(1);
		t30.start();
	}
	
	private int testType ;	//which interface..
	public InterfaceTester(int t) {
		this.testType = t;
	}

	public void run() {
		switch(this.testType){
		case 0: 
			testInit();
			break;
		case 1:
			testGetAdList();
			break;
		case 2:
			testAddLog();
			break;
		}
	}
	
	
	private void testInit() {
		for(int i=0;i<100;i++) {
			String imei = String.format("%s%s%s", Math.round(Math.random()*1000), Math.round(Math.random()*1000),Math.round(Math.random()*1000));
			
			String sdk = "2";
			String xcid = "0";
			StringBuffer data = new StringBuffer();
//			data.append(DesUtils.flag?DesUtils.encryptDES("coo_id"):"coo_id")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1234567"):"1234567");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("imei"):"imei")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(imei):imei);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("sdk"):"sdk")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(sdk):sdk);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("channelId"):"channelId")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("123ss"):"123ss");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("type"):"type")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1"):"1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xc_coo_id"):"xc_coo_id")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(xcid):xcid);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("sdkversion"):"sdkversion")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("2"):"2");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xmodel"):"xmodel")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("m1note"):"m1note");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xversion"):"xversion")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("5.1"):"5.1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("ximsi"):"ximsi")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("460026029668041"):"460026029668041");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xinternet"):"xinternet")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1"):"1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xoperator"):"xoperator")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("46002"):"46002");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xwidth"):"xwidth")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1080"):"1080");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xheight"):"xheight")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1920"):"1920");
//			try{
//				String resp = HttpUtils.sentPost("http://121.201.34.67:8081/advnew/newopenOrSale.action", data.toString());
//
//				System.out.println(DesUtils.flag?DesUtils.decryptDES(resp):resp);
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
		}
	}
	
	private void testGetAdList() {
		for(int i=0;i<100;i++) {
			String imei = String.format("%s%s%s", Math.round(Math.random()*1000), Math.round(Math.random()*1000),Math.round(Math.random()*1000));
			
			String sdk = "2";
			String xcid = "0";
			StringBuffer data = new StringBuffer();
//			data.append(DesUtils.flag?DesUtils.encryptDES("coo_id"):"coo_id")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1234567"):"1234567");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("imei"):"imei")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(imei):imei);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("sdk"):"sdk")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(sdk):sdk);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("channelId"):"channelId")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("123ss"):"123ss");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("type"):"type")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1"):"1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("language"):"language")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("en"):"en");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xc_coo_id"):"xc_coo_id")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(xcid):xcid);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("sdkversion"):"sdkversion")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("2"):"2");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xmodel"):"xmodel")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("m1note"):"m1note");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xversion"):"xversion")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("5.1"):"5.1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("ximsi"):"ximsi")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("460026029668041"):"460026029668041");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xinternet"):"xinternet")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1"):"1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xoperator"):"xoperator")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("46002"):"46002");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xwidth"):"xwidth")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1080"):"1080");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xheight"):"xheight")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1920"):"1920");
//			try{
//				String url = "http://121.201.34.67:8081/advnew/newgetApks.action";
////				url = "http://localhost:6873/kok_advserver/newgetApks.action";
//				String resp = HttpUtils.sentPost(url, data.toString());
//				if(resp != null) {
//					System.out.println(DesUtils.flag?DesUtils.decryptDES(resp):resp);
//				}
//				else {
//					System.out.println("response testGetAdList null..........................");
//				}
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
		}
	}


	private void testAddLog() {
		for(int i=0;i<100;i++) {
			String imei = String.format("%s%s%s", Math.round(Math.random()*1000), Math.round(Math.random()*1000),Math.round(Math.random()*1000));
			
			String sdk = "2";
			String xcid = "0";
			StringBuffer data = new StringBuffer();
//			data.append(DesUtils.flag?DesUtils.encryptDES("coo_id"):"coo_id")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1234567"):"1234567");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("imei"):"imei")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(imei):imei);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("sdk"):"sdk")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(sdk):sdk);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("channelId"):"channelId")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("123ss"):"123ss");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("type"):"type")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1"):"1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xc_coo_id"):"xc_coo_id")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(xcid):xcid);
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("sdkversion"):"sdkversion")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("2"):"2");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xmodel"):"xmodel")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("m1note"):"m1note");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xversion"):"xversion")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("5.1"):"5.1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("ximsi"):"ximsi")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("460026029668041"):"460026029668041");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xinternet"):"xinternet")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1"):"1");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xoperator"):"xoperator")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("46002"):"46002");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xwidth"):"xwidth")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1080"):"1080");
//
//			data.append("&");
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("xheight"):"xheight")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES("1920"):"1920");
//
//			data.append("&");
//			JSONObject jsInfo = new JSONObject();
//			jsInfo.put("pkgid", UUID.randomUUID().toString());
//			JSONArray logs = new JSONArray();
//			JSONObject log = new JSONObject();
//			log.put("apkid", 11206);
//			log.put("sdk", 2);
//			log.put("sdkType", 1);
//			log.put("dataType", 1);
//			logs.add(log);
//			jsInfo.put("infors", logs);
//
//			String infos = String.format("{\"infors\":[%s]}",jsInfo.toString());
//
//			data.append(DesUtils.flag?DesUtils.encryptDES("infors"):"infors")
//			.append("=").append(DesUtils.flag?DesUtils.encryptDES(infos):infos);
//
//			try{
//				String resp = HttpUtils.sentPost("http://121.201.34.67:8081/advnew/newbackDatas.action", data.toString());
//
//				System.out.println(DesUtils.flag?DesUtils.decryptDES(resp):resp);
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
		}
	}
	
}
