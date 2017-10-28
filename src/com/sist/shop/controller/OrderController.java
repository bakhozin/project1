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
		
		String code = request.getParameter("code");    // �ٷ� �ֹ��Ҷ� ��ǰ �ڵ� 
		String total1 = request.getParameter("total");  // �� ��ǰ ���� , ǰ�������� �̰ɷ� ����ؾߵ�  
		String cartOrder = request.getParameter("cartOrder"); // cart������ �ֹ����� �ƴ��� Ȯ���� �༮ 
		
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
	
		
		if(cartOrder != null){ // cart���� ���۵� �ֹ� 
			
			if(total < 30000 ){ 
				shipping=2500;
			}else{
				shipping=0;
			}			
			
			int orderTotal = total+shipping; // ��ۺ� �ջ� 
			
			clist = cdao.listCart(mid);
			session.setAttribute("cList", clist);  //īƮ���� �� �ֹ��ϰ��, �ֹ����� ����� �÷����� ���ǿ� ��Ƽ� ���
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
			request.setAttribute( "code", code);	//���� �ֹ��� ��� �ڵ常���� �ֹ����� 							

		}
						
		
		request.setAttribute("shipping", shipping);
		request.setAttribute("mg", mg);
		request.setAttribute("mname", mname);
		request.setAttribute("mphone", mphone);
		
		
		return "order.jsp";
	}

}
