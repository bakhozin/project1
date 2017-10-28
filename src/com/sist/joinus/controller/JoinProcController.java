package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;
import com.sist.vo.Members;

public class JoinProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		
		String mid = request.getParameter("mid");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String answer = request.getParameter("answer");
		String question = request.getParameter("question");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		
		Members m = new Members();
		m.setMid(mid);
		m.setName(name);
		m.setPwd(pwd);
		m.setBirthday(birthday);
		m.setPhone(phone);
		m.setQuestion(question);
		m.setAnswer(answer);
		m.setEmail(email);
		m.setGender(gender);
		
		MemberDAO mdao = new MemberDAO();
		int af =mdao.addMember(m);
		
		if(af==1){
			System.out.println("회원가입에 성공");
			return "redirect:login.do";
			
		}else{
			System.out.println("회원가입에 실패");
			return "redirect:login.do";
		}

		
		
	}
	
	

}
