package com.vam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

public interface AuthorService {
	
	/*작가등록 메서드*/
	public void authorEnroll(AuthorVO author) throws Exception;

	/*작가 목록 + 페이징처리*/
	//com.vam.model.AuthorVO
	public ArrayList<AuthorVO> authorGetList(Criteria cri) throws Exception;
	
	/*작가 총수 + 페이징 처리*/
	public int authorGetTotal(Criteria cri)  throws Exception;
	
	/*작가 상세 페이지*/
	public AuthorVO authorGetDetail(int authorId) throws Exception;
	
}
