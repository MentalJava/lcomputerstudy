package com.lcomputerstudy.testmvc.service;

import com.lcomputerstudy.testmvc.dao.CommDAO;
import com.lcomputerstudy.testmvc.vo.Comm;

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
}