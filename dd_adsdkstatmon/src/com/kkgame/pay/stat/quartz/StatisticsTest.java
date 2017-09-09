package com.kkgame.pay.stat.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StatisticsTest extends BaseTestWithoutTransaction {

	public static DataStatistics dataStatistics;
		
	static {
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/conf/spring/applicationContext-mongodb.xml"});
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
