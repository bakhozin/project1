<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>login</title>
 
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- font awesome -->
    <link href="../css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom Style -->
    <link href="../css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></scri.row>.containerpt>
    <![endif]-->
    <script type="text/javascript">
	$(function(){
		$('#loginBtn').click(function(){
			var id=$('#id').val();
			var pwd=$('#pwd').val();
			if(id.trim()==""){
				$('#id').focus();
				return;
			}
			if(pwd.trim()==""){
				$('#pwd').focus();
				return;
			}
			$('#log_frm').submit(); // 데이터 전송(login.jsp한테)
									// action="../member/login.jsp" 
		});
		 $('#idDialog').dialog({
			autoOpen:false,
			width:450,
			height:250,
			modal:true,
			buttons:[
				{
					text:'아이디찾기',
					click:function(){
						var name=$('#searchName').val();
						var email=$('#searchEmail').val();
						
						  /* alert("email="+email);   */
						if(email.trim()==""){
							alert("Email을 입력해주세요.")
							$('#searchEmail').focus();
							return;
						}
						if(name.trim()==""){
							alert("이름을 입력해주세요.")
							$('#searchName').focus();
							return;
						}
						$.ajax({
							type:'POST',
							 url:'searchId.jsp', 
							data:{"email":email,"name":name},
							success:function(response){   
			  					var data=response.trim();
			  					 
			  					   /* alert(data); */  
								var msg="";
									if(data==""){
										msg="일치하는 ID가 없습니다.";
										var html="<td align=center colspan=2><input type=button id=ok value=OK onclick=ok()></td>";
										
									}else{
										msg="당신의 ID는"+data+"입니다.";
									}
									$('#idResult').text(msg);
									/*
										text() ==> getter
										text("aaa") ==> setter
									*/
							}
						});
					}
				},
				{
					text:'취소',
					click:function(){
						$(this).dialog("close");
					}
				}
			]
		
		});
		$('#idSearch').click(function(){
			$('#idDialog').dialog('open');
		});
		$('#pwdDialog').dialog({
			autoOpen:false,
			width:550,
			height:350,
			modal:true,
			buttons:[
				{
					text:'비밀번호찾기',
					click:function(){
						
						var id=$('#searchId').val();
							if(id.trim()==""){
							$('#searchId').focus();
							return;
						}
							var question=$('#searchQuestion').val();
							if(question.trim()==""){
							$('#searchQuestion').focus();
							return;
						}
							var answer=$('#searchAnswer').val();
							if(answer.trim()==""){
							$('#searchAnswer').focus();
							return;
						}
						
					
						$.ajax({
							type:'POST',
							 url:'searchPwd.jsp', 
							data:{"id":id,"question":question,"answer":answer},
							success:function(response){   
			  					var data=response.trim();

			  					var msg="";
									if(data==""){
										msg="정보를 다시 확인해주세요.";
										var html="<td align=center colspan=2><input type=button id=ok value=OK onclick=ok()></td>";
										
									}else{
										msg="당신의 비밀번호는 "+data+"입니다.";
									}
									$('#pwdResult').text(msg);

									
							}
						});
					}
				},
				{
					text:'취소',
					click:function(){
						$(this).dialog("close");
					}
				}
			]
		
		});
		$('#pwdSearch').click(function(){
			$('#pwdDialog').dialog('open');
			
		}); 
	});
</script>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="page-header">
          <h2>DD SHOP 로그인</h2>
        </div>
        <div class="col-md-6">
          <div class="login-box well">
        <form accept-charset="UTF-8" role="form" method="post" action="loginProc.do">
