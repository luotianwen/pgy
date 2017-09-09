package thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/2
 *          Time: 11:13
 * @author: Xiao Jia
 */
public class GetSHDisListTest {
    private GetSHDisListTest(){}

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/getSHDisList?
    * cooId=1234567&
    * imei=123888888553&
    * sdkVersion=1
    * xcooId=0&
    * */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String cooId="cooId=1234567"; sb.append(cooId); sb.append(SEPARATOR);
        String xcooId="xcooId=0"; sb.append(xcooId); sb.append(SEPARATOR);

        String sbStr = sb.toString();
        //for (int i = 1; i <= 10000; i++) {
        while(true){
            StringBuilder reqSB = new StringBuilder(sbStr);
            // sdkVersion
            reqSB.append("sdkversion=2");
//            reqSB.append(random.nextInt(9));
            reqSB.append(SEPARATOR);
            // imei
            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) ;
            reqSB.append("imei=");
            reqSB.append(imei);

            System.out.println(imei);
            System.out.println(reqSB.toString());

            String resp = HttpUtils.sentPost("http://127.0.0.1:8180/getSHDisList", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
//                e.printStackTrace();
            }
        }
    }
}
