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
		//���̵����忡�� üũ�ڽ����̵� �����´� 		
		
		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
		
		//���� ���ؽ�Ʈ ��ü �ּҰ� �޶�°�
		
		
		Members m = dao.getMember(mid);
		if(m==null){
			System.out.println("���̵� �����ϴ�.");
			return "redirect:login.do?error=IDx";
		}else if(!pwd.equals(m.getPwd())) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			return "redirect:login.do?error=PWDx";
		}else {
			System.out.println("�α��� ����");
			
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid); //setAttrubute("key", value);
		
			if(checkBoxMid!=null && !checkBoxMid.equals("")){
				Cookie ck = new Cookie("mid",mid);
				ck.setMaxAge(60*60*24*30);   
				System.out.println("MID ��Ű ����");
				response.addCookie(ck);			    
			
				Cookie ck2 = new Cookie("pwd", pwd);
				ck2.setMaxAge(60*60*24*30);  
				System.out.println("PWD ��Ű ����");
				response.addCookie(ck2);			  
			
			
			}else{
				Cookie ck = new Cookie("mid", null);
				ck.setMaxAge(0);  
				System.out.println("MID��Ű ����");
				response.addCookie(ck);
			
				Cookie ck2 = new Cookie("pwd", null);
				ck2.setMaxAge(0); 
				System.out.println("PWD��Ű ����");
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
