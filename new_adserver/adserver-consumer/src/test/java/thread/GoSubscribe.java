package thread;

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
public class GoSubscribe {
    private GoSubscribe(){}

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/goLink?
    * adId=1
    * */
    public static void main(String[] args) {
        for (int i = 1; i <= 10000; i++) {
            int adId = i % 4 == 0 ? 1 : i % 4;
            String adIdStr = "internet=1&imei=imeihao"+i+"d"+adId+"&xmodel=m1note"+ adId+"&xversion=5.1&xoperator=46000&cooId=12213";
            //http://192.168.199.172:8082/goOnline?adId=1&imei=123456&type=1&pkgid=随机数
            // String resp = HttpUtils.sentPost("http://192.168.199.172:8082/goOnline", "" + adIdStr);
//            String resp = HttpUtils.sentPost("http://127.0.0.1:8180/goLink", "" + adIdStr);
            HttpUtils.sentPost("http://127.0.0.1:8080/gosubscribe", "" + adIdStr);
            System.out.println("link请求次数______"+i);
//            http://198.100.96.74:8580/goOnline?adId=-1&imei=1234562&type=2&pkgid=8
        }

//        for(int i = 1;i<60;i++){
//           String mm =  "cooId=10&type=5";
//           HttpUtils.sentPost("http://192.168.199.172:8082/gosublink", "" + mm);
//        }
    }
}
