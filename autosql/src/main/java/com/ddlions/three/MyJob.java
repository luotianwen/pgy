package com.ddlions.three;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/5/18
 *          Time: 10:27
 * @author: mm
 * @since 3.0
 */
public class MyJob implements Job {

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //op
        System.out.println("执行了3版本的表维护");
        execsql(1);
         execsql(2);
        execsql(3);
        execsql(4);
        //execsql(5);
    }

    private void execsql(final int style) {
        executorService.execute(new Runnable() {
            public void run() {
                if (style == 1) {
                    Opsql.exec();
                } else if (style == 2) {
                    Hzsql.exec();
                } else if (style == 3) {
                    Sdksql.exec();
                } else if (style == 4) {
                    Ddlsql.exec();
                }
                else if (style == 5) {
                    Mongodbsql.exec();
                }
            }
        });
    }


}
