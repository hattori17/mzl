package com.mzl.controller.school;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mzl.entity.Page;
import com.mzl.entity.DataTable;
import com.mzl.service.school.textService;
import com.mzl.tools.Datagrid;
import com.mzl.tools.Json;
import com.mzl.tools.Msg;


@Controller
@RequestMapping("textAction")
public class textAction {
	
	private String jspSpace="/school/text/";
	
	@Autowired
	private textService textService;
	
	@RequestMapping("index.action")
	public ModelAndView index(HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(jspSpace+"text_list");
		return mv;
	}
	
	@RequestMapping("selectModuleList.action")
	public void select_class_list(HttpServletResponse response,Page page){
		List<Map<String,Object>> select_class_list = new ArrayList<Map<String,Object>>();
		Json.write(select_class_list, response);
	}
	
	@RequestMapping("select.action")
	public void select(HttpServletResponse response,Page page,Integer key_id,String name_desc,String insert_time,Integer module_key_id){
		Datagrid dg = textService.select(page,key_id,name_desc,insert_time,module_key_id);
		Json.datagrid(dg, response);
	}
	
	@RequestMapping("select_test.action")
	public void select_test(HttpServletResponse response,HttpServletRequest request, Integer key_id,String name_desc,String insert_time,Integer module_key_id,
			int draw){ 
        /*String order_column = "";
        String order_dir = "";
        int draw=0;
        int start=0;
        int length=0;
        String[] col_arr = new String[100];
        Map<String,String[]>params=request.getParameterMap();  
        for(String attr:params.keySet()){  
            String[] val=params.get(attr);  
            //System.out.println(attr+"="+Arrays.toString(val));
            System.out.println(attr+"="+val[0]);  
            if(attr.equals("start"))  
                start=Integer.parseInt(val[0]);  
            if(attr.equals("length"))  
                length=Integer.parseInt(val[0]);  
            if(attr.equals("order[0][column]")){
            	String string_order_column=val[0].toString();
            }
            if(attr.equals("draw"))  
                draw=Integer.parseInt(val[0]);  
        }*/
        
		Datagrid dg = textService.select(new Page(),key_id,name_desc,insert_time,module_key_id);
		
		JSONObject obj = new JSONObject();  
        obj.put("draw", draw);  
        obj.put("recordsTotal", dg.getTotal());  
        obj.put("recordsFiltered", dg.getTotal());  
        obj.put("data", dg.getRows());  
        
		Json.write(obj, response);
	}
	
	@RequestMapping(value="select_test1.action")
	public @ResponseBody void select_test1(HttpServletResponse response,HttpServletRequest request //,@RequestBody DataTable datatable
			) throws IOException{ 
		
		BufferedReader reader = request.getReader();
		String input = null;
		StringBuffer requestBody = new StringBuffer();
		while((input = reader.readLine()) != null) {
		requestBody.append(input);
		}
		JSONObject parameterJSON = new JSONObject();
		parameterJSON = JSONObject.parseObject(requestBody.toString());
		
		Page page = new Page();
		
		int draw = parameterJSON.getIntValue("draw");
		int start = parameterJSON.getIntValue("start");
		int length = parameterJSON.getIntValue("length");
		int order_column = (int)((Map<String,Object>)parameterJSON.getJSONArray("order").get(0)).get("column");
		String order_dir = ((Map<String,Object>)parameterJSON.getJSONArray("order").get(0)).get("dir").toString();
		String order = ((Map<String,Object>)parameterJSON.getJSONArray("columns").get(order_column)).get("data").toString();
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
        
		Json.write(obj, response);
	}
	
	@RequestMapping("add.action")
	public ModelAndView add(HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(jspSpace+"text_add");
		return mv;
	}
	
	@RequestMapping("insert_text.action")
	public void insert_text(HttpServletRequest request,HttpServletResponse response,
			String module,String title,String desc,String keywords,String author,String content){
		String user_key_id = request.getSession().getAttribute("key_id").toString();
		Msg msg = textService.insert_text(module,title,desc,keywords,author,content,user_key_id);
		Json.write(msg, response);
	}
	
	@RequestMapping("delete_text.action")
	public void delete_text(HttpServletResponse response,String key_ids){
		Msg msg = textService.delete_text(key_ids);
		Json.write(msg, response);
	}
	
	@RequestMapping("edit.action")
	public ModelAndView edit(HttpServletResponse response,Integer key_id){
		ModelAndView mv = new ModelAndView();
		Map<String, Object> text = textService.selectOne(key_id);
		mv.addObject("text", text);
		mv.setViewName(jspSpace+"text_edit");
		return mv;
	}
	
	@RequestMapping("update_text.action")
	public void update_text(HttpServletRequest request,HttpServletResponse response,
			Integer key_id,String module,String title,String desc,String keywords,String author,String content){
		String user_key_id = request.getSession().getAttribute("key_id").toString();
		Msg msg = textService.update_text(key_id,module,title,desc,keywords,author,content,user_key_id);
		Json.write(msg, response);
	}
	
}
