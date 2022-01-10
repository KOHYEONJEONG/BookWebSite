package com.vam.mapper;

import java.util.ArrayList;
import java.util.List;

import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

public interface AuthorMapper {
	
	/*�۰� ���*/
	public void authorEnroll(AuthorVO author);
	
	/*�۰� ���*/
	public ArrayList<AuthorVO> authorGetList(Criteria cri);
	
	/*�۰� �Ѽ�*/
	/* ���ǹ��� ����� 'keyword' �����͸� ���޹ޱ� ���� �Ķ���ͷ� Criteria Ŭ������ �ο�*/
	public int authorGetTotal(Criteria cri); 
	
	/*�۰� �� ������ ����(Mapper,Service �޼���)*/
	public AuthorVO authorGetDetail(int authorId);
}
