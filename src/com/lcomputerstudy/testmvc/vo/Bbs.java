package com.lcomputerstudy.testmvc.vo;

public class Bbs {
	private int bbsID;
	private String bbsTitle;
	private String bbsContents;
	private int bbsViews;
	private String bbsUserID;
	private String bbsDate;
	private int rownum;
	
	public int getBbsID() {
		return bbsID;
	}
	
	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}
	
	public String getBbsTitle() {
		return bbsTitle;
	}
	
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	
	public String getBbsContents() {
		return bbsContents;
	}
	
	public void setBbsContents(String bbsContents) {
		this.bbsContents = bbsContents;
	}
	
	public int getBbsViews() {
		return bbsViews;
	}
	
	public void setBbsViews(int bbsViews) {
		this.bbsViews = bbsViews;
	}
	public String getBbsUserID() {
		return bbsUserID;
	}
	
	public void setBbsUserID(String bbsUserID) {
		this.bbsUserID = bbsUserID;
	}
	
	public String getBbsDate() {
		return bbsDate;
	}
	
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
}
