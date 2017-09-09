package thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/2
 *          Time: 11:24
 * @author: Xiao Jia
 */
public class GoLinkTest {
    private GoLinkTest(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/goLink?
    * adId=1
    * */
    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            int adId = i % 4 == 0 ? 1 : i % 4;
            String adIdStr = "adId=" + adId;
            System.out.println(adId);
            //String resp = HttpUtils.sentPost("http://192.168.199.172:8082/goLink", "" + adIdStr);
            String resp = HttpUtils.sentPost("http://198.100.96.74:7280/goLink", "" + adIdStr);
            System.out.println(resp);

            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
//                e.printStackTrace();
            }
        }
    }
}
