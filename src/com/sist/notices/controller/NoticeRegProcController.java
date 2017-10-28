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
		//���� ���� ��ġ�� ���䵥 ���� ������ġ�� ����? ���� �Լ��� �ִ� 
		String realPath = request.getServletContext().getRealPath(path);
		System.out.println("realPath :"+realPath);
		
		MultipartRequest mulReq = new MultipartRequest(request,realPath,10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		String fileSrc = mulReq.getFilesystemName("file"); // ���� 2.jpg�� �÷����� ���� ������ �����? 
		String orifileSrc = mulReq.getOriginalFileName("file"); // 
		System.out.println("fileSrc =" + fileSrc);
		System.out.println("orifileSrc =" + orifileSrc);
		
		String mid = (String)request.getSession().getAttribute("mid");
		String title = mulReq.getParameter("title");
		String content = mulReq.getParameter("content");
		
		
		//�⺻�� ����Ʈ����, 4��°�� ���ڵ�Ÿ�� 5��°�� ���� �̸��� �ö������ �ڵ�����?
		Notice n = new Notice();
		
		n.setWriter(mid);
		n.setTitle(title);
		n.setContent(content);
		n.setFileSrc(fileSrc);
		
	
		NoticeDAO ndao = new NoticeDAO();
		int af = ndao.addNotices(n, mid);
		if(af==1) {
			System.out.println("�Խñ��߰� ����");
			return "redirect:notice.do";
		}else {
			System.out.println("�Խñ��߰� ����");
			return "redirect:notice.do";
		}

	}
	
}
