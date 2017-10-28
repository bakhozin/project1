<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>	
<style type="text/css">
.jumbotron{
	background-image:url('http://www.thehook.co.kr/data/shopimages/mainbanner/1118888d837cba2f44709a5a6a362edb0.jpg');
	background-size:cover;
	color:white;	
}

</style>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">

<script type="text/javascript">



</script>
</head>
<body>  
<!--   부트스트랩에서 제공하는 navbar라는 클래스의 구성요소를 이용해서 만들었다. -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">﻿
				 <span class="sr-only"></span>
				 <span class="icon-bar"></span>
				 <span class="icon-bar"></span>
				 <span class="icon-bar"></span>
				 </button>
				 <a class="navbar-brand" href="main.do">DD SHOP</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collpase-1">
<!-- 			상단 BAR안에 개별적인 링크가 들어간다 -->
				<ul class="nav navbar-nav">
					<c:if test="${mid ==null }">
						<li class=""><a href="#" data-target="#modal" data-toggle="modal">마이페이지<span class="sr-only"></span></a></li>
					</c:if>
					<c:if test="${mid !=null }">
						<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">마이 페이지<span class="caret"></span></a>
						<ul class="dropdown-menu">
								<li><a href="memberEdit.do">회원정보수정</a></li>
								<li><a href="../shop/cartList.do">장바구니</a></li>
								<li><a href="../shop/orderList.do">나의 구매내역</a></li>
						</ul>						
					</li>
					</c:if>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">게시판<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../notices/preview.do">PREVIEW</a></li>
							<li><a href="../notices/notice.do">자유게시판</a></li>
							<li><a href="#">고객센터</a></li>
						</ul>
					</li>
<!-- 					dropdown 하나의 링크를 클릭했을때 아래쪽으로 추가적으로 리스트가 나오는 -->
					<li class="dropdown">
						<a href="../shop/shop.do" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">SHOP<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../shop/shop.do">전체 상품</a></li>
							<li><a href="../shop/searchGoods.do?top=1">상의</a></li>
							<li><a href="../shop/searchGoods.do?btm=1">하의</a></li>
							<li><a href="../shop/searchGoods.do?acc=1">악세사리</a></li>
						</ul>
					</li>
				</ul>
<!-- 				검색창 -->
				<form action="../shop/searchGoods.do" class="navbar-form navbar-left">
					<div class="form-group">>
						<input type="text" name="goods" class="form-control" placeholder="상품명을 입력하세요.">
					</div>
	  			
					<button type="submit" class="btn btn-default">검색</button>
				</form>
<!-- 				로그인/회원가입 처리용 dropdown var  -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
							<c:if test="${mid==null}">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">LOGIN<span class="caret"></span></a>
							
							<ul class="dropdown-menu">
								<li><a href="../joinus/login.do">로그인</a></li>
								<li><a href="../joinus/join.do">회원가입</a></li>
							</ul>
							</c:if>
							<c:if test="${mid!=null}">
							<a href="logout.do" class="dropdown-toggle" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false">로그아웃<span class="caret"></span></a>
								<ul class="dropdown-menu">
							<li><a href="#" data-target="#logout" data-toggle="modal">로그아웃</a></li>
							<li><a href="memberEdit.do">회원정보수정</a></li>
						</ul>	
							</c:if>														
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1 class="text-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
			<p class="text-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			<p class="text-center"><a class="btn btn-primary btn-lg" href="../shop/shop.do" role="button" style="background-color: black">쇼핑하러가기</a></p>			
		</div>	
		<div class="row">
			<div class="col-md-4">
				<h4>DD SHOP 의 특징</h4>
				<p>DD SHOP에서 많이좀 사세요. 팔건 없습니다. </p>
				<p><a class="btn btn-default" data-target="#modal" data-toggle="modal">자세히 알아보기</a></p>
			</div>
			<div class="col-md-4">
				<h4>폭넓은 상품목록</h4>
				<p>DD SHOP에서 많이좀 사세요. 팔건 없습니다. </p>
				<p><a class="btn btn-default" href="#">자세히 알아보기</a></p>
			</div>
			<div class="col-md-4">
				<h4>후려치는 가격</h4>
				<p>DD SHOP에서 많이좀 사세요. 팔건 없습니다. </p>
				<p><a class="btn btn-default" href="#">자세히 알아보기</a></p>
			</div>
		</div>
		<hr>
		<div class="panel panel-primary">
			<div class="panel-heading" style="background-color: black">
				<h3 class="panel-title">
				&nbsp;&nbsp;Preview</h3>
			</div>
			<c:forEach var="i" begin="0" end="2" >
			
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<a href="https://www.musinsa.com/?m=news"><img class="media-object" src="${pList.get(i).image}" alt="차범근"></a>
					</div>
					<div class="media-body">
						<h4 class="media-heading"><a href="https://www.musinsa.com/?m=news">${pList.get(i).title}</a>&nbsp;<span class="badge">New</span></h4>
						<a href="https://www.musinsa.com/?m=news">${pList.get(i).content}</a>
						<div style="float: right"><br><br><br><br>${pList.get(i).regdate }</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
	<footer style="background-color: #000000; color: #ffffff">
		<div class="container"> <!-- 푸터태그 안에는 컨테이너가 들어간다. -->
			<br>
			<div class="row"> <!-- 다합쳤을때 총12가 되도록 숫자를 만든다. col-sm-2는 2만큼의 공간만 사용하도록 만들어져있다 -->
				<div class="col-sm-2" style="text-align:center;"><h5>Copyright &copy; 2017</h5><h5>박호진(Hojin park)</h5></div>
				<div class="col-sm-4"><h4>대표자 소개</h4><p>저는 쌍용 교육센터에서 IT교육을 받고있는 박호진입니다. 첫 웹 프로젝트 작품입니다.</p></div>
				<div class="col-sm-2"><h4 style="text-align: center;">SNS</h4>
						<a href="http://www.youtube.com" class="list-group-item"><span><img src="../images/youtube.png" width="30px" height="25px"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;유투브</a>
					<div class="list-group">
						<a href="http://www.facebook.com" class="list-group-item"><span><img src="../images/facebook.png" width="30px" height="25px"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;페이스북</a>
						<a href="http://www.naver.com" class="list-group-item"><span><img src="../images/naver.png" width="30px" height="25px"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;네이버</a>
					</div>
				</div>
				<div class="col-sm-2"><h4></h4><p></p></div>
				<div class="col-sm-2"><h4 style="text-align: center;"><span class="glyphicon glyphicon-ok"></span>&nbsp;by 박호진</h4></div>
				
			</div>
		</div>
	</footer>
<!-- 	모달창 구현	 -->

	<div class="row">
		<div class="modal" id="modal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align:center;">
						로그인이 필요합니다.<br>
						<a class="btn btn-defualt" href="../joinus/login.do">로그인하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="modal" id="logout" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align:center;">
						정말 로그아웃 하시겠습니까??<br>
						<a class="btn btn-default" href="../joinus/logout.do">OK</a>
						<button class="btn btn-default" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>