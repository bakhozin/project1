package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.ShopDAO;
import com.sist.vo.Shop;

public class ShopController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//DAO에서 SHOP 데이터를 가져와서 shop.jsp로 진열해야된다. 
		ShopDAO sdao= new ShopDAO();
		ArrayList<Shop> sList = sdao.listShop();

		request.setAttribute("sList", sList);  //리스트 전송
		
		
		return "shop.jsp";
	}

}
