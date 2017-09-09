package com.kkgame.pay.stat.quartz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.service.AdDataStatisticsService;
import com.kkgame.pay.stat.service.DdlStatisticsService;
import com.kkgame.pay.stat.service.IncomeStatisticsService;
import com.kkgame.pay.stat.service.UserDataStatisticsService;
import com.kkgame.pay.stat.service.UserRetentionStatisticsService;
import com.kkgame.pay.stat.util.Constants;
import com.kkgame.pay.stat.util.HttpUtils;

public class DataStatistics {

	private static final Log log = LogFactory.getLog(DataStatistics.class);

	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	private IncomeStatisticsService incomeStatisticsService;
	private UserDataStatisticsService userDataStatisticsService;
	private UserRetentionStatisticsService userRetentionStatisticsService;
	private AdDataStatisticsService adDataStatisticsService;
	private DdlStatisticsService ddlStatisticsService;
	private static final String POST_URL = "http://advhz.kokgc.com/inter/DdlData!data.action";

	private static long lastUpdateTime = 0;

	public void test() {
		// if(lastUpdateTime==0) {
		// lastUpdateTime = Calendar.getInstance().getTimeInMillis();
		// Timer timer = new Timer();
		// timer.scheduleAtFixedRate(new CatTask(), 10, 10*1000);
		// }
		// 版本销量
	
		/*for (Integer type : Constants.TYPE_LIST) {
 			statUserData(i, type);
 		}
 		for (Integer type : Constants.TYPE_LIST) {
 			statVersionUserData(i, type);
 		}*/
//		
//		for (Integer type : Constants.TYPE_LIST) {
//			statUserActiveData(i, type);
//		}
//		
//		for(Integer type:Constants.TYPE_LIST) {
//			statTotalUserActiveData(i,type);
//		}
//		for (Integer type : Constants.TYPE_LIST) {
//			statVersionUserActiveData(i, type);
//		}
//		for(Integer type:Constants.TYPE_LIST) {
//			statTotalVersionUserActiveData(i,type);
//		}
//		statSendData(i, Constants.SDK);
//		statData(i, 0);

 		//statProjectData(i, 0);
 		//statVersionProjectData(i, 0);
 		//statSendData(0, Constants.SDK);
		
		//everyFiveMinWork();
		/*for (Integer type : Constants.TYPE_LIST) {
			statUserActiveData(i, type);
		}*/
		int i=0;
		/* for(Integer type:Constants.LINK_TYPE_LIST) {
			statLinkData(i,type);
		}*/
		// statAdLinkData(i);
		 // statAdLinkDataUV(i);
		  //statAdLinkDataPv(i);

		log.info("stat ds data start");
/*
		for(Integer type:Constants.DS_TYPE_LIST) {
			statDSData(i,type);
		}
		statDSData(i);
		statDSDataPv(i);*/
//		everyFiveMinWork();
		statOfferSdkData(i);
	}

	/**
	 * 更新广告总激活率信息
	 * @param i
     */
	private void statTotalSubData(int i) {
		try{
			adDataStatisticsService.statTotalSubAdData(i);
		}catch (Exception e){
			log.debug(e);
		}
	}


	public void everyFiveMinDdlWork() {
		/*int i = 0;
		log.info("stat ddldata start");
		syncProductChannel(i);
		statClickData(i);
		statSaleData(i);
		statMonthSaleData(i);
		updateTotalSaleData(i);
		sendData(i);
		log.info("stat ddlend start");*/
	}
	
