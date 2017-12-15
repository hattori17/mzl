package com.mzl.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mzl.entity.BaseResult;
import com.mzl.tools.AjaxReturn;

/**
 * 异常统一处理
 * @author zhuli
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	@SuppressWarnings("rawtypes")
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		ModelAndView mv = new ModelAndView();
        if (request.getHeader("accept").contains("application/json")  || (request.getHeader("X-Requested-With")!= null && request
                .getHeader("X-Requested-With").contains("XMLHttpRequest") )) {
        	//ajax异步请求
        	try {
                //PrintWriter writer = response.getWriter();
                String msg= ex.getMessage();
                String err="系统发生错误！请查看日志！";
                if(msg!=null){
                	char c = msg.charAt(0);
                	if(c >= 0x4E00 &&  c <= 0x9FA5){ //如果msg第一个字符是中文说明是手动抛出异常
                    	err = msg;
                    }
                }
                //writer.write("{\"success\":false,\"msg\":\""+err+"\"}");
                //writer.flush();
                BaseResult result = new BaseResult();
                result.setResultCode(-1);
                result.setResultMessage(err);
                AjaxReturn.write(result, response);
            } catch (Exception e) {
                
            }
        	this.writeLog(request, ex);
        	return null;
        }else{
        	//非ajax异步请求，直接跳转到错误页
        	this.writeLog(request, ex);
        	mv.addObject("success", "false");
        	String msg= ex.getMessage();
            String err="系统发生错误！";
            if(msg!=null){
            	char c = msg.charAt(0);
            	if(c >= 0x4E00 &&  c <= 0x9FA5){ //如果msg第一个字符是中文说明是手动抛出异常
                	err = msg;
                }
            }
    		mv.addObject("msg", err);
    		mv.setViewName("/error");
        	return mv;
        }
	}

	private void writeLog(HttpServletRequest request,Exception ex){
    	//记录错误日志
    	String params = "" ;
        Enumeration param = request.getParameterNames() ;
        while(param.hasMoreElements()){  
        	String paraName=(String)param.nextElement();  
        	params+=paraName+": "+request.getParameter(paraName)+";";  
    	} 
        ErrorLog.SetLog(ex,request) ;
        request.setAttribute("head.referer" , request.getHeader("referer"));
        request.setAttribute("params", params);
        request.setAttribute("err", ex.getMessage());  
        request.setAttribute("err_url", request.getRequestURL());
    }
	
}