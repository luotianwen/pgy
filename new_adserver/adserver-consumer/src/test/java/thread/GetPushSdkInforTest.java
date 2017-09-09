package thread;

import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/2/29
 *          Time: 16:59
 * @author: Xiao Jia
 */
public class GetPushSdkInforTest {

    private GetPushSdkInforTest(){}

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/newgetPushSdkInfor.action?
    * coo_id=1234567&
    * imei=123888888553&
    * version=1
    * */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String coo_id="coo_id=123456"; sb.append(coo_id); sb.append(SEPARATOR);

        String sbStr = sb.toString();
//        for (int i = 1; i <= 21; i++) {
            StringBuilder reqSB = new StringBuilder(sbStr);
            // version
            reqSB.append("version=1");
            reqSB.append(SEPARATOR);
            // imei
//            String imei = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + i;
            String imei = "11111111";
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);
            System.out.println(reqSB.toString());

            String resp = HttpUtils.sentPost("http://104.250.137.146:8081/newservice/newgetPushSdkInfor.action", reqSB.toString());
            try {
//                System.out.println(DesUtils.flag?DesUtils.decryptDES(resp):resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
    }
}
