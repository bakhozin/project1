<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/delete.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#delBtn').click(function(){
		var id=$('#userid').val();
		var pwd=$('#userpwd').val();
		/* alert(pwd); */
		
		//DB 비밀번호랑 들어온 비밀번호랑 비교해서 제어문 만들어라 
		
		$('#delForm').submit(); 
	});
});
</script>
</head>
<body>
<center>
<div id="form">
	<table id="top" width=1100>
		<tr>
			<td id="subject">회원탈퇴</td>
		</tr>
	</table>
	<br/>
	<span id="sub">회원 탈퇴를 할 수 있습니다. 다음 안내사항을 꼭 읽어주신 후 탈퇴하시기 바랍니다.</span><br/><br/>
	<table id="table" width=1100 height=300>
		<tr>
			<td><span>01.</span> 회원탈퇴 시, 고객님의 개인정보는 모두 삭제처리 됩니다.</td>
		</tr>
		<tr>
			<td><span>01.</span> 회원탈퇴는 즉시 처리됩니다.</td>
		</tr>
		<tr>
			<td><span>01.</span> 탈퇴 후 재가입이 가능합니다.</td>
		</tr>
		<tr>
			<td><span>01.</span> 비밀번호를 한번 더 입력 후 확인 버튼을 누르면 탈퇴가 완료됩니다.</td>
		</tr>
		
	</table>
	 <form id="delForm" action="deleteMemberProc.do" name="delForm" method="post"> 
	    <input type=hidden name=mid id="userid" value="${sessionScope.mid }">
	    비밀번호
	    <input type=password name=pwd id="userpwd">
	    
	    <input type=button id="delBtn" value="확인" style="text-decoration: none">
    </form> 
</div>    
</center>
</body>
</html>