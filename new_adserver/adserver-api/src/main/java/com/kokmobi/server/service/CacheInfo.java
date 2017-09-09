package com.kokmobi.server.service;

import java.util.Date;

/**
 * 带有过期时间的类<br>
 * 		当前访问时间与{@link #start}之间的时间差超过{@link #seconds}则为过期
 */
public class CacheInfo {
	private long seconds;
	private Date start;
	private Object object;
	
	public CacheInfo(Date start, long seconds, Object o) {
		this.seconds = seconds;
		this.object = o;
		this.start = start;
	}
	
	public boolean isExpired() {
		boolean rv = false;
		Date d = new Date();
		if((d.getTime()/1000 - this.start.getTime()/1000)>=seconds) {
			rv = true;
		}
		
		return rv;
	}

	public Object getObject() {
		return object;
	}
	
}
