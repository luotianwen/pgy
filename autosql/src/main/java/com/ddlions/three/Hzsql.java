package com.ddlions.three;

import com.ddlions.util.DateUtil;
import com.ddlions.util.ReadPro;
import com.ddlions.util.StaticUtil;

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
public class Hzsql {
       public  static void exec(){
           execsql( );
       }
      private static  void execsql(){
          System.out.println("合作平台执行");
          try {

              String month= DateUtil.getStringNextMonth();
              if(!month.equals(StaticUtil.hzmonth))
              {
                  StaticUtil.hzmonth=month;
                  StaticUtil.hz=false;
              }

               if(! StaticUtil.hz) {
                   StaticUtil.hz = true;
                   Class.forName("com.mysql.jdbc.Driver");//指定连接类型
                   Connection conn = DriverManager.getConnection(ReadPro.getValue("hz_url"), ReadPro.getValue("hz_username"), ReadPro.getValue("hz_password"));
                   conn.setAutoCommit(false);
                   Statement stmt = conn.createStatement();
                   List<String> sqls =getModel();
                   for (String sql : sqls) {
                       stmt.addBatch("CREATE TABLE IF NOT EXISTS " + sql.split("_model")[0] + "_" + (month) + " LIKE " + sql + "" + ";");
                   }
                   int[] updateCounts = stmt.executeBatch();



                   stmt.close();
                   conn.close();
                   System.out.println("合作平台"+month+"执行成功");
               }

          } catch (Exception e) {
              e.printStackTrace();
              StaticUtil.hz=false;
          }

      }

      private static List<String> getModel(){
          List sqls=new ArrayList();
          sqls.add("ddl_data_model");
          sqls.add("divide_stat_model");
          sqls.add("register_data_model");
          //新版才有
          sqls.add("project_subscribe_model");

          return sqls;
      }

}
