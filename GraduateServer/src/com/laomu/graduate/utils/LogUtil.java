package com.laomu.graduate.utils;

import org.apache.log4j.Logger;

public class LogUtil {

	static Logger log = Logger.getRootLogger();

	public static void warn(String logInfo) {
		log.warn(logInfo);
		System.out.println(logInfo);
	}
	
	public static void debug(String logInfo) {
		log.debug(logInfo);
		System.out.println(logInfo);
	}
	
	public static void error(String logInfo) {
		log.error(logInfo);
		System.out.println(logInfo);
	}
}
