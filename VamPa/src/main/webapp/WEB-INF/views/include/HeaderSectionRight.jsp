<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- jstl 활용한 숫자 표시 형식 변경 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<!-- jquery - url -->
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/headerRight.css?after">
</head>
<body>

	<!-- 로그인 하기 전 -->
	<c:if test="${member== null}">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	      <div class="navbar-nav" id="login-navbar">
	        <!-- <a class="nav-link disabled">마이페이지</a> -->
	        <a class="nav-link" id="login-nav-link" data-bs-toggle="modal" data-bs-target="#staticBackdrop">로그인</a>
	      </div>
	    </div>
	  </div>
	  
	<!-- 로그인 모달 -->
	  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-login">
		
			<div class="modal-content">
				<div class="modal-header">				
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				
				<div class="modal-body">
					<form method="POST" id="login_form">
						<div class="form-group">
							<i class="fa fa-user"></i>
							
							<!-- id입력 -->
							<input type="text" class="form-control" name="memberId" id="id_input" placeholder="Username" required="required">
						</div>
						<div class="form-group">
							<i class="fa fa-lock"></i>
							
							<!-- pwd입력 -->
							<input type="password" class="form-control"  name="memberPw" id="pw_input" placeholder="Password" required="required">					
						</div>
						
							<div class="form-group" id="login_warn">
								<!-- 로그인 실패시 script에서 innerHtml로 작성해줌 -->
							</div>
						
						<div class="form-group">
						
							<!-- 로그인 -->
							<input type="button" class="btn btn-primary btn-block btn-lg" value="로그인" id="login_button">
						</div>
					</form>
				</div>
				
				<div class="modal-footer">
					<a href="./test">비밀번호 찾기</a> |
					<a href="./member/joinPage">회원가입</a>
				</div>
			</div>
		</div>
	  </div>
	</nav>
	</c:if>
	
	<!-- 로그인한 상태 -->
	<c:if test="${member != null}">
		<div class="login_success_area">
			<div>
				&nbsp;<span class="fa fa-user">&nbsp;:&nbsp;${member.memberName}</span> <!-- ID 로고 -->
				<br/>
				&nbsp;<span class="fa fa-money">&nbsp;:&nbsp;${member.money}</span> <!-- ID 로고 -->
				<br/>
				&nbsp;<span class="fa fa-star">&nbsp;:&nbsp;${member.point}</span> <!-- ID 로고 -->
				<br/>
				<!-- <a id="gnb_logout_button">로그아웃</a> -->
				
			</div>
		</div>
	</c:if>
	
	<script type="text/javascript">
		/*로그인 버튼 클릭 메서드*/	
		$(document).on("click",'#login_button',function(){
			//로그인 메서드 서버 요청
			var memberId = $('#id_input').val();
			var memberPw = $('#pw_input').val();
			var display=document.getElementById("login_warn");
			
			$.ajax({
				url: "/member/login",
				type:"post",
				data : {memberId:memberId, memberPw:memberPw},
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',//중요
				success:function(result){
					if(result==0){
						//alert(result);
						console.log(result);
						display.innerHTML = "아이디 비밀번호를 다시 확인해주세요";
					}else{
						console.log(result);
						location.href="/main";
						/* $("#login_form").attr("action","/member/login");
						$("#login_form").submit(); */
					}
				},error:function(){
					alert("에러입니다");
				}
					
			});
			
		});
			
			
	</script>
</body>
</html>