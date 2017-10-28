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
					<li class="active"><a href="#">마이페이지<span class="sr-only"></span></a></li>
					<li><a href="#">SHOP</a></li>
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
<div class="panel-heading" style="background-color: black;">
<h3 style="color: white;">글쓰기</h3></div>
<table class="table table-bordered">
    <thead>
    </thead>
    <tbody>
        <form action="noticeRegProc.do" method="post" enctype="multipart/form-data">
            <tr>
                <th>제목: </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="title" class="form-control"/></td>
            </tr>
            <tr>
                <th>내용: </th>
                <td><textarea cols="10" placeholder="내용을 입력하세요. " name="content" class="form-control" style="height:200px;"></textarea></td>
            </tr>
            <tr>
                <th>첨부파일: </th>
                <td><input type="file" placeholder="파일을 선택하세요. " name="file" class="form-control"/></td>
            </tr>
            <tr>
                <th>비밀번호: </th>
                <td><input type="text" name="pwd" placeholder="비밀번호를 입력하세요" class="form-control"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="등록" onclick="sendData()" class="btn pull-right"/>
<!--                     <input type="button" value="reset" class="btn pull-left"/> -->
                    <input type="button" value="글 목록" class="btn pull-left" onclick="javascript:location.href='notice.do?pg=${pg}'"/>                                             
                </td>
            </tr>
        </form>
    </tbody>
</table>
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
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
</body>
</html>