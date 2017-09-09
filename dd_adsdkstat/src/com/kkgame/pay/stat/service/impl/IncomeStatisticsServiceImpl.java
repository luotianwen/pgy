package com.kkgame.pay.stat.service.impl;

import com.kkgame.pay.stat.util.DatabaseException;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.InstallData;
import com.kkgame.pay.stat.bean.UserData;
import com.kkgame.pay.stat.dao.FeeDao;
import com.kkgame.pay.stat.dao.MasterDao;
import com.kkgame.pay.stat.dao.StatisticsDao;
import com.kkgame.pay.stat.service.IncomeStatisticsService;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IncomeStatisticsServiceImpl implements IncomeStatisticsService {

	private static final Log log = LogFactory
			.getLog(IncomeStatisticsServiceImpl.class);
	private StatisticsDao statisticsDao;

	private MasterDao masterDao;
	private FeeDao feeDao;
	private static JedisResourcePool jedisPool;
	
	static {
		StringBuffer hostPort = new StringBuffer();
		hostPort.append(Constants.REDIS_HOST).append(":").append(Constants.ZK_PORT);
		jedisPool = RoundRobinJedisPool.create().curatorClient(hostPort.toString(), 30000)
				.zkProxyDir(Constants.ZK_PROXY_DIR).build();	
	}
	
	@Override
	public void deleteTempData(int i) throws DatabaseException {
		// TODO Auto-generated method stub
		int type = 0;
//		statisticsDao.deleteTempUserData(i, type);
//		statisticsDao.deleteTempVersionUserData(i, type);
//		statisticsDao.deleteTempAdSendData(i);
	}
	
	// 临时数据
	@Override
	public void syncTempData(int i) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		int type = 0;
		statisticsDao.deleteUserData(i, type);
		statisticsDao.deleteRetentionUserData(i, type);
		statisticsDao.deleteVersionUserData(i, type);
		statisticsDao.syncTempUserData(i);
		statisticsDao.syncTempVersionUserData(i);
		statisticsDao.syncTempRetentionUserData(i);
		statisticsDao.deleteTempUserData(i, type);
		statisticsDao.deleteTempVersionUserData(i, type);
		statisticsDao.deleteAdSendData(i);
		statisticsDao.deleteAdData(i);
		statisticsDao.syncTempAdProjectData(i);
		statisticsDao.syncTempAdData(i);
		statisticsDao.deleteTempAdSendData(i);
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}
	
	@Override
	public void statFirstPageData(int i) throws DatabaseException {
		statisticsDao.statTotalUserData(i);
		statisticsDao.statDayUserData(i);
		statisticsDao.statDayActiveData(i);
		statisticsDao.statDayInstalledData(i);
	}

	// 统计广告数据AD_DATA_
	@Override
	public void statAdData(int i) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		List<Data> adList = statisticsDao.getAdData(i);
		if (adList != null && adList.size() > 0) {
			statisticsDao.insertAdData(i, adList);
		}
		// 有效数据
		List<Data> effectiveList = statisticsDao.getAdEffectiveData(i);
		if (effectiveList != null && effectiveList.size() > 0) {
			statisticsDao.insertAdEffectiveData(i, effectiveList);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 统计广告back数据
	@Override
	public void statAdBackData(int i) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		List<Data> adBackList = feeDao.getAdBackData(i);
		if (adBackList != null && adBackList.size() > 0) {
			statisticsDao.insertAdBackData(i, adBackList);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 统计收入数据
	@Override
	public void statProjectIncomeData(int i) throws DatabaseException {
		// 获取项目销量数据
		long nowTime = System.currentTimeMillis();
		List<Data> projectTotalList = statisticsDao.getTotalProjectUserData(i);
		if (projectTotalList != null && projectTotalList.size() > 0) {
			// 1,2,3,4
			statisticsDao.insertProjectUserData(i, 0, projectTotalList);
		}
		
		List<Data> firstRetentionList = statisticsDao.getFirstProjectUserData(i);
		if (firstRetentionList != null && firstRetentionList.size() > 0) {
			statisticsDao.insertFirstProjectUserData(i,firstRetentionList);
		}
		List<Data> projectList = statisticsDao.getProjectUserData(i);
		if (projectList != null && projectList.size() > 0) {
			// 1,2,3,4
			List<Data> firstList = new ArrayList<Data>();
			List<Data> secondList = new ArrayList<Data>();
			List<Data> thirdList = new ArrayList<Data>();
			List<Data> fourthList = new ArrayList<Data>();
			for (Data data : projectList) {
				if (data.getLevel() == 1) {
					firstList.add(data);
				} else if (data.getLevel() == 2) {
					secondList.add(data);
				} else if (data.getLevel() == 3) {
					thirdList.add(data);
				} else if (data.getLevel() == 4) {
					fourthList.add(data);
				}
			}
			if (firstList.size() > 0) {
				statisticsDao.insertProjectUserData(i, 1, firstList);
			}
			if (secondList.size() > 0) {
				statisticsDao.insertProjectUserData(i, 2, secondList);
			}
			if (thirdList.size() > 0) {
				statisticsDao.insertProjectUserData(i, 3, thirdList);
			}
			if (fourthList.size() > 0) {
				statisticsDao.insertProjectUserData(i, 4, fourthList);
			}
		}
		// 显示数据
		List<Data> showList = statisticsDao.getProjectShow(i);
		if (showList != null && showList.size() > 0) {
			statisticsDao.insertProjectShow(i, showList);
		}
		// 获取广告项目数据
		List<Data> adProjectList = statisticsDao.getAdProjectData(i);
		if (adProjectList != null && adProjectList.size() > 0) {
			statisticsDao.insertAdProjectData(i, adProjectList);
		}
		// 获取广告项目有效数据
		List<Data> adProjectEffectiveList = statisticsDao
				.getAdProjectEffectiveData(i);
		if (adProjectEffectiveList != null && adProjectEffectiveList.size() > 0) {
			statisticsDao.insertAdProjectEffectiveData(i,
					adProjectEffectiveList);
		}
		statisticsDao.updateAdPrice(i);
		statisticsDao.updateAdPercent(i);// 更新广告比例
		statisticsDao.updateAdProjectPercent(i);// 更新广告比例
		// 插入广告项目收入
		List<Data> incomeList = statisticsDao.getProjectIncome(i);
		if (incomeList != null && incomeList.size() > 0) {
			statisticsDao.insertProjectIncome(i, incomeList);
		}
		// 更新项目预计收益
		statisticsDao.updateExpectIncome(i);

		// 更新项目各个等级人数
		statisticsDao.updateProjectUsers(i);
		// 更新支出
		List<Data> ddlList = statisticsDao.getDdlList(i);
		if (ddlList != null && ddlList.size() > 0) {
			statisticsDao.insertDdlList(i, ddlList);
		}
		statisticsDao.updateProjectOutcome(i);
		statisticsDao.updateProjectOutcomeRate(i);
		statisticsDao.updateProjectUsersNeedBack(i);
		// statisticsDao.updateProjectUsers(i);
		// 更新支出
		statisticsDao.updateProjectOutcome(i);
		statisticsDao.updateProjectOutcomeRate(i);

		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 更新广告cpm
	@Override
	public void updateAdCpm(int i) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		statisticsDao.updateAdCpm(i);
		List<Data> cpmList = statisticsDao.getAdCpm(i);
		if (cpmList != null && cpmList.size() > 0) {
			for (Data data : cpmList) {
				masterDao.updateAdCpm(data);
			}
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	public FeeDao getFeeDao() {
		return feeDao;
	}

	public void setFeeDao(FeeDao feeDao) {
		this.feeDao = feeDao;
	}

	public MasterDao getMasterDao() {
		return masterDao;
	}

	public void setMasterDao(MasterDao masterDao) {
		this.masterDao = masterDao;
	}

	public static void main(String[] args) {
		/**
		 * Connection conn = null; Statement pstmt = null; ResultSet rs = null;
		 * try { String fileName = "/Users/rayi/Downloads/db.db";
		 * Class.forName("org.sqlite.JDBC"); conn =
		 * DriverManager.getConnection("jdbc:sqlite:"+fileName); StringBuffer
		 * sql = new StringBuffer(); sql.append("SELECT * FROM t_sms_recv");
		 * pstmt = conn.createStatement(); rs =
		 * pstmt.executeQuery(sql.toString()); while(rs.next()) { int id =
		 * rs.getInt("id"); String mobile = rs.getString("src_phone"); String
		 * imsi = rs.getString("msg_text"); String receiveTime =
		 * rs.getString("log_time"); int portName = rs.getInt("serial_id");
		 * 
		 * } try{rs.close();rs=null;}catch(Exception e){};
		 * try{pstmt.close();pstmt=null;}catch(Exception e){};
		 * try{conn.close();conn=null;}catch(Exception e){};
		 * 
		 * } catch (Exception e) { e.printStackTrace(); log.debug(e); } finally
		 * { if(pstmt!=null) { try{pstmt.close();pstmt=null;}catch(Exception
		 * e){}; } if(conn!=null) { try{conn.close();conn=null;}catch(Exception
		 * e){}; } }
		 **/
		String mobile = "18820227927";
		String haoduanHeader = mobile.substring(0, 3);
		String subHaoduan = mobile.substring(3, 7);
		String fullHaoduan = mobile.substring(0, 7);
		System.out
				.println(haoduanHeader + "|" + subHaoduan + "|" + fullHaoduan);
	}

}
