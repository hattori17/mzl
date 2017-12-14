package com.mzl.controller.wx;


import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mzl.entity.Page;
import com.mzl.service.school.textService;
import com.mzl.tools.Datagrid;
import com.mzl.tools.Json;
import com.mzl.tools.Msg;

@Controller
@RequestMapping("receiveAction")
public class receiveAction {
	
	private String jspSpace="/wx/";
	
	@Autowired
	private textService textService;
	
	@RequestMapping("index.action")
	public ModelAndView index(HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(jspSpace+"receive");
		return mv;
	}
	
	@RequestMapping("receive.action")
	public void receive(HttpServletResponse response,HttpServletRequest request) throws IOException{ 
		
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
		page.setPage(start);
		page.setRows(length);
		page.setSort(order);
		page.setOrder(order_dir);
		
		//搜索参数获取
		String search_key=parameterJSON.getString("search_key"),tsn=parameterJSON.getString("tsn"),
				ft=parameterJSON.getString("ft"),et=parameterJSON.getString("et"),usip=parameterJSON.getString("usip");
		//获取链接
		String wxid = "";
		if(usip!=null&&!"".equals(usip))wxid = utils.get_wxid(usip);
		
		//设置搜索参数
		Map<String, String> datas=new HashMap<>();
		datas.put("query", search_key);
		datas.put("interation", "");
		datas.put("ie", "utf8");
		datas.put("type", "2");
		datas.put("ft", ft==null?"":ft); 
		datas.put("et", et==null?"":et);
		datas.put("tsn", tsn);
		datas.put("usip", usip==null?"":usip);
		datas.put("wxid", wxid==null?"":wxid);
		datas.put("page", start/10+1+"");
		
		Document doc = utils.getPage(datas);
		//总记录数
		int result_cnt = 0;
		if(doc.select("#pagebar_container").isEmpty()) {
			result_cnt = doc.select(".txt-box h3 a").size();
		}else {
			result_cnt = Integer.parseInt(doc.select("#pagebar_container .mun").text().replace("找到约", "").replace("条结果", "").replaceAll(",", ""));
		}
		//计算页数
		//int page_cnt = result_cnt/10+1;
		
		Elements els = doc.select(".txt-box");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Element el:els) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Element u = el.selectFirst("h3 a");
			String title = u.text(); //文章标题
			String url = u.attr("href"); //具体文章的连接
			String date = el.selectFirst(".s-p").attr("t");//发布日期
			String user = el.selectFirst(".s-p").selectFirst("a").text();//发布公众号
			String user_url = el.selectFirst(".s-p").selectFirst("a").attr("href");
			Date d = new Date(Long.parseLong(date+"000"));
			map.put("title", title);
			map.put("url", "<a href=\""+url+"\" target=\"_blank\">点击查看</a>");
			map.put("date", sdf.format(d));
			map.put("user", "<a href=\""+user_url+"\" target=\"_blank\">"+user+"</a>");
			list.add(map);
		}
		
		JSONObject obj = new JSONObject();  
        obj.put("draw", draw);  
        obj.put("recordsTotal", result_cnt);  
        obj.put("recordsFiltered", result_cnt);  
        obj.put("data", list);  
        
		Json.write(obj, response);
	}
	
}
