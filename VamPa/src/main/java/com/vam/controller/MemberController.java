package com.vam.controller;

import java.util.Random;


import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.MemberVO;
import com.vam.service.MemberService;


@Controller
@RequestMapping(value="/member")//MemberController ����� ��Ȯ�� �������ֱ� ���ؼ�
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private JavaMailSender mailSender; 
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;//��й�ȣ ���ڵ�, security-context.xml�� ������
	
	//ȸ������ ������ �̵�
	@RequestMapping(value="/joinPage", method=RequestMethod.GET)
	public String joinPage() {
		logger.info("joinPage ����");
		return "member/JoinPage";
	}
	
	//ȸ������ ���� (js���� action�� /member/join���� �ѱ�)
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO member) throws Exception {
		//view�� name�� VO�� �Է��� �ʵ�� ������.
		
		String rawPw = "";//���ڵ� �� ��й�ȣ
		String encodePw = "";//���ڵ� �� ��й�ȣ
		
		rawPw = member.getMemberPw(); //��й�ȣ ������ ����
		encodePw = pwEncoder.encode(rawPw);//��й�ȣ ���ڵ�(����)
		member.setMemberPw(encodePw);//���ڵ��� ��й�ȣ member��ü�� �ٽ� ����
		
		//logger.info("join ����");
		memberservice.memberJoin(member);
		//logger.info("join service ����");
		
		return "redirect:/main";
	}
	
	//�α����� ���(������ �̵� ����)
	
	//���̵� �ߺ�üũ
	@RequestMapping(value="/memberIdChk", method=RequestMethod.POST)
	@ResponseBody//�߿�(����)
	public int memberIdChkPOST(String memberId) throws Exception{

		logger.info("memberIdChk() ����");//���Ź޴��� Ȯ��

		int result = memberservice.idCheck(memberId);
		
		logger.info("����� = "+result);//���Ź޴��� Ȯ��
		
		return result;
		
	}
	
	/*�̸��� ������ȣ ����(�ּ�ó�� Ǯ�� ��)*/
	@RequestMapping(value = "/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		//root-context.xml��
		//pom.xml�� ������������
		//35,36�� ����(����������)
		
		logger.info("�̸��� ������ ���� Ȯ��");
		logger.info("������ȣ : "+email);
		
		/*������ȣ(����) ����*/
		Random random = new Random();
		
		//- 111111 ~ 999999 ����
		int checkNum = random.nextInt(888888)+111111;
		logger.info("������ȣ : "+checkNum);
		
		/*���� ������ȣ�� ���Ϸ� ������*/
		/*
		String setFrom = "nanabi08@naver.com";//root-context.xml�� ���߱�
		String toMail = email;//view�� �Է��� ����
		String title="ȸ������ ���� �̸����Դϴ�."; //�̸��� ����
		String content = //�̸��� ����
				"Ȩ�������� �湮���ּż� �����մϴ�."+
				"<br><br>"+
				"���� ��ȣ�� "+checkNum+"�Դϴ�"+
				"<br>"+
				"�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �������ּ���";
		
		// �ּ�ó�� ���� : ���� �����������ϱ� Ȯ�θ� �Ѱ�.(�����۵���)
		 * try {
		 * 
		 * MimeMessage message = mailSender.createMimeMessage(); MimeMessageHelper
		 * helper = new MimeMessageHelper(message, true, "utf-8");
		 * helper.setFrom(setFrom); helper.setTo(toMail); helper.setSubject(title);
		 * helper.setText(content,true); mailSender.send(message);
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }
		 */
		 
		 String num =Integer.toString(checkNum);
		 return num;
		
	}
	
	/*�α���-���â-ajax*/
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public int loginPOST(HttpServletRequest request, MemberVO member, Model model ) throws Exception {
		//���̵�, ��й�ȣ�� ������ ��� memberId, memberName, adminCk, money, point
		//�����Ͱ� ��� MemberVO ��ü�� ����ǰ� �˴ϴ�. 
		
		HttpSession session = request.getSession();
		String rawPw="";//�Է��� password
		String encodePw="";//���ڵ��� password
		
		MemberVO loginVO = memberservice.memberLogin(member);//�Է��� id�� ��ġ�ϴ� ��
		
		int result = 1;
		
		if(loginVO == null) { //��ġ�ϴ� ���̵� ���� x
			result = 0;
			return result;//0(�α��ν���)
		}else {
			//��ġ�ϴ� ���̵� ���� ��
			rawPw = member.getMemberPw();     //����ڰ� ������ ��й�ȣ(view)
			encodePw = loginVO.getMemberPw(); //�����ͺ��̽��� ������ ���ڵ��� ��й�ȣ(db)
			
			if(true == pwEncoder.matches(rawPw, encodePw)) {//���ڵ��� ��� ��ȯ�ؼ� ��
				//��й�ȣ ��ġ���� �Ǵ�
				loginVO.setMemberPw("");//���ڵ��� ��й� ���� ����(session�� pw���ڵ��� ���� ���Ϸ���)
				//System.out.println("adminȮ�� : "+loginVO.getAdminCk());
				
				session.setAttribute("member", loginVO);//����� ������ session�� ����
				return result;//1(�α��� ����)
				
			}else {
				result = 0;
				return result;//0(�α��� ����)
			}
		}
	}
	
	/*�񵿱�,���������� �α׾ƿ�*/
	@RequestMapping(value="/logout")
	@ResponseBody
	public void logoutMainGET(HttpServletRequest request)throws Exception{
		
		logger.info("�α׾ƿ��޼��� ����");
		HttpSession session = request.getSession();
		
		session.invalidate();//���ǻ���
		
	}
	
}
