package com.mzl.service.school;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzl.dao.school.textDao;
import com.mzl.entity.Page;
import com.mzl.tools.Datagrid;

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
	
}