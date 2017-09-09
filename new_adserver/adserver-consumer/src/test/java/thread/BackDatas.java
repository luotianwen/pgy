package thread;

import com.kokmobi.server.util.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 17:04
 * @author: Xiao Jia
 */
public class BackDatas {
    private BackDatas(){}

    private static final String str = "coo_id=1234567&channelId=123ss&type=2&xc_coo_id=1&xmodel=m1note&xversion=5.1&sdkversion=5&" +
            "ximsi=460026029668041&xinternet=1&xoperator=CMCC&xwidth=1080&xheight=1920&";

    private static final String SEPARATOR="&";
    /*
    * http://127.0.0.1:8080/newbackDatas.action?
    * coo_id=1234567&
    * xc_coo_id=1&
    * imei=123888888553&
    * xmodel=m1note&
    * xversion=5.1&
    * ximsi=460026029668041&
    * xinternet=1&
    * xoperator=CMCC&
    * xwidth=1080&
    * xheight=1920&
    * sdkversion=1.0&
    * infors={"infors":[{"pkgid":"8e883436-11fa-48a9-bffd-b371232df799","infors":[{"apkid":1,"sdkType":1,"dataType":1,"sdk":2}]}]}
    * */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        addList(list);

//        Random random = new Random();
//        for (int m = 2; m <= 5; m++) {
//
//            for (int k = 1; k <= 5; k++) {
//
//                for (int j = 1; j <= 9; j++) {
//                    switch (j) {
//                        case 4:
//                        case 5:
//                        case 6:
//                        case 7:
//                        case 9:
//                            break;
//                    }

                    for (int i = 0; i < list.size(); i++) {
                        StringBuilder reqSB = new StringBuilder(str);
                        // imei
                        String imei = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + i;
                        reqSB.append("imei=");
                        reqSB.append(imei);
                        reqSB.append(SEPARATOR);

                        JSONObject jsInfo = new JSONObject();
                        jsInfo.put("pkgid", list.get(i));
                        JSONArray logs = new JSONArray();
                        JSONObject log = new JSONObject();
                        log.put("apkid", 11206);
                        log.put("sdk", 2);
                        log.put("sdkType", 5);
//                        log.put("dataType", 4);
                        log.put("dataType", 1);
                        logs.add(log);
                        jsInfo.put("infors", logs);
                        String infos = String.format("{\"infors\":[%s]}", jsInfo.toString());
                        reqSB.append("infors=");
                        reqSB.append(infos);

//                        System.out.println(imei);
                        System.out.println(reqSB.toString());

                        String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newbackDatas.action", reqSB.toString());
//                        try {
//                            System.out.println(DesUtils.decryptDES(resp));
//                        } catch (Exception e) {
//                            System.out.println(resp);
//
//                        }
                    }

//                }
//            }
//        }
    }

    private static void addList(List<String> list) {
        for (int i = 1; i <= 100; i++) {
            list.add(UUID.randomUUID().toString());
        }
    }

}
