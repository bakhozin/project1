package com.sist.joinus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.util.CookieManager;

public class LoginController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] coos = request.getCookies(); //荑좏궎�뒗 �뿬�윭媛쒖씪�닔�룄�엳�떎. request�뒗 荑좏궎媛앹껜瑜� 諛곗뿴濡쒕컺�뒗�떎.
		String cookieMid = CookieManager.getCookie(coos, "mid");
		String cookiePwd = CookieManager.getCookie(coos, "pwd");

	
			System.out.println("cookieMid :" + cookieMid);
			if(cookieMid!=null && !cookieMid.equals("")){
				//MID 荑좏궎 �깮�꽦 
				request.setAttribute("cookieMid", cookieMid);

			}

			System.out.println("cookiePwd :" + cookiePwd);
			if(cookiePwd!=null && !cookiePwd.equals("")){
				//PWD 荑좏궎 �깮�꽦 
				request.setAttribute("cookiePwd", cookiePwd);

			}

			String error = request.getParameter("error");
			if(error !=null && !error.equals("")) {
				if(error.equals("IDx")){
					error="아이디가 틀립니다.";
				}else if(error.equals("PWDx")) {
					error="비밀번호가 틀립니다.";
				}
			}
			request.setAttribute("error", error);

			return "login.jsp";

	}

}
