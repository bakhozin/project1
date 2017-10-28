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
		
		//DAO���� SHOP �����͸� �����ͼ� shop.jsp�� �����ؾߵȴ�. 
		ShopDAO sdao= new ShopDAO();
		ArrayList<Shop> sList = sdao.listShop();

		request.setAttribute("sList", sList);  //����Ʈ ����
		
		
		return "shop.jsp";
	}

}
