package com.sist.notices.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.NoticeDAO;
import com.sist.vo.Notice;

public class NoticeRegController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pg = request.getParameter("pg");
		String field = request.getParameter("f");
		String query = request.getParameter("q");
		String urlQuery = request.getParameter("urlQuery");
		if(query==null){
			query="";
		}else{
			urlQuery = URLEncoder.encode(query, "UTF-8");
			System.out.println("Reg_urlQuery =" + urlQuery);
			
		}
		
	
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("mid"));
		if(session.getAttribute("mid")==null) {
			session.setAttribute("returnURL", "noticeReg.do?pg="+pg+"&f="+field+"&q="+query);
			return "redirect:/joinus/login.do";
		}else {
			String mid = (String)session.getAttribute("mid");
			System.out.println("mid =" +mid);
			
			request.setAttribute("pg", pg);
			return "noticeReg.jsp";
		}						
	}
}
