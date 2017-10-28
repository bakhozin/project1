package com.sist.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.ShopDAO;
import com.sist.vo.Shop;

public class SearchKindController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String top = request.getParameter("top");
		String btm = request.getParameter("btm");
		String acc = request.getParameter("acc");
		
		ShopDAO sdao = new ShopDAO();
		ArrayList<Shop> sList = null;
		
		if(top != null){
			sList = sdao.listKIND(top);
		
		}else if(btm != null){
			sList = sdao.listKIND(btm);

		}else if(acc != null){
			sList = sdao.listKIND(acc);

		}
		
	    request.setAttribute("sList", sList);
		
	
		return "searchGoods.jsp";
	}

}
