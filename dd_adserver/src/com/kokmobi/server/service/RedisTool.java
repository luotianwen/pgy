package com.kokmobi.server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.service.impl.RedistServiceImpl;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;

import org.apache.curator.RetrySleeper;
import redis.clients.jedis.Jedis;


public class RedisTool {

//	public static final String ZK_ADDR ="104.250.145.186:2181";
	public static final String ZK_ADDR ="192.168.222.5:2181,192.168.222.6:2181,192.168.222.7:2181";
	public static final String ZK_PROXY_DIR = "/zk/codis/db_ddsdk/proxy";
	private static Log logger = LogFactory.getLog(RedistServiceImpl.class);

	private static JedisResourcePool jedisPool;

	static {
		jedisPool = RoundRobinJedisPool.create()
		        .curatorClient(ZK_ADDR, 30000).zkProxyDir(ZK_PROXY_DIR).build();
	}

	/**
	 * 插入key-val键值数据
	 */
	public static void set(String key, String val) {
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
	public static String get(String key) {
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
	 * integer类型自增
	 * @param key
	 * @return
	 */
	public static Long incr(String key) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {
			String val = jedis.get(key);
			logger.info("incrKey method time:"+ (System.currentTimeMillis()-start) + "ms");
			return jedis.incr(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * integer类型按照给定数字增加
	 * @param key
	 * @param incrValue
	 * @return
	 */
	public static Long incrBy(String key, long incrValue) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {
			String val = jedis.get(key);
			logger.info("incrBy method time:"+ (System.currentTimeMillis()-start) + "ms");
			return jedis.incrBy(key, incrValue);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 对象空闲时间
	 * @param key
	 * @return
	 */
	public static Long objectIdletime(String key) {
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {
			System.out.println("key:" + jedis.get(key));
//			Long idletime = jedis.objectIdletime(key);
			logger.info("objectIdletime method time:"+ (System.currentTimeMillis()-start) + "ms");
			return 0L;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 往list中插入数据，对值不排重
	 */
	public static long lpush(String key, String jsonObject) {
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
	public static long sadd(String key, String val) {
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
	public static Set<String> smembers(String key) {
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
	public static long zadd(String key, double score, String val) {
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
	public static Set<String> zrevrange(String key, long start, long end) {
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
	public static long expire(String key, int seconds) {
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
	public static long del(String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.del(key);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	public static long lpush(String key, List<String> records) {
		long rv = 0;
		if(null==records||records.size()==0)
			return rv;
		
		long start = System.currentTimeMillis();
		try (Jedis jedis = jedisPool.getResource()) {
			rv = jedis.lpush(key, records.toArray(new String[0]));
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
		logger.info("lpush string[] method time:"+ (System.currentTimeMillis()-start) + "ms");
		return rv;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RedisTool.set("test1", "123");
//
//		String r = RedisTool.get("test1");
//
//		System.out.println(r);
		RedisTool.set("int", "nihao");
//		System.out.println(RedisTool.incr("int"));
//		System.out.println(RedisTool.incr("int"));
//		System.out.println(RedisTool.incr("int"));
//		System.out.println("===========================");
//		try {
//			Thread.sleep(5);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println("exception===");
//		}
		System.out.println("objectIdletime:" + RedisTool.objectIdletime("int"));
//		System.out.println("===========================");
//		System.out.println("incrBy 3:" + RedisTool.incrBy("int", 3));
//		System.out.println("incrBy 5:" + RedisTool.incrBy("int", 5));
	}

}
