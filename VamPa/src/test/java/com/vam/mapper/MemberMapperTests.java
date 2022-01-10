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

	// ��� ���� �׽�Ʈ

	@Autowired
	private MemberMapper membermapper; // MemberMapper.java �������̽� ������ ����

	
	
	/*�α��� Ȯ��
	 * @Test
	 * public void memberLogin() throws Exception{ MemberVO member = new
	 * MemberVO();
	 * 
	 * �ùٸ� ���̵� ��� �Է°�� 
	 * member.setMemberId("test"); 
	 * member.setMemberPw("test");
	 * 
	 * membermapper.memberLogin(member);
	 * System.out.println("����� : "+ * membermapper.memberLogin(member));
	 * }
	 */

	/*id �ߺ�üũ
	 * @Test
	 * public void memberIdChk() throws Exception{ String id="admin"; //�����ϴ�
	 * ���̵� String id2 = "test123";
	 * 
	 * membermapper.idCheck(id); membermapper.idCheck(id2); }
	 */

	/*ȸ������
	 * @Test
	 * public void memeberJoin() throws Exception{
	 * 
	 * MemberVO member = new MemberVO();
	 * 
	 * member.setMemberId("sprint_test");
	 * member.setMemberPw("spring_test");
	 * member.setMemberName("spring_test");
	 * member.setMemberMail("spring_test");
	 * member.setMemberAddr1("spring_test");//�����ּ�
	 * member.setMemberAddr2("spring_test");
	 * member.setMemberAddr3("spring_test");
	 * 
	 * membermapper.memberJoin(member);
	 * 
	 * }
	 */

}
