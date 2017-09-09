package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/9
 *          Time: 16:42
 * @author: martins
 */
public class NewjarUpdate {

    public static void main(String[] args) {
        String str="version=20";
//        http://192.168.199.172:8082/newservice/newjarUpdate?version=5
//        String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newjarUpdate", str);
        String resp = HttpUtils.sentPost("http://127.0.0.1:8082/newjarUpdate", str);
        try {
            System.out.println(DesUtils.decryptDES(resp));
        } catch (Exception e) {
            System.out.println(resp);
        }
    }
}
