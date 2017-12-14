package com.mzl.tools;

import java.io.InputStream;
import java.util.Properties;

import com.mzl.log.ErrorLog;

public class PublicClass {

	/*
	   * 获取参数文件
	   * */
	  public static Properties getProperties(){
			InputStream inputStream = new PublicClass().getClass().getClassLoader().getResourceAsStream("config/jdbc/application.properties");
		    Properties p = new Properties();
		    try{
		    	p.load(inputStream);
		    }catch(Exception e){
		    	ErrorLog.SetLog("getProperties失败", "com.main.tools.getProperties");
		    }
		    return p;
		}
}
