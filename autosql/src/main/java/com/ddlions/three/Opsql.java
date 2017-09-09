package com.ddlions.three;

import com.ddlions.util.ReadPro;
import com.ddlions.util.StaticUtil;
import com.ddlions.util.DateUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
public class Opsql {
    public static void main(String [] arg){
        exec();
    }
       public  static void exec(){
           execsql( );
       }
      private static  void execsql(){
          System.out.println("运营平台执行");
          try {


              String month= DateUtil.getStringNextMonth();


              if(!month.equals(StaticUtil.opmonth))
              {
                  StaticUtil.opmonth=month;
                  StaticUtil.op=false;
              }

               if(! StaticUtil.op) {
                   StaticUtil.op = true;
                  /* Class.forName("com.mysql.jdbc.Driver");//指定连接类型
                   Connection conn = DriverManager.getConnection(ReadPro.getValue("op_url"), ReadPro.getValue("op_username"), ReadPro.getValue("op_password"));
                   conn.setAutoCommit(false);
                   Statement stmt = conn.createStatement();*/
                   List<String> sqls =getModel();
                   for (String sql : sqls) {
                       //stmt.addBatch
                       System.out.println("CREATE TABLE IF NOT EXISTS " + sql.split("_model")[0] + "_" + (month) + " LIKE " + sql + "" + ";");
                   }
                  /*  int[] updateCounts = stmt.executeBatch();



                   stmt.close();
                   conn.close();*/
                   System.out.println("运营平台"+month+"执行成功");
               }

          } catch (Exception e) {
              e.printStackTrace();
              StaticUtil.op=false;
          }

      }

    public static List<String> getModel(){
          List sqls=new ArrayList();
          sqls.add("ad_data_dssdk_model");
          sqls.add("ad_data_link_model");
          sqls.add("ad_data_model");
          sqls.add("ad_data_stat_link_model");
          sqls.add("ad_data_stat_model");
          sqls.add("apk_cphc_total_model");
          sqls.add("data_log_stat_model");
          sqls.add("ddl_click_data_model");
          sqls.add("ddl_sale_data_model");
          sqls.add("guard_data_model");
          sqls.add("guard_user_data_model");
          sqls.add("project_ad_income_model");
          sqls.add("project_hz_total_model");
          sqls.add("project_three_model");
          sqls.add("retention_data_model");
          sqls.add("register_data_model");
          sqls.add("register_distinct_data_model");
          sqls.add("user_ad_data_model");
          sqls.add("version_data_model");
          sqls.add("ad_data_subscribe_model");

          return sqls;
      }

}
