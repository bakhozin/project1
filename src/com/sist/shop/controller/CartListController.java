package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.CartDAO;
import com.sist.vo.Cart;

public class CartListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//��ٱ��� ����Ʈ 
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("mid");
		String error1 = request.getParameter("error1"); // cartUpdate���� �ֹ� ������ �����ϸ� �����ڵ带 �޴´�. 
		int total = 0;
		
		CartDAO cdao= new CartDAO();
		
		ArrayList<Cart> clist = cdao.listCart(mid);
		
		
		for(int i=0; i<clist.size(); i++){  
			
			if(clist.get(i).getQnt() != 0){
				total = total+clist.get(i).getPrice()*clist.get(i).getOrderQnt();
			}else{
				continue;
			}
		}
		
		request.setAttribute("cList", clist);	
		request.setAttribute("error1", error1);
		request.setAttribute("total", total);         //�ջ� ���� ���� 
		
		return "cartList.jsp";
	}
	
	

}
