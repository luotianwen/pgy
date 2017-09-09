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
 *          Date: 2016/2/29
 *          Time: 16:59
 * @author: martins
 */
public class GetPushSdkInfor {

    private GetPushSdkInfor(){}

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/newgetPushSdkInfor.action?
    * coo_id=1234567&
    * imei=123888888553&
    * version=1
    * */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String coo_id="coo_id=1234567"; sb.append(coo_id); sb.append(SEPARATOR);

        String sbStr = sb.toString();
        Random random = new Random();
        for (int i = 1; i <= 1; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // version
            reqSB.append("version=2");
//            reqSB.append(random.nextInt(9));
            reqSB.append(SEPARATOR);
            // imei
            String imei = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + i;
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);
            System.out.println(reqSB.toString());

            // ip 1.22.0.0
            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/newservice/newgetPushSdkInfor.action", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
            }
        }
    }
}
