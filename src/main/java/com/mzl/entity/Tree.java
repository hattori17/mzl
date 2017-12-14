package com.mzl.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Tree {

	private String text;
	private HashMap<String,String> attributes;
	private ArrayList<Tree> children;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	public ArrayList<Tree> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Tree> children) {
		this.children = children;
	}
	
	
}
