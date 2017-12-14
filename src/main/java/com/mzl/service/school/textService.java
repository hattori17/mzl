package com.mzl.service.school;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzl.dao.school.textDao;
import com.mzl.entity.Page;
import com.mzl.tools.Datagrid;
import com.mzl.tools.Msg;

@Service
public class textService {
	@Autowired
	private textDao textDao;
	
	public Datagrid select(Page page,Integer key_id, String name_desc,String insert_time,Integer module_key_id) {
		return textDao.select(page,key_id,name_desc,insert_time,module_key_id);
	}
	
	public Map<String, Object> selectOne(Integer key_id){
		return textDao.selectOne(key_id);
	}
	
	
	@Transactional
	public Msg insert_text(String module,String title,String desc,String keywords,String author,String content,String opr_id) {
		Msg msg = new Msg();
		boolean success=true;
		String err_msg = "";
		//写记录
		if(success){
			int cnt = textDao.insert_text(module,title,desc,keywords,author,content,opr_id);
			if(cnt!=1) {
				success = false;
				err_msg = "新增失败！";
				throw new RuntimeException(err_msg);
			}else {
				success = true;
				err_msg = "新增成功！";
			}
		}
		msg.setMsg(err_msg);
		msg.setSuccess(success);
		return msg;
	}

	@Transactional
	public Msg delete_text(String key_ids) {
		Msg msg = new Msg();
		boolean success=true;
		String[] arr_key_id = key_ids.split(",");
		String string_key_id = "(";
		for(int i=0;i<arr_key_id.length;i++) {
			string_key_id+=arr_key_id[i]+"," ;
		}
		string_key_id = string_key_id.substring(0,string_key_id.length()-1)+")";
		int cnt = textDao.delete_text(string_key_id);
		msg.setMsg("删除"+cnt+"行！");
		msg.setSuccess(success);
		return msg;
	}
	
	@Transactional
	public Msg update_text(Integer key_id, String module, String title, String desc, String keywords, String author,
			String content, String opr_id) {
		Msg msg = new Msg();
		boolean success=true;
		String err_msg = "";
		//更新记录
		if(success){
			int cnt = textDao.update_text(key_id,module,title,desc,keywords,author,content,opr_id);
			if(cnt!=1) {
				success = false;
				err_msg = "更新失败！";
				throw new RuntimeException(err_msg);
			}
		}
		msg.setSuccess(success);
		msg.setMsg(err_msg);
		return msg;
	}
	
	
}