package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;
import com.sist.vo.Members;

public class LoginProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        MemberDAO dao = new MemberDAO();
		
		String checkBoxMid = request.getParameter("checkBoxMid");
		//아이디저장에서 체크박스아이디 가져온다 		
		
		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
		
		//서블릿 콘텍스트 객체 주소값 달라는것
		
		
		Members m = dao.getMember(mid);
		if(m==null){
			System.out.println("아이디가 없습니다.");
			return "redirect:login.do?error=IDx";
		}else if(!pwd.equals(m.getPwd())) {
			System.out.println("비밀번호가 틀렸습니다.");
			return "redirect:login.do?error=PWDx";
		}else {
			System.out.println("로그인 성공");
			
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid); //setAttrubute("key", value);
		
			if(checkBoxMid!=null && !checkBoxMid.equals("")){
				Cookie ck = new Cookie("mid",mid);
				ck.setMaxAge(60*60*24*30);   
				System.out.println("MID 쿠키 생성");
				response.addCookie(ck);			    
			
				Cookie ck2 = new Cookie("pwd", pwd);
				ck2.setMaxAge(60*60*24*30);  
				System.out.println("PWD 쿠키 생성");
				response.addCookie(ck2);			  
			
			
			}else{
				Cookie ck = new Cookie("mid", null);
				ck.setMaxAge(0);  
				System.out.println("MID쿠키 삭제");
				response.addCookie(ck);
			
				Cookie ck2 = new Cookie("pwd", null);
				ck2.setMaxAge(0); 
				System.out.println("PWD쿠키 삭제");
				response.addCookie(ck2);	
			}
			
			String returnURL = (String)request.getSession().getAttribute("returnURL");
			
			if(returnURL != null && !returnURL.equals("")){
				String contextRootName= request.getContextPath();
				System.out.println("contextRootName :" + contextRootName);
				return "redirect:"+contextRootName+returnURL;
			}else{
				
				return "redirect:loginMain.do";
			}
		}				
		
	}

}
