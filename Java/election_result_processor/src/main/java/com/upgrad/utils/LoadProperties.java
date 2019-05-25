package com.upgrad.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class LoadProperties {
	public static Properties prop;
	static Logger logger = Logger.getLogger(LoadProperties.class);

	public LoadProperties(String fileName) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static Object getProperties(String key) {
		if (prop == null) {
			logger.error("Properties not intialized. Exiting");
			throw new RuntimeException("Properties not intialized. Exiting");
		}
		return prop.get(key);
	}

}