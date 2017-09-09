package thread;

import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/2/29
 *          Time: 15:27
 * @author: Xiao Jia
 */
public class OpenOrSale {
    private static int time = 1;
    private static final String sbStr = "coo_id=1234567&channelId=123ss&type=1&xc_coo_id=123123&xmodel=m1note&" +
            "xversion=5.1&ximsi=460026029668041&xinternet=1&xoperator=CMCC&xwidth=1080&xheight=1920&sdkversion=2&sdk=2&";

    private OpenOrSale(){}

    /*
    * http://127.0.0.1:8080/newopenOrSale.action?
    * coo_id=1234567&
    * imei=123888888553&
    * sdk=1&
    * channelId=123ss&
    * type=1&
    * xc_coo_id=123123&
    * xmodel=m1note&
    * xversion=5.1&
    * ximsi=460026029668041&
    * xinternet=1&
    * xoperator=CMCC&
    * xwidth=1080&
    * xheight=1920&
    * sdkversion=1.0
    * */
    public static void main(String[] args) {
//        for (int i = 0; i <= 1500; i++) {
//             new ManyThread().start();
//        }
        sendRequest(getTime(), 100000);
    }

    static class ManyThread extends Thread {
//        public static
        @Override
        public void run() {
            sendRequest(getTime(), 100);
        }
    }

    public synchronized static int getTime() {
        return time++;
    }

    public static void sendRequest(int time, int total) {
//        long start;
        for (int i = 1; i <= total; i++) {
//            start = System.currentTimeMillis();
            StringBuilder reqSB = new StringBuilder(sbStr);

            // a[100, 500] b[500, 50] [800, 30] c[1000, 60] d[1500, 60] e[2000, 100] f[3000, 200] ghi[1500, 100] jkl[1, 100000]
            String imei = time + "l" + i;
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);

            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newopenOrSale.action", reqSB.toString());
//            System.out.println(imei + "  :  " + (System.currentTimeMillis() - start));
        }
    }


}
