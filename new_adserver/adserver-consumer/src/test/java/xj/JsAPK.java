package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 17:07
 * @author: martins
 */
public class JsAPK {
    private JsAPK(){}

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/newjsApk.action?
    * coo_id=1234567&
    * pkgid=1&
    * imei=123888888553
    * */
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        String coo_id="coo_id=1234567"; sb.append(coo_id); sb.append(SEPARATOR);

        List<String> list = new ArrayList<>();
        addList(list);

        String sbStr = sb.toString();
        Random random = new Random();

        for (String s : list) {
            StringBuilder reqSB = new StringBuilder(sbStr);

            // pkgid
            reqSB.append("pkgid=");
            reqSB.append(s);
            reqSB.append(SEPARATOR);
            // imei
            String imei = "201603181";
//            String imei = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            reqSB.append("imei=");
            reqSB.append(imei);
            System.out.println(imei);
            System.out.println(reqSB.toString());

            String resp = HttpUtils.sentPost("http://127.0.0.1:8082/newservice/newjsApk.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newjsApk.action", reqSB.toString());
//            String resp = HttpUtils.sentPost("http://104.250.137.146:8081/newservice/newjsApk.action", reqSB.toString());
//            http://127.0.0.1:8080/newjsApk.action?coo_id=1234567&pkgid=1&imei=123888888553
            try {
                System.out.println(DesUtils.decryptDES(resp));
            } catch (Exception e) {
                System.out.println(resp);
//                e.printStackTrace();
            }
        }
    }

    private static void addList(List<String> list) throws IOException {
//        FileReader reader = new FileReader("C:\\Users\\Administrator\\Desktop\\pkgId.txt");
//        BufferedReader bReader = new BufferedReader(reader);
//        String line;
//        while (null != (line = bReader.readLine())) {
//            list.add(line);
//            System.out.println(line);
//        }
        list.add("5350a888c3eb4c9881c9645ab25686fb11256");
    }

}
