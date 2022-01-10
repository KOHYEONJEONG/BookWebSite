package com.vam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.vam.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor{

	/*servelt-context.xml�� ���ͼ��� �� ������*/
	
	/*
	  ������ �޼���("/admin/**")�� �����ϴ� ������� admiCk 1���� Ȯ���ϴ� �۾��� �ٽ��Դϴ�.
	   ���� "member"session������ MemberVOŸ���� ������ ���� ��, 
	  �ش� ������ ���� admiCk�� ���� ȣ���Ͽ� ���ϴ� ������ �ۼ����־�� �մϴ�. 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		MemberVO loginVO = (MemberVO)session.getAttribute("member");//����ȯ
		
		if(loginVO == null || loginVO.getAdminCk() == 0) {
			response.sendRedirect("/main");//�����ڰ� �ƴ� �Ϲ�ȸ���� �� 
		}
		
		return true;//�����ڰ� �´°��, loginVO.getAdminCk() == 1
	}
}
