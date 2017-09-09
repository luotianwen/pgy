package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.util.Random;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/2/29
 *          Time: 15:27
 * @author: martins
 */
public class OpenOrSale {
    private OpenOrSale(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/newopenOrSale.action?
    * coo_id=1234567&
    * imei=123888888553&
    * sdk=1&
    * channelId=123ss&
    * type=1&
    * xc_coo_id=123123&
    * xmodel=m1note&
    * xversion=5.1&
    * ximsi=460026029668041&
    * xinternet=1&
    * xoperator=CMCC&
    * xwidth=1080&
    * xheight=1920&
    * sdkversion=1.0
    * */
    public static void main(String[] args) {
//        while (1 == 1) {
//            int i = new Random().nextInt(10000) + 1;
            sendRequest(3);
//            try {
//                Thread.sleep(60*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void sendRequest(int total) {
        StringBuilder sb = new StringBuilder();

        String coo_id="coo_id=1234567"; sb.append(coo_id); sb.append(SEPARATOR);
//        String channelId = "channelId=123"; sb.append(channelId); sb.append(SEPARATOR);
//        String type = "type=1"; sb.append(type); sb.append(SEPARATOR);
        String xc_coo_id = "xc_coo_id=123123"; sb.append(xc_coo_id); sb.append(SEPARATOR);
        String xmodel="xmodel=m1note"; sb.append(xmodel); sb.append(SEPARATOR);
        String xversion = "xversion=5.1"; sb.append(xversion); sb.append(SEPARATOR);
        String ximsi = "ximsi=460026029668041"; sb.append(ximsi); sb.append(SEPARATOR);
        String xinternet = "xinternet=1"; sb.append(xinternet); sb.append(SEPARATOR);
        String xoperator = "xoperator=CMCC"; sb.append(xoperator); sb.append(SEPARATOR);
        String xwidth = "xwidth=1080"; sb.append(xwidth); sb.append(SEPARATOR);
        String xheight = "xheight=1920"; sb.append(xheight); sb.append(SEPARATOR);

        String sbStr = sb.toString();

        Random random = new Random();
        for (int i = 1; i <= total; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // channelId
            reqSB.append("channelId=");
            reqSB.append("" + (random.nextInt(3) + 1) + random.nextInt(3) + random.nextInt(3));
            reqSB.append(SEPARATOR);
            // channelId
            reqSB.append("type=");
            int type = random.nextInt(2) + 1;
            reqSB.append(type);
            reqSB.append(SEPARATOR);
            // sdkversion
            reqSB.append("sdkversion=2");
//                reqSB.append(random.nextInt(9));
            reqSB.append(SEPARATOR);
            // sdk
            reqSB.append("sdk=2");
    //            reqSB.append(random.nextInt(5));
            reqSB.append(SEPARATOR);
            // imei
//            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + i;
//            String imei = "2016031417" + i;      // 14号 【1，500】 sdk

            /*
            * 20160310182018
                null
                20160310182019
            * */
//            String imei = time + "a" + i;
//            String imei = "201603018123124" ; // 4号【1，1000】  sdk
//            String imei = "201603051541" + i; // 5号【1，419】 silent
//            String imei = "201603051542" + i; // 5号【420，1000】 silent
//            String imei = "20160310|" + i;  // 7号【1000，1999】 sdk
            String imei = "66" + i;    // 8号【1，10000】 sdk
//            String imei = "201603051541" + i;    // 9号【500，899】 silent
//            String imei = "2016031411a" + i;
//            String imei = "2016031509a" + i; //sdk [1, 100]
//            String imei;
//            if (type == 1) {
//                imei = "20160310|" + total;
//            } else {
////                i--;
//                imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + total;
//            }
            reqSB.append("imei=");
            reqSB.append(imei);

            System.out.println(imei);
            System.out.println(reqSB.toString());

//            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newopenOrSale.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://104.250.137.146:8081/newservice/newopenOrSale.action", reqSB.toString());
            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/newservice/newopenOrSale.action", reqSB.toString());
            try {

                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
//                    e.printStackTrace();
                System.out.println(resp);
            }
        }
    }


}
