package com.lcomputerstudy.testmvc.controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.database.DBconnection;
import com.lcomputerstudy.testmvc.service.BbsService;
import com.lcomputerstudy.testmvc.service.CommService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Bbs;
import com.lcomputerstudy.testmvc.vo.Comm;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		int usercount = 0;
		int bbscount = 0;
		int page = 1;
		String pw = null;
		String idx;
		HttpSession session = null;
		command = checkSession(request, response, command);
		boolean isRedirected = false;
		
		
		switch (command) {
		case "/user-list.do":
			String reqPage = request.getParameter("page");
			if (reqPage != null) 
				page = Integer.parseInt(reqPage);
			
			UserService userService = UserService.getInstance();
			usercount = userService.getUsersCount();
			
			Pagination pagination = new Pagination();
			pagination.setPage(page);
			pagination.setCount(usercount);
			pagination.init();
			
			ArrayList<User> list = userService.getUsers(pagination);
			
			view = "user/list";
			request.setAttribute("list", list);
			request.setAttribute("pagination", pagination);
			break;
			
		case "/user-insert.do":
			view = "user/insert";
			break;
			
		case "/user-insert-process.do":
			User user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("password"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
			user.setU_age(request.getParameter("age"));
			
			userService = UserService.getInstance();
			userService.insertUser(user);
					
			view = "user/insert-result";
			break;
			
		case "/user-login.do":
			view = "user/login";
			break;
			
		case "/user-login-process.do":
			idx = request.getParameter("login_id");
			pw = request.getParameter("login_password");
			
			userService = UserService.getInstance();
			user = userService.loginUser(idx,pw);
			
			if(user != null) {
				session = request.getSession();
				session.setAttribute("user", user);
				
				view = "user/login-result";
			} else {
				view = "user/login-fail";
			}
			break;
			
		case "/logout.do":
			session = request.getSession();
			session.invalidate();
			view = "user/login";
			break;
			
		case "/access-denied.do":
			view = "user/access-denied";
			break;
			
		case "/board-bbslist.do":
			String reqPage1 = request.getParameter("page");
			if (reqPage1 != null) 
				page = Integer.parseInt(reqPage1);
			
			BbsService bbsService = BbsService.getInstance();
			bbscount = bbsService.getTotalCount();
			
			Pagination pagination1 = new Pagination();
			pagination1.setPage(page);
			pagination1.setCount(bbscount);
			pagination1.init();
			
			ArrayList<Bbs> list1 = bbsService.getList(pagination1);
			
			view = "board/bbsList";
			request.setAttribute("list1", list1);
			request.setAttribute("pagination1", pagination1);
			break;
			
		case "/board-bbscontents.do":
			String strGroup = request.getParameter("group");
			Bbs bbs5 = null;
		
			if (strGroup != null) {
				bbs5 = new Bbs();
				bbs5.setBbsgroup(Integer.parseInt(request.getParameter("group")));
				bbs5.setBbsorder(Integer.parseInt(request.getParameter("order")));
				bbs5.setBbsdepth(Integer.parseInt(request.getParameter("depth")));
			}
			
			view = "board/bbsContents";
			request.setAttribute("bbs", bbs5);
			break;
			
		case "/board-bbscontents-process.do":
			Bbs bbs = new Bbs();
			String pGroup = request.getParameter("bbsgroup");
			bbs.setBbsUserID(request.getParameter("userid"));
			bbs.setBbsTitle(request.getParameter("title"));
			bbs.setBbsContents(request.getParameter("contents"));
			
			
			if (!(pGroup.equals(""))) {
				bbs.setBbsgroup(Integer.parseInt(request.getParameter("bbsgroup")));
				bbs.setBbsorder(Integer.parseInt(request.getParameter("bbsorder")));
				bbs.setBbsdepth(Integer.parseInt(request.getParameter("bbsdepth")));
				bbsService = BbsService.getInstance();
				bbsService.replyBbs(bbs);
			} else {	
			bbsService = BbsService.getInstance();
			bbsService.insertBbs(bbs);
			}
			
			view = "board/bbsResult";
			request.setAttribute("bbs", bbs);
			break;
			
		case "/board-bbsdetail.do":
			Bbs bbs1 = new Bbs();
			bbs1.setBbsID(Integer.parseInt(request.getParameter("bbsid")));
			bbsService = BbsService.getInstance();
			bbs1 = bbsService.getDetail(bbs1);
			
			String reqPage2 = request.getParameter("page");
			if (reqPage2 != null) 
				page = Integer.parseInt(reqPage2);
			
			CommService commService = CommService.getInstance();
			int commcount = commService.getCount();
			
			pagination = new Pagination();
			pagination.setPage(page);
			pagination.setCount(commcount);
			pagination.init();
			
			ArrayList<Comm> list2 = commService.getList(pagination, bbs1);
			
			view = "board/bbsDetail";
			request.setAttribute("bbs", bbs1);	
			request.setAttribute("list", list2);
			request.setAttribute("pagination", pagination);
			break;
		case "/aj-comment-update.do":
			Comm comm = new Comm();
			comm.setC_userid(request.getParameter("c_uid"));	
			comm.setC_comments(request.getParameter("com"));
			comm.setB_id(Integer.parseInt(request.getParameter("bbsid")));
			comm.setC_id(Integer.parseInt(request.getParameter("cid")));
			
			commService = CommService.getInstance();
			commService.editComments(comm);
			
			bbs = new Bbs();
			bbs.setBbsID(Integer.parseInt(request.getParameter("bbsid")));
			bbsService = BbsService.getInstance();
			bbs = bbsService.getDetail(bbs);
			
			String reqPage3 = request.getParameter("page");
			if (reqPage3 != null) 
				page = Integer.parseInt(reqPage3);
			
			commService = CommService.getInstance();
			int commcounts = commService.getCount();
			
			pagination = new Pagination();
			pagination.setPage(page);
			pagination.setCount(commcounts);
			pagination.init();
			
			ArrayList<Comm> list3 = commService.getList(pagination, bbs);
			
			
			view = "comments/aj-comment-list";
			request.setAttribute("list", list3);
			break;
			
		case "/board-bbsedit.do":
			Bbs bbs3 = new Bbs();
			bbs3.setBbsID(Integer.parseInt(request.getParameter("bbsid")));
			
			bbsService = BbsService.getInstance();
			bbs3 = bbsService.getDetail(bbs3);
			
			view = "board/bbsEdit";
			request.setAttribute("bbs", bbs3);
			break;
			
		case "/board-bbsedit-process.do":
			bbs = new Bbs();
			bbs.setBbsID(Integer.parseInt(request.getParameter("bbsid")));
			bbs.setBbsUserID(request.getParameter("userid"));
			bbs.setBbsTitle(request.getParameter("title"));
			bbs.setBbsContents(request.getParameter("contents"));
			
			bbsService = BbsService.getInstance();
			bbsService.getEdit(bbs);
			
			view = "board/bbsEdit-result";
			request.setAttribute("bbs", bbs);
			break;
			
		case "/board-bbsdelete-process.do":
			Bbs bbs4 = new Bbs();
			
			bbs4.setBbsID((Integer.parseInt(request.getParameter("bbsid"))));
			
			bbsService = BbsService.getInstance();
			bbsService.getDelete(bbs4);
			
			view = "board/bbsDelete";
			break;
			
		case "/aj-comment-delete.do":
			comm = new Comm();
			
			comm.setC_id(Integer.parseInt(request.getParameter("cid")));
			
			commService = CommService.getInstance();
			commService.deleteComments(comm);
			
			view = "board/bbsDetail";
			break;
			
		case "/comments-comm-process.do":
			String b_id = request.getParameter("b_id");
			
			comm = new Comm();
			if(!(b_id.equals(""))) {
				comm.setB_id(Integer.parseInt(b_id));
				comm.setC_userid(request.getParameter("userid"));
				comm.setC_comments(request.getParameter("comments"));
				
				commService = CommService.getInstance();
				commService.insertComments(comm);
			}
			isRedirected = true;
			view = "board-bbsdetail.do?bbsid="+comm.getB_id();
			break;
		}
		
		if (!isRedirected) {
			RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
			rd.forward(request, response);
		} else { 
			response.sendRedirect(view);
		}
	}


	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
}
