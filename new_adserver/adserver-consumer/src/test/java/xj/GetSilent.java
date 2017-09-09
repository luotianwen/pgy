package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.util.Random;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 18:52
 * @author: martins
 */
public class GetSilent {
    private GetSilent(){}

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

        String coo_id="coo_id=1234567"; sb.append(coo_id); sb.append(SEPARATOR);
        String channelId = "channelId=60300"; sb.append(channelId); sb.append(SEPARATOR);
        String type = "type=2"; sb.append(type); sb.append(SEPARATOR);
        String xc_coo_id = "xc_coo_id=0"; sb.append(xc_coo_id); sb.append(SEPARATOR);
        String internet = "internet=1"; sb.append(internet); sb.append(SEPARATOR);
        String language = "language=zh"; sb.append(language); sb.append(SEPARATOR);
        String country = "country=CN"; sb.append(country); sb.append(SEPARATOR);
        String apkid = "apkid=1,2"; sb.append(apkid); sb.append(SEPARATOR);

        String sbStr = sb.toString();
        Random random = new Random();
        for (int i = 1; i <= 3; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // sdkversion
            reqSB.append("sdkversion=9");
//            reqSB.append(random.nextInt(9));
            reqSB.append(SEPARATOR);
            // sdk
            reqSB.append("sdk=5");
//            reqSB.append(random.nextInt(5));
            reqSB.append(SEPARATOR);
            // imei
//            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + i;
            String imei = "11112242"+i;
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);
            System.out.println(reqSB.toString());

            /*"1.15.255.254"*/

            /*
            * {"plugId":11255,
            * "plugType":0,
            * "plugName":"cps",
            * "plugDownloadURL":"http://cdn.cdn1face.com/cps_160226.apk",
            * "plugPackageName":"cps",
            * "plugSevName":"android.intent.action.qwe
            *
            * */
//            String test = "coo_id=123499&imei=864566020866492&sdk=2&channelId=75&internet=1&language=zh&xc_coo_id=0&xmodel=Lenovo A360t&xversion=4.4.2&ximsi=00000000&xoperator=&xwidth=480&xheight=854&sdkversion=11&apkid=";

//            String resp = HttpUtils.sentPost("http://198.100.96.74:8580/newservice/newgetSilent.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://198.100.96.74:8586/newservice/newgetSilent.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://interface1.addlions.com/newservice/newgetSilent.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newgetSilent.action", reqSB.toString());
            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/newservice/newgetSilent.action", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
            }
        }
    }
}
