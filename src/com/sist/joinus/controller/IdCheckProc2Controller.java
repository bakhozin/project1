package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;

public class IdCheckProc2Controller implements Controller{


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO mdao = new MemberDAO();
		
		String mid = request.getParameter("mid");
		System.out.println("idCheckProc2�� mid:"+mid);
		
		int count = mdao.searchMid(mid);  //�ߺ� üũ 
		
		request.setAttribute("count", count);
		
		
		
		
		if(count == 0){
			System.out.println("���̵� �ߺ����� : X ");
			return "join.jsp";
		}else{
			System.out.println("��� �Ұ����� ���̵��Դϴ�.");
			return "join.jsp";
		}
		
	}

}
