<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	request.setCharacterEncoding("UTF-8");

	String tp = request.getParameter("target");
	if (tp == null) {
	   tp = "home";
	}
	
	String targetPage = tp +".jsp" ;

%>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
 -->
<title>메인화면</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css?after">
</head>
<body>

  <div class="container">
  	<div class="item">
  		<!-- gnb area(로그인,비로그인,관리자) -->
  		<jsp:include page="include/GnbArea.jsp" flush="false" />
  	</div>
  	<div class="item">
  		<jsp:include page="include/HeaderSection.jsp" flush="false" />
	</div>
	<div class="item">
  		<jsp:include page="include/HeaderSectionRight.jsp" flush="false" />
	</div>
	<div class="item">
		<H1>navi area</H1>
	</div>
	
  	<div class="item">
  		<jsp:include page="<%=targetPage%>" flush="false"/>
  	</div>
  	
  	<div class="item">
  		<jsp:include page="include/FooterSection.jsp" flush="false" />
	</div>
  </div>

</body>
</html>