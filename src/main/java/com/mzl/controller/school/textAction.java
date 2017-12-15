package com.mzl.controller.school;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzl.entity.Page;
import com.mzl.entity.UserInfo;
import com.mzl.controller.BaseController;
import com.mzl.entity.DataTable;
import com.mzl.service.school.textService;
import com.mzl.tools.Datagrid;
import com.mzl.tools.AjaxReturn;

@Controller
@RequestMapping("textAction")
public class textAction  {
	
	private static final Log LOGGER = LogFactory.getLog(textAction.class);
	
	private String jspSpace="/school/text/";
	
	@Autowired
	private textService textService;
	
	@RequestMapping("index.action")
	public ModelAndView index(HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(jspSpace+"text_list");
		return mv;
	}
	
	@RequestMapping("select.action")
	public void select(HttpServletResponse response,Page page,Integer key_id,String name_desc,String insert_time,Integer module_key_id){
		Datagrid dg = textService.select(page,key_id,name_desc,insert_time,module_key_id);
		AjaxReturn.write(dg, response);
	}
	
	@RequestMapping(value = "select_test1.action", method=RequestMethod.POST ,produces = "application/json; charset=utf-8")
	public void select_test1(HttpServletResponse response,HttpServletRequest request ,@RequestBody DataTable datatable
			) throws IOException{ 
		
		LOGGER.debug(JSON.toJSON(datatable));
		
		Page page = new Page();
		
		int draw = datatable.getDraw();
		int length = datatable.getLength();
		int order_column = datatable.getOrder().get(0).getColumn();
		String order_dir = datatable.getOrder().get(0).getDir();
		String order = datatable.getColumns().get(order_column).getData();
		
		page.setPage(0);
		page.setRows(length);
		page.setSort(order);
		page.setOrder(order_dir);
		
		Datagrid dg = textService.select(page,null,null,null,null);
		
		JSONObject obj = new JSONObject();  
        obj.put("draw", draw);  
        obj.put("recordsTotal", 20);  
        obj.put("recordsFiltered", 20);  
        obj.put("data", dg.getRows());  
        
		AjaxReturn.write(obj, response);;
	}
	
	
}
