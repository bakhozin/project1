package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;
import com.sist.vo.Members;

public class MemberEditProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
    request.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = request.getSession();
	    
		String mid = (String) session.getAttribute("mid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");			
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String phone = request.getParameter("phone");
		String locations = request.getParameter("locations");
		String email = request.getParameter("email");
		String birthday = year+"-"+month+"-"+day;
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		

		MemberDAO mdao = new MemberDAO();
		Members m = mdao.getMember(mid);
		
		m.setPwd(pwd);
		m.setName(name);
		m.setBirthday(birthday);
		m.setPhone(phone);
		m.setLocations(locations);
	    m.setEmail(email);
	    m.setQuestion(question);
	    m.setAnswer(answer);
		
		
		
		request.setAttribute("m", m);
		
		int af = mdao.updateMembers(m);
		if(af==1){
			System.out.println("회원정보수정완료");
			return "memberEditProc.jsp";
			
		}else{
			System.out.println("회원정보수정실패");
			return "redirect:login.do";
		}

	}

}
