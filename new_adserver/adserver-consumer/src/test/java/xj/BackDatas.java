package xj;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 17:04
 * @author: martins
 */
public class BackDatas {
    private BackDatas(){}

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
        StringBuilder sb = new StringBuilder();

        String coo_id = "coo_id=1234567";
        sb.append(coo_id);
        sb.append(SEPARATOR);
        String channelId = "channelId=123";
        sb.append(channelId);
        sb.append(SEPARATOR);
        String type = "type=2";
        sb.append(type);
        sb.append(SEPARATOR);
        String xc_coo_id = "xc_coo_id=1";
        sb.append(xc_coo_id);
        sb.append(SEPARATOR);
        String xmodel = "xmodel=m1note";
        sb.append(xmodel);
        sb.append(SEPARATOR);
        String xversion = "xversion=5.1";
        sb.append(xversion);
        sb.append(SEPARATOR);
        String sdkversion = "sdkversion=10";
        sb.append(sdkversion);
        sb.append(SEPARATOR);
        String ximsi = "ximsi=460026029668041";
        sb.append(ximsi);
        sb.append(SEPARATOR);
        String xinternet = "xinternet=1";
        sb.append(xinternet);
        sb.append(SEPARATOR);
        String xoperator = "xoperator=CMCC";
        sb.append(xoperator);
        sb.append(SEPARATOR);
        String xwidth = "xwidth=1080";
        sb.append(xwidth);
        sb.append(SEPARATOR);
        String xheight = "xheight=1920";
        sb.append(xheight);
        sb.append(SEPARATOR);

        List<String> list = new ArrayList<>();
        addList(list);

        String sbStr = sb.toString();
        Random random = new Random();
//        for (int m = 2; m <= 5; m++) {
//
//            for (int k = 1; k <= 5; k++) {
//


                    for (int i = 0; i < list.size(); i++) {
                        for (int j = 4; j <= 4; j++) {
                        StringBuilder reqSB = new StringBuilder(sbStr);
                        // imei
//                        String imei = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + i;
                        reqSB.append("imei=");
                        reqSB.append("69935");
                        reqSB.append(SEPARATOR);

                        JSONObject jsInfo = new JSONObject();
                        jsInfo.put("pkgid", list.get(i));
                        JSONArray logs = new JSONArray();
                        JSONObject log = new JSONObject();
                        log.put("apkid", 11312); //
                        log.put("sdk", 2);
                        log.put("sdkType", 2);
//                        log.put("dataType", 4);
                        log.put("dataType", j);
                        logs.add(log);
                        jsInfo.put("infors", logs);
                        String infos = String.format("{\"infors\":[%s]}", jsInfo.toString());
                        reqSB.append("infors=");
                        reqSB.append(infos);

//                        System.out.println(imei);
                        System.out.println(reqSB.toString());

                        String resp = HttpUtils.sentPost("http://127.0.0.1:8082/newservice/newbackDatas.action", reqSB.toString());
//                        String resp = HttpUtils.sentPost("http://104.250.137.146:8081/newservice/newbackDatas.action", reqSB.toString());
//                        String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newbackDatas.action", reqSB.toString());
                        try {
                            System.out.println(DesUtils.decryptDES(resp));
                        } catch (Exception e) {
                            System.out.println(resp);

                        }
                    }

                }
//            }
//        }
    }

    private static void addList(List<String> list) {
        for (int i = 1; i <= 1; i++) {
            list.add(UUID.randomUUID().toString());
        }
    }

}
