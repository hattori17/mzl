package com.mzl.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mzl.log.ErrorLog;

public class FormatDate {
	public String return_date(int i) {
		if (i == 0) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy.MM.dd HH:mm:ss");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			return str_date1;
		} else if (i == 1) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			return str_date1;
		} else if (i == 2) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			return str_date1;
		} else if (i == 3) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyMMddHHmmss");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			return str_date1;
		} else if (i == 4) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyyMMdd");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			return str_date1;
		} else if (i==5){
			SimpleDateFormat   df   =   new   SimpleDateFormat("yyyyMMdd");  		   
			  Calendar   c   =   Calendar.getInstance();       //   当时的日期和时间  
			  int   d   =   c.get(Calendar.DAY_OF_MONTH);     //   取出“日”数  
			  --d;                                                                       //   将“日”减一，即得到前一天  
			  c.set(Calendar.DAY_OF_MONTH,   d);               //   将“日”数设置回去  
			  //c.set(Calendar.DAY_OF_MONTH,   0);               //   如果当前日期是1日的情况会怎么样？  
			                                                           //   这里演示了这种情况(这里情况下1-1=0所以直接赋0值了)  
			  return df.format(c.getTime());   //   这里打印了上个月的最后一天，嘿嘿，满意吧。
		}else if (i==6){
			SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy年MM月dd日");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			return str_date1;
		}else if (i==7){
			SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");  		   
			  Calendar   c   =   Calendar.getInstance();       //   当时的日期和时间  
			  int   d   =   c.get(Calendar.DAY_OF_MONTH);     //   取出“日”数  
			  d = d + 1 ;                                                          //日加上一天         
			  c.set(Calendar.DAY_OF_MONTH,   d);               //   将“日”数设置回去  
			  return df.format(c.getTime());   //   这里打印了上个月的最后一天，嘿嘿，满意吧。
		}else if (i==8){
			SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");  		   
			  Calendar   c   =   Calendar.getInstance();       //   当时的日期和时间  
			  int   d   =   c.get(Calendar.DAY_OF_MONTH);     //   取出“日”数  
			  d = d - 1 ;                                     //日减上一天         
			  c.set(Calendar.DAY_OF_MONTH,   d);             //   将“日”数设置回去  
			  return df.format(c.getTime());   
		}else{
			return "error";
		}
	}
	public String getDateRandom(){
		String filename="" ;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
			Date currentTime = new Date();// 得到当前系统时间
			String str_date1 = formatter.format(currentTime); // 将日期时间格式化
			filename = str_date1+((int)(Math.random()*9000)+1000);
		} catch (Exception e) {
			ErrorLog.SetLog(e.getMessage(), this.getClass().getName()+"."+new Exception().getStackTrace()[0].getMethodName()) ;
		}
		return filename;
	}
	public String lpad(String s, int n, String replace) {  
        while (s.length() < n) {  
            s = replace+s;  
        }  
        return s;  
    }  
	public String rpad(String s, int n, String replace) {  
        while (s.length() < n) {  
            s = s+replace;  
        }  
        return s;  
    }
	
	public boolean isdate(String str){
	    try{
	    	if(str==null || (str.length()!=10 && str.length()!=8)){
	    		return false ;
	    	}
	    	DateFormat formatter=null ;
	    	if(str.length()==10){
	    		if(str.indexOf("-")!=-1){
	    			formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		}
	    		if(str.indexOf(".")!=-1){
	    			formatter = new SimpleDateFormat("yyyy.MM.dd");
	    		}
	    	}
	    	if(str.length()==8){
		    	formatter = new SimpleDateFormat("yyyyMMdd");
	    	}
	    	Date date = (Date)formatter.parse(str);
		    return str.equals(formatter.format(date));
	    	
	    }catch(Exception e){
	       return false;
	    }
	}
}
