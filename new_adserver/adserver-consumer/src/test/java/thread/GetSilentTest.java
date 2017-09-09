package com.kokmobi.server.test.thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.util.Random;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 18:52
 * @author: Xiao Jia
 */
public class GetSilentTest {
    private GetSilentTest(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/newgetSilent.action?
    * coo_id=a603010&
    * imei=11111111&
    * sdk=1&
    * channelId=an60300&
    * internet=1&
    * type=0&
    * language=zh&
    * country=CN&
    * xc_coo_id=0&
    * sdkversion=4&
    * apkid=1,2
    * */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String coo_id="coo_id=123456"; sb.append(coo_id); sb.append(SEPARATOR);
        String channelId = "channelId=60300"; sb.append(channelId); sb.append(SEPARATOR);
        String type = "type=0"; sb.append(type); sb.append(SEPARATOR);
        String xc_coo_id = "xc_coo_id=0"; sb.append(xc_coo_id); sb.append(SEPARATOR);
        String internet = "internet=1"; sb.append(internet); sb.append(SEPARATOR);
        String language = "language=zh"; sb.append(language); sb.append(SEPARATOR);
        String country = "country=CN"; sb.append(country); sb.append(SEPARATOR);
        String apkid = "apkid=1,2"; sb.append(apkid); sb.append(SEPARATOR);

        String sbStr = sb.toString();
        Random random = new Random();

        while(true){

            // for (int i = 1; i <= 1; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // sdkversion
            reqSB.append("sdkversion=4");
//            reqSB.append(random.nextInt(9));
            reqSB.append(SEPARATOR);
            // sdk
            reqSB.append("sdk=5");
//            reqSB.append(random.nextInt(5));
            reqSB.append(SEPARATOR);
            // imei
//            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + i;
            String imei = "868753026222609";
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);
            System.out.println(reqSB.toString());

//            String resp = HttpUtils.sentPost("http://104.250.137.146:8081/newservice/newgetSilent.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newgetSilent.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://198.100.96.74:8580/newservice/newgetSilent.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://interface.ddlions.com/newservice/newgetSilent.action?coo_id=75&imei=864566020866492&sdk=5&channelId=75&internet=1&language=zh&xc_coo_id=75&xmodel=Lenovo A360t&xversion=4.4.2&ximsi=00000000&xoperator=&xwidth=480&xheight=854&sdkversion=10&apkid=\n" +
//                    "           ", reqSB.toString());
            String resp = HttpUtils.sentPost("http://127.0.0.1:8180/newservice/newgetSilent.action", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
            }
        }
    }
}
