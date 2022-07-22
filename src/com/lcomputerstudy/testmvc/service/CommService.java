package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.CommDAO;
import com.lcomputerstudy.testmvc.vo.Comm;
import com.lcomputerstudy.testmvc.vo.Pagination;

public class CommService {
	private static CommService service = null;
	private static CommDAO dao = null;
	
	private CommService() {
		
	}
	
	public static CommService getInstance() {
		if(service == null) {
			service = new CommService();
			dao = CommDAO.getInstance();	
		}
		return service;
	}
	
	public void insertComments(Comm comm) {
		dao.insertComments(comm);
	}
	
	public void replyComments(Comm comm) {
		dao.replyComments(comm);
	}

	public int getCount() {
		return dao.getCount();
	}

	public ArrayList<Comm> getList(Pagination pagination) {
		return dao.getList(pagination);
	}
}