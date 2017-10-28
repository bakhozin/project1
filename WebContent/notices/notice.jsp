<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
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
				 <a class="navbar-brand" href="../joinus/loginMain.do">DD SHOP</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collpase-1">
<!-- 			상단 BAR안에 개별적인 링크가 들어간다 -->
				<ul class="nav navbar-nav">
<!-- 				active는 현재 선택이 되어있다는 뜻  -->
					<li class="active"><a href="#">게시판<span class="sr-only"></span></a></li>
					<li><a href="../shop/shop.do">SHOP</a></li>
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
				<form class="navbar-form navbar-left">
					<div class="form-group">>
						<input type="text" class="form-control" placeholder="상품명을 입력하세요.">
					</div>
	  			
					<button type="submit" class="btn btn-default">검색</button>
				</form>
<!-- 				로그인/회원가입 처리용 dropdown var  -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">LOGIN<span class="caret"></span></a>
							
							<ul class="dropdown-menu">
								<li><a href="#">로그인</a></li>
								<li><a href="#">회원가입</a></li>
							</ul>														
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading" style="background-color: black; height: 55px">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon"></span> &nbsp;&nbsp;게시판 입니다.
							<form class="navbar-form navbar-right">
							<div class="form-group">
								 <input type="text" name="search" class="form-control"
									placeholder="제목을 입력하세요.">
							</div>

							<button type="submit" class="btn btn-default">검색</button>
						</form>
						</h3>
					</div>
					<!-- 					<div class="panel-body"> -->			
<!-- 					</div> -->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${nList}">
								<tr>
									<td class="seq">${i.seq}</td>
									<td class="title"><a
										href="noticeDetail.do?seq=${i.seq}&pg=${pg}&f=${field}&q=${urlQuery}&hitOn=${1}">${i.title}</a></td>
									<td class="writer">${i.writer}</td>
									<td class="regdate">${i.regdate}</td>
									<td class="hit">${i.hit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>				
				</div>
				<p class="article-comment margin-small">
					<c:if test="${mid != null}">
						<a class="btn btn-default pull-right"
							href="noticeReg.do?pg=${pg}&q=${urlQuery}&f=${field}">글쓰기</a>
					</c:if>
					<c:if test="${mid == null }">
						<a class="btn btn-default pull-right" href="../joinus/login.do">글쓰기</a>
					</c:if>

				</p>
				<p id="cur-page" class="margin-small">
					<span class="strong">${pg}</span> / ${finalPage}
				</p>
				<div id="pager-wrapper" class="margin-small">
					<div class="text-center">
						<c:if test="${sPage!=1}">
							<p id="btnPrev">
								<a class="button btn-prev"
									href="notice.do?pg=${sPage-1}&f=${field}&q=${urlQuery}">이전</a>
							</p>
						</c:if>

						<ul class="pagination">
							<c:forEach var="i" begin="0" end="4">
								<li>
								    <c:if test="${i+sPage <= finalPage}">								
											<a href="notice.do?pg=${sPage+i}&f=${field}&q=${urlQuery}">${sPage+i}</a>
									</c:if>
								</li>
							</c:forEach>
						</ul>
						<c:if test="${sPage+4 < finalPage}">
							<p id="btnNext">
								<a class="button btn-next"
									href="notice.do?pg=${sPage+5}&f=${field}&q=${urlQuery}">다음</a>
							</p>
						</c:if>
					</div>
				</div>
			</div>
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
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
</body>
</html>