package com.ddlions.two;

import com.ddlions.util.DateUtil;
import com.ddlions.util.ReadPro;
import com.ddlions.util.StaticUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
public class TwoSdksql {
       public  static void exec(){
           execsql( );
       }
      private static  void execsql(){
          System.out.println("sdk平台2执行");
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

                   //日表
                   sqls =getDateModel();
                   for (String object : sqls) {
                       for (int i = 1; i <= dates; i++) {
                           stmt.addBatch("CREATE TABLE IF NOT EXISTS " +object.split("_model")[0] + "_" + sdf.format(sdf.parse(month +i)) + " LIKE " + object + "" + ";");
                       }
                       stmt.executeBatch();
                   }
                   sqls =getDate10Model();
                    //10个表
                   for (String object : sqls) {
                       for (int i = 1; i <= dates; i++) {
                           for (int j = 0; j < 10; j++) {
                               stmt.addBatch("CREATE TABLE IF NOT EXISTS " + object.split("_model")[0] + "_" + j + "_" + sdf.format(sdf.parse(month + i)) + " LIKE " + object + "" + ";");
                           }
                           stmt.executeBatch();
                       }
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
          return sqls;
      }

    private static List<String> getDate10Model(){
        List a=new ArrayList();
        a.add("adv_feeback_list_activate_model");
        a.add("adv_feeback_list_click_model");
        a.add("adv_feeback_list_download_model");
        a.add("adv_feeback_list_installed_model");
        a.add("adv_feeback_list_show_model");
        a.add("adv_feeback_pkg_model");
        a.add("adv_sents_list_model");
        a.add("apk_actusers_model");
        a.add("sdk_actusers_model");
        a.add("slient_actusers_model");
        a.add("guide_actusers_model");
        a.add("actusers_model");
        return a;
    }

    private static  List<String> getDateModel() {
        List<String> b = new ArrayList<String>();
        b.add("guide_users_cooid_model");
        b.add("slient_users_cooid_model");
        b.add("sdk_users_cooid_model");
        b.add("apk_users_cooid_model");
        b.add("a_cphc_model");
        b.add("sdk_users_model");
        b.add("apk_users_model");
        //218不要
        b.add("slient_users_model");
        b.add("guide_users_model");
        return b;
    }
}
