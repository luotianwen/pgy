package com.kokmobi.server.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.service.RedisService;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import static com.kokmobi.server.service.RedisTool.ZK_ADDR;


public class RedistServiceImpl implements RedisService {
	//public static final String ZK_ADDR ="121.201.34.72:2181";
	//public static   String ZK_ADDR ="192.168.199.206:2181,192.168.199.123:2181,192.168.199.172:2181";
	public static final String ZK_ADDR ="192.168.222.4:2181";

	public static final String ZK_PROXY_DIR = "/zk/codis/db_ddsdk/proxy";
	private static Log logger = LogFactory.getLog(RedistServiceImpl.class);
			
	private JedisResourcePool jedisPool;


	public void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		jedisPool = RoundRobinJedisPool.create()
		        .curatorClient(ZK_ADDR, 30000).zkProxyDir(ZK_PROXY_DIR).poolConfig(config).build();
	}


	/**
	 * 插入key-val键值数据
	 */
	public void set(String key, String val) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {			
		    jedis.set(key, val);		    
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		logger.info("set method time:"+ (System.currentTimeMillis()-start) + "ms");
	}

	/**
	 * 获取key-val数据
	 */
	public String get(String key) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {			
		    String val = jedis.get(key);
		    logger.info("get method time:"+ (System.currentTimeMillis()-start) + "ms");
		    return val;		    
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 往list中插入数据，对值不排重
	 */
	public long lpush(String key, String jsonObject) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {			
		    long rv = jedis.lpush(key, jsonObject);
		    logger.info("lpush string method time:"+ (System.currentTimeMillis()-start) + "ms");
		    return rv;
		}
		catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
			return 0;
		}
	}

	/**
	 * 往集合中插入数据，对值有排重
	 */
	public long sadd(String key, String val) {
		try (Jedis jedis = jedisPool.getResource()) {	
		    return jedis.sadd(key, val);
		}
		catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
			return 0;
		}
		
	}
	/**
	 * 获取某集合中的成员
	 */
	public Set<String> smembers(String key) {
		try (Jedis jedis = jedisPool.getResource()) {	
			return jedis.smembers(key);		    
		}
		catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
			return new HashSet<String>();
		}
	}
	/**
	 * 往有序集合中插入数据，对值有排重-对分值进行了排序
	 */
	public long zadd(String key, double score, String val) {
		try (Jedis jedis = jedisPool.getResource()) {	
			return jedis.zadd(key, score, val);	    
		}
		catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
			return 0;
		}
	}
	/**
	 * 获取根据分值倒序的区间数据
	 */
	public Set<String> zrevrange(String key, long start, long end) {
		try (Jedis jedis = jedisPool.getResource()) {	
			return jedis.zrevrange(key, start, end);	    
		}
		catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
			return new HashSet<String>();
		}
	}
	/**
	 * 设置key的过期时间
	 */
	public long expire(String key, int seconds) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {	
			long rv = jedis.expire(key, seconds);
			logger.info("expire method time:"+ (System.currentTimeMillis()-start) + "ms");
			return rv;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	@Override
	public long del(String key) {
		try (Jedis jedis = jedisPool.getResource()) {	
			return jedis.del(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	@Override
	public long lpush(String key, List<String> records) {
		long start = System.currentTimeMillis();
		long rv = 0;
		try (Jedis jedis = jedisPool.getResource()) {	
//			
			
//			for(int i=0;i<records.size();i++) {				
//				rv = jedis.lpush(key, records.get(i));
				rv = jedis.lpush(key, records.toArray(new String[0]));
//			}
				
//		    return rv;
		}
		catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
			return 0;
		}
		logger.info("lpush string[] method time:"+ (System.currentTimeMillis()-start) + "ms");
		return rv;
	}
	                                                                                                                                                                                                                                                                                   
	
	
}
