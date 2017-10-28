<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- 부트스트랩 사용할때 rel = stylesheet 무조건써야된다. link3줄로 css 경로 지정  -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/font-awesome.min.css" media="screen" title="no title" charset="utf-8">
<link rel="stylesheet" href="../css/style4.css" media="screen" title="no title" charset="utf-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">

$(function(){

	$('#dialog').dialog({
		autoOpen:false,
		width:450,
		height:250,
		modal:true,
		buttons:[
			{
				text:'중복체크',
				click:function(){
					
					var id=$('#id').val();
					/* alert("id="+id); */
					if(id.trim()==""){
						$('#id').focus();
						return;
					}
					$.ajax({
						type:'POST',
						 url:'idcheck.jsp', 
						data:{"id":id},
						success:function(response){   
		  					var count=response.trim();
		  					/* alert(count); */
							var data="";
								if(count==0){
									data=id+"는(은) 사용가능한 ID입니다.";
									var html="<td align=center colspan=2><input type=button id=ok value=OK onclick=ok()></td>";
									$('#ok').html(html);
								}else{
									data=id+"는(은) 이미 사용중인 ID입니다.";
								}
								$('#result').text(data);

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
	$('#idBtn').click(function(){
		$('#dialog').dialog('open');
	});

$(function(){
	$('#btnSub').click(function(){
		if($('#userpwd').val()!=$('#userpwd1').val()){
			alert("비밀번호와 비밀번호 재입력이 다릅니다.")
			$('#userpwd').focus();
			return false;
		}else if($('#username').val()=="" || $('#userphone').val()=="" || $('#useremail').val()=="" || $('#userdate').val()=="" || $('#userAnswer').val()=="" || $('#userid').val()==""){
			alert("정보를 모두 입력해주세요.")
			return false;
		}else{
			alert("회원가입이 완료되었습니다.")
		}
	});
});
	
});
function ok(){
	parent.joinForm.mem_id.value=$('#id').val();
	jQuery('#dialog').dialog('close');
}
</script>
</head>
<body>

<article class="container">
        <div class="page-header">
          <h1>회원가입 <small>Sign Up</small></h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
          <form id="joinForm" action="member/join_ok.jsp" name="joinForm" method=post>
          <div class="form-group">
              <label>ID</label>
              <div class="input-group">
                <input type="text" class="form-control" id="userid" name=mem_id placeholder="ID를 입력해주세요." readonly>
                <span class="input-group-btn">
                <input type=button id="idBtn" class="btn btn-success" value="중복체크">
                  <!-- <button id="idBtn" class="btn btn-success" value="중복체크">중복체크<i class="fa fa-mail-forward spaceLeft"></i></button> -->
                </span>
              </div>
            </div>
            <!-- <div class="form-group">
              <label for="InputEmail">ID</label>
              <input type="text" class="form-control" id="userid" name=mem_id placeholder="ID를 입력해주세요." style="width:80%" readonly required>
              <input type=button id="idBtn" class="btn btn-success" value="중복체크" style="width:20%" style="float:right">
            </div> -->
            <div class="form-group">
              <label>비밀번호</label>
              <input type="password" class="form-control" id="userpwd" name=pwd placeholder="비밀번호">
            </div>
            <div class="form-group">
              <label>비밀번호 확인</label>
              <input type="password" class="form-control" id="userpwd1" name=pwd1 placeholder="비밀번호 확인">
              <p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
            </div>
            <div class="form-group">
              <label>이름</label>
              <input type="text" class="form-control" id="username" name=name placeholder="이름을 입력해 주세요">
            </div>
        <div class="form-group">
              <label>성별</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type=radio name=gender value="남자" checked>남자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		  <input type=radio name=gender value="여자">여자
            </div>
            <div class="form-group">
              <label>전화번호</label>
              <input type="text" class="form-control" id="userphone" name=phone placeholder="'-' 없이 입력해주세요.">
            </div>
 
            <div class="form-group">
              <label>Email</label>
              <input type="text" class="form-control" id="useremail" name=email placeholder="Email을 입력해주세요." style="width:100%">
            </div>
            <div class="form-group">
              <label>생년월일</label>
              <input type="date" class="form-control" id="userdate" name=birthday>
              
             <!--  input type="date"로 생년월일 입력받았다. -->
              
            </div>
            <label for="InputPassword1">* 다음은 비밀번호 찾기용 질문입니다.</label>
            <div class="form-group">
              <label>질문</label>
              <div class="input-group">
              	<select id="userQuestion" name="question" class="form-control">
			      <option>당신의 졸업한 초등학교의 이름은?</option>
			      <option>가장 존경하는 인물은?</option>
			      <option>가장 친한 친구의 이름은?</option>
			      <option>가장 좋아하는 음식은?</option>
			      <option>가장 친한 친구의 이름은?</option>
			     </select>
                
              </div>
            </div>
            <div class="form-group">
              <label>답변</label>
              <input type="text" class="form-control" id="userAnswer" name="answer" placeholder="답변을 입력해주세요.">
            </div>
            <div class="form-group text-center">
            
            	<!-- pointer 화살표 모양으로 바뀐다. -->
            	<input type=submit id="btnSub" class="btn btn-info" style="cursor:pointer" value="회원가입">
            	<input type=button id="btnCan" class="btn btn-warning" style="cursor:pointer" value="가입취소"
	      onclick="javascript:history.back()"> 
	      
<!-- 	      onclick="javascript:history.back()  취소누르면 전화면으로 돌아간다. -->
            
            </div>
          </form>
        </div>
		
        
      </article>
      <div id="dialog" title="중복체크">
	  	<table id="table_content" width=350>
			<tr>
			 	<!-- 태그안에 속성값 바로 넣었다. -->
				<td width=20% align=center>ID</td>
				<td width=80% align=left>
					<input type=text name=id size=20 id="id">
					<!-- <input type=button value="중복체크" id="checkBtn"> -->
				</td>
			</tr>
			<tr>
				<td align=center id="result" colspan="2"></td>
			</tr>
			<tr id="ok">
				
			</tr>
		</table>
	  </div>
      
  </body>
</html>