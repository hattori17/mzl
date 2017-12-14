package com.mzl.dao.school;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mzl.dao.BaseDao;

import com.mzl.entity.Page;
import com.mzl.tools.Datagrid;

@Repository
public class textDao{

	@Autowired
	BaseDao BaseDao;
	
	public Datagrid select(Page page,Integer key_id, String name_desc,String insert_time,Integer module_key_id) {
		Datagrid dg =  new Datagrid();
		String sql="select a.key_id,a.module_key_id,a.title,a.`desc`,a.keywords,a.insert_time,a.author,a.other_sign,a.parent_key_id,a.permit_key_id,"
				+ "b.`name` as module_name,c.`name` as user_name "
				+ "from t_text a,t_module b,t_user c where a.module_key_id=b.key_id and a.user_key_id=c.key_id ";
		if(name_desc!=null && !"".equals(name_desc)) {
			sql+=" and (a.title like '%"+name_desc+"%' or a.`desc` like '%"+name_desc+"%') ";
		}
		if(insert_time!=null && !"".equals(insert_time)) {
			sql+=" and DATE_FORMAT(a.insert_time,'%Y-%m-%d')='"+insert_time+"'";
		}
		if(module_key_id!=null) {
			sql+=" and a.module_key_id="+module_key_id;
		}
		if(key_id!=null) {
			sql+=" and a.key_id="+key_id;
		}
		dg = BaseDao.getDatagrid(sql, page);
		return dg;
	}
	
	public Map<String, Object> selectOne(Integer key_id) {
		Map<String, Object> dg =  new HashMap<String, Object>();
		String sql="select a.* "
				+ "from t_text a where 1=1 ";
		if(key_id!=null) {
			sql+=" and a.key_id="+key_id;
		}
		dg = BaseDao.getMap(sql);
		return dg;
	}
		
	public int insert_text(String module,String title,String desc,String keywords,String author,String content,String opr_id){
		String sql="insert into t_text(module_key_id,title,`desc`,keywords,content,insert_time,user_key_id,author,other_sign,parent_key_id,permit_key_id) "
				+ " values ("+module+",'"+title+"','"+desc+"','"+keywords+"','"+content+"',sysdate(),'1','"+author+"',null,null,'1,2,3,4')";
		return BaseDao.update(sql);
	}
	
	public int delete_text(String string_key_id){
		String sql="delete from t_text where key_id in "+string_key_id;
		return BaseDao.update(sql);
	}

	public int update_text(Integer key_id, String module, String title, String desc, String keywords, String author,
			String content, String opr_id) {
		String sql="update t_text set module_key_id="+module+",title='"+title+"',`desc`='"+desc+"',keywords='"+keywords+"',"
				+ "content='"+content+"',user_key_id=1,author='"+author+"' where key_id="+key_id+" ";
		return BaseDao.update(sql);
	}
	
}
