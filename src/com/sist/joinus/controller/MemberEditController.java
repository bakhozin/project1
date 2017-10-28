package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;
import com.sist.vo.Members;

public class MemberEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
	    String mid = (String)session.getAttribute("mid");
		
		MemberDAO mdao = new MemberDAO();
		
		Members m = mdao.getMember(mid);
		
		request.setAttribute("m", m);
	    
		
		
		
		return "memberEdit.jsp";
	}
	
	

}