	public void everyFiveMinWork() { 
		 int i = 0;

		
		// 销量
		log.info("statUserData start");
		for (Integer type : Constants.TYPE_LIST) {
			statUserData(i, type);
		}
		log.info("statUserData end");
		log.info("statProjectUserData start");
		//项目销量
		for (Integer type : Constants.TYPE_LIST) {
			statProjectUserData(i, type);
		}		
		log.info("statProjectUserData end");
		log.info("statUserActiveData start");
		// 活跃
		for (Integer type : Constants.TYPE_LIST) {
			statUserActiveData(i, type);
		}
		log.info("statUserActiveData end");
		log.info("statTotalUserActiveData start");
		// 总活跃
		for(Integer type:Constants.TYPE_LIST) {
			statTotalUserActiveData(i,type);
		}
		log.info("statTotalUserActiveData end");
		log.info("statSendData start");
		// 基本数据
		statSendData(i, Constants.SDK);
		log.info("statSendData end");



		log.info("stat adLink data start");
		for(Integer type:Constants.LINK_TYPE_LIST) {
			statLinkData(i,type);
		}
		statAdLinkData(i);
		statAdLinkDataUV(i);
		statAdLinkDataPv(i);
		log.info("stat adLink data end");

		log.info("stat ds data start");

		for(Integer type:Constants.DS_TYPE_LIST) {
			statDSData(i,type);
		}
		statDSData(i);
		statDSDataPv(i);
		log.info("stat ds data end");

		log.info("statData start");
		statData(i, 0);
		// 按项目统计广告数据
		log.info("statData end");
		log.info("statProjectData start");
		statProjectData(i, 0);
		// 版本销量
		log.info("statProjectData end");

		log.info("statVersionUserData start");
		for (Integer type : Constants.TYPE_LIST) {
			statVersionUserData(i, type);
		}
		log.info("statVersionUserData end");
		log.info("statVersionProjectUserData start");
		for (Integer type : Constants.TYPE_LIST) {
			statVersionProjectUserData(i, type);
		}
		log.info("statVersionProjectUserData end");
		log.info("statVersionUserActiveData start");
		// 活跃
		for (Integer type : Constants.TYPE_LIST) {
			statVersionUserActiveData(i, type);
		}
		log.info("statVersionUserActiveData end");
		log.info("statTotalVersionUserActiveData start");
		for(Integer type: Constants.TYPE_LIST) {
			statTotalVersionUserActiveData(i,type);
		}
		log.info("statTotalVersionUserActiveData end");
		log.info("statVersionProjectData start");
		statVersionProjectData(i, 0);
		log.info("statVersionProjectData end");
		log.info("syncTempData start");		
		syncTempData(i);
		log.info("syncTempData end");
		log.info("statRetentionData start");
		for (Integer type : Constants.TYPE_LIST) {
			statRetentionData(0, type);
		}
		log.info("statRetentionData end");
		log.info("updateDaySendData start");
		updateDaySendData(i);
		log.info("updateDaySendData end");
		log.info("statAdData start");
		statAdData(i);
		log.info("statAdData end");
		log.info("statAdBackData start");
		statAdBackData(i);
		log.info("statAdBackData end");
		log.info("statProjectIncomeData start");
		statProjectIncomeData(i);
		log.info("statProjectIncomeData end");
		log.info("updateAdCpm start");
		updateAdCpm(i);
		log.info("updateAdCpm end");
		log.info("statMonthUserData start");
		statMonthUserData(i);
		log.info("statMonthUserData end");
		log.info("updateTotalUserData start");
		updateTotalUserData(i);
		log.info("updateTotalUserData end");
		log.info("statFirstPageData start");
		statFirstPageData(i);
		log.info("statFirstPageData end");
		log.info("statSubData start");
		statSubData(i);
		log.info("statSubData end");
		log.info("statTotalSubData start");
		statTotalSubData(i);
		log.info("statTotalSubData end");
		//订阅SDK
		log.info("statOfferShowDate start");
		statOfferSdkData(i);
		log.info("statOfferShowDate end");
		log.info("statOfferSendDate start");
		offerSdkSendData(i);
		log.info("statOfferSendDate end");
		log.info("offerSdkActiveData start");
		offerSdkActiveData(i);
		log.info("offerSdkActiveData end");
	 }

	private void offerSdkActiveData(int i) {
		try{
			adDataStatisticsService.offerSdkActiveData(i);
		}catch (Exception e){
			log.debug(e);
		}
	}

	private void offerSdkSendData(int i) {
		try{
			adDataStatisticsService.offerSdkSendData(i);
		}catch (Exception e){
			log.debug(e);
		}
	}

	private void statOfferSdkData(int i) {
		try{
			adDataStatisticsService.statOfferShowDate(i);
		}catch (Exception e){
			log.debug(e);
		}
	}


