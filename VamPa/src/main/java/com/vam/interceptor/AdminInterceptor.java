package com.vam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.vam.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor{

	/*servelt-context.xml에 인터셉터 빈 생성함*/
	
	/*
	  관리자 메서드("/admin/**")에 접근하는 사용자의 admiCk 1인지 확인하는 작업이 핵심입니다.
	   따라서 "member"session정보를 MemberVO타입의 변수에 담은 후, 
	  해당 변수를 통해 admiCk의 값을 호출하여 비교하는 로직을 작성해주어야 합니다. 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		MemberVO loginVO = (MemberVO)session.getAttribute("member");//형변환
		
		if(loginVO == null || loginVO.getAdminCk() == 0) {
			response.sendRedirect("/main");//관리자가 아닌 일반회원일 시 
		}
		
		return true;//관리자가 맞는경우, loginVO.getAdminCk() == 1
	}
}
