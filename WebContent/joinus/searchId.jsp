<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<jsp:useBean id="model" class="com.sist.member.model.MemberModel"/>
<%
	model.IdSearch(request);
%>
${data }