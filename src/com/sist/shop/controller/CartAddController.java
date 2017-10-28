package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.CartDAO;
import com.sist.dao.ShopDAO;
import com.sist.vo.Cart;
import com.sist.vo.Shop;

public class CartAddController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �� ��ٱ��� �߰� ���� �������� 
		
		HttpSession session = request.getSession();		

		String goodsCode = request.getParameter("code");	// ���õǾ��� �ڵ尪 ��ü�� ����
		String mid = (String)session.getAttribute("mid");		// ���� ���� ���� ��������
		ShopDAO sdao = new ShopDAO();
		CartDAO cdao = new CartDAO();
		Shop s = sdao.getProduct(goodsCode);
		Cart c = new Cart();
		
		System.out.println("goodsCode1 :"+goodsCode);

		ArrayList<Cart> cList = cdao.listCart(mid);    // ��� ����Ʈ ����� ��ü ����
		// Ŭ������ �� �߿��� ������ ���õǾ��� ���� cart ��ü�� ������ �ֱ�
		int orderQnt = 1;
		
		Cart myCart = cdao.getCart(mid);   // �� īƮ ���� Ȯ�� 
		
		if(myCart==null){
			
			c.setMid(mid);
			c.setCode(s.getCode());
			c.setImage(s.getImage());
			c.setName(s.getName());
			c.setOrderQnt(orderQnt);
			c.setPrice(s.getPrice());
			c.setQnt(s.getQnt());
			c.setSize(s.getSize());		

			cdao.addProduct(c);
			
			return "redirect:cartList.do";		
			
		}else{
			Cart myGoods = cdao.getCart(mid, goodsCode);  // īƮ�� ��ǰ���ִ��� CHECK

			if(myGoods != null){
				
				orderQnt = myGoods.getOrderQnt()+1;
				
				int af=cdao.updateCart(orderQnt, mid, goodsCode); //�̹� �����Ѵٸ� �ֹ����� �߰�	
				
				if(af==1){
					System.out.println("��ǰ�� �����Ͽ� �ֹ������� �߰��Ͽ����ϴ�.");
				}
				
				return "redirect:cartList.do";		

				
			}else{
				
				c.setMid(mid);
				c.setCode(s.getCode());
				c.setImage(s.getImage());
				c.setName(s.getName());
				c.setOrderQnt(orderQnt);
				c.setPrice(s.getPrice());
				c.setQnt(s.getQnt());
				c.setSize(s.getSize());		

				cdao.addProduct(c);		
				
				return "redirect:cartList.do";		
							
			}
	
		}
		
	}
	
}
