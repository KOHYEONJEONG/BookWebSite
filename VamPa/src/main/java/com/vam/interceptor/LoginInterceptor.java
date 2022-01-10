package com.vam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/*servelt-context.xml�� ���ͼ��� �� ������*/	

/*
  �Ͼ Ȯ���� �ſ� ������ ���� �۾� ��
   ������ ������ ���ŵ��� �ʾ� �α����� ���� 
   ���ο� ������ ������ �� �߻��� �� �ִ� ������ �����ϱ� ���ؼ�
    �α��� �޼��尡 �ִ� MemberController.java��
   �����ϱ� �� ������ �����ϴ� �۾��� ���ְ��� �մϴ�.
   
   +
   ������ ������ url�� �˾Ƶ� ������ �ϱ����ؼ� ���� ������(����ȭ������ �̵��ǰ�)
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {// ������ �������ִ� �ڵ带 �߰�
		
		System.out.println("LoginInterceptor preHandle ����");
		
		HttpSession session = request.getSession();
		session.invalidate();//��������
		
		return true;
	}
}
