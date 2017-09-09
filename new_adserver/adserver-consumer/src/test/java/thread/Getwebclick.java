package thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/2
 *          Time: 11:03
 * @author: Xiao Jia
 */
public class Getwebclick {
    private Getwebclick(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/goLink?
    * adId=1
    * */
    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            String adIdStr = "imei=g" + i;
             HttpUtils.sentPost("http://192.168.199.172:8082/getwebclick?coo_id=88&sdkversion=1&internet=1&operator=1&type=1&imsi=1&operatorn=1", "" + adIdStr);
            System.out.println(i);
        }
    }
}
