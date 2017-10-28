package com.sist.notices.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.NoticeDAO;
import com.sist.vo.Notice;

public class NoticeEditController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		String pg = request.getParameter("pg");
		String field = request.getParameter("f");
		String query = request.getParameter("q");
		String urlQuery = URLEncoder.encode(query, "UTF-8");
		NoticeDAO ndao = new NoticeDAO();
		Notice n = ndao.getNotices(seq);
		
		request.setAttribute("seq", seq);
		request.setAttribute("pg", pg);
		request.setAttribute("field", field);
		request.setAttribute("query", query);
		request.setAttribute("urlQuery", urlQuery);
		request.setAttribute("n", n);

		
		return "noticeEdit.jsp";
	}
	
/*	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		String pg = request.getParameter("pg");
		String field = request.getParameter("f");
		String query = request.getParameter("q");
		String urlQuery = URLEncoder.encode(query, "UTF-8");
		NoticeDAO ndao = new NoticeDAO();
		Notice n = ndao.getNotices(seq);
		
		request.setAttribute("seq", seq);
		request.setAttribute("pg", pg);
		request.setAttribute("field", field);
		request.setAttribute("query", query);
		request.setAttribute("urlQuery", urlQuery);
		request.setAttribute("n", n);
		
		request.getRequestDispatcher("noticeEdit.jsp").forward(request, response);
	}*/

}
