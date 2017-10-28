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
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
<style type="text/css">
#list{
	background-color:white;

}
.features_items{
	background-color:white;
}
img{
	width:300px;
	height:300px;
	border: 1px solid white;

}


span.reducedfrom {
	text-decoration: line-through;
	margin-right: 3%;
	color: #555;
	font-size: 16px;
	font-weight: 500;
}
span.actual {
	color: tomato;
    font-size: 1em;
    margin-right: 5%;
    font-weight: bold;
  
}

}

</style>
<script type="text/javascript">
$(document).ready(function(){
	
	var uPrice = $(".actual").text();
	var uPrice = uPrice*1.3;

	$("#comeon").text(uPrice);
	
	
});


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
				 <a class="navbar-brand" href="#">DD SHOP</a>
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
								<li><a href="../joinus/memberEdit.do">회원정보수정</a></li>
								<li><a href="cartList.do">장바구니</a></li>
								<li><a href="orderList.do">나의 구매내역</a></li>
						</ul>						
					</li>
					</c:if>
					<li><a href="../notices/notice.do">게시판</a></li>
<!-- 					dropdown 하나의 링크를 클릭했을때 아래쪽으로 추가적으로 리스트가 나오는 -->
					<li class="dropdown">
						<a href="shop.do" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">SHOP<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="shop.do">전체 상품</a></li>
							<li><a href="searchGoods.do?top=1">상의</a></li>
							<li><a href="searchGoods.do?btm=1">하의</a></li>
							<li><a href="searchGoods.do?acc=1">악세사리</a></li>
						</ul>
					</li>
				</ul>
<!-- 				검색창 -->
				<form action="searchGoods.do" class="navbar-form navbar-left">
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
							<li><a href="../joinus/memberEdit.do">회원정보수정</a></li>
						</ul>	
							</c:if>														
					</li>
				</ul>
			</div>
		</div>
	</nav>
			<div class="container">
		<div class="features_items" id="#list">
			<!--features_items-->
			<div class="page-header">
				<h1>
					상품리스트 <small>Goods List</small>
				</h1>
			</div>

			<c:forEach var="s" items="${sList}">
				<div class="col-sm-4">
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a href="shopDetail.do?code=${s.code}"><img
									src="${s.image }" alt="" /></a>
								<h4>${s.name }</h4>
								<div class="price_single">
									<p>
										<span class="reducedfrom">${s.price*1.3 }</span><span
											class="actual item_price">${s.price }원</span><a
											href="shopDetail.do?code=${s.code}">상품보기</a>
									</p>
								</div>


								<!-- 로그인, 비로그인 구분해서 제어한다. -->
								<c:if test='${mid==null}'>
									<a href="#" class="btn btn-default add-to-cart"
										data-target="#mcart" data-toggle="modal"><i
										class="fa fa-shopping-cart"></i>Add to cart</a>
								</c:if>
								<c:if test='${mid!=null}'>
									<a href="cartAdd.do?code=${s.code}"
										class="btn btn-default add-to-cart"><i
										class="fa fa-shopping-cart"></i>Add to cart</a>
								</c:if>
							</div>
						</div>
						<hr>
						
					</div>
				</div>
			</c:forEach>

		</div>
		<!--features_items-->
					</div>
	<footer style="background-color: #000000; color: #ffffff">
		<div class="container"> <!-- 푸터태그 안에는 컨테이너가 들어간다. -->
			<br>
			<div class="row"> <!-- 다합쳤을때 총12가 되도록 숫자를 만든다. col-sm-2는 2만큼의 공간만 사용하도록 만들어져있다 -->
				<div class="col-sm-2" style="text-align:center;"><h5>Copyright &copy; 2017</h5><h5>박호진(Hojin park)</h5></div>
				<div class="col-sm-4"><h4>대표자 소개</h4><p>저는 쌍용 교육센터에서 IT교육을 받고있는 박호진입니다. 첫 웹 프로젝트 작품입니다.</p></div>
				<div class="col-sm-2"><h4 style="text-align: center;">SNS</h4>
						<a href="http://www.youtube.com" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;유투브</a>
					<div class="list-group">
						<a href="http://www.facebook.com" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;페이스북</a>
						<a href="http://www.naver.com" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;네이버</a>
					</div>
				</div>
				<div class="col-sm-2"><h4></h4><p></p></div>
				<div class="col-sm-2"><h4 style="text-align: center;"><span class="glyphicon glyphicon-ok"></span>&nbsp;by 박호진</h4></div>
				
			</div>
		</div>
	</footer>
	
	<div class="row">
		<div class="modal" id="mcart" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align:center;">
						로그인 후 장바구니에 담아주세요.<br>
						<a class="btn btn-defualt" href="../joinus/login.do">돌아가기</a>
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

</body>
</html>