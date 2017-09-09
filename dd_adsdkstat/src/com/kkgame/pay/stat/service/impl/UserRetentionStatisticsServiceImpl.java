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
import com.kkgame.pay.stat.service.UserRetentionStatisticsService;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserRetentionStatisticsServiceImpl implements UserRetentionStatisticsService {

	private static final Log log = LogFactory.getLog(UserRetentionStatisticsServiceImpl.class);
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

	// 留存数据
	@Override
	public void statRetentionData(int num, int type) throws DatabaseException {
		long nowTime = System.currentTimeMillis();
		statFirstRetentionData(num, type);
		statSecondRetentionData(num, type);
		statThirdRetentionData(num, type);
		statFourthRetentionData(num, type);
		statFifthRetentionData(num, type);
		statSixthRetentionData(num, type);
		statSeventhRetentionData(num, type);
		statFiftyRetentionData(num, type);
		statThirtyRetentionData(num, type);
		long lastTime = System.currentTimeMillis();
		log.info("time is" + (lastTime - nowTime) / 1000);
	}

	private void statFirstRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前1天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 1);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertFirstUserData(i+1, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertFirstUserData(i+1, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}

	}

	private void statSecondRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前2天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 2);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertSecondUserData(i + 2, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertSecondUserData(i + 2, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}

	}

	private void statThirdRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前3天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 3);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertThirdUserData(i + 3, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertThirdUserData(i + 3, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statFourthRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前4天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 4);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertFourthUserData(i + 4, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertFourthUserData(i + 4, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}

	}

	private void statFifthRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前5天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 5);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertFifthUserData(i + 5, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertFifthUserData(i + 5, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statSixthRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前6天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 6);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);

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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertSixthUserData(i + 6, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertSixthUserData(i + 6, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statSeventhRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前7天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 7);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);

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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertSeventhUserData(i + 7, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertSeventhUserData(i + 7, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}

	}

	private void statFiftyRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前15天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 15);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertFiftyUserData(i + 15, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertFiftyUserData(i + 15, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
	}

	private void statThirtyRetentionData(int i, int type) throws DatabaseException {
		// 获取当前日期，前30天
		String statDate = CalendarFormat.getYmd(i);
		String registerDate = CalendarFormat.getYmd(i + 30);
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.USER_ACTIVE_REGISTER_STAT).append("_").append(statDate).append("_").append(registerDate).append("_")
				.append(type);
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
					list = new ArrayList<UserData>();
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
						statisticsDao.insertThirtyUserData(i + 30, type, insertList);
						insertList = new ArrayList<UserData>();
					}
				}
				if(insertList.size()>0) {
					statisticsDao.insertThirtyUserData(i + 30, type, insertList);
				}
			}
		} catch (Exception e) {
			log.debug(e);
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
