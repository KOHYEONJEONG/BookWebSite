package com.vam.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;
import com.vam.service.AuthorService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTests {

	// ��� ���� �׽�Ʈ

	@Autowired
	private AuthorService service;//�������̽�(30������ �����ϸ� �̰ɷ� �ٲٱ�)
	
	@Autowired
	private AuthorMapper mapper;//�������̽�(27���� ���� �׽�Ʈ�Ϸ���)
	
	@Test 
	public void authorGetDetatilTest() throws Exception {
		int authorId= 30;
		AuthorVO author = service.authorGetDetail(authorId);
		
		System.out.println("author....."+author);
	}
	
	
	/*
	 * @Test 
	 * public void authorGetTotalTest() throws Exception{ Criteria cri = new
	 * Criteria(); 
	 * cri.setKeyword("����");
	 * int total = service.authorGetTotal(cri);
	 * System.out.println("total......."+total); }
	 */
	
	/*
	 * //�۰� ���
	 * 
	 * @Test public void authorGetListTest() throws Exception{
	 * 
	 * Criteria cri = new Criteria(1,10); ArrayList<AuthorVO> list =
	 * service.authorGetList(cri);
	 * 
	 * for(int i =0; i<list.size(); i++) {
	 * System.out.println("list"+i+"......."+list.get(i)); } }
	 */
	
	/*�۰� ���
	 * @Test 
	 * public void authorEnroll() throws Exception{
	 * 
	 * AuthorVO author = new AuthorVO();
	 * 
	 * author.setAuthorId(5); author.setNationId("01"); author.setAuthorName("�׽�Ʈ");
	 * author.setAuthorIntro("�׽�Ʈ �Ұ�");
	 * 
	 * service.authorEnroll(author); }
	 */
	
	

}
