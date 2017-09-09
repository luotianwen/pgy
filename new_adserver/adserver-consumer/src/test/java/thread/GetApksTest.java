package thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/2/29
 *          Time: 17:04
 * @author: Xiao Jia
 */
public class GetApksTest {
    private GetApksTest(){}

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/newgetApks.action?
    * coo_id=1234567&
    * imei=11111111&
    * sdk=2&
    * channelId=an60300&
    * internet=1&
    * type=0&
    * language=zh&
    * country=CN&
    * xc_coo_id=0&
    * sdkversion=5
    * */
    public static void main(String[] args) {
        String sb = "coo_id=123456&imei=868753026222609&channelId=60300&internet=1&type=0&" +
                "language=zh&country=CN&xc_coo_id=0&sdkversion=5";

        while(true){
            // for (int i = 1; i <= 1000000; i++) {
            StringBuilder reqSB = new StringBuilder(sb);
            // sdk
            reqSB.append(SEPARATOR);
            reqSB.append("sdk=2");

            System.out.println("==================" + reqSB.toString());
            WriteFile("getApk-silent", "==================" + reqSB.toString());

           /* String resp = HttpUtils.sentPost("http://104.250.137.146:8081/newservice/newgetApks.action", reqSB.toString());
          */
            String resp = HttpUtils.sentPost("http://127.0.0.1:8180/newservice/newgetApks.action", reqSB.toString());

            try {
                System.out.println(DesUtils.decryptDES(resp));
                WriteFile("getApk-silent", DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
                WriteFile("getApk-silent", resp);
            }
        }
    }

    public static void WriteFile(String fileName, String file) {
       /* if (file == null) return;
        fileName = CalendarFormat.ymdFormat.format(new Date()) +" "+ fileName + ".txt";
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\test-log\\" + fileName, true);
            writer.write(file);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
