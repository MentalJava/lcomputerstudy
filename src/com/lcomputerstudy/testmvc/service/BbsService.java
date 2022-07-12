package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BbsDAO;
import com.lcomputerstudy.testmvc.vo.Bbs;
import com.lcomputerstudy.testmvc.vo.Pagination;

public class BbsService {

	private static BbsService service = null;
	private static BbsDAO dao = null;
	
	private BbsService() {
		
	}
	
	public static BbsService getInstance() {
		if(service == null) {
			service = new BbsService();
			dao = BbsDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Bbs> getList(Pagination pagination) {
		return dao.getList(pagination);
	}
	
	public void insertBbs(Bbs bbs) {
		dao.insertBbs(bbs);
	}
	
	public int getTotalCount() {
		return dao.getTotalCount();
	}

	public Bbs getDetail(Bbs bbs1) {
		return dao.getDetail(bbs1);
	}

	public void getEdit(Bbs bbs2) {
		dao.getEdit(bbs2);
	}

	public void getDelete(Bbs bbs4) {
		dao.getDelete(bbs4);
	}
	
	public void replyBbs(int order, int depth, int group) {
		dao.replyBbs(order, depth, group);
	}
}
