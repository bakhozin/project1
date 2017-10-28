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

public class NoticeDetailController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String seq =null;
		String pg = null;
		seq = request.getParameter("seq");
		pg = request.getParameter("pg");
		String field = request.getParameter("f");
		String query = request.getParameter("q");
		String urlQuery = URLEncoder.encode(query, "UTF-8");
		
		String hitOn = request.getParameter("hitOn");
		

		if(field==null || field.equals("")) {
			field = "TITLE";
		}
		if(query==null) {
			query = "";
		}else {
			urlQuery = URLEncoder.encode(query, "UTF-8");
		}

		
		if(hitOn != null && !hitOn.equals("")){  //수정조회수 제어
			NoticeDAO ndao2 = new NoticeDAO();
			ndao2.getHit(seq);
		}
		
		//session 얻어오기
		HttpSession session = request.getSession();
		if(session.getAttribute("mid")==null) {
			session.setAttribute("returnURL", "/customer/noticeDetail.do?seq="+seq+"&pg="+pg+"&f="+field+"&q="+query+"&urlQuery="+urlQuery);
			
			return "redirect:../joinus/login.do";
		}else {
			NoticeDAO ndao = new NoticeDAO();
			Notice n = ndao.getNotices(seq);
			
			request.setAttribute("n", n);
			request.setAttribute("pg", pg);
			//request.setAttribute("seq", seq);
			request.setAttribute("field", field);
			request.setAttribute("query", query);
			request.setAttribute("urlQuery", urlQuery);
			if(n.getFileSrc() != null){
				String urlFileName = URLEncoder.encode(n.getFileSrc(), "UTF-8");
				request.setAttribute("urlFileName", urlFileName);	
			}
			
			
			
			return "noticeDetail.jsp";
		}
	}
}
