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
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gnb.css?after">
</head>
<body>
	
		<div class="top_gnb_area">
			<ul class="list">
			<c:if test="${empty member}">
			<!-- 로그인 실패 -->
				<li>
					<a href="/member/joinPage">회원가입</a>
				</li>
			</c:if>
			
			<c:if test="${not empty member}">
				<!-- 로그인 성공 -->
				<c:if test="${member.adminCk == 1}">
					<!-- 관리자 계정 -->
					<li><a href="/admin/main">관리자 페이지</a></li>
				</c:if>
				
				<li id="gnb_logout_button">로그아웃</li>
				<li>마이룸</li>
				<li>장바구니</li>
			</c:if>
			
				<li>고객센터</li>
			</ul>
		</div>
	<script type="text/javascript">
	
	/*비동기 로그아웃 버튼 작동*/
	$(document).on("click",'#gnb_logout_button',function(){
		alert("버튼 작동");
		$.ajax({
			type:"POST",
			url:"/member/logout",
			success:function(data){
				alert("로그아웃 성공");
				/*새로 고침을 해주어야 세션의 변경사항이 화면에 반영된다.*/
				document.location.reload();
			},error:function(){
				alert("에러입니다");
			}
		});
	});
	</script>
	
</body>
</html>