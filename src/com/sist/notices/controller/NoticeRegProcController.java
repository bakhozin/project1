package com.sist.notices.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.controller.Controller;
import com.sist.dao.NoticeDAO;
import com.sist.vo.Notice;

public class NoticeRegProcController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String mid = request.getParameter("mid");
		String path = "/notices/upload";
		//현재 나의 위치는 여긴데 나의 물리위치는 어디니? 묻는 함수가 있다 
		String realPath = request.getServletContext().getRealPath(path);
		System.out.println("realPath :"+realPath);
		
		MultipartRequest mulReq = new MultipartRequest(request,realPath,10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		String fileSrc = mulReq.getFilesystemName("file"); // 나는 2.jpg에 올렸지만 실제 파일이 저장된? 
		String orifileSrc = mulReq.getOriginalFileName("file"); // 
		System.out.println("fileSrc =" + fileSrc);
		System.out.println("orifileSrc =" + orifileSrc);
		
		String mid = (String)request.getSession().getAttribute("mid");
		String title = mulReq.getParameter("title");
		String content = mulReq.getParameter("content");
		
		
		//기본은 바이트단위, 4번째는 인코딩타입 5번째는 같은 이름이 올라왔을때 자동수정?
		Notice n = new Notice();
		
		n.setWriter(mid);
		n.setTitle(title);
		n.setContent(content);
		n.setFileSrc(fileSrc);
		
	
		NoticeDAO ndao = new NoticeDAO();
		int af = ndao.addNotices(n, mid);
		if(af==1) {
			System.out.println("게시글추가 성공");
			return "redirect:notice.do";
		}else {
			System.out.println("게시글추가 실패");
			return "redirect:notice.do";
		}

	}
	
}