	public void everyFiveDayDdlWork() {
	/*	log.info("stat ddldata start");
		int i = 1;
		statClickData(i);
		statSaleData(i);
		sendData(i);
		statMonthSaleData(i);
		updateTotalSaleData(i);
		log.info("stat ddldata end");*/
	}

	public void everyFiveDayWork() {
		int i = 1;		
		for (Integer type : Constants.TYPE_LIST) {
			statUserData(i, type);
		}
		for (Integer type : Constants.TYPE_LIST) {
			statProjectUserData(i, type);
		}
		for (Integer type : Constants.TYPE_LIST) {
			statUserActiveData(i, type);
		}
		for(Integer type: Constants.TYPE_LIST) {
			statTotalUserActiveData(i,type);
		}
		statSendData(i, Constants.SDK);
		// apk数据
		statData(i, 0);
		statProjectData(i, 0);
		// 版本销量
		for (Integer type : Constants.TYPE_LIST) {
			statVersionUserData(i, type);
		}
		for (Integer type : Constants.TYPE_LIST) {
			statVersionProjectUserData(i, type);
		}
		// 活跃
		for (Integer type : Constants.TYPE_LIST) {
			statVersionUserActiveData(i, type);
		}
		for(Integer type: Constants.TYPE_LIST) {
			statTotalVersionUserActiveData(i,type);

		}
		statVersionProjectData(i, 0);
		syncTempData(i);
		for (Integer type : Constants.TYPE_LIST) {
			statRetentionData(i, type);
		}
		statAdData(i);
		statAdBackData(i);
		statProjectIncomeData(i);
		updateAdCpm(i);
		statMonthUserData(i);
		updateTotalUserData(i);

		log.info("stat adLink data start");
		for(Integer type:Constants.LINK_TYPE_LIST) {
			statLinkData(i,type);
		}
		statAdLinkData(i);
		statAdLinkDataUV(i);
		statAdLinkDataPv(i);
		log.info("stat ds data start");

		for(Integer type:Constants.DS_TYPE_LIST) {
			statDSData(i,type);
		}
		statDSData(i);
		statDSDataPv(i);
		log.info("stat ds data end");
		log.info("stat adLink data end");
		log.info("statSubData start");
		statSubData(i);
		log.info("statSubData end");
		log.info("statTotalSubData start");
		statTotalSubData(i);
		log.info("statTotalSubData end");
		//订阅SDK
		log.info("statOfferShowDate start");
		statOfferSdkData(i);
		log.info("statOfferShowDate end");
		log.info("statOfferSendDate start");
		offerSdkSendData(i);
		log.info("statOfferSendDate end");
		log.info("offerSdkActiveData start");
		offerSdkActiveData(i);
		log.info("offerSdkActiveData end");
	}

