<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />


<title>회원가입</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/join.css">
</head>

<!-- 주소록 API를 사용하기 위해서 외부 스크립트 파일은 연결 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
	
	var code = "";//이메일 전송 인증번호 저장위한 코드
	
	$(document).ready(function() {
		//회원가입 버튼(회원가입 버튼)
		$("#join_button").click(function () {
						
			$("#join_form").attr("action","/member/join");
			$("#join_form").submit();
		})
		
	});
	
	function checkId(){
		
		var memberId = $('#id_input').val(); //id입력값
		//var data = {memberId : memberId} // 컨트롤에 넘길 데이터 이름 : 데이터(입력 값)
		
		$.ajax({
				url: "/member/memberIdChk",
				type:"post",
				data : {memberId : memberId}, //데이터전송
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',//중요
				
				success:function(result){
					//console.log("성공여부" + result);//개발자도구에서 확인가능
					if(result != 1){
						console.log(result);
						$('#id_input_re_1').css("display","inline-block");
						//사용 가능한 아이디입니다.
						$('#id_input_re_2').css("display","none");//숨기기
					}else{
						console.log(result);
						$('#id_input_re_2').css("display","inline-block");
						//이미 존재하는 아이디입니다.
						$('#id_input_re_1').css("display","none");
					}
				},//success 종료
		        error:function(){
		            alert("에러입니다");
		        }
		}); //ajax종료
		
	};//아이디 검사 함수 종료
	
	/*인증번호 이메일 전송*/
	$(document).on("click", "#mail_check_button", function(){
		var email = $("#mail_input").val();//입력한 이메일
		var checkBox = $("#mail_check_input");//인증번호 입력란
		var boxWrap = $(".mail_check_input_box");//인증번호 입력란
		
		
		$.ajax({
			type:"GET",
			url:"/member/mailCheck?email="+email,
			success:function(data){
				//console.log("data : "+data);
				checkBox.attr("disabled",false);//인증번호 입력이 가능하게 해줌.
				boxWrap.attr("id", "mail_check_input_box_true");//색상변경
				
				alert(data);
				
				code = data;//MemberController가 보내준 String 인증번호
				
			},//success 종료
	        error:function(){
	        	boxWrap.attr("id", "mail_check_input_box_false");//색상변경
	            alert("다시 인증번호 전송을 눌러주세요.");
	            
	        }
		});
	});
	
	/*인증번호 비교*/
	$(document).on("click","#mail_check_input",function() {
		//인증번호 입력란에 데이터를 입력한 뒤 마우스로 다른 곳을 클릭 시에 실행이 됩니다.
		var inputCode = $("#mail_check_input").val();//사용자 인증번호 입력코드
		console.log(inputCode);
		
		var checkResult = $("#mail_check_input_box_warn");//비교 결과
		
		if(inputCode == code){ //이메일에 보낸 인증번호와 입력한 값 일치할경우
			console.log("맞음");
			checkResult.html("인증번호가 일치합니다.");
			checkResult.attr("class","correct"); //span태그 색상이 변경(초록)
		}else{
			console.log("틀림");
			checkResult.html("인증번호를 다시 확인해주세요.");
			checkResult.attr("class","incorrect"); //span태그 색상이 변경(빨강)
		}
	});
	
	/*다음 주소 api연동*/
	function execution_daum_address() {
		
		new daum.Postcode({
			oncomplete: function(data) {//data매개변수는 팝업창에서 선택한 주소에 대한 정보를 반환받는 객체 변수
				
				//팝업에서 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분
				
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
 
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
 
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    
                    //수정이유)  주소가 입력되는 필드에 추가 항목 필드에 입력될 정보가 함께 입력되도록 만들 것입니다.
        			//(추가)주소변수 문자열과 참고항목 문자열 합치기
        			addr += extraAddr;
		                
                } else {
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //document.getElementById("sample6_extraAddress").value = '';
                    
                    addr +=' ';//추가
                }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                //document.getElementById('sample6_postcode').value = data.zonecode;
                //document.getElementById("sample6_address").value = addr;
               
                $("#address_input_1").val(data.zonecode); //제이쿼리 사용으로 바꿈
                $("#address_input_2").val(addr);	      //제이쿼리 사용으로 바꿈
                
                $("#address_input_3").attr("readonly",false);
                $("#address_input_3").focus();
                
                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById("sample6_detailAddress").focus();
			}
		}).open();
	}
	
function checkPwd(){
	var pw = $('#pw_input').val();
	var pwck = $('#pwck_input').val();
	
	var checkResult = $("#final_pwck_ck");
	
	if(pw == pwck){
		checkResult.text('비밀번호가 일치합니다.');
		console.log("일치");
		checkResult.css('color','green');
	}else{
		checkResult.text('비밀번호를 다시 확인해주세요.');
		console.log("불일치");
		checkResult.css('color','red');
	}
};	
	
</script>

<body>
	<div class="signup-form">
		<form method="post" id="join_form">
			<h2>회원가입</h2>
			<hr>
			
			<!-- 아이디 -->
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> 
							<span class="fa fa-id-badge"></span> <!-- ID 로고 -->
						</span>
					</div>

					<input type="text" class="form-control" id="id_input" name="memberId" oninput="checkId()"
						placeholder="ID" required="required">
					
					<div class="input-group">	
						<span id="id_input_re_1" style="display: none;">사용 가능한 아이디입니다.</span>	
						<span id="id_input_re_2" style="display: none;">아이디가 이미 존재합니다.</span>
					</div>	
				</div>
			</div>

			<!-- 비밀번호 -->
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> 
							<i class="fa fa-lock"></i> <!-- 자물쇠 로고 -->
						</span>
					</div>
					
					<input type="text" class="form-control" id="pw_input" name="memberPw"
						placeholder="Password" required="required">
				</div>
			</div>
		
			<!-- 비밀번호 확인 -->
			<div class="form-group">
				<div class="input-group">
					
					<div class="input-group-prepend">
						<span class="input-group-text"> 
							<i class="fa fa-lock"></i> <!-- 자물쇠 로고 -->
							<i class="fa fa-check"></i><!-- 자물쇠 체크된 로고-->
						</span>
					</div>
					
					<input type="text" class="form-control" id="pwck_input" name="confirm_password" 
						placeholder="비밀번호 확인" required="required"  oninput="checkPwd()">
					
					<div class="input-group">	
						<span id="final_pwck_ck"></span>
					</div>	
						
				</div>
			</div>

			<!-- 이름 -->
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> 
							<span class="fa fa-user"></span> <!-- 사람 로고 -->
						</span>
					</div>

					<input type="text" class="form-control" name="memberName" id="user_input"
						placeholder="Username" required="required">
				</div>
			</div>


			<!-- 이메일 -->
			<div class="form-group">
				<!-- 이부분이  이메일 로고 -->
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> 
							<i class="fa fa-paper-plane"></i> <!-- 이메일(종이비행기) 로고 -->
						</span>
					</div>


					<input type="email" class="form-control" name="memberMail" id="mail_input"
						placeholder="Email Address" required="required">
				</div>

				<div class="input-group" id="mail_check_wrap">
					<br> 
					<div  class="mail_check_input_box" id="mail_check_input_box_false">
						<input type="text" name="인증번호" 
							id="mail_check_input"
							class="form-control" 
							placeholder="인증번호 입력" required="required" disabled="disabled">
					</div>
					
					<div>
						<button type="button" class="btn btn-primary btn-lg" id="mail_check_button">
							인증번호전송
						</button>
					</div>
					
					<div class="clearfix"></div>
                    <span id="mail_check_input_box_warn"></span>
					<!-- 이메일 인증번호 추가 end -->
				</div>
			</div>
			<!-- 여기까지 이메일 -->


			<!-- 주소 -->
			<div class="input-group">
				<!-- 주소 추가 -->
				<br> 
					<input type="text" name="memberAddr1" id="address_input_1" class="form-control" placeholder="우편번호" required="required" readonly="readonly">
					
					<button type="button" class="btn btn-primary btn-lg" onclick="execution_daum_address()">
						주소찾기
					</button>
				<!-- 이메일 인증번호 추가 end -->

				<div class="input-group">
					<input type="text" class="form-control" name="memberAddr2" id="address_input_2" placeholder="도로명 주소" required="required" readonly="readonly">
				</div>		
						
				<div class="input-group">
					<input type="text" class="form-control" name="memberAddr3" id="address_input_3" placeholder="상세 주소" required="required" readonly="readonly">
				</div>		
				
				</div><!-- 주소안 폼 -->
				

				<!-- 주소 추가 end -->

				<div class="form-group">
					<!-- 이용약관에 동의 -->
					<label class="form-check-label"> 
						<input type="checkbox" required="required"> 이용약관 및 개인정보 보호정책에 동의합니다.
					</label>
				</div>

				<div class="form-group" align="center">
					<button type="submit" class="btn btn-primary btn-lg" id="join_button">
						Sign Up
					</button>
				</div>
		</form>

		<div class="text-center">
			<a href="/main">메인화면</a>
		</div>
	</div> <!-- 주소 큰틀 -->
</body>
</html>