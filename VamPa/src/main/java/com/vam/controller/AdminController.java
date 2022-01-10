package com.vam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;
import com.vam.model.PageDTO;
import com.vam.service.AuthorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping("/main")
	public String mainPage() {
		
		return "/admin/main";
	}
	
	
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsManage", method = RequestMethod.GET)
    public String goodsManageGET(Model model) throws Exception{
        logger.info("상품 관리 페이지 접속");
        String target = "goodsManage";
        model.addAttribute("target", target);
        return "redirect:/admin/main";
        
    }
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public String goodsEnrollGET(Model model) throws Exception{
        logger.info("상품 등록 페이지 접속");
        String target = "goodsEnroll";
        model.addAttribute("target", target);
        return "redirect:/admin/main";
    }
    
    
    @RequestMapping(value = "authorEnrollPage", method = RequestMethod.GET)
    public String authorEnrollPage(Model model) throws Exception{
    	//작가등록 페이지이동
    	String target = "authorEnroll";
    	logger.info("작가등록 페이지");
        model.addAttribute("target", target);
    	return "redirect:/admin/main"; 
    }
    
    /* 작가 등록 페이지 접속 */
    @RequestMapping(value = "authorEnroll", method = RequestMethod.POST)
    public String authorEnrollPOST(Model model, AuthorVO author,RedirectAttributes rttr) throws Exception{
    	
        logger.info("authorEnrollPOST : "+author);
        authorService.authorEnroll(author);//작가 등록 쿼리 수행
        logger.info("확인중");
        
        rttr.addFlashAttribute("enroll_result", author.getAuthorName());//등록 성공 메시지(작가이름) , 일회성
        
        return "redirect:/admin/main";
    }
    
    /* 작가 관리 페이지 접속 */
    @RequestMapping(value = "authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri,Model model) throws Exception{
    	//RedirectAttributes rttr
        logger.info("작가 관리 페이지 접속 ..... "+cri);//1번 페이지에 10개 헹  
       
        //String target = "authorManage";
       //model.addAttribute("target", target);//url에 보여짐
        
        ArrayList<AuthorVO> list= authorService.authorGetList(cri);
     
		  for(int i =0; i<list.size(); i++) {
			  System.out.println(list.get(i).getAuthorId());
			  System.out.println(list.get(i).getAuthorName());
		  	  System.out.println(list.get(i).getNationName());
		  	  System.out.println(list.get(i).getRegDate());
		  	  System.out.println(list.get(i).getUpdateDate());
		  }
		  model.addAttribute("dto", list);//url에 안보임, addAttribute로 바꾸면 안되더라?
	      logger.info("작가 관리 페이지 list 전송");
		  
	      if(!list.isEmpty()) {
	    	  //작가 검색 후 존재하는 경우
	    	  model.addAttribute("list", list);
	      }
	      else {
	    	//작가 검색했는데 없는 경우
	    	  model.addAttribute("listCheck", "empty");
	      }
	      
		  /*페이지 이동 인터페이스 데이터*/
	      int total = authorService.authorGetTotal(cri);
	      logger.info("작가 관리 페이지 total="+total); //성공
	    
	      /*페이징*/
		  PageDTO pageMaker = new PageDTO(cri, total);
		  model.addAttribute("pageMaker",pageMaker);
		  // 간편=> model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
	      logger.info("작가 관리 페이지 pageMaker 전송 성공");//실패 ㅠ 
		  
    } 
    
    
    @RequestMapping(value = "/authorDetail", method = RequestMethod.GET)
    public void authorManagePage(int authorId, Criteria cri, Model model) throws Exception{
    	//작가 상세 페이지 이동
    	/*작가 상세 페이제에는 2가지의 정보를 넘겨 주기 위해서 
    	 * int타입인 authorId 변수와 AuthorVO클래스를 파라미터로 부여하였습니다.*/
    	logger.info("작가 상세 페이지 이동");
    	
    	/* # 작가 관리 페이지 정보
    	 *  Criteria는 '작가 작가 상세 페이지(authorDetail.jsp)'에 이동하기 전 
    	 * '작가 관리 페이지(authorManage.jsp)'의 정보입니다. 
    	 *  작가 관리 페이지에 대한 정보를 추가해주는 이유는 '작가 상세 페이지'에서 '작가 관리 페이지'로 
    	 *  다시 이동할 때 기존의 '작가 관리 페이지'로 이동하기 위함입니다. 
    	 *  만약 해당 정보가 없다면 무조건 1페이지의 '작가 관리 페이지'로 이동하게 될 것입니다.
    	 */
    	model.addAttribute("cri", cri);
    	
    	/*선택된 작가 정보*/
    	model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
    	
    	
    	
    }
}
