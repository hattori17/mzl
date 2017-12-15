package com.mzl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mzl.dao.BaseDao;
import com.mzl.entity.BaseResult;
import com.mzl.entity.Tree;

@Service
public class BaseService {
	@Autowired
	private BaseDao BaseDao;
	
	public BaseResult login(String phone,String password){
		return BaseDao.login(phone, password);
	}
	
	private ArrayList<Tree> trees;
	
	//获取程序列表
	public String get_tree(){
		trees = new ArrayList<Tree>();
		String tree="";
		int parent_id=0;
		List<Map<String,Object>> list_tree = BaseDao.list_prg_parentID(parent_id);//根据parent_id获取列表
		this.Loop(list_tree,trees);
		tree = JSON.toJSON(trees).toString();
		return tree;
	}
	
	//无限层级递归取节点
	private void Loop(List<Map<String,Object>> list_tree,ArrayList<Tree> child){
		for(Map<String,Object> map:list_tree){
			int id = Integer.parseInt(map.get("id").toString());
			int parent_id = Integer.parseInt(map.get("parent_id").toString());
			String prg_name = map.get("prg_name").toString();
			String prg_url = map.get("prg_url")==null?"":map.get("prg_url").toString();
			Tree tree = new Tree();
			tree.setText(prg_name);
			if(!"".equals(prg_url)){
				HashMap<String,String> attributes = new HashMap<String,String>();
				attributes.put("url", prg_url);
				tree.setAttributes(attributes);
			}
			List<Map<String,Object>> child_tree = BaseDao.list_prg_parentID(id);//根据parent_id获取列表
			if(child_tree.size()>0){
				ArrayList<Tree> v_child = new ArrayList<Tree>();
				this.Loop(child_tree,v_child);
				tree.setChildren(v_child);
				if(parent_id==0){//根节点
					trees.add(tree);
				}else{
					child.add(tree);
				}
			}else{
				child.add(tree);
			}
		}
	}
}