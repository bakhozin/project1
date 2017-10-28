package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.MemberDAO;
import com.sist.dao.OrderDAO;
import com.sist.dao.ShopDAO;
import com.sist.vo.Cart;
import com.sist.vo.Members;
import com.sist.vo.Order;
import com.sist.vo.Shop;

public class OrderProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//주문 정보를 받아서 완료처리 까지 한다. 
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String cartOrder = request.getParameter("cartOrder");
		String shipping = request.getParameter("shipping");
		int total = Integer.parseInt(request.getParameter("total"));
		String locations = request.getParameter("locations");
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		String name = request.getParameter("name"); 
		String mid =(String) session.getAttribute("mid");
		
		

		String year = String.valueOf(Calendar.YEAR);
		String month = String.valueOf(Calendar.MONTH) ;
		String day = String.valueOf(Calendar.DATE);
		String oname =null;
		String random = null;
		String orderNumber;
		
		String[]phones = phone.split("-");
		for(int i=0; i<6; i++){
			random =random+String.valueOf((int)(Math.random()*9)+1);

		}

		if(cartOrder!=null){
			ArrayList<Cart> cList = (ArrayList<Cart>)session.getAttribute("cList");
			for(int i=0; i<cList.size(); i++){
				if(i==0){
					oname = cList.get(i).getName();
					code = cList.get(i).getCode();
				}
				oname = oname+","+cList.get(i).getName();	
				code = code+","+cList.get(i).getCode();
			}
			String[]ocodes = code.split(",");
			String ocode=null;
			for(int i=0; i< ocodes.length; i++){
				ocode = ocode+ocodes[i];
			}
			
			orderNumber = year +month+day+ocode+phones[2]+random;		
			
			session.removeAttribute("cList");  // 세션 제거 
		}else{
			ShopDAO sdao = new ShopDAO();
			Shop s = sdao.getProduct(code);
			oname = s.getName();   //단일 주문 상품시 
			
			orderNumber = year +month+day+code+phones[2]+random;		
		}
	

		OrderDAO odao = new OrderDAO();
		Order o = new Order();
		//주문정보 추가 
		o.setOrderNumber(orderNumber);
		o.setPhone(phone);
		o.setCode(code);
		o.setLocations(locations);
		o.setMid(mid);
		o.setName(name);
		o.setShipping(Integer.parseInt(shipping));
		o.setTotal(total);
		o.setGoods(oname);
		
		odao.addOrder(o);
		//마일리지값 업데이트
		int mg = (int)(total * 0.1);
		
		MemberDAO mdao = new MemberDAO();  // 트랜잭션해야되는ㄷ ㅔㅠㅠ 
		Members m = mdao.getMember(mid);
		m.setMg(m.getMg()+mg);		
		mdao.updateMG(m);
		
		
		
		//배송비 출력 
		String orderTotal;
		if(total < 30000){
			orderTotal = String.valueOf(total - 2500) + "배송비: 2500원";
			request.setAttribute("orderTotal", orderTotal);			
		}else{
			request.setAttribute("orderTotal", total);
		}
		
		
		request.setAttribute("id", mid);
		request.setAttribute("oname", oname);
		request.setAttribute("mg", mg);
		request.setAttribute("name", name);
		request.setAttribute("locations", locations);
		request.setAttribute("phone", phone);
		request.setAttribute("orderNumber", orderNumber);
		
		
		return "order_ok.jsp";
	}

}
