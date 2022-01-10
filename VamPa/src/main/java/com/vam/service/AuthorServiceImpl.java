package com.vam.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.vam.mapper.AuthorMapper;
import com.vam.model.AuthorVO;
import com.vam.model.Criteria;

@Service
public class AuthorServiceImpl implements AuthorService {

	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	AuthorMapper authorMapper; //DAO�������̽� ��������
	
	/*�۰� ���*/
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		authorMapper.authorEnroll(author);
	}

	/*�۰� ���*/
	@Override
	public ArrayList<AuthorVO> authorGetList(Criteria cri) throws Exception {
		return authorMapper.authorGetList(cri);
	}

	/*�۰� �Ѽ�*/
	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		log.info("serviceImpl_authorGetTotal()...."+cri);
		return authorMapper.authorGetTotal(cri);
	}

	/*�۰� �� ������*/
	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		log.info("authorGetDetail......"+authorId);
		return authorMapper.authorGetDetail(authorId);
	}

}
