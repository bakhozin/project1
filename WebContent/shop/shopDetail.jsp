<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<style type="text/css">

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


.table>tbody>tr>td, .table>tfoot>tr>td{
    vertical-align: middle;
}
@media screen and (max-width: 600px) {
    table#cart tbody td .form-control{
		width:20%;
		display: inline !important;
	}
	.actions .btn{
		width:36%;
		margin:1.5em 0;
	}
	
	.actions .btn-info{
		float:left;
	}
	.actions .btn-danger{
		float:right;
	}
	
	table#cart thead { display: none; }
	table#cart tbody td { display: block; padding: .6rem; min-width:320px;}
	table#cart tbody tr td:first-child { background: #333; color: #fff; }
	table#cart tbody td:before {
		content: attr(data-th); font-weight: bold;
		display: inline-block; width: 8rem;
	}
	
	
	
	table#cart tfoot td{display:block; }
	table#cart tfoot td .btn{display:block;}
	
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		
		var uPrice = $(".actual").text();
		var uPrice = uPrice*1.3;
		
		 $(".actual").append("원");

		$("#comeon").text(uPrice+"원");
		
		
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
		<!-- 				<div class="col-sm-9 padding-right"> -->
		<div class="">
			<!--product-details-->
			<div class="col-sm-8">
				<div class="">
					<h1>
						제품정보 <small>${sDetail.name }</small>
					</h1>
					&nbsp;
				</div>
			</div>
			<div class="col-sm-12">
			<hr>	
				<div class="">
					<!--/product-information-->
					<img src="${sDetail.image}" class="newarrival" alt=""
						style="width: 300px; height: 350px; float: left;">
				</div>
				<div class="col-sm-2"></div>
				<div class="col-sm-6">		
					<h4>PRODUCT INFO<small>&nbsp;&nbsp;&nbsp;제품 정보</small></h4>
					<p>- 제품이름 : <b>${sDetail.name }</b></p>
					<p>- 제품번호: <b>${sDetail.code }</b></p>
<!-- 					<img src="images/product-details/rating.png" alt="" /> -->
					<p>
					  	-제품재고: <b>${sDetail.qnt}</b>
					</p>
					<p>
						-사이즈: <b>${sDetail.size}</b>
					</p>
					<hr>
					<span>
						<h4>PRICE INFO<small>&nbsp;&nbsp;&nbsp;가격 정보</small></h4>
							<p>
								-적립금: <b>${sDetail.mg }</b>
							</p>
						<p class="m_5">
							  - 제품 가격 :
							<span class="reducedfrom" id="comeon"></span><span
								class="actual">${sDetail.price}</span>
						</p> 
						<hr>
						<h4>SELECT OPTION<small>&nbsp;&nbsp;&nbsp;옵션선택</small></h4>
						<select class="form-control"> 
						<option>${sDetail.size}</option> 
						</select>
						<br> 
					
						<c:if test="${sDetail.qnt>0}">
							<div class="btn_form">
								<form action="cartAdd.do?code=${sDetail.code}" method="post">
									<c:if test='${mid==null}'>
										<input name="orderQnt" type="number"
											class="btn btn-fefault cart" value="1" />
										<a href="#" class="btn btn-default add-to-cart"
											data-target="#mcart" data-toggle="modal"><i
											class="fa fa-shopping-cart"></i>Add to cart</a>
										<button name="buy" type="button" class="btn btn-default"
											data-target="#mcart" data-toggle="modal">구매하기</button>

									</c:if>
									<c:if test='${mid!=null}'>
										<input name="orderQnt" type="number"
											class="btn btn-fefault cart" value="1" />
										<button type="submit" class="btn btn-default">
											<i class="fa fa-shopping-cart"></i>Add to cart
										</button>
										<input name="buy" type="submit" value="구매하기"
											class="btn btn-default" title="">
									</c:if>

								</form>
							</div>

						</c:if> <c:if test="${sDetail.qnt <=0}">
							<button type="button" class="btn btn-danger" id="btn-delete"
								style="background-color: black; color: white;">
								<span class="glyphicon glyphicon-remove"></span> 품절
							</button>
						</c:if>
					</span> <a href=""><img src="images/product-details/share.png"
						class="share img-responsive" alt="" /></a>
					<div id="similar-product" class="carousel slide"
						data-ride="carousel"></div>
				</div>
				
				</div>
				
				<!--/product-information-->
				<div class="col-sm-12">
				<hr>
					<img src="${sDetail.content }" class="share img-responsive" alt="" />
				</div>
			</div>
		</div>
		<!-- 				</div> -->
	</div>
	<!-- 			리뷰게시판 -->
	<hr>
	<div class="container">
		<div class="col-sm-12">
			<div class="panel panel-primary">
				<div class="panel-heading" style="background-color: black">
					<h3 class="panel-title">&nbsp;&nbsp;REVIEW</h3>
				</div>
				<div class="panel-body">
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object" src="../images/cha.jpg"
								alt="차범근" width="60px" height="80px"></a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">
								<a href="#">신상품 1번</a>&nbsp;<span class="badge">New</span>
							</h4>
							너무 싸고 좋네요. 빨리사세요.
							<div class="pull right" style="float: right">
								차범근&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div>
					<div class="media">	
						<div class="media-left">
							<a href="#"><img class="media-object" src="../images/ryu.jpg"
								alt="류헨진" width="60px" height="80px"></a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">
								<a href="#">신상품 2번</a>&nbsp;<span class="badge">New</span>
							</h4>
							많이 아쉽네요. 사진이랑 달라요, 다시 사고싶지 않습니다.
							<div class="pull right" style="float: right">
								류현진&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div>	
					<div class="media">	
						<div class="media-left">
							<a href="#"><img class="media-object" src="../images/choo.jpg"
								alt="추신수" width="60px" height="80px"></a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">
								<a href="#">신상품 3번</a>
							</h4>
							맘에드네요. 조만간 재주문합니다.
							<div class="pull right" style="float: right">
								추신수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div>
					<div class="media">	
						<div class="media-left">
							<a href="#"><img class="media-object" src="../images/6.jpg"
								alt="손흥민" width="60px" height="80px"></a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">
								<a href="#">상품 4번</a>
							</h4>
							강추!!
							<div class="pull right" style="float: right">
								이상해씨&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</div>		
					</div>
				</div>

			</div>
			<p class="article-comment margin-small">
			<c:if test="${mid != null}">
				<a class="btn btn-default pull-right" href="#" style="background-color: lightgrey">리뷰작성</a>
			</c:if>
			<c:if test="${mid == null }">
				<a class="btn btn-default pull-right" href="#" style="background-color: lightgrey">리뷰작성</a>
			</c:if>
		</p>
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
		<div class="row">
		<div class="modal" id="mcart" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align:center;">
						로그인 후 주문해 주세요.<br>
						<a class="btn btn-default" href="../joinus/login.do">로그인하기</a>
						<button class="btn btn-default" data-dismiss="modal">취소</button>						
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
						<a class="btn btn-default" href="../joinus/login.do">로그인하기</a>
						<button class="btn btn-default" data-dismiss="modal">취소</button>						
					</div>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>