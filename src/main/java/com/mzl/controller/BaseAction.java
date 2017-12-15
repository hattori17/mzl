package com.mzl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mzl.entity.BaseResult;
import com.mzl.service.BaseService;

@Controller
@RequestMapping("/")
public class BaseAction {
	
	@Autowired
	private BaseService BaseService;
	
	@RequestMapping("index.action")
	public ModelAndView index(HttpServletResponse response,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String tree = "";
		tree = BaseService.get_tree();
		mv.addObject("tree", tree);
		mv.setViewName("/index");
		return mv;
	}
	
	@RequestMapping("login.action")
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("check_login.action")
	public ModelAndView check_login(HttpServletResponse response,HttpServletRequest request,String phone,String password) throws IOException{
		ModelAndView mv = new ModelAndView();
		BaseResult br = BaseService.login(phone, password);
		if(br.getResultCode()==0){
			Map<String,Object> map = new HashMap<String,Object>();
			map = (Map<String, Object>) br.getResultData();
			request.getSession().setAttribute("key_id", map.get("key_id"));
			request.getSession().setAttribute("name", map.get("name"));
			mv.setViewName("/school/index");
			mv = this.index(response, request);
			String path = request.getContextPath();
			response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/");
		}else{
			mv.addObject("msg", "账号密码错误或账号已被锁定！");
			mv.setViewName("/login");
		}
		return mv;
	}
	
	@RequestMapping("logout.action")
	public ModelAndView logout(HttpServletResponse response,HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView();
		request.getSession().setAttribute("key_id", null);
		mv.setViewName("/login");
		return mv;
	}
	
}
