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
import com.kkgame.pay.stat.service.UserDataStatisticsService;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserDataStatisticsServiceImpl implements UserDataStatisticsService {

	private static final Log log = LogFactory.getLog(UserDataStatisticsServiceImpl.class);
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

	// 销量REGISTER_DATA_
	@Override
	public void statUserData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		if (type == Constants.SDK) {
			statisticsDao.deleteTempUserData(i, type);
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_INFO_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_").append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setProjectId(Integer.parseInt(key[2]));
							userData.setChannelId(key[3]);
							userData.setPluginId(Integer.parseInt(key[4]));
							userData.setCountry(Integer.parseInt(key[5]));
							userData.setCountryLevel(Integer.parseInt(key[6]));
							userData.setUserCount(score);
							list.add(userData);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertUserData(i, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertUserData(i, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}

		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}
	
	@Override
	public void statProjectUserData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_INFO_PROJECT_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_").append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setProjectId(Integer.parseInt(key[2]));
							userData.setChannelId(key[3]);
							userData.setPluginId(Integer.parseInt(key[4]));
							userData.setCountry(Integer.parseInt(key[5]));
							userData.setCountryLevel(Integer.parseInt(key[6]));
							userData.setUserCount(score);
							list.add(userData);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertUserDataProject(i, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertUserDataProject(i, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}

		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 销量REGISTER_DATA_
	@Override
	public void statVersionUserData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		if (type == Constants.SDK) {
			statisticsDao.deleteTempVersionUserData(i, type);
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_INFO_VERSION_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_")
				.append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setVersion(key[2]);
							userData.setUserCount(score);
							list.add(userData);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertVersionUserData(i, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertVersionUserData(i, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}
	
	
	
	// 销量REGISTER_DATA_
		@Override
		public void statVersionProjectUserData(int i, int type) throws DatabaseException {
			long nowTime = System.currentTimeMillis();
			StringBuffer sb = new StringBuffer();
			sb.append(Constants.USER_INFO_PROJECT_VERSION_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_")
					.append(type);
			// List<UserData> list = feeDao.getUserDataList(i, type);
			List<UserData> list = null;
			try (Jedis jedis = jedisPool.getResource()) {
				long count = jedis.zcard(sb.toString());
				int times = (int) count / Constants.RECORD_COUNT;
				long remainder = count % Constants.RECORD_COUNT;
				long start = 0;
				long end = 0;
				int flag = 0;
				if (remainder > 0) {
					flag = 1;
				}
				flag += times;
				list = new ArrayList<UserData>();
				for (int j = 0; j < flag; j++) {
					start = (j * Constants.RECORD_COUNT);
					end = (start+Constants.RECORD_COUNT-1);
					Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
					if (userInfoList != null && userInfoList.size() > 0) {
						for (Tuple tuple : userInfoList) {
							String keys = tuple.getElement();
							int score = (int) tuple.getScore();
							if (keys != null) {
								String[] key = keys.split("\\|");
								UserData userData = new UserData();
								userData.setStatDate(key[0]);
								userData.setType(Integer.parseInt(key[1]));
								userData.setVersion(key[2]);
								userData.setUserCount(score);
								list.add(userData);
							}
						}
					}
				}
				if (null != list && list.size() > 0) {
					int size = list.size();
					List<UserData> insertList = new ArrayList<UserData>();
					for(int k=0;k<size;k++) {
						insertList.add(list.get(k));
						if(k>0&&k%Constants.SQL_COUNT==0) {
							statisticsDao.insertVersionProjectUserData(i, type, insertList);
							insertList = new ArrayList<UserData>();
						}
					}
					if(insertList.size()>0) {
						statisticsDao.insertVersionProjectUserData(i, type, insertList);
					}
				}
			} catch (Exception e) {
				log.debug(e);
			}
			long lastTime = System.currentTimeMillis();
			log.info("time is" + (lastTime - nowTime) / 1000);
		}

	// 活跃REGISTER_DATA_
	@Override
	public void statUserActiveData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_PROJECT_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_")
				.append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setProjectId(Integer.parseInt(key[2]));
							userData.setChannelId(key[3]);
							userData.setPluginId(Integer.parseInt(key[4]));
							userData.setCountry(Integer.parseInt(key[5]));
							userData.setCountryLevel(Integer.parseInt(key[6]));
							userData.setActiveCount(score);
							list.add(userData);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertUserActiveData(i, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertUserActiveData(i, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
		
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 活跃REGISTER_DATA_
	@Override
	public void statVersionUserActiveData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_PROJECT_VERSION_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_")
				.append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setVersion(key[2]);
							userData.setActiveCount(score);
							list.add(userData);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertVersionUserActiveData(i, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertVersionUserActiveData(i, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
		
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 总活跃用户
	@Override
	public void statTotalUserActiveData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_").append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setProjectId(Integer.parseInt(key[2]));
							userData.setChannelId(key[3]);
							userData.setPluginId(Integer.parseInt(key[4]));
							userData.setCountry(Integer.parseInt(key[5]));
							userData.setCountryLevel(Integer.parseInt(key[6]));
							userData.setActiveCount(score);
							list.add(userData);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertTotalUserActiveData(i, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertTotalUserActiveData(i, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
		
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 总活跃用户
	@Override
	public void statTotalVersionUserActiveData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_VERSION_STAT).append("_").append(CalendarFormat.getYmd(i)).append("_")
				.append(type);
		// List<UserData> list = feeDao.getUserDataList(i, type);
		List<UserData> list = null;
		try (Jedis jedis = jedisPool.getResource()) {
			long count = jedis.zcard(sb.toString());
			int times = (int) count / Constants.RECORD_COUNT;
			long remainder = count % Constants.RECORD_COUNT;
			long start = 0;
			long end = 0;
			int flag = 0;
			if (remainder > 0) {
				flag = 1;
			}
			flag += times;
			list = new ArrayList<UserData>();
			for (int j = 0; j < flag; j++) {
				start = (j * Constants.RECORD_COUNT);
				end = (start+Constants.RECORD_COUNT-1);
				Set<Tuple> userInfoList = jedis.zrangeWithScores(sb.toString(), start, end);
				if (userInfoList != null && userInfoList.size() > 0) {
					for (Tuple tuple : userInfoList) {
						String keys = tuple.getElement();
						int score = (int) tuple.getScore();
						if (keys != null) {
							String[] key = keys.split("\\|");
							UserData userData = new UserData();
							userData.setStatDate(key[0]);
							userData.setType(Integer.parseInt(key[1]));
							userData.setVersion(key[2]);
							userData.setActiveCount(score);
							list.add(userData);
						}
					}
				}
			}

			if (null != list && list.size() > 0) {
				int size = list.size();
				List<UserData> insertList = new ArrayList<UserData>();
				for(int k=0;k<size;k++) {
					insertList.add(list.get(k));
					if(k>0&&k%Constants.SQL_COUNT==0) {
						statisticsDao.insertTotalVersionUserActiveData(i, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertTotalVersionUserActiveData(i, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	@Override
	public void statUserProjectActiveData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		int j = 0;
		List<UserData> list = feeDao.getUserProjectActiveDataList(i, type, j);
		if (null != list && list.size() > 0) {
			statisticsDao.insertUserProjectActiveData(i, type, list);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	@Override
	public void statVersionUserProjectActiveData(int i, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		int j = 0;
		List<UserData> list = feeDao.getVersionUserProjectActiveDataList(i, type, j);
		if (null != list && list.size() > 0) {
			statisticsDao.insertVersionUserProjectActiveData(i, type, list);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 统计按月用户数据REGISTER_DATA_
	@Override
	public void statMonthUserData(int i) throws DatabaseException {
		// 删除当月总销量
		long nowTime = System.currentTimeMillis();
		statisticsDao.deleteMonthUserData(i);
		List<UserData> list = statisticsDao.getMonthUserDataList(i);
		List<UserData> firstList = new ArrayList<UserData>();
		List<UserData> secondList = new ArrayList<UserData>();
		List<UserData> thirdList = new ArrayList<UserData>();
		List<UserData> fourthList = new ArrayList<UserData>();
		if (null != list && list.size() > 0) {
			for (UserData data : list) {
				if (data.getType() == Constants.SDK) {
					firstList.add(data);
				} else if (data.getType() == Constants.SINK) {
					secondList.add(data);
				} else if (data.getType() == Constants.LEAD) {
					thirdList.add(data);
				} else if (data.getType() == Constants.SILENCE) {
					fourthList.add(data);
				}
			}
			if (firstList.size() > 0) {
				statisticsDao.insertMonthUserData(i, 1, firstList);
			}
			if (secondList.size() > 0) {
				statisticsDao.insertMonthUserData(i, 2, secondList);
			}
			if (thirdList.size() > 0) {
				statisticsDao.insertMonthUserData(i, 3, thirdList);
			}
			if (fourthList.size() > 0) {
				statisticsDao.insertMonthUserData(i, 4, fourthList);
			}
		}
		List<UserData> activeList = statisticsDao.getMonthUserActiveDataList(i);
		if (activeList != null && activeList.size() > 0) {
			firstList = new ArrayList<UserData>();
			secondList = new ArrayList<UserData>();
			thirdList = new ArrayList<UserData>();
			fourthList = new ArrayList<UserData>();
			for (UserData data : activeList) {
				if (data.getType() == Constants.SDK) {
					firstList.add(data);
				} else if (data.getType() == Constants.SINK) {
					secondList.add(data);
				} else if (data.getType() == Constants.LEAD) {
					thirdList.add(data);
				} else if (data.getType() == Constants.SILENCE) {
					fourthList.add(data);
				}
			}
			if (firstList.size() > 0) {
				statisticsDao.insertMonthUserActiveData(i, 1, firstList);
			}
			if (secondList.size() > 0) {
				statisticsDao.insertMonthUserActiveData(i, 2, secondList);
			}
			if (thirdList.size() > 0) {
				statisticsDao.insertMonthUserActiveData(i, 3, thirdList);
			}
			if (fourthList.size() > 0) {
				statisticsDao.insertMonthUserActiveData(i, 4, fourthList);
			}
		}
		List<UserData> incomeList = statisticsDao.getMonthUserIncomeDataList(i);
		if (incomeList != null && incomeList.size() > 0) {
			statisticsDao.insertMonthUserIncomeData(i, incomeList);
		}
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	// 更新总用户数据
	@Override
	public void updateTotalUserData(int i) throws DatabaseException {
		List<UserData> total = statisticsDao.getTotalUserData();
		if (total != null && total.size() != 0) {
			for (UserData data : total) {
				data.setCoo_id("" + data.getProjectId());
				masterDao.updateProjectUserData(data);
			}
		}
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
		System.out.println(haoduanHeader + "|" + subHaoduan + "|" + fullHaoduan);
	}

}