	/**
	 * 同步临时数据
	 * 
	 * @param i
	 */
	public void syncTempData(int i) {
		try {
			incomeStatisticsService.syncTempData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	


	private void statFirstPageData(int i) {
		// TODO Auto-generated method stub
		try {
			incomeStatisticsService.statFirstPageData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	

	private void deleteTempData(int i) {
		// TODO Auto-generated method stub
		try {
			incomeStatisticsService.deleteTempData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 销量
	 * 
	 * @param i
	 */
	public void statLinkData(int i, int type) {
		try {
			adDataStatisticsService.statLinkData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	public void statDSData(int i, int type) {
		try {
			adDataStatisticsService.statDSData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 激活量
	 * @param i
     */
	private void statSubData(int i) {
		try{
			adDataStatisticsService.statSubAdData(i);
		}catch (Exception e){
			log.debug(e);
		}
	}

	/**
	 * 销量
	 * 
	 * @param i
	 */
	public void statAdLinkData(int i) {
		try {
			adDataStatisticsService.statAdLinkData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	public void statDSData(int i) {
		try {
			adDataStatisticsService.statDSData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	/**
	 * 销量
	 *
	 * @param i
	 */
	public void statAdLinkDataUV(int i) {
		try {
			adDataStatisticsService.statAdLinkDataUV(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	/**
	 * 销量
	 *
	 * @param i
	 */
	public void statAdLinkDataPv(int i) {
		try {
			adDataStatisticsService.insertLinkPv(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	public void statDSDataPv(int i) {
		try {
			adDataStatisticsService.insertDsPv(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}



	/**
	 * 销量
	 * 
	 * @param i
	 */
	public void statUserData(int i, int type) {
		try {
			userDataStatisticsService.statUserData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 销量
	 * 
	 * @param i
	 */
	public void statProjectUserData(int i, int type) {
		try {
			userDataStatisticsService.statProjectUserData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 版本销量
	 * 
	 * @param i
	 */
	public void statVersionUserData(int i, int type) {
		try {
			userDataStatisticsService.statVersionUserData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 版本销量
	 * 
	 * @param i
	 */
	public void statVersionProjectUserData(int i, int type) {
		try {
			userDataStatisticsService.statVersionProjectUserData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 按月销量
	 * 
	 * @param i
	 */
	public void statMonthUserData(int i) {
		try {
			userDataStatisticsService.statMonthUserData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 按月销量
	 * 
	 * @param i
	 */
	public void updateTotalUserData(int i) {
		try {
			userDataStatisticsService.updateTotalUserData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 活跃
	 * 
	 * @param i
	 */
	public void statUserActiveData(int i, int type) {
		try {
			userDataStatisticsService.statUserActiveData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 版本活跃
	 * 
	 * @param i
	 */
	public void statVersionUserActiveData(int i, int type) {
		try {
			userDataStatisticsService.statVersionUserActiveData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 活跃
	 * 
	 * @param i
	 */
	public void statUserProjectActiveData(int i, int type) {
		try {
			userDataStatisticsService.statUserProjectActiveData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}

	}
	
	/**
	 * 版本活跃
	 * 
	 * @param i
	 */
	public void statVersionUserProjectActiveData(int i, int type) {
		try {
			userDataStatisticsService.statVersionUserProjectActiveData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}

	}
	
	

	/**
	 * 活跃
	 * 
	 * @param i
	 */
	public void statTotalUserActiveData(int i,int type) {
		try {
			userDataStatisticsService.statTotalUserActiveData(i,type);
		} catch (Exception e) {
			log.debug(e);
		}

	}
	
	/**
	 * 版本活跃
	 * 
	 * @param i
	 */
	public void statTotalVersionUserActiveData(int i,int type) {
		try {
			userDataStatisticsService.statTotalVersionUserActiveData(i,type);
		} catch (Exception e) {
			log.debug(e);
		}

	}

	/**
	 * 运营数据
	 * 
	 * @param i
	 */
	public void statData(int i, int type) {
		try {
			adDataStatisticsService.statData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 运营数据
	 * 
	 * @param i
	 */
	public void statProjectData(int i, int type) {
		try {
			adDataStatisticsService.statProjectData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 * 运营数据
	 * 
	 * @param i
	 */
	public void statVersionProjectData(int i, int type) {
		try {
			adDataStatisticsService.statVersionProjectData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 留存
	 * 
	 * @param i
	 */
	public void statRetentionData(int i, int type) {
		try {
			userRetentionStatisticsService.statRetentionData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 发送接收
	 * 
	 * @param i
	 */
	public void statSendData(int i, int type) {
		try {
			adDataStatisticsService.statSendData(i, type);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 发送接收
	 * 
	 * @param i
	 */
	public void updateDaySendData(int i) {
		try {
			adDataStatisticsService.updateDaySendData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * ad回传数据
	 * 
	 * @param i
	 */
	public void statAdData(int i) {
		try {
			incomeStatisticsService.statAdData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * ad回传数据
	 * 
	 * @param i
	 */
	public void statAdBackData(int i) {
		try {
			incomeStatisticsService.statAdBackData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * ad回传数据
	 * 
	 * @param i
	 */
	public void updateAdCpm(int i) {
		try {
			incomeStatisticsService.updateAdCpm(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	/**
	 * 项目收入数据
	 * 
	 * @param i
	 */
	public void statProjectIncomeData(int i) {
		try {
			incomeStatisticsService.statProjectIncomeData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 *  渠道
	 * @param i
	 */
	public void syncProductChannel(int i) {
		try {
			ddlStatisticsService.syncProductChannel(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 *  点击
	 * @param i
	 */
	public void statClickData(int i) {
		try {
			ddlStatisticsService.statClickData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	
	/**
	 *  销量
	 * @param i
	 */
	public void sendData(int i) {
		try {
			List<DdlData> dataList = ddlStatisticsService.getSendData(i);
			if(null!=dataList&&dataList.size()>0) {
				for(DdlData data:dataList) {
					try{
						StringBuffer sb = new StringBuffer();
						sb.append("ddlDataVO.time=").append(data.getStatDate()).append("&");
						sb.append("ddlDataVO.projectId=").append(data.getProjectId()).append("&");
						sb.append("ddlDataVO.clickCount=").append(data.getClickCount()).append("&");
						sb.append("ddlDataVO.saleCount=").append(data.getShowSaleCount()).append("&");
						sb.append("ddlDataVO.validClickCount=").append(data.getValidClickCount());
						String resp = HttpUtils.sentPost(POST_URL, sb.toString());
						log.debug(resp);
					}catch (Exception e) {
						log.debug(e);
					}
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
	}
	
	/**
	 *  按月销量
	 * @param i
	 */
	public void statSaleData(int i) {
		try {
			ddlStatisticsService.statSaleData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	

	private void updateTotalSaleData(int i) {
		try {
			ddlStatisticsService.updateTotalSaleData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statMonthSaleData(int i) {
		try {
			ddlStatisticsService.statMonthSaleData(i);
		} catch (Exception e) {
			log.debug(e);
		}
	}
	

	public IncomeStatisticsService getIncomeStatisticsService() {
		return incomeStatisticsService;
	}

	public void setIncomeStatisticsService(IncomeStatisticsService incomeStatisticsService) {
		this.incomeStatisticsService = incomeStatisticsService;
	}

	public UserDataStatisticsService getUserDataStatisticsService() {
		return userDataStatisticsService;
	}

	public void setUserDataStatisticsService(UserDataStatisticsService userDataStatisticsService) {
		this.userDataStatisticsService = userDataStatisticsService;
	}

	public UserRetentionStatisticsService getUserRetentionStatisticsService() {
		return userRetentionStatisticsService;
	}

	public void setUserRetentionStatisticsService(UserRetentionStatisticsService userRetentionStatisticsService) {
		this.userRetentionStatisticsService = userRetentionStatisticsService;
	}

	public AdDataStatisticsService getAdDataStatisticsService() {
		return adDataStatisticsService;
	}

	public void setAdDataStatisticsService(AdDataStatisticsService adDataStatisticsService) {
		this.adDataStatisticsService = adDataStatisticsService;
	}

	public DdlStatisticsService getDdlStatisticsService() {
		return ddlStatisticsService;
	}

	public void setDdlStatisticsService(DdlStatisticsService ddlStatisticsService) {
		this.ddlStatisticsService = ddlStatisticsService;
	}

//	private boolean sendMsg(InstallData data) {
//		boolean flag = false;
//		StringBuffer sb = new StringBuffer();
//		sb.append("projectId=").append(data.getProjectId()).append("&").append(
//				"imei=").append(data.getImei()).append("&").append("price=")
//				.append(data.getPrice()).append("&").append("linkId=").append(
//						data.getId());
//		try {
//			String resp = HttpUtil.post(data.getSendUrl(), sb.toString());
//			if (null != resp && "ok".equalsIgnoreCase(resp)) {
//				flag = true;
//			}
//		} catch (Exception e) {
//			flag = false;
//		}
//		return flag;
//	}
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("ddlDataVO.time=").append("2015-12-29").append("&");
		sb.append("ddlDataVO.projectId=").append(11265).append("&");
		sb.append("ddlDataVO.clickCount=").append(1).append("&");
		sb.append("ddlDataVO.saleCount=").append(0).append("&");
		sb.append("ddlDataVO.validClickCount=").append(0);
		String resp = HttpUtils.sentPost(POST_URL, sb.toString());
		log.debug(resp);
	}
	
}