<!-- 					쿠키로 자동로그인 제어  -->
						<c:if test="${empty cookieMid || empty cookiePwd}">
							<legend>로그인</legend>
							<div class="form-group">
								<label for="username-email">아이디</label> <input name="mid"
									value='' id="username-email" placeholder="Username"
									type="text" class="form-control" />
							</div>
							<div class="form-group">
								<label for="password">비밀번호</label> <input name="pwd"
									id="password" value='' placeholder="Password" type="password"
									class="form-control" />
							</div>
						</c:if>
						<c:if test="${!empty cookieMid && !empty cookiePwd }">
							<legend>로그인</legend>
							<div class="form-group">
								<label for="username-email">아이디</label> <input name="mid"
									value='${cookieMid}' id="username-email"
									placeholder="Username" type="text"
									class="form-control" />
							</div>
							<div class="form-group">
								<label for="password">비밀번호</label> <input name="pwd"
									id="password" value='${cookiePwd}' placeholder="Password"
									type="password" class="form-control" />
							</div>
						</c:if>
						<c:if test="${!empty cookieMid && !empty cookiePwd}">
							<li><input type="checkbox" name="checkBoxMid" checked="checked"/> 자동로그인</li>
						</c:if>
						<c:if test="${empty cookieMid || empty cookiePwd}">
							<li><input type="checkbox" name="checkBoxMid" /> 자동로그인</li>
						</c:if>

						<div class="form-group">
                <input type="submit" class="btn btn-default btn-login-submit btn-block m-t-md" value="Login" />
            </div>
            <a style="cursor:pointer" id="idSearch" style="text-decoration: none">아이디찾기</a>&nbsp;&nbsp;
            <a style="cursor:pointer" id="pwdSearch" style="text-decoration: none">비밀번호찾기</a>
            <hr />
            <div class="form-group">
                <a href="join.do" class="btn btn-default btn-block m-t-md"> 회원가입</a>
            </div>
        </form>
          </div>
        </div>
      </div>
    </div>
    <div id="idDialog" title="아이디찾기">
	  	<table id="table_content" width=200>
	  		<tr>
				<td width=20% align=center>이름:&nbsp;</td>
				<td width=80% align=left>
					<input type=text name=searchName size=30 id="searchName" placeholder="이름을 입력해주세요.">
					<!-- <input type=button value="중복체크" id="checkBtn"> -->
				</td>
			</tr>
			<tr>
				<td width=20% align=center>Email:&nbsp;</td>
				<td width=80% align=left>
					<input type=text name=searchEmail size=30 id="searchEmail" placeholder="Email을 입력해주세요.">
					<!-- <input type=button value="중복체크" id="checkBtn"> -->
				</td>
			</tr>
			<tr>
				<td align=center id="idResult" colspan="2"></td>
			</tr>
			<tr id="IDOK">
				
			</tr>
		</table>
	  </div>
	  <div id="pwdDialog" title="비밀번호찾기">
	  	<table id="table_content" width=300>
			<tr>
				<td width=20% align=center>ID:&nbsp;</td><br>
					<td width=80% align=left>
					<input type=text name=id size=30 id="searchId" placeholder="ID를 입력해주세요.">
					<!-- <input type=button value="중복체크" id="checkBtn"> -->
				</td>
			</tr>
			<tr>
				<td width=20% align=center>질문:&nbsp;</td><br>
					<td width=80% align=left>
					<select id="searchQuestion" name=question>
			      <option>당신의 졸업한 초등학교의 이름은?</option>
			      <option>가장 존경하는 인물은?</option>
			      <option>가장 친한 친구의 이름은?</option>
			      <option>가장 좋아하는 음식은?</option>
			      <option>가장 친한 친구의 이름은?</option>
			     </select>
					<!-- <input type=select name=question size=30 id="searchQuestion"> -->
					<!-- <input type=button value="중복체크" id="checkBtn"> -->
				</td>
			</tr>
			<tr>
				<td width=20% align=center>답:&nbsp;</td><br>
					<td width=80% align=left>
					<input type=text name=answer size=30 id="searchAnswer" placeholder="답변을 입력해주세요.">
					<!-- <input type=button value="중복체크" id="checkBtn"> -->
				</td>
			</tr>
			<tr>
				<td align=center id="pwdResult" colspan="2"></td>
			</tr>
			<tr id="PWDOK">
				
			</tr>
		</table>
	  </div>

  </body>
  </html>