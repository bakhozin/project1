package com.sist.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import com.sist.controller.Controller;
import com.sist.dao.CartDAO;
import com.sist.dao.ShopDAO;
import com.sist.vo.Cart;
import com.sist.vo.Shop;

public class CartUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int orderQnt =Integer.parseInt(request.getParameter("orderQnt")); //������ ���� 
				
		String code = request.getParameter("scode"); //��ǰ �ڵ带 �޾Ƽ� �������� üũ�Ұ��̴�. 	

		System.out.println("code Ȯ��"+code);
		HttpSession session = request.getSession();

		String mid =(String)session.getAttribute("mid");
		
		CartDAO cdao = new CartDAO();
		
		Cart c = cdao.getCart(mid);
		
		//�߰� �ֹ� �� �� ������ŭ ��� �ִ��� üũ�ؾߵȴ�. 
		ShopDAO sdao = new ShopDAO();
		Shop s = sdao.getProduct(code);
		
		int qnt = s.getQnt(); //��� �޾Ҵ�.
		
		
		if(orderQnt > qnt){
			String error1="��� �����մϴ�.";
			
			System.out.println("cartUpdate: ������");
			return "redirect:cartList.do?error1="+error1;
			
		}else{ //��� ������ , cart��� �������ش�.
			
			c.setOrderQnt(orderQnt);
			
			int af =cdao.updateCart(orderQnt, mid, code); //mid�� ���� īƮ�߿� Ư�� ��ǰ�ڵ�(code)������ ��ǰ�� �ֹ�����(orderQnt)�� ���� 
			
			if(af==1){
				System.out.println("�����Ϸ�");
			}else{
				System.out.println("��������");
			}
			
			
			return "redirect:cartList.do";
		}
		
	

	}


}
