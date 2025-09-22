package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static String path="config/config.properties";
	private static String env;
	private static Properties prop = new Properties();

	private ConfigManager(){
		
	}
	
	
	
	static {
		
		
	env=System.getProperty("env","qa");
	
	switch(env) {
	
	case "dev":{
		path="config/config.dev.properties";
		break;
	}
	case "qa":{
		path="config/config.qa.properties";
		break;
	}
	default:
		path="config/config.qa.properties";

//	
//	case "dev"-> path="config/config.dev.properties";	
//	case "qa" -> path="config/config.qa.properties";
//	default->path="config/config.qa.properties";
		
	}
	
	
	InputStream inputStream =	Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
	
		if(inputStream==null) {
			throw new RuntimeException("Cannot find the file  at the path "+path);
		}
		try {
			prop.load(inputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readProperty(String key) throws IOException {
		return prop.getProperty(key);
	}

}
