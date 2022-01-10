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
@RequestMapping(value="/member")//MemberController 사용을 명확히 구분해주기 위해서
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private JavaMailSender mailSender; 
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;//비밀번호 인코딩, security-context.xml에 빈등록함
	
	//회원가입 페이지 이동
	@RequestMapping(value="/joinPage", method=RequestMethod.GET)
	public String joinPage() {
		logger.info("joinPage 진입");
		return "member/JoinPage";
	}
	
	//회원가입 성공 (js에서 action에 /member/join으로 넘김)
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO member) throws Exception {
		//view에 name을 VO에 입력한 필드와 맞췄음.
		
		String rawPw = "";//인코딩 전 비밀번호
		String encodePw = "";//인코딩 후 비밀번호
		
		rawPw = member.getMemberPw(); //비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw);//비밀번호 인코딩(보안)
		member.setMemberPw(encodePw);//인코딩된 비밀번호 member객체에 다시 저장
		
		//logger.info("join 진입");
		memberservice.memberJoin(member);
		//logger.info("join service 성공");
		
		return "redirect:/main";
	}
	
	//로그인은 모달(페이지 이동 없음)
	
	//아이디 중복체크
	@RequestMapping(value="/memberIdChk", method=RequestMethod.POST)
	@ResponseBody//중요(응답)
	public int memberIdChkPOST(String memberId) throws Exception{

		logger.info("memberIdChk() 집입");//수신받는지 확인

		int result = memberservice.idCheck(memberId);
		
		logger.info("결과값 = "+result);//수신받는지 확인
		
		return result;
		
	}
	
	/*이메일 인증번호 전송(주석처리 풀면 됨)*/
	@RequestMapping(value = "/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		//root-context.xml과
		//pom.xml에 의존성주입함
		//35,36줄 포함(의존성주입)
		
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : "+email);
		
		/*인증번호(난수) 생성*/
		Random random = new Random();
		
		//- 111111 ~ 999999 범위
		int checkNum = random.nextInt(888888)+111111;
		logger.info("인증번호 : "+checkNum);
		
		/*이제 인증번호를 메일로 보내기*/
		/*
		String setFrom = "nanabi08@naver.com";//root-context.xml과 맞추기
		String toMail = email;//view에 입력한 메일
		String title="회원가입 인증 이메일입니다."; //이메일 제목
		String content = //이메일 내용
				"홈페이지를 방문해주셔서 감사합니다."+
				"<br><br>"+
				"인증 번호는 "+checkNum+"입니다"+
				"<br>"+
				"해당 인증번호를 인증번호 확인란에 기입해주세요";
		
		// 주석처리 이유 : 메일 지저분해지니까 확인만 한것.(정상작동함)
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
	
	/*로그인-모달창-ajax*/
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public int loginPOST(HttpServletRequest request, MemberVO member, Model model ) throws Exception {
		//아이디, 비밀번호가 존재할 경우 memberId, memberName, adminCk, money, point
		//데이터가 담긴 MemberVO 객체가 저장되게 됩니다. 
		
		HttpSession session = request.getSession();
		String rawPw="";//입력한 password
		String encodePw="";//인코딩된 password
		
		MemberVO loginVO = memberservice.memberLogin(member);//입력한 id와 일치하는 지
		
		int result = 1;
		
		if(loginVO == null) { //일치하는 아이디 존재 x
			result = 0;
			return result;//0(로그인실패)
		}else {
			//일치하는 아이디가 존재 시
			rawPw = member.getMemberPw();     //사용자가 제출한 비밀번호(view)
			encodePw = loginVO.getMemberPw(); //데이터베이스에 저장한 인코딩된 비밀번호(db)
			
			if(true == pwEncoder.matches(rawPw, encodePw)) {//인코딩된 비번 변환해서 비교
				//비밀번호 일치여부 판단
				loginVO.setMemberPw("");//인코딩된 비밀번 정보 지움(session에 pw인코딩을 저장 안하려고)
				//System.out.println("admin확인 : "+loginVO.getAdminCk());
				
				session.setAttribute("member", loginVO);//사용자 정보를 session에 저장
				return result;//1(로그인 성공)
				
			}else {
				result = 0;
				return result;//0(로그인 실패)
			}
		}
	}
	
	/*비동기,메인페이지 로그아웃*/
	@RequestMapping(value="/logout")
	@ResponseBody
	public void logoutMainGET(HttpServletRequest request)throws Exception{
		
		logger.info("로그아웃메서드 진입");
		HttpSession session = request.getSession();
		
		session.invalidate();//세션삭제
		
	}
	
}
