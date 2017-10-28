
package com.sist.member.model;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.MemberDAO;



public class MemberModel {

	  public void IdSearch(HttpServletRequest request) throws UnsupportedEncodingException
	  {
			request.setCharacterEncoding("utf-8");
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");


			MemberDAO mdao = new MemberDAO();
			String mid = mdao.searchMid(name, email);
			
			request.setAttribute("data", mid);
					

			System.out.println("아이디 찾기에 성공했습니다.");
			request.setAttribute("mid", mid);
			
	  }
	  
	  public void PwdSearch(HttpServletRequest request) throws UnsupportedEncodingException
	  {	  
	  request.setCharacterEncoding("utf-8");
		
		String mid = request.getParameter("id");
		String answer = request.getParameter("answer");
		String question = request.getParameter("question");


		MemberDAO mdao = new MemberDAO();
		String pwd = mdao.searchPwd(mid, answer, question);
		
		request.setAttribute("data", pwd);
		
	  }
	  
	  public void IdCheck(HttpServletRequest request)
	  {
		  String mid=request.getParameter("id");
		  MemberDAO dao=new MemberDAO();
		  int count=dao.searchMid(mid);
		  request.setAttribute("count", count);
		  
		  
	  }
  }