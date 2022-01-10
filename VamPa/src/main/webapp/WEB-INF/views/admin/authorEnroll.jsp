<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  
<script type="text/javascript">

 	$(document).ready(function() {
		
		var result = '<c:out value="${enroll_result}"/>';//인용부호 오류가 여기서 걸리기에 제대로 써주기
		checkResult(result); 
		
		function checkResult(result) {
			if(result === ''){
				return;
			}
			
			alert("작가'${enroll_result}'을 등록하였습니다.");
		}
		
	});

	/*등록 버튼*/
	$(document).on("click","#enrollBtn",function(){
		
	    /* 검사 통과 유무 변수 */
	    let nameCheck = false;            // 작가 이름
	    let nationCheck = false;        // 소속 국가
	    let introCheck = false;            // 작가 소개    
	 
	    /* 입력값 변수 */
	    let authorName = $('input[name=authorName]').val();        // 작가 이름
	    let nationId = $('select[name=nationId]').val();        // 소속 국가
	    let authorIntro = $('input[name=authorIntro]').val();    // 작가 소개
	    /* 공란 경고 span태그 */
	    let wAuthorName = $('#warn_authorName');
	    let wNationId = $('#warn_nationId');
	    let wAuthorIntro = $('#warn_authorIntro');    
	    
	    /* 작기 이름 공란 체크 */
	    if(authorName ===''){
	        wAuthorName.css('display', 'block');
	        nameCheck = false;
	    } else{
	        wAuthorName.css('display', 'none');
	        nameCheck = true;
	    }
	    
	    /* 소속 국가 공란 체크 */
	    if(nationId ==='none'){
	        wNationId.css('display', 'block');
	        nationCheck = false;
	    } else{
	        wNationId.css('display', 'none');
	        nationCheck = true;
	    }    
	    
	    /* 작가 소개 공란 체크 */
	    if(authorIntro ===''){
	        wAuthorIntro.css('display', 'block');
	        introCheck = false;
	    } else{
	        wAuthorIntro.css('display', 'none');
	        introCheck = true;
	    }    
	    
	    /* 최종 검사 */
	    if(nameCheck && nationCheck && introCheck){
	        $("#enrollForm").submit();    
	    } else{
	        return;
	    }
	});
	
	/*취소 버튼*/
	$(document).on("click","#cancelBtn",function(){
		
		location.href="/admin/main?target="+"authorManage";
		
	});
</script>
</head>
<body>
	<h1>작가등록</h1>
	<form action="/admin/authorEnroll" method="post" id="enrollForm">
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가 이름</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="authorName">
                    				<span id="warn_authorName">작가 이름을 입력해주세요.</span>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>소속 국가</label>
                    			</div>
                    			<div class="form_section_content">
                    				<select name="nationId">
                    					<option value="none" selected>=== 선택 ===</option>
                    					<option value="01">국내</option>
                    					<option value="02">국외</option>
                    				</select>
                    				<span id="warn_nationId">소속 국가를 선택해주세요.</span>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가소개</label>
                    				<hr>
                    			</div>
                    			<div class="form_section_content">
                    				<!-- 작가소개 -->
                    				<input name="authorIntro" type="text">
                    				<span id="warn_authorIntro">작가 소개를 입력 해주세요.</span>
                    			</div>
                    		</div>
                   </form>
                   			<div class="btn_section">
                   				<button id="cancelBtn" class="btn">취 소</button>
	                    		<button id="enrollBtn" class="btn enroll_btn">등 록</button>
	                    	</div> 
	
</body>
</html>