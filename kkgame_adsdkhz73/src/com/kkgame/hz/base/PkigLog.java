package com.kkgame.hz.base;

import org.apache.log4j.Logger;

public class PkigLog {
	public static Logger logger;

	private PkigLog() {
	}

	public static void debug(Object obj) {
		if (null != logger) {
			logger.debug(obj);
		}
	}

	public static void info(Object obj) {
		if (null != logger) {
			logger.info(obj);
		}
	}

	public static void warn(Object obj) {
		if (null != logger) {
			logger.warn(obj);
		}
	}

	public static void error(Object obj) {
		if (null != logger) {
			logger.error(obj);
		}
	}

	public static void error(Object obj, Throwable th) {
		if (null != logger) {
			logger.error(obj, th);
		}
	}

	public static void fatal(Object obj) {
		if (null != logger) {
			logger.fatal(obj);
		}
	}
}