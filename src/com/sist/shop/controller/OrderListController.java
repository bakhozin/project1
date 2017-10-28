package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.dao.OrderDAO;
import com.sist.vo.Order;

public class OrderListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("mid");

		OrderDAO odao = new OrderDAO();		
		
		ArrayList<Order> oList = odao.listOrder(mid);
		
		
		
		request.setAttribute("oList", oList);
		
		
		return "orderList.jsp";
	}

}
