package com.ddlions.one;

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
public class OneSdksql {
    public static void exec() {
        execsql();
    }

    private static void execsql() {
        System.out.println("sdk平台2执行");
        try {

            String month = DateUtil.getStringNextMonth();
            if (!month.equals(StaticUtil.sdkmonth)) {
                StaticUtil.sdkmonth = month;
                StaticUtil.sdk = false;
            }

            if (!StaticUtil.sdk) {
                StaticUtil.sdk = true;
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(ReadPro.getValue("sdk_url"), ReadPro.getValue("sdk_username"), ReadPro.getValue("sdk_password"));
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                List<String> sqls = getMonthModel();
                int dates = DateUtil.getMonthDateNum();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                //月表
                String a = "";
                for (String object : sqls) {
                    stmt.addBatch("CREATE TABLE " + object + "_" + month + " LIKE " + object + ";");
                }


                int[] updateCounts = stmt.executeBatch();


                //日表
                sqls = getDateModel();

                for (String object : sqls) {
                    for (int i = 1; i <= dates; i++) {
                        a = i + "";
                        if (i < 10)
                            a = "0" + i;
                        stmt.addBatch("CREATE TABLE " + object + "_" + month + "_" + a + " LIKE " + object + ";");
                    }
                    stmt.executeBatch();
                }

                stmt.close();
                conn.close();
                System.out.println("sdk平台" + month + "执行成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            StaticUtil.sdk = false;
        }

    }

    private static List<String> getMonthModel() {
        List b = new ArrayList();
        b.add("a_cphc");
        b.add("a_sale");
        return b;
    }


    private static List<String> getDateModel() {
        List<String> a = new ArrayList<String>();
        a.add("a_apklogin");
        a.add("a_download");
        a.add("a_jsback");
        a.add("a_pushdata");
        a.add("a_pushlog");
        a.add("a_zsback");
        return a;
    }
}
