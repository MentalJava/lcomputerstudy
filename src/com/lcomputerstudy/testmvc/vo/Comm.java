package com.lcomputerstudy.testmvc.vo;

public class Comm {
	private int c_id;
	private int b_id;
	private int c_group;
	private int c_order;
	private int c_depth;
	private int rownum;
	private String c_userid;
	private String c_date;
	private String c_comments;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getC_id() {
		return c_id;
	}
	
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	
	public int getB_id() {
		return b_id;
	}
	
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	
	public int getC_group() {
		return c_group;
	}
	
	public void setC_group(int c_group) {
		this.c_group = c_group;
	}
	
	public int getC_order() {
		return c_order;
	}
	
	public void setC_order(int c_order) {
		this.c_order = c_order;
	}
	
	public int getC_depth() {
		return c_depth;
	}
	
	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}
	
	public String getC_userid() {
		return c_userid;
	}
	
	public void setC_userid(String c_userid) {
		this.c_userid = c_userid;
	}
	
	public String getC_comments() {
		return c_comments;
	}
	
	public void setC_comments(String c_comments) {
		this.c_comments = c_comments;
	}
	
	public String getC_date() {
		return c_date;
	}
	
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
}
