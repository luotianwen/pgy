package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.util.Random;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/2
 *          Time: 11:31
 * @author: martins
 */
public class CheckUpdate {
    private CheckUpdate(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/checkUpdate?
    * pkgType=1&
    * version=
    * */
    public static void main(String[] args) {
        for (int i = 1; i <= 1; i++) {
            StringBuilder reqSB = new StringBuilder();

            reqSB.append("pkgType=0");
//            reqSB.append(new Random().nextInt(5));
            reqSB.append(SEPARATOR);

            reqSB.append("version=");
            reqSB.append(new Random().nextInt(2)); //随机数为0或1

            System.out.println(reqSB.toString());
            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/checkUpdate", reqSB.toString());
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
//                e.printStackTrace();
            }
        }
    }
}
