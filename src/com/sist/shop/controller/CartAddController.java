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
		// 딱 장바구니 추가 단추 눌렀을때 
		
		HttpSession session = request.getSession();		

		String goodsCode = request.getParameter("code");	// 선택되어진 코드값 객체에 저장
		String mid = (String)session.getAttribute("mid");		// 세션 유저 정보 가져오기
		ShopDAO sdao = new ShopDAO();
		CartDAO cdao = new CartDAO();
		Shop s = sdao.getProduct(goodsCode);
		Cart c = new Cart();
		
		System.out.println("goodsCode1 :"+goodsCode);

		ArrayList<Cart> cList = cdao.listCart(mid);    // 어레이 리스트 사용할 객체 생성
		// 클래스의 값 중에서 이전에 선택되어진 값만 cart 객체에 가지고 있기
		int orderQnt = 1;
		
		Cart myCart = cdao.getCart(mid);   // 내 카트 유무 확인 
		
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
			Cart myGoods = cdao.getCart(mid, goodsCode);  // 카트에 상품이있는지 CHECK

			if(myGoods != null){
				
				orderQnt = myGoods.getOrderQnt()+1;
				
				int af=cdao.updateCart(orderQnt, mid, goodsCode); //이미 존재한다면 주문수량 추가	
				
				if(af==1){
					System.out.println("상품이 존재하여 주문수량을 추가하였습니다.");
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
