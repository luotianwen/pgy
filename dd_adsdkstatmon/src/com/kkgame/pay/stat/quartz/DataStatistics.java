package com.kkgame.pay.stat.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.pay.stat.service.StatisticsService;
import com.kkgame.pay.stat.util.Constants;

public class DataStatistics {

	private static final Log log = LogFactory.getLog(DataStatistics.class);

	private StatisticsService statisticsService;




	public void test() {
		System.out.println("统计");
		//statDssdkAdData();
		everyFiveMinWork();
	}

	public void everyFiveMinWork() {
		System.out.println("开始统计");
		if (!Constants.ISRUNOFFERSDK) {
			System.out.println("开始订阅SDK展示统计");
			Constants.ISRUNOFFERSDK = true;
			statOffersdkAdData();
		}
		if(!Constants.ISRUNSUBLINK){
			System.out.println("开始外放链接订阅广告统计");
			Constants.ISRUNSUBLINK = true;
			statSubLinkData();
		}
		if(!Constants.ISRUNSUB){
			System.out.println("开始订阅广告统计");
			Constants.ISRUNSUB = true;
			statSubData();
		}
		if (!Constants.ISRUNDSSDK) {
			System.out.println("开始电商广告统计");
			Constants.ISRUNDSSDK = true;
			statDssdkAdData();
		}

		if (!Constants.ISRUNXL) {
			System.out.println("开始销量统计");
			Constants.ISRUNXL = true;
			// 销量
			statUserData();
		}
		if (!Constants.ISRUNXMXL) {
			System.out.println("开始项目销量统计");
			Constants.ISRUNXMXL = true;
			// 项目销量
			statProjectUserData();
		}
		if (!Constants.ISRUNHY) {
			System.out.println("开始活跃统计");
			Constants.ISRUNHY = true;
			// 活跃
			statActiveData();
		}
		if (!Constants.ISRUNXF) {
			System.out.println("开始下发统计");
			Constants.ISRUNXF = true;
			// 下发
			statSentData();
		}
		if (!Constants.ISRUNJS) {
			System.out.println("开始 接收成功统计");
			Constants.ISRUNJS = true;
			// 接收成功
			statSentSuccData();
		}
		if (!Constants.ISRUNZS) {
			System.out.println("开始展示成功统计");
			Constants.ISRUNZS = true;
			// 展示成功
			statShowData();
		}

		if (!Constants.ISRUNDJ) {
			System.out.println("开始点击统计");
			Constants.ISRUNDJ = true;
			// 点击
			statClickData();
		}
		if (!Constants.ISRUNXZ) {
			System.out.println("开始下载统计");
			Constants.ISRUNXZ = true;
			// 下载
			statDownloadData();
		}
		if (!Constants.ISRUNAZ) {
			System.out.println("开始 安装统计");
			Constants.ISRUNAZ = true;
			// 安装
			statInstalledData();
		}
		if (!Constants.ISRUNJH) {
			System.out.println("开始激活统计");
			Constants.ISRUNJH = true;
			// 激活
			statActivateData();
		}
		if (!Constants.ISRUNLINK) {
			System.out.println("开始链接广告统计");
			Constants.ISRUNLINK = true;
			// 激活
			statLinkAdData();
		}
		if (!Constants.ISRUNIFRAME) {
			System.out.println("开始IFRAME广告统计");
			Constants.ISRUNIFRAME = true;
			// 激活
			statIframeAdData();
		}
		if (!Constants.ISRUNIFRAMEACTIVE) {
			System.out.println("开始IFRAME激活广告统计");
			Constants.ISRUNIFRAMEACTIVE = true;
			// 激活
			statIframeActiveAdData();
		}
	}

	private void statIframeActiveAdData() {
		try {
			statisticsService.statIframeActiveAdData();
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statIframeAdData() {
		try {
			statisticsService.statIframeAdData();
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statOffersdkAdData() {
		try {
			statisticsService.saveOffersdkAdData();
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statSubLinkData() {
		statisticsService.saveSubLinkData();
	}

	private void statSubData() {
		statisticsService.saveSubData();
	}

	// 激活
	private void statActivateData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveActivate();
		} catch (Exception e) {
			log.debug(e);
		}

	}

	private void statDssdkAdData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveDssdkAdData();
		} catch (Exception e) {
			log.debug(e);
		}

	}
	// 安装
	private void statLinkAdData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveLinkAdData();
		} catch (Exception e) {
			log.debug(e);
		}

	}
	// 安装
	private void statInstalledData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveInstalled();
		} catch (Exception e) {
			log.debug(e);
		}

	}

	// 下载
	private void statDownloadData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveDownload();
		} catch (Exception e) {
			log.debug(e);
		}

	}

	// 点击
	private void statClickData() {
		// TODO Auto-generated method stub

		try {
			statisticsService.saveClick();
		} catch (Exception e) {
			log.debug(e);
		}

	}

	// 展示成功
	private void statShowData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveShow();
		} catch (Exception e) {
			log.debug(e);
		}

	}

	// 下发成功
	private void statSentSuccData() {
		// TODO Auto-generated method stub
		try {
			statisticsService.saveSentSucc();
		} catch (Exception e) {
			log.debug(e);
		}
	}

	// 下发
	private void statSentData() {
		try {
			statisticsService.saveSent();
		} catch (Exception e) {
			log.debug(e);
		}
	}

	// 项目销量
	private void statProjectUserData() {

		try {
			statisticsService.saveProjectActive();

		} catch (Exception e) {
			log.debug(e);
		}

	}

	// 活跃
	private void statActiveData() {
		

		try {
			statisticsService.saveActive();
		} catch (Exception e) {
			log.debug(e);
		}

	}

	/**
	 * 销量
	 * 
	 * @param i
	 */
	private void statUserData() {
		try {
			statisticsService.saveUser();
		} catch (Exception e) {
			log.debug(e);
		}
	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
}
