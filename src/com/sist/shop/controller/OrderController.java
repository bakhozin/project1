package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.CartDAO;
import com.sist.dao.MemberDAO;
import com.sist.dao.ShopDAO;
import com.sist.vo.Cart;
import com.sist.vo.Members;
import com.sist.vo.Shop;

public class OrderController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String code = request.getParameter("code");    // 바로 주문할때 상품 코드 
		String total1 = request.getParameter("total");  // 총 상품 가액 , 품절때문에 이걸로 계산해야됨  
		String cartOrder = request.getParameter("cartOrder"); // cart에서온 주문인지 아닌지 확인할 녀석 
		
		int total = Integer.parseInt(total1);	
		
		HttpSession session = request.getSession(); 
		String mid = (String)session.getAttribute("mid");
		MemberDAO mdao = new MemberDAO();
		Members m = mdao.getMember(mid);
		
		String mname = m.getName();
		String mphone = m.getPhone();
			
		int mg = m.getMg();
		
		
		ShopDAO sdao = new ShopDAO();
		Shop s = null;
		ArrayList<Cart> clist = null;
		CartDAO cdao = new CartDAO();
		
		int orderQnt = 0;
		int shipping = 0;
	
		
		if(cartOrder != null){ // cart에서 조작된 주문 
			
			if(total < 30000 ){ 
				shipping=2500;
			}else{
				shipping=0;
			}			
			
			int orderTotal = total+shipping; // 배송비 합산 
			
			clist = cdao.listCart(mid);
			session.setAttribute("cList", clist);  //카트에서 온 주문일경우, 주문에서 사용할 컬렉션을 세션에 담아서 사용
			request.setAttribute("orderTotal",  orderTotal);
			request.setAttribute("total", total);
			request.setAttribute("cartOrder", cartOrder);

		  
		}else{
			s = sdao.getProduct(code);
			
			if(s.getPrice()*orderQnt < 30000){
				shipping=2500;
			}else{
				shipping=0;
			}
			
			int orderTotal = s.getPrice()*orderQnt+shipping;
			total = s.getPrice()*orderQnt;			
			
			String sname = s.getName();
			orderQnt =Integer.parseInt(request.getParameter("orderQnt"));
			request.setAttribute("sname", sname);
			request.setAttribute("orderTotal", orderTotal);
			request.setAttribute("total", total);
			request.setAttribute( "code", code);	//단일 주문일 경우 코드만으로 주문제어 							

		}
						
		
		request.setAttribute("shipping", shipping);
		request.setAttribute("mg", mg);
		request.setAttribute("mname", mname);
		request.setAttribute("mphone", mphone);
		
		
		return "order.jsp";
	}

}
