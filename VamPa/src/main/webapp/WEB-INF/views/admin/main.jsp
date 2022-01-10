<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- jstl 활용한 숫자 표시 형식 변경-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<% 
	request.setCharacterEncoding("UTF-8");

	String tp = request.getParameter("target");
	System.out.println("target : "+tp);
	if (tp == null) {
	   tp = "goodsEnroll";
	}
	
	String targetPage = tp +".jsp" ;//이제 EL태그안에서 해당 페이지를 보여줌
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인 페이지</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/main.css?after">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gnb.css?after">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/authorManage.css?after">

</head>
<body>

 <div class="container">
  	<div class="item">
  		<!-- gnb area(로그인,비로그인,관리자) -->
  		<jsp:include page="../include/GnbArea_Admin.jsp" flush="false" />
  	</div>
	
	<div class="item">
		<h1>관리자 페이지</h1>
	</div>
	
  	<div class="item">
  	<!-- 왼쪽 -->
  		
  		<div class="admin_navi_wrap">
  		  		<ul>
                  <li >
                      <a class="admin_list_01" href="/admin/goodsEnroll">상품 등록</a>
                  </li>
                  <li>
                      <a class="admin_list_02" href="/admin/goodsManage">상품 관리</a>
                  </li>
                  <li>
                      <a class="admin_list_03" href="/admin/authorEnrollPage">작가 등록</a>                            
                  </li>
                  <li>
                      <a class="admin_list_04" href="/admin/authorManage">작가 관리</a>                            
                  </li>
                  <li>
                      <a class="admin_list_05">회원 관리</a>                            
                  </li>                                                                                             
              </ul>
         </div>    

  	</div>
  	
  	<div class="item" id="admin_content_wrap">
  		<div class="admin_content_subject">
  			<jsp:include page="<%=targetPage%>" flush="false"/>
  		</div>
  	</div>
  	
  	<div class="item">
  		<jsp:include page="../include/FooterSection.jsp" flush="false" />
	</div>
  </div>
</body>
</html>