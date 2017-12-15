package com.mzl.entity;

import java.io.Serializable;
import java.util.List;

public class DataTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1846907860940954765L;
	
	private List<Column> columns;
	private Integer draw;
	private Integer length;
	private Integer start;
	private List<Order> order;
	private Search search;
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	
	
}
