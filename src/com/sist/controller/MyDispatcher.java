package com.sist.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDispatcher extends HttpServlet {
	
	private Map<String, Controller> conMap = new HashMap<String, Controller>();	
	
	@Override
	public void init(ServletConfig config) throws ServletException { // txt 읽어오기

         String path = config.getInitParameter("txtPath");
         System.out.println("myWeb path:"+path);
         
            try {
				Reader rd = new FileReader(path);
				BufferedReader br = new BufferedReader(rd);
				String readMap;
				while((readMap=br.readLine())!=null){
					System.out.println("myWeb readMap:"+ readMap);
					
					String[]readMaps= readMap.split(" ");
					String uri = readMaps[0];
					Controller conObject = (Controller)Class.forName(readMaps[1]).newInstance();
					conMap.put(uri, conObject);					
				}			
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
	}
	
	private void dowork(HttpServletRequest request, HttpServletResponse response) { //작업공간
		String requestURI = request.getRequestURI();
		System.out.println("requestURI="+requestURI);
		
		Controller controller = conMap.get(requestURI);			
		
		String toMove;
		
		try {
			toMove = controller.execute(request, response);
			String[] toMoves=toMove.split(":");
			if(toMoves[0].equals("redirect")){
				response.sendRedirect(toMoves[1]);
			}else{
				request.getRequestDispatcher(toMove).forward(request, response);
				
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}		
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dowork(request,response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dowork(request,response);
	}
	
}
