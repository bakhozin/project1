package com.sist.notices.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.dao.PreviewDAO;
import com.sist.vo.Preview;

public class PreviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PreviewDAO pdao = new PreviewDAO();
		ArrayList<Preview> pList = pdao.listPreview();
		
		
		request.setAttribute("pList", pList);
		
		
		return "preview.jsp";
	}
	

}
