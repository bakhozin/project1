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

		int orderQnt =Integer.parseInt(request.getParameter("orderQnt")); //수정할 수량 
				
		String code = request.getParameter("scode"); //상품 코드를 받아서 재고수량을 체크할것이다. 	

		System.out.println("code 확인"+code);
		HttpSession session = request.getSession();

		String mid =(String)session.getAttribute("mid");
		
		CartDAO cdao = new CartDAO();
		
		Cart c = cdao.getCart(mid);
		
		//추가 주문 할 수 있을만큼 재고가 있는지 체크해야된다. 
		ShopDAO sdao = new ShopDAO();
		Shop s = sdao.getProduct(code);
		
		int qnt = s.getQnt(); //재고를 받았다.
		
		
		if(orderQnt > qnt){
			String error1="재고가 부족합니다.";
			
			System.out.println("cartUpdate: 재고부족");
			return "redirect:cartList.do?error1="+error1;
			
		}else{ //재고가 있을때 , cart재고를 수정해준다.
			
			c.setOrderQnt(orderQnt);
			
			int af =cdao.updateCart(orderQnt, mid, code); //mid가 가진 카트중에 특정 상품코드(code)를가진 상품의 주문수량(orderQnt)을 수정 
			
			if(af==1){
				System.out.println("수정완료");
			}else{
				System.out.println("수정실패");
			}
			
			
			return "redirect:cartList.do";
		}
		
	

	}


}
