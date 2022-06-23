package com.lcomputerstudy.testmvc.controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		int usercount = 0;
		int page = 1;
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch (command) {
		case "/user-list.do":
			String reqPage = request.getParameter("page");
			if (reqPage != null) {
				page = Integer.parseInt(reqPage);
				page = (page-1)*3;
			}
			UserService userService = UserService.getInstance();
			ArrayList<User> list = userService.getUsers(page);
			usercount = userService.getUsersCount();
			view = "user/list";
			request.setAttribute("list", list);
			request.setAttribute("usercount", usercount);
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
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
		rd.forward(request, response);
	}
}
