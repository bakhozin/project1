<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="bCheck" value="${bCheck }"/>
<c:if test="${bCheck==1 }">
	<script>
		alert("탈퇴가 완료되었습니다.");
		<%
			session.invalidate();
		%>
		location.href="../menu/main.do";
	</script>
</c:if>
<c:if test="${bCheck!=1 }">
	<script>
		alert("비밀번호가 틀립니다.");
		history.back();
	</script>
</c:if>


