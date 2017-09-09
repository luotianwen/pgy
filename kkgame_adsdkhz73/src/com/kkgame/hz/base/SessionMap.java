package com.kkgame.hz.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SessionMap {

	public Map<String, String> userMap = null;
	
	public Map<Integer,String>  provinceMap = null; 

	private static SessionMap instance = null;

	private SessionMap() {
		userMap = new LinkedHashMap<String, String>();
		provinceMap = new LinkedHashMap<Integer, String>();
	}

	public static synchronized SessionMap getInstance() {
		if (instance == null) {
			instance = new SessionMap();
		}
		return instance;
	}

	public synchronized void addUser(String loginId, String name) {
		if (isValid(loginId)) {
			removeUser(loginId);
		}
		userMap.put(loginId, name);
	}

	public synchronized void removeUser(String loginId) {
		userMap.remove(loginId);
	}

	public synchronized boolean isValid(String loginId) {
		return userMap.containsKey(loginId);
	}

	public synchronized Map getUserMap() {
		Map<String, String> aMap = new HashMap<String, String>(userMap);
		return aMap;
	}

}
