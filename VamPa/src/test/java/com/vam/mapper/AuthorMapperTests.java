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

	// 디비 연동 테스트

	@Autowired
	private AuthorService service;//인터페이스(30라인이 성공하면 이걸로 바꾸기)
	
	@Autowired
	private AuthorMapper mapper;//인터페이스(27라인 전에 테스트하려고)
	
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
	 * cri.setKeyword("현정");
	 * int total = service.authorGetTotal(cri);
	 * System.out.println("total......."+total); }
	 */
	
	/*
	 * //작가 목록
	 * 
	 * @Test public void authorGetListTest() throws Exception{
	 * 
	 * Criteria cri = new Criteria(1,10); ArrayList<AuthorVO> list =
	 * service.authorGetList(cri);
	 * 
	 * for(int i =0; i<list.size(); i++) {
	 * System.out.println("list"+i+"......."+list.get(i)); } }
	 */
	
	/*작가 등록
	 * @Test 
	 * public void authorEnroll() throws Exception{
	 * 
	 * AuthorVO author = new AuthorVO();
	 * 
	 * author.setAuthorId(5); author.setNationId("01"); author.setAuthorName("테스트");
	 * author.setAuthorIntro("테스트 소개");
	 * 
	 * service.authorEnroll(author); }
	 */
	
	

}
