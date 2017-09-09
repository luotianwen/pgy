package com.ddlions.two;

import com.ddlions.three.Hzsql;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/12
 *          Time: 0:18
 * @author: mm
 * @since 3.0
 */
public class TwoJob implements Job {

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //op
        System.out.println("执行了2版本的表维护");
        execsql(1);
        execsql(2);
        execsql(3);
    }

    private void execsql(final int style) {
        executorService.execute(new Runnable() {
            public void run() {
                if (style == 1) {
                    Opsql2.exec();
                } else if (style == 2) {
                    Hzsql.exec();
                } else if (style == 3) {
                    TwoSdksql.exec();
                }
            }
        });
    }
}
