package com.ddlions.three;

import com.ddlions.util.ReadPro;
import com.ddlions.util.StaticUtil;
import com.ddlions.util.DateUtil;

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
public class Ddlsql {
       public  static void exec(){
           execsql( );
       }
      private static  void execsql(){
          System.out.println("ddl执行");
          try {

              String month= DateUtil.getStringNextMonth();
              if(!month.equals(StaticUtil.ddlmonth))
              {
                  StaticUtil.ddlmonth=month;
                  StaticUtil.ddl=false;
              }

               if(! StaticUtil.ddl) {
                   StaticUtil.ddl = true;
                   Class.forName("com.mysql.jdbc.Driver");
                   Connection conn = DriverManager.getConnection(ReadPro.getValue("ddl_url"), ReadPro.getValue("ddl_username"), ReadPro.getValue("ddl_password"));
                   conn.setAutoCommit(false);
                   Statement stmt = conn.createStatement();
                   List<String> sqls =getMonthModel();
                   int dates=DateUtil.getMonthDateNum();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                   //日表
                   for (String sql : sqls) {
                       for (int i = 1; i <=dates; i++) {
                           stmt.addBatch( "CREATE TABLE  IF NOT EXISTS " + sql.split("_model")[0] + "_" + sdf.format(sdf.parse(month + i)) + " LIKE " + sql + "" + ";");
                       }

                   }
                   int[] updateCounts = stmt.executeBatch();

                   //10个表
                   sqls =getDateModel();
                   for (String sql : sqls) {
                       for (int i = 1; i <= dates; i++) {
                           for (int j = 0; j < 10; j++) {
                               stmt.addBatch("CREATE TABLE  IF NOT EXISTS " + sql.split("_model")[0] + "_" + j + "_" + sdf.format(sdf.parse(month + i)) + " LIKE " + sql + "" + ";");
                           }
                           stmt.executeBatch();
                       }
                   }




                   stmt.close();
                   conn.close();
                   System.out.println("ddl"+month+"执行成功");
               }

          } catch (Exception e) {
              e.printStackTrace();
              StaticUtil.ddl=false;
          }

      }

    private static List<String> getMonthModel(){
          List sqls=new ArrayList();
          sqls.add("PRODUCTSALE_model");
          sqls.add("INSTALL_ADJUST_model");
          return sqls;
      }
    private static List<String> getDateModel(){
        List sqls=new ArrayList();
        sqls.add("CLICKS_ADJUST_model");
        sqls.add("CLICKS_LOG_model");
        return sqls;
    }
}
