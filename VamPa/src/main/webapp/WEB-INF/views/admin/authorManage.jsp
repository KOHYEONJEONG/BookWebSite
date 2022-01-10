<%-- <%@page import="org.apache.ibatis.reflection.SystemMetaObject"%> --%>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- jstl 활용한 숫자 표시 형식 변경-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% 
	request.setCharacterEncoding("UTF-8");
	String tp = request.getParameter("target");
	System.out.println("Manager - target : "+tp);
%>	


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>authorManage</title>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/main.css?after">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gnb.css?after">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/authorManage.css?after">
</head>
<script type="text/javascript">


	/* 페이지 이동 버튼 */
	$(document).on("click",".pageMaker_btn a", function(e){
    
    	e.preventDefault(); //a태그는 새 페이지를 만들기때문에 
    	//a 태그의 href 속성이 중단되고 e.preventDefault()를 띄우는 것을 알 수 있다.
    	
    	$('#moveForm').find("input[name='pageNum']").val($(this).attr("href"));
    
    	
    	$('#moveForm').attr("action","/admin/authorManage");
    	$('#moveForm').submit();
	});
	
	
	var inputKeyword = $("#keyword").val();
	/* 작거 검색 버튼 동작 */
	$(document).on("click","#search_btn", function(e){
		
		e.preventDefault();//a태그 기능)눌러도 다른 페이지 이동 안하고 보여줌(중요)
		
		/* 검색 키워드 유효성 검사 */
		if(!searchForm.find("input[name='keyword']").val()){
			alert(inputKeyword);
			return false;
		}
	
		/*성공*/
		//alert(inputKeyword);
		$('#searchForm').find("input[name='pageNum']").val("1");
		
		$('#searchForm').attr("action","/admin/authorManage");
		$('#searchForm').submit();
		
	});
	
	
	/*작가 상세 페이지 이동*/
	$(document).on("click",".move", function(e) {
		alert("click");
		
		e.preventDefault();
		
		$('#moveForm').append("<input type='hidden' name='authorId' value='"+ $(this).attr("href") + "'>");
		alert("성공1");
		$('#moveForm').attr("action", "/admin/authorDetail");
		alert("성공2");
		$('#moveForm').submit();
		alert("성공");
		
	});
	
    
</script>
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
  	<!-- 왼쪽(side) -->
  		
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
	<h1>작가 관리</h1>
	
  	<div class="admin_content_subject">
		<div class="author_table_wrap">
		
		<!-- 게시물 o (+ 검색후 작가 나올 때)-->
		<c:if test="${listCheck != 'empty'}">
			<table class="author_table">
				<thead>
					<tr>
						<td class="th_column_1">작가 번호</td>
						<td class="th_column_2">작가 이름</td>
						<td class="th_column_3">작가 국가</td>
						<td class="th_column_4">등록 날짜</td>
						<td class="th_column_5">수정 날짜</td>
					</tr>
				</thead>
				
				<c:forEach items="${dto}" var="dto">
					<tr>
					<td>
					<c:out value="${dto.authorId}"></c:out> </td>
	               
	                <td>
	                <!-- 작가 상세페이지로 이동 -->
	                	<a class="move" href='<c:out value="${dto.authorId}"/>'>
	                		<c:out value="${dto.authorName}"></c:out>
	                	</a>
	                </td>
	                
	                <td><c:out value="${dto.nationName}"></c:out> </td>
	                 <td><fmt:formatDate value="${dto.regDate}" pattern="yyyy-MM-dd"/></td>
	                 <td><fmt:formatDate value="${dto.updateDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
			
			<!-- 게시물 x(+ 검색후 작가 안 나올 때) -->
			<c:if test="${listCheck == 'empty'}">
				<div class="table_empty">
					등록된 작가가 없습니다.
				</div>
				<div align="center">
					<button onclick="location.href='/admin/authorManage'">뒤로가기</button>
				</div>
			</c:if>
			
			
			
			<!-- 검색 영역 -->
             <div class="search_wrap">
                  <form id="searchForm" method="get">
                    	<div class="search_input">
                    		<input type="text" name="keyword" id="keyword" 
                    			value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    		<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                    		<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                    		<button class='search_btn'>검 색</button>
                    	</div>
                   </form>
              </div> 

			<!-- http://localhost:8182/admin/authorManage?pageNum=2&amount=10 테스트로 타이핑(성공) -->
			<!-- 페이지 이동 인터페이스 영역 -->
			<div class="pageMaker_wrap">
		
				<ul class="pageMaker">
		
					<!-- 이전 버튼 -->
					<c:if test="${pageMaker.prev}">
						<li class="pageMaker_btn prev"><a
							href="${pageMaker.pageStart - 1}">이전</a></li>
					</c:if>
		
					<!-- 페이지 번호 1,2,3,4 등 (가운데) -->
					<c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
						<!-- "active":""}"인 이유는 : 해당 버튼에 현재 페이지임을 알 수 있도록 css 설정을 해주기 위함입니다. -->
						<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
							<a href="${num}">${num}</a>
						</li>
					</c:forEach>
		
					<!-- 다음 버튼 -->
					<c:if test="${pageMaker.next}">
						<li class="pageMaker_btn next">
						<a href="${pageMaker.pageEnd + 1 }">다음</a></li>
					</c:if>
		
				</ul>
		
		
					<!-- AdminController에서 authorManage url을 통해 authorManageGET에서 void로 내려받음 -->
				<form id="moveForm" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 
					<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
					<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>
			</div>
		</div>
	</div>
	</div>
	
	<div class="item">
  		<jsp:include page="../include/FooterSection.jsp" flush="false" />
	</div>
  </div>
</body>
</html>