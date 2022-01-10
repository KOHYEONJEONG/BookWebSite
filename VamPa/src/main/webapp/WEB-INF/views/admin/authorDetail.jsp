<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- jstl 활용한 숫자 표시 형식 변경-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/main.css?after">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gnb.css?after">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/authorDetail.css?after">


<script type="text/javascript">




</script>

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
	<h1>작가 상세</h1>
	
  		<div class="admin_content_subject">
			<div class="author_table_wrap">
			
		  		<div class="admin_content_main">
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>작가 번호</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" name="authorId" readonly="readonly" value="<c:out value='${authorInfo.authorId }'></c:out>">
                   				
                   			</div>
                   		</div>                    
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>작가 이름</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" name="authorName" readonly="readonly" value="<c:out value='${authorInfo.authorName }'></c:out>" >
                   			</div>
                   		</div>
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>소속 국가</label>
                   			</div>
                   			<div class="form_section_content">
                   				<select class="input_block" name="nationId" >
                   					<option value="none" selected 
                   						disabled="disabled">=== 선택 ===</option>
                   					<option value="01" disabled="disabled" <c:out value=" ${authorInfo.nationId eq '01' ?'selected':''}"/>>국내</option>
                   					<option value="02" disabled="disabled" <c:out value=" ${authorInfo.nationId eq '02' ?'selected':''}"/>>국외</option>
                   				</select>
                   			</div>
                   		</div>
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>작가소개</label>
                   			</div>
                   			<div class="form_section_content">
                   				<textarea class="input_block" name="authorIntro" readonly="readonly"><c:out value='${authorInfo.authorIntro }'/></textarea>
                   			</div>
                   		</div>
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>등록 날짜</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.regDate}" pattern="yyyy-MM-dd"/>">
                   			</div>
                   		</div>
                   		                   		                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>수정 날짜</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.updateDate}" pattern="yyyy-MM-dd"/>">
                   			</div>
                   		</div>
                   			<div class="btn_section">
                   				<button id="cancelBtn" class="btn">작가 목록</button>
	                    		<button id="modifyBtn" class="btn modify_btn">수 정</button>
	                    	</div> 
	                    	
	                    <!-- Criteria 정보를 저장하기 위해서(되돌아갈떄 원래 작가관리목록 위치 페이지로 돌아가려고) -->	
	                    <form id="moveForm" method="get">
                			<input type="hidden" name="authorId" value='<c:out value="${authorInfo.authorId }"/>'>
                			<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
                			<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
                			<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
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