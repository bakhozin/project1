package com.sist.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.CartDAO;

public class CartDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cseq = request.getParameter("cseq");
		System.out.println(cseq);
		
		CartDAO cdao = new CartDAO();
		
		int af = cdao.deleteCart(cseq);
		
		if(af==1){
			System.out.println("카트삭제성공");
			return "redirect:cartList.do";
		}else{
			System.out.println("카트삭제실패");
			return "redirect:cartList.do";
		}
		
	}
	
	
	
	
	

}
