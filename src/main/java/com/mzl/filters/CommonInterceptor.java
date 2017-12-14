package com.mzl.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 拦截统一处理
 * @author zhuli
 *
 */
public class CommonInterceptor implements HandlerInterceptor{

	boolean success=false;
	private String basePath="";
	String key_id="";
	String iframeID = "";
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("正在检查用户有没有登录");
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		boolean success=false;
		//iframeID
		iframeID = request.getParameter("iframeID");
		//获取项目路径
		String path = request.getContextPath();
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String url = request.getRequestURL().toString();
		String request_url=request.getServletPath();
		//检查登陆
		HttpSession session = request.getSession(false);
		//登录页或页面请求页无需登录
		if(url.indexOf("login.action")>-1 || url.indexOf("pageInterface")>-1){
			//System.out.println("本次请求无需登录");
			success = true;
		}else if(session != null && session.getAttribute("key_id")!=null  && !session.getAttribute("key_id").equals("")){
			key_id = session.getAttribute("key_id").toString();
			//System.out.println("已登录:"+opr_id);
			//检查权限
			String[] a = request_url.split("/");
			if(a.length>2){
				success = this.checkAuth(request_url, key_id);
				if(!success){
					//System.out.println("没权限");
					PrintWriter writer = response.getWriter();
	                writer.write("没权限");
	                writer.flush();
				}
			}else{
				//路径只有一层的不检查权限
				success = true;
			}
		}else{
			success = false;
			if (request.getHeader("accept").contains("application/json")  || (request.getHeader("X-Requested-With")!= null && request
	                .getHeader("X-Requested-With").contains("XMLHttpRequest") )) {
	        	//ajax异步请求，error，弹出登录提示
	        	try {
	        		PrintWriter writer = response.getWriter();
	                writer.write("<font color='red'>会话已失效,请重新登录后再操作！</font><a target='_blank' href='"+basePath+"login.action'>点击登陆</a>");
	                writer.flush();
	            } catch (IOException e) {
	                
	            }
	        }else{
	        	//非ajax异步请求，直接跳转到登陆页
	        	response.sendRedirect(basePath+"login.action");
	        }
		}
		return success;
	}
	
	private boolean checkAuth(String request_url,String opr_id){
		boolean success = true;
		String[] a = request_url.split("/");
		String prg_id = a[a.length-2];
		String operation = a[a.length-1];
		//System.out.println("需要检查"+opr_id+"有没有程序"+prg_id+"的"+operation+"权限");
		return success;
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView mv) throws Exception {
		if(mv!=null){
			mv.addObject("basePath",basePath);
			mv.addObject("key_id",key_id);
			mv.addObject("iframeID", iframeID);
		}
	}
}

