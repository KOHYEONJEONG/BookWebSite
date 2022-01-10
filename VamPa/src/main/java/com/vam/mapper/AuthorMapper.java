package com.vam.mapper;

import java.util.ArrayList;
import java.util.List;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

public interface AuthorMapper {
	
	/*작가 등록*/
	public void authorEnroll(AuthorVO author);
	
	/*작가 목록*/
	public ArrayList<AuthorVO> authorGetList(Criteria cri);
	
	/*작가 총수*/
	/* 조건문에 사용할 'keyword' 데이터를 전달받기 위해 파라미터로 Criteria 클래스를 부여*/
	public int authorGetTotal(Criteria cri); 
	
	/*작가 상세 페이지 쿼리(Mapper,Service 메서드)*/
	public AuthorVO authorGetDetail(int authorId);
}
