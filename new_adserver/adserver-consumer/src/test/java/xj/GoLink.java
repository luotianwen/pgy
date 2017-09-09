package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/2
 *          Time: 11:24
 * @author: martins
 */
public class GoLink {
    private GoLink(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/goLink?
    * adId=1
    * */
    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            int adId = i % 4 == 0 ? 1 : i % 4;
            String adIdStr = "adId=" + adId;
            System.out.println(adId);
//            String resp = HttpUtils.sentPost("http://104.250.137.146:8081/goLink", "" + adIdStr);
            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/goLink", "" + adIdStr);

            System.out.println(resp.toString());

            try {
                System.out.println(DesUtils.decryptDES(resp));
//                System.out.println(DesUtils.flag ? DesUtils.decryptDES(resp) : resp);
            } catch (Exception e) {
                System.out.println(resp);
//                e.printStackTrace();
            }
        }
    }
}
