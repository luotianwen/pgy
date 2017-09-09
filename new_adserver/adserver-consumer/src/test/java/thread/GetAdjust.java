package thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 19:13
 * @author: Xiao Jia
 */
public class GetAdjust {
    private GetAdjust(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/getadjust.action?
    * pname=WifiBooster&
    * imei=123888888553
    * */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String pname = "pname=WifiBooster"; sb.append(pname); sb.append(SEPARATOR);

        String sbStr = sb.toString();
        for (int i = 1; i <= 1000; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // imei
            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + i;
            reqSB.append("imei=");
            reqSB.append(imei);

            System.out.println(imei);
            System.out.println(reqSB.toString());

            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/getadjust.action", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
//                e.printStackTrace();
            }
        }
    }
}
