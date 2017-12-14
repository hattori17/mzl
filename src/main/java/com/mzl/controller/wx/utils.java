package com.mzl.controller.wx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class utils {

	public static String get_wxid(String query) throws IOException{
		Map<String, String> datas_wxid=new HashMap<>();
		datas_wxid.put("zhnss", "1");
		datas_wxid.put("type", "1");
		datas_wxid.put("ie", "utf8");
		datas_wxid.put("query", query);
		
		Document doc_wxid = Jsoup.connect("http://weixin.sogou.com/weixin")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate, sdch")
				.header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
				.header("Connection", "keep-alive")
				.header("Cookie", "IPLOC=CN3301; SUID=FD01C4733320910A000000005A2012BC; SUV=1512051388259149; ABTEST=0|1512051389|v1; SNUID=E31CDA6E1E1B412DF8E568381E36AF7E; weixinIndexVisited=1; JSESSIONID=aaa3TmeAwtIhc4q7WNv8v")
				.header("Host", "weixin.sogou.com")
				.header("Upgrade-Insecure-Requests", "1")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
				.data(datas_wxid)
				.timeout(10000)
				.get();
		
		
		JSONObject wxobj = JSON.parseObject(doc_wxid.body().text());
		//{"code":"success","openid":"oIWsFt1Mbf-Dq5apkcDpjeSgfyWI","query":"淘宝那点事儿222222","sourcename":"淘宝那点事儿","weixinhao":"wuweizatan"}
		
		String wxid = wxobj.getString("openid");
		
		return wxid;
	}
	
	//按搜索参数获取一页的信息
	public static Document getPage(Map<String, String> datas) throws IOException{
		Document doc = Jsoup.connect("http://weixin.sogou.com/weixin")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate, sdch")
				.header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
				.header("Connection", "keep-alive")
				.header("Cookie", "IPLOC=CN3301; SUID=FD01C4733320910A000000005A2012BC; SUV=1512051388259149; ABTEST=0|1512051389|v1; SNUID=E31CDA6E1E1B412DF8E568381E36AF7E; weixinIndexVisited=1; JSESSIONID=aaa3TmeAwtIhc4q7WNv8v")
				.header("Host", "weixin.sogou.com")
				.header("Referer", "http://weixin.sogou.com/weixin?type=2&s_from=input&query=&ie=utf8&_sug_=n&_sug_type_=")
				.header("Upgrade-Insecure-Requests", "1")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
				.data(datas)
				.timeout(10000)
				.get();
		return doc ;
	}
}
