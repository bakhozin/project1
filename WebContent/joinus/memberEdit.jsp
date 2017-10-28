<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/font-awesome.min.css" media="screen" title="no title" charset="utf-8">
<link rel="stylesheet" href="../css/style4.css" media="screen" title="no title" charset="utf-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
   $(function(){
	 $('#upBtn').click(function(){
// 		 .val() 이 입력한 값을 가져오게한다.
		 if($('#userpwd').val()!=$('#userpwd1').val()){
				alert("비밀번호와 비밀번호 재입력이 다릅니다.")
				$('#userpwd').focus();  // pointing 이 이동한다. 
				return false;  // return false를 줘야지 화면 그 상태로 유지된다. 안주면 정보 처음부터 다입력해야된다. 
			}else if($('#username').val()=="" || $('#userphone').val()=="" || $('#useremail').val()=="" || $('#userdate').val()=="" || $('#userAnswer').val()=="" || $('#userid').val()==""){
				alert("정보를 모두 입력해주세요.")
				return false;
			}else{
				alert("수정이 완료되었습니다.")
				var id=$('#userid').val();
				var pwd=$('#userpwd').val();
				/* alert(pwd); */
				$('#updateForm').submit();
			}
	 }) ;
  });


</script>
</head>
<body>


      <article class="container">
        <div class="page-header">
          <h1>수정/탈퇴 <small></small></h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
          <form id="updateForm" action="member/infoUpdate_ok.jsp" name="updateForm" method=post>
          <div class="form-group">
              <label for="username">ID</label>&nbsp;&nbsp;&nbsp;&nbsp;
              <!-- <div class="input-group"> -->
                <%-- <span style="font-size: 15px;">${sessionScope.id}</span> --%>
                <input type="text" class="form-control" id="userid" name=mem_id value="${sessionScope.mid}" placeholder="${sessionScope.mid}" readonly>
                <%-- <input type=hidden name=mem_id id="userid" value="${sessionScope.id }"> --%>
                <span class="input-group-btn">
               
                </span>
              <!-- </div> -->
            </div>
            
            <div class="form-group">
              <label for="InputPassword1">비밀번호</label>
              <input type="password" class="form-control" id="userpwd" name=pwd placeholder="비밀번호" required>
            </div>
            <div class="form-group">
              <label for="InputPassword2">비밀번호 확인</label>
              <input type="password" class="form-control" id="userpwd1" name=pwd1 placeholder="비밀번호 확인" required>
              <p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
            </div>
            <div class="form-group">
              <label for="username">이름</label>&nbsp;&nbsp;&nbsp;
              <%-- <span style="font-size: 15px;">${sessionScope.name}</span> --%>
              <input type="text" class="form-control" value="${m.name}" readonly>
              <!-- <input type="text" class="form-control" id="username" name=mem_name placeholder="이름을 입력해 주세요" required> -->
            </div>
            <div class="form-group">
              <label for="username">성별</label>&nbsp;&nbsp;&nbsp;
              <%-- <span style="font-size: 15px;">${sessionScope.sex}</span> --%>
              <input type="text" class="form-control" value="${m.gender}" readonly>
              
              <!-- <input type=radio name=mem_sex value="남자" checked>남자
	   		  <input type=radio name=mem_sex value="여자">여자 -->
            </div>
            <div class="form-group">
              <label>전화번호</label>
              <input type="text" class="form-control" id="userphone" name=phone value="${m.phone }" required>
            </div>
     
            <div class="form-group">
              <label for="InputEmail">Email</label>
              <input type="text" class="form-control" id="usermail" name=email value="${m.email }" required>
            </div>
            <div class="form-group">
              <label for="InputPassword1">생년월일</label>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="text" class="form-control" id="userdate" name=birth value="${m.birthday}" readonly>
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
              <input type="text" class="form-control" id="userAnswer" name=answer placeholder="답변을 입력해주세요." required>
            </div>
           
            <div class="form-group text-center">
            	<input type=button id="upBtn" class="btn btn-info" style="cursor:pointer" value="수정">
            	<input type=button id="Cancel" class="btn btn-warning" style="cursor:pointer" value="취소"
	      onclick="javascript:history.back()">
	      <a href="deleteMember.do"><input type=button id="delBtn" class="btn btn-info" style="cursor:pointer" value="회원탈퇴"></a>
             
            </div>
          </form>
        </div>
		
        
      </article>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    Include all compiled plugins (below), or include individual files as needed
    <script src="js/bootstrap.min.js"></script> -->
  </body>
</html>