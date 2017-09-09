package com.ddlions.three;

import com.ddlions.util.DateUtil;
import com.ddlions.util.ReadPro;
import com.ddlions.util.StaticUtil;
import com.mongodb.DB;
import com.mongodb.Mongo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/5/18
 *          Time: 14:25
 * @author: mm
 * @since 3.0
 */
public class Mongodbsql {
    public  static void exec(){
        execsql( );
    }
    private static  void execsql(){
        System.out.println("sdk平台执行mongodb");
        try {

            String month= DateUtil.getStringUpMonth();
            if(!month.equals(StaticUtil.mongodbmonth))
            {
                StaticUtil.mongodbmonth=month;
                StaticUtil.mongodb=false;
            }

            if(! StaticUtil.mongodb) {
                StaticUtil.mongodb = true;


                List<String> sqls ;
                int dates=DateUtil.getStringUpMonthDateNum();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Mongo mg= new Mongo(ReadPro.getValue("mongodb_url"),Integer.parseInt(ReadPro.getValue("mongodb_port")));
                DB db= mg.getDB(ReadPro.getValue("mongodb_dbname"));
                //10个表
                sqls =getDateModel();
                for (String object : sqls) {
                    for (int i = 1; i <= dates; i++) {
                       db.getCollection(object.split("_model")[0] + "_" + sdf.format(sdf.parse(month +i))).drop();
                        System.out.println("删除"+object.split("_model")[0] + "_" + sdf.format(sdf.parse(month +i))+"成功");
                    }

                }
                db=null;
                mg.close();




                System.out.println("sdk平台"+month+"执行mongodb成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            StaticUtil.mongodb=false;
        }

    }


    private static List<String> getDateModel(){
        List b=new ArrayList();
        b.add("adv_sents_list_model");
        b.add("adv_feeback_list_activate_model");
        b.add("adv_feeback_list_click_model");
        b.add("adv_feeback_list_download_model");
        b.add("adv_feeback_list_installed_model");
        b.add("adv_feeback_list_show_model");
        b.add("adv_feeback_list_link_model");
        b.add("adv_sents_succ_list_model");
        b.add("guard_logs_model");
        b.add("adv_sents_succ_list_model");
        b.add("adv_link_list_sub_model");
        b.add("adv_click_list_sub_model");
        return b;
    }
    public static  void main(String str[]){
        System.out.println(DateUtil.getStringUpMonthDateNum());
    }
}
