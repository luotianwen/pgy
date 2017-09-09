package com.kkgame.pay.stat.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StatisticsTest {

	protected static ApplicationContext ctx;

	protected Object getBean(String beanname) {
		return ctx.getBean(beanname);
	}

	public static DataStatistics dataStatistics;

	static {
		ctx = new ClassPathXmlApplicationContext(new String[] { "conf/spring/applicationContext.xml" });
		dataStatistics = (DataStatistics) ctx.getBean("dataStatistics");
	}

	public void testWork() throws Exception {
		dataStatistics.test();
	}

	public static void main(String[] args) {
		StatisticsTest test = new StatisticsTest();
		try {
			test.testWork();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
