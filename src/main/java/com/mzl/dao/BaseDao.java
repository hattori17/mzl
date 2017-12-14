package com.mzl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mzl.entity.Page;
import com.mzl.tools.Datagrid;
import com.mzl.tools.Msg;

@Repository
public class BaseDao {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> getList(String sql){
		List<Map<String,Object>> list = (List<Map<String,Object>>) jdbcTemplate.queryForList(sql);
		return list;
	}
	
	public Map<String,Object> getMap(String sql){
		List<Map<String,Object>> list = (List<Map<String,Object>>) jdbcTemplate.queryForList(sql);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list.size()>0){
			map = list.get(0);
		}
		return map;
	}
	
	public Datagrid getDatagrid(String sql,Page page){
		Datagrid dg = new Datagrid();
		String query_sql=sql;
		if(page.getSort()!=null&&!"".equals(page.getSort())){
			query_sql+=" order by "+page.getSort()+" "+page.getOrder();
		}
		/*if(page.getPage()>0 && page.getRows()>0){
			query_sql+=" limit "+(page.getPage()-1)*page.getRows()+","+page.getRows()+"";
		}*/
		if(page.getRows()>0){
			query_sql+=" limit "+page.getPage()+","+page.getRows()+"";
		}
		List<Map<String,Object>> list = (List<Map<String,Object>>) jdbcTemplate.queryForList(query_sql);
		int count = jdbcTemplate.queryForInt("select count(0) from ("+sql+") t");
		dg.setRows(list);
		dg.setTotal(count);
		return dg;
	}
	
	/**
	 * 操作 insert,update,delete
	 * @param sql
	 * @return
	 */
	public int update(String sql){
		return jdbcTemplate.update(sql);
	}
	
	public Object getFirstCeil(String sql){
		Object value=null;
		List<Map<String,Object>> list = (List<Map<String,Object>>) jdbcTemplate.queryForList(sql);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list.size()>0){
			map = list.get(0);
			for (String key : map.keySet()) {
			    value = map.get(key);
			    break;
			}
		}
		return value;
	}
	
	/*登陆*/
	public Msg login(String phone,String password){
		Msg msg = new Msg();
		String sql="select name,key_id from t_user where phone='"+phone+"' and password='"+password+"' and status=1";
		Map<String,Object> map = this.getMap(sql);
		if(!map.isEmpty()){
			msg.setSuccess(true);
			msg.setObj(map);
		}else{
			msg.setSuccess(false);
		}
		return msg;
	}
	
	public List<Map<String,Object>> list_prg_parentID(int parent_id){
		String sql="select * from tt_prg where show_yn=1 and parent_id="+parent_id+" order by prg_seq";
		List<Map<String,Object>> list = this.getList(sql);
		return list;
	}
	
}
