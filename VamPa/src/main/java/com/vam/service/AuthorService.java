package com.vam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

public interface AuthorService {
	
	/*�۰���� �޼���*/
	public void authorEnroll(AuthorVO author) throws Exception;

	/*�۰� ��� + ����¡ó��*/
	//com.vam.model.AuthorVO
	public ArrayList<AuthorVO> authorGetList(Criteria cri) throws Exception;
	
	/*�۰� �Ѽ� + ����¡ ó��*/
	public int authorGetTotal(Criteria cri)  throws Exception;
	
	/*�۰� �� ������*/
	public AuthorVO authorGetDetail(int authorId) throws Exception;
	
}
