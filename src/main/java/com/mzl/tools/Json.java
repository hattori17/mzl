package com.mzl.tools;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;

public class Json {
	public static void write(Object object,HttpServletResponse response) {
		String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void datagrid(Datagrid dg,HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(dg, "yyyy-MM-dd HH:mm:ss");
			StringBuffer sb = new StringBuffer (json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(sb.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void msg(Msg msg,HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(msg, "yyyy-MM-dd HH:mm:ss");
			StringBuffer sb = new StringBuffer (json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(sb.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
