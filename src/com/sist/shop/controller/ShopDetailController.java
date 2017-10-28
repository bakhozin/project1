package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.ShopDAO;
import com.sist.vo.Shop;

public class ShopDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String goodsCode = request.getParameter("code");  //s_name 상품이름 
		
		ShopDAO sdao= new ShopDAO();
		Shop sDetail = sdao.getProduct(goodsCode);

		request.setAttribute("sDetail", sDetail);  //상품객체 전송

		return "shopDetail.jsp";
		
	}

}
