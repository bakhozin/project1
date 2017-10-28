package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;
import com.sist.vo.Members;

public class DeleteMemberProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDAO mdao = new MemberDAO();
		   HttpSession session = request.getSession();		   
		   String mid = (String)session.getAttribute("mid");
		   Members m = mdao.getMember(mid);
		   
		   int af = mdao.deleteMember(m);
		   
		   request.setAttribute("bCheck", af);
		   
		   if(af==1){
			   System.out.print("회원탈퇴가 완료되었습니다.");
			
		   }else{
			   System.out.println("회원탈퇴에 실패했습니다.");		
		   }	
		   
		   return "deleteMember_ok.jsp";
			
	}

}
