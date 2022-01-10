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
	
	
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "goodsManage", method = RequestMethod.GET)
    public String goodsManageGET(Model model) throws Exception{
        logger.info("��ǰ ���� ������ ����");
        String target = "goodsManage";
        model.addAttribute("target", target);
        return "redirect:/admin/main";
        
    }
    
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public String goodsEnrollGET(Model model) throws Exception{
        logger.info("��ǰ ��� ������ ����");
        String target = "goodsEnroll";
        model.addAttribute("target", target);
        return "redirect:/admin/main";
    }
    
    
    @RequestMapping(value = "authorEnrollPage", method = RequestMethod.GET)
    public String authorEnrollPage(Model model) throws Exception{
    	//�۰���� �������̵�
    	String target = "authorEnroll";
    	logger.info("�۰���� ������");
        model.addAttribute("target", target);
    	return "redirect:/admin/main"; 
    }
    
    /* �۰� ��� ������ ���� */
    @RequestMapping(value = "authorEnroll", method = RequestMethod.POST)
    public String authorEnrollPOST(Model model, AuthorVO author,RedirectAttributes rttr) throws Exception{
    	
        logger.info("authorEnrollPOST : "+author);
        authorService.authorEnroll(author);//�۰� ��� ���� ����
        logger.info("Ȯ����");
        
        rttr.addFlashAttribute("enroll_result", author.getAuthorName());//��� ���� �޽���(�۰��̸�) , ��ȸ��
        
        return "redirect:/admin/main";
    }
    
    /* �۰� ���� ������ ���� */
    @RequestMapping(value = "authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri,Model model) throws Exception{
    	//RedirectAttributes rttr
        logger.info("�۰� ���� ������ ���� ..... "+cri);//1�� �������� 10�� ��  
       
        //String target = "authorManage";
       //model.addAttribute("target", target);//url�� ������
        
        ArrayList<AuthorVO> list= authorService.authorGetList(cri);
     
		  for(int i =0; i<list.size(); i++) {
			  System.out.println(list.get(i).getAuthorId());
			  System.out.println(list.get(i).getAuthorName());
		  	  System.out.println(list.get(i).getNationName());
		  	  System.out.println(list.get(i).getRegDate());
		  	  System.out.println(list.get(i).getUpdateDate());
		  }
		  model.addAttribute("dto", list);//url�� �Ⱥ���, addAttribute�� �ٲٸ� �ȵǴ���?
	      logger.info("�۰� ���� ������ list ����");
		  
	      if(!list.isEmpty()) {
	    	  //�۰� �˻� �� �����ϴ� ���
	    	  model.addAttribute("list", list);
	      }
	      else {
	    	//�۰� �˻��ߴµ� ���� ���
	    	  model.addAttribute("listCheck", "empty");
	      }
	      
		  /*������ �̵� �������̽� ������*/
	      int total = authorService.authorGetTotal(cri);
	      logger.info("�۰� ���� ������ total="+total); //����
	    
	      /*����¡*/
		  PageDTO pageMaker = new PageDTO(cri, total);
		  model.addAttribute("pageMaker",pageMaker);
		  // ����=> model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
	      logger.info("�۰� ���� ������ pageMaker ���� ����");//���� �� 
		  
    } 
    
    
    @RequestMapping(value = "/authorDetail", method = RequestMethod.GET)
    public void authorManagePage(int authorId, Criteria cri, Model model) throws Exception{
    	//�۰� �� ������ �̵�
    	/*�۰� �� ���������� 2������ ������ �Ѱ� �ֱ� ���ؼ� 
    	 * intŸ���� authorId ������ AuthorVOŬ������ �Ķ���ͷ� �ο��Ͽ����ϴ�.*/
    	logger.info("�۰� �� ������ �̵�");
    	
    	/* # �۰� ���� ������ ����
    	 *  Criteria�� '�۰� �۰� �� ������(authorDetail.jsp)'�� �̵��ϱ� �� 
    	 * '�۰� ���� ������(authorManage.jsp)'�� �����Դϴ�. 
    	 *  �۰� ���� �������� ���� ������ �߰����ִ� ������ '�۰� �� ������'���� '�۰� ���� ������'�� 
    	 *  �ٽ� �̵��� �� ������ '�۰� ���� ������'�� �̵��ϱ� �����Դϴ�. 
    	 *  ���� �ش� ������ ���ٸ� ������ 1�������� '�۰� ���� ������'�� �̵��ϰ� �� ���Դϴ�.
    	 */
    	model.addAttribute("cri", cri);
    	
    	/*���õ� �۰� ����*/
    	model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
    	
    	
    	
    }
}
