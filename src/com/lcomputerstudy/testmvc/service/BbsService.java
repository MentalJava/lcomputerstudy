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

}
