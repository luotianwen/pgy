package com.kkgame.pay.stat.service.impl;

import com.kkgame.pay.stat.bean.*;
import com.kkgame.pay.stat.dao.StatisticsDao;
import com.kkgame.pay.stat.service.StatisticsService;
import com.kkgame.pay.stat.util.Constants;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatisticsServiceImpl implements StatisticsService {

	private static JedisResourcePool jedisPool;

	private static final Log log = LogFactory
			.getLog(StatisticsServiceImpl.class);
	private StatisticsDao statisticsDao;
	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	@Override
	public void saveActive() {
		executorService.execute(new Runnable() {
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				try {
					jedis = jedisPool.getResource();
					List<UserData> listUser = new ArrayList<UserData>();
					String userRedis;
					UserData adVO;
					while (flag) {
						userRedis= jedis.rpop("user_active_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							i++;
							/*adDataObject = JSONObject.fromObject(userRedis);
							adVO= (UserData) JSONObject.toBean(
									adDataObject, UserData.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.SQL_COUNT == 0) {
								i = 0;
								statisticsDao.saveActive(listUser);

								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Constants.ISRUNHY=false;
				}

			 }
		 });

	}

	@Override
	public void saveUser() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {

				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				try {

					StringBuffer hostPort = new StringBuffer();
					hostPort.append(Constants.REDIS_HOST).append(":")
							.append(Constants.ZK_PORT);
					jedisPool = RoundRobinJedisPool.create()
							.curatorClient(hostPort.toString(), 30000)
							.zkProxyDir(Constants.ZK_PROXY_DIR).build();
					jedis = jedisPool.getResource();
					List<UserData> listUser = new ArrayList<UserData>();
					String userRedis;
					UserData adVO;
					while (flag) {
						  userRedis = jedis.rpop("user_info_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (UserData) JSONObject.toBean(
									adDataObject, UserData.class);
							listUser.add(adVO);

							if (i > 0 && i %Constants.SQL_COUNT == 0) {
								i = 0;
								statisticsDao.saveUser(listUser);

								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Constants.ISRUNXL=false;
				}


			 }
		 });

	}

	@Override
	public void saveProjectActive() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				UserData adVO;
				try {
					jedis = jedisPool.getResource();
					List<UserData> listUser = new ArrayList<UserData>();
					while (flag) {
						  userRedis = jedis.rpop("user_info_project_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (UserData) JSONObject.toBean(
									adDataObject, UserData.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.SQL_COUNT == 0) {
								i = 0;
								statisticsDao.saveProjectActive(listUser);

								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNXMXL=false;
				}
			}
		});

	}

	@Override
	public void saveSent() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					String userRedis;
					AdDataVO adVO;
					while (flag) {
						  userRedis = jedis.rpop("ad_send_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i %Constants.RECORD_COUNT== 0) {
								i = 0;

								statisticsDao.saveSent(listUser);

								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNXF=false;
				}

			}
		});

	}

	@Override
	public void saveSentSucc() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					while (flag) {
						  userRedis = jedis.rpop("ad_send_succ_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;

								statisticsDao.saveSentSucc(listUser);
								listUser.clear();

							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNJS=false;
				}

			}
		});

	}

	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	@Override
	public void saveShow() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					while (flag) {
						  userRedis = jedis.rpop("ad_show_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;

								statisticsDao.saveShow(listUser);
								listUser.clear();

							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNZS=false;
				}

			}
		});

	}

	@Override
	public void saveActivate() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					while (flag) {
						  userRedis = jedis.rpop("ad_activate_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;

								statisticsDao.saveActivate(listUser);
								listUser.clear();

							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNJH=false;
				}

			}
		});

	}

	@Override
	public void saveInstalled() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					while (flag) {
						  userRedis = jedis.rpop("ad_installed_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;

								statisticsDao.saveInstalled(listUser);

								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNAZ=false;
				}

			}
		});

	}

	@Override
	public void saveDownload() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					while (flag) {
						  userRedis = jedis.rpop("ad_download_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;

								statisticsDao.saveDownload(listUser);
								listUser.clear();

							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNXZ=false;
				}

			}
		});

	}

	@Override
	public void saveClick() {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listUser = new ArrayList<AdDataVO>();
					while (flag) {
						  userRedis = jedis.rpop("ad_click_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							  adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;
								statisticsDao.saveClick(listUser);
								listUser.clear();

							}*/

						}
					}
				} catch (Exception e) {
					Constants.ISRUNDJ=false;
				}

			}
		});

	}

	@Override
	public void saveLinkAdData() {
		{

			executorService.execute(new Runnable() {
				@Override
				public void run() {
					StringBuffer hostPort = new StringBuffer();
					hostPort.append(Constants.REDIS_HOST).append(":")
							.append(Constants.ZK_PORT);
					jedisPool = RoundRobinJedisPool.create()
							.curatorClient(hostPort.toString(), 30000)
							.zkProxyDir(Constants.ZK_PROXY_DIR).build();
					boolean flag = true;
					Jedis jedis = null;
					int i = 0;
					JSONObject adDataObject = null;
					String userRedis;
					AdLinkDataVO adVO;
					try {
						jedis = jedisPool.getResource();
						List<AdLinkDataVO> listUser = new ArrayList<AdLinkDataVO>();
						while (flag) {
							userRedis = jedis.rpop("ad_link_data_day");
							if ((userRedis == null) || ("nil".equals(userRedis))) {
								try {
									Thread.sleep(300L);
								} catch (InterruptedException e) {
								}
							} else {
								/*i++;
								adDataObject = JSONObject.fromObject(userRedis);
								adVO = (AdLinkDataVO) JSONObject.toBean(
										adDataObject, AdLinkDataVO.class);
								listUser.add(adVO);

								if (i > 0 && i % Constants.RECORD_COUNT == 0) {
									i = 0;
									statisticsDao.saveLinkAdData(listUser);
									listUser.clear();

								}*/

							}
						}
					} catch (Exception e) {
						Constants.ISRUNDJ=false;
					}

				}
			});

		}
	}

	@Override
	public void saveDssdkAdData() {
		{

			executorService.execute(new Runnable() {
				@Override
				public void run() {
					StringBuffer hostPort = new StringBuffer();
					hostPort.append(Constants.REDIS_HOST).append(":")
							.append(Constants.ZK_PORT);
					jedisPool = RoundRobinJedisPool.create()
							.curatorClient(hostPort.toString(), 30000)
							.zkProxyDir(Constants.ZK_PROXY_DIR).build();
					boolean flag = true;
					Jedis jedis = null;
					int i = 0;
					JSONObject adDataObject = null;
					String userRedis;
					AdDssdkDataVO adVO;
					try {
						jedis = jedisPool.getResource();
						List<AdDssdkDataVO> listUser = new ArrayList<AdDssdkDataVO>();
						while (flag) {
							userRedis = jedis.rpop("ad_dssdk_data_day");
							if ((userRedis == null) || ("nil".equals(userRedis))) {
								try {
									Thread.sleep(300L);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} else {
								/*i++;
								adDataObject = JSONObject.fromObject(userRedis);
								adVO = (AdDssdkDataVO) JSONObject.toBean(
										adDataObject, AdDssdkDataVO.class);
								listUser.add(adVO);

								if (i > 0 && i % Constants.RECORD_COUNT == 0) {
									i = 0;
									statisticsDao.saveDssdkAdData(listUser);
									System.out.println("电商"+i);
									listUser.clear();
								}*/

							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						Constants.ISRUNDSSDK=false;
					}

				}
			});

		}
	}

	@Override
	public void saveSubData() {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdSubDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdSubDataVO> listUser = new ArrayList<AdSubDataVO>();
					while (flag) {
						userRedis = jedis.rpop("ad_sub_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							adVO = (AdSubDataVO) JSONObject.toBean(
									adDataObject, AdSubDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;
								statisticsDao.saveSubAdData(listUser);
								System.out.println("电商"+i);
								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Constants.ISRUNSUB=false;
				}

			}
		});
	}

	@Override
	public void saveSubLinkData() {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0;
				JSONObject adDataObject = null;
				String userRedis;
				SubLinkDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<SubLinkDataVO> listUser = new ArrayList<SubLinkDataVO>();
					while (flag) {
						userRedis = jedis.rpop("ad_sub_link_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							adVO = (SubLinkDataVO) JSONObject.toBean(
									adDataObject, SubLinkDataVO.class);
							listUser.add(adVO);

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;
								statisticsDao.saveSubLinkAdData(listUser);
								listUser.clear();
							}*/

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Constants.ISRUNSUBLINK=false;
				}

			}
		});
	}

	@Override
	public void saveOffersdkAdData() {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0; int j = 0;
				JSONObject adDataObject = null;
				String userRedis;
				AdDataVO adVO;
				try {
					jedis = jedisPool.getResource();
					List<AdDataVO> listshow = new ArrayList<AdDataVO>();
					List<AdDataVO> listclick = new ArrayList<AdDataVO>();
					while (flag) {
						userRedis = jedis.rpop("ad_offersdk_data_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {/*
							adDataObject = JSONObject.fromObject(userRedis);
							adVO = (AdDataVO) JSONObject.toBean(
									adDataObject, AdDataVO.class);
							if(adVO.getBackType().equals("1")){
								i++;
								listshow.add(adVO);
							}else {
								j++;
								listclick.add(adVO);
							}

							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;
								statisticsDao.saveShow(listshow);
								System.out.println("订阅SDK展示回传"+i);
								listshow.clear();
							}
							if (j > 0 && j % Constants.RECORD_COUNT == 0) {
								j = 0;
								statisticsDao.saveClick(listclick);
								System.out.println("订阅SDK点击回传"+j);
								listclick.clear();
							}*/
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Constants.ISRUNOFFERSDK=false;
				}

			}
		});
	}

	@Override
	public void statIframeAdData() {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				StringBuffer hostPort = new StringBuffer();
				hostPort.append(Constants.REDIS_HOST).append(":")
						.append(Constants.ZK_PORT);
				jedisPool = RoundRobinJedisPool.create()
						.curatorClient(hostPort.toString(), 30000)
						.zkProxyDir(Constants.ZK_PROXY_DIR).build();
				boolean flag = true;
				Jedis jedis = null;
				int i = 0; int j = 0;
				JSONObject adDataObject = null;
				String userRedis;
				IframeVO iframeVO;
				try {
					jedis = jedisPool.getResource();
					List<IframeVO> listshow = new ArrayList<IframeVO>();
					while (flag) {
						userRedis = jedis.rpop("adData_iframe_day");
						if ((userRedis == null) || ("nil".equals(userRedis))) {
							try {
								Thread.sleep(300L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							/*i++;
							adDataObject = JSONObject.fromObject(userRedis);
							iframeVO = (IframeVO) JSONObject.toBean(
									adDataObject, IframeVO.class);
							listshow.add(iframeVO);
							if (i > 0 && i % Constants.RECORD_COUNT == 0) {
								i = 0;
								statisticsDao.saveIframeActive(listshow,"adv_iframe_list_");
								System.out.println("iframe下发数据"+i);
								listshow.clear();
							}*/
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Constants.ISRUNIFRAME=false;
				}
			}
		});
	}

	@Override
	public void statIframeActiveAdData() {
		executorService.execute(new Runnable() {
		@Override
		public void run() {
			StringBuffer hostPort = new StringBuffer();
			hostPort.append(Constants.REDIS_HOST).append(":")
					.append(Constants.ZK_PORT);
			jedisPool = RoundRobinJedisPool.create()
					.curatorClient(hostPort.toString(), 30000)
					.zkProxyDir(Constants.ZK_PROXY_DIR).build();
			boolean flag = true;
			Jedis jedis = null;
			int i = 0; int j = 0;
			JSONObject adDataObject = null;
			String userRedis;
			IframeVO iframeVO;
			try {
				jedis = jedisPool.getResource();
				List<IframeVO> listshow = new ArrayList<IframeVO>();
				while (flag) {
					userRedis = jedis.rpop("adData_iframeActive_day");
					if ((userRedis == null) || ("nil".equals(userRedis))) {
						try {
							Thread.sleep(300L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						/*adDataObject = JSONObject.fromObject(userRedis);
						iframeVO = (IframeVO) JSONObject.toBean(
								adDataObject, IframeVO.class);
						listshow.add(iframeVO);

						statisticsDao.saveIframeActive(listshow,"adv_iframe_active_");
						System.out.println("iframe激活数据"+i);
						listshow.clear();*/

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Constants.ISRUNIFRAMEACTIVE=false;
			}

		}
	});
	}

}
