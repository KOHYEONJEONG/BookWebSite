package com.vam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/*servelt-context.xml에 인터셉터 빈 생성함*/	

/*
  일어날 확률은 매우 낮지만 이전 작업 중
   세션이 완전히 제거되지 않아 로그인을 위해 
   새로운 세션을 저장할 때 발생할 수 있는 에러를 방지하기 위해서
    로그인 메서드가 있는 MemberController.java에
   진입하기 전 세션을 제거하는 작업을 해주고자 합니다.
   
   +
   관리자 페이지 url을 알아도 못들어가게 하기위해서 만든 페이지(메인화면으로 이동되게)
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {// 세션을 제거해주는 코드를 추가
		
		System.out.println("LoginInterceptor preHandle 적용");
		
		HttpSession session = request.getSession();
		session.invalidate();//세션종료
		
		return true;
	}
}
