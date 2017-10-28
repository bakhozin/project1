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

public class NoticeDelProcController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String seq = request.getParameter("seq");
		String pg = request.getParameter("pg");
		String field = request.getParameter("f");
		String query = request.getParameter("q");
		String urlQuery = URLEncoder.encode(query, "UTF-8");
		NoticeDAO ndao = new NoticeDAO();
		Notice n = ndao.getNotices(seq);
		int af = ndao.deleteNotice(n);
		if(af==1) {
			System.out.println("삭제성공");
			return "redirect:notice.do?seq="+seq+"&pg="+pg+"&f="+field+"&q="+urlQuery;
		}else {
			System.out.println("삭제실패");
			return null;
		}
	}

}
