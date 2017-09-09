package com.ddlions.three;

import com.ddlions.util.DateUtil;
import com.ddlions.util.ReadPro;
import com.ddlions.util.StaticUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Sdksql {
       public  static void exec(){
           execsql( );
       }
      private static  void execsql(){
          System.out.println("sdk平台执行");
          try {

              String month= DateUtil.getStringNextMonth();
              if(!month.equals(StaticUtil.sdkmonth))
              {
                  StaticUtil.sdkmonth=month;
                  StaticUtil.sdk=false;
              }

               if(! StaticUtil.sdk) {
                   StaticUtil.sdk = true;
                   Class.forName("com.mysql.jdbc.Driver");
                   Connection conn = DriverManager.getConnection(ReadPro.getValue("sdk_url"), ReadPro.getValue("sdk_username"), ReadPro.getValue("sdk_password"));
                   conn.setAutoCommit(false);
                   Statement stmt = conn.createStatement();
                   List<String> sqls =getMonthModel();
                   int dates=DateUtil.getMonthDateNum();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                   //月表

                   for (String string : sqls) {
                       stmt.addBatch("CREATE TABLE IF NOT EXISTS "+ string.split("_model")[0] + "_" + (month) + " LIKE " + string + "" + ";");
                   }
                   int[] updateCounts = stmt.executeBatch();

                   //10个表
                   sqls =getDateModel();
                   for (String object : sqls) {
                       for (int i = 1; i <= dates; i++) {
                           stmt.addBatch("CREATE TABLE IF NOT EXISTS " +object.split("_model")[0] + "_" + sdf.format(sdf.parse(month +i)) + " LIKE " + object + "" + ";");
                       }
                       stmt.executeBatch();
                   }




                   stmt.close();
                   conn.close();
                   System.out.println("sdk平台"+month+"执行成功");
               }

          } catch (Exception e) {
              e.printStackTrace();
              StaticUtil.sdk=false;
          }

      }

    private static List<String> getMonthModel(){
          List sqls=new ArrayList();
          sqls.add("a_apkcount_model");
          sqls.add("a_webcount_model");
          sqls.add("a_dssdkcount_model");
          return sqls;
      }
    private static List<String> getDateModel(){
        List b=new ArrayList();
        b.add("guide_users_project_model");
        b.add("slient_users_project_model");
        b.add("websdk_users_project_model");

        b.add("websdk_users_project_model");
        b.add("offline_users_project_model");

        b.add("sdk_users_project_model");
        b.add("apk_users_project_model");

        b.add("a_cphc_model");
        b.add("sdk_users_model");
        b.add("apk_users_model");
        b.add("slient_users_model");
        b.add("guide_users_model");
        b.add("websdk_users_model");
        b.add("offline_users_model");


       b.add("apk_actusers_model");
        b.add("sdk_actusers_model");
        b.add("slient_actusers_model");
        b.add("guide_actusers_model");
        b.add("websdk_actusers_model");
        b.add("offline_actusers_model");
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
        return b;
    }
    public static  void main(String str[]){
       List<String> sqls = Arrays.asList("websdk_users_model");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (String object : sqls) {
            for (int i = 1; i <= 31; i++) {
                try {
                    System.out.println("CREATE TABLE IF NOT EXISTS " +object.split("_model")[0] + "_" + sdf.format(sdf.parse("201607" +i)) + " LIKE " + object + "" + ";");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
