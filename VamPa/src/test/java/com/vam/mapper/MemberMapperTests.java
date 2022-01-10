package com.vam.mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;

import com.vam.model.MemberVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	// 디비 연동 테스트

	@Autowired
	private MemberMapper membermapper; // MemberMapper.java 인테페이스 의존성 주입

	
	
	/*로그인 확인
	 * @Test
	 * public void memberLogin() throws Exception{ MemberVO member = new
	 * MemberVO();
	 * 
	 * 올바른 아이디 비번 입력경우 
	 * member.setMemberId("test"); 
	 * member.setMemberPw("test");
	 * 
	 * membermapper.memberLogin(member);
	 * System.out.println("결과값 : "+ * membermapper.memberLogin(member));
	 * }
	 */

	/*id 중복체크
	 * @Test
	 * public void memberIdChk() throws Exception{ String id="admin"; //존재하는
	 * 아이디 String id2 = "test123";
	 * 
	 * membermapper.idCheck(id); membermapper.idCheck(id2); }
	 */

	/*회원가입
	 * @Test
	 * public void memeberJoin() throws Exception{
	 * 
	 * MemberVO member = new MemberVO();
	 * 
	 * member.setMemberId("sprint_test");
	 * member.setMemberPw("spring_test");
	 * member.setMemberName("spring_test");
	 * member.setMemberMail("spring_test");
	 * member.setMemberAddr1("spring_test");//우편주소
	 * member.setMemberAddr2("spring_test");
	 * member.setMemberAddr3("spring_test");
	 * 
	 * membermapper.memberJoin(member);
	 * 
	 * }
	 */

}
