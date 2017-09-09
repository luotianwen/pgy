package com.kokmobi.server.service;

import java.util.List;
import java.util.Set;

public interface RedisService {
	public void set(String key, String val);
	public String get(String key);	
	public long lpush(String key, String val);
	public long lpush(String key, List<String> records);
	public long sadd(String key, String val);
	public Set<String> smembers(String key);
	public long zadd(String key, double score, String val);
	public Set<String> zrevrange(String key, long start, long end);
	public long expire(String key, int serconds) ;
	public long del(String key) ;
}
