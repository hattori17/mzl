package com.mzl.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.mzl.basic.StaticParameters;
import com.mzl.tools.FormatDate;


public class ErrorLog {
	private static String fileName = StaticParameters.ERROR_LOG_FOLDER+"/error_log_"+new FormatDate().return_date(1)+".log";
	
	public static void SetLog(String message,String errorurl){
			String s1 = new String();
			try {
				File f = new File(fileName);
				if (!f.exists()) {
					f.createNewFile();
					//linux系统下修改写出来的文件的权限
					if(System.getProperty("os.name").toLowerCase().indexOf("linux")>=0){
						Runtime.getRuntime().exec("chmod 777 "+fileName);
					}
				}				
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f,true),"gbk");      
		        BufferedWriter writer=new BufferedWriter(write);
		        s1 =  "\r\n------------"+new FormatDate().return_date(2) +"-------"+errorurl+"------------\r\n";
				s1 +=  message;
		        writer.append(s1);      
		        writer.close();
			} catch (Exception e) {
				System.out.println(e.getMessage()) ;
			}

		}
	
	@SuppressWarnings("rawtypes")
	public static void SetLog(Exception ex ,HttpServletRequest request){
		String params = "" ;
        Enumeration param = request.getParameterNames() ;
        while(param.hasMoreElements()){  
        	String paraName=(String)param.nextElement();  
        	params+=paraName+": "+request.getParameter(paraName)+";";  
    	} 
        //ex.toString();
        String message ="head.referer:"+request.getHeader("referer")+"\r\nerror_url:"+request.getRequestURL()+"\r\nparams:"+params+"\r\nex_message:"+ex.getMessage();
        
        
		String s1 = new String();
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
				//linux系统下修改写出来的文件的权限
				if(System.getProperty("os.name").toLowerCase().indexOf("linux")>=0){
					Runtime.getRuntime().exec("chmod 777 "+fileName);
				}
			}				
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f,true),"gbk");      
	        BufferedWriter writer=new BufferedWriter(write);
	        s1 =  "\r\n------------"+new FormatDate().return_date(2) +"-------------------\r\n";
			s1 +=  message;
	        writer.append(s1);      
	        writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()) ;
		}

	}
	
	public static void ClearLog(){
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fw =  new FileWriter(f);
			fw.write("");
			fw.close();
			//linux系统下修改写出来的文件的权限
			if(System.getProperty("os.name").toLowerCase().indexOf("linux")>=0){
				Runtime.getRuntime().exec("chmod 777 "+fileName);
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage()) ;
		}
	}
	
	public static String GetLog(){
		String s = new String();
		String s1 = new String();
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			
			InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");       
            BufferedReader reader=new BufferedReader(read);              
            while ((s = reader.readLine()) != null){
            	s1 += s +  "\r\n"+"<br/>";
            }
            read.close();
			return s1;
		} catch (Exception e) {
			//System.out.println(e.getMessage()) ;
		}
		return s1;
	}
	
}
