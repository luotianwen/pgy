package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 19:45
 * @author: martins
 */
public class GetDisList {
    private GetDisList(){}

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/getDisList?
    * cooId=1234567&
    * xcooId=0&
    * imei=123888888553&
    * sdkVersion=1
    * */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String cooId="cooId=1234567"; sb.append(cooId); sb.append(SEPARATOR);
        String xcooId="xcooId=0"; sb.append(xcooId); sb.append(SEPARATOR);

        String sbStr = sb.toString();
        Random random = new Random();
        for (int i = 1; i <= 2; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // sdkVersion
            reqSB.append("sdkversion=7");
//            reqSB.append(random.nextInt(9));
            reqSB.append(SEPARATOR);
            // imei
            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + i;
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);
            System.out.println(reqSB.toString());

//            String resp = HttpUtils.sentPost("http://104.250.137.146:8081/getDisList", reqSB.toString());
            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/getDisList", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
            }
        }
    }
}
