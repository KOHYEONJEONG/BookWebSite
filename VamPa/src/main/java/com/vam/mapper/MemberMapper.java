package com.vam.mapper;

import com.vam.model.MemberVO;

public interface MemberMapper {//DAO �������̽�
	
	//MemberMapper.java �����
	
	public void memberJoin(MemberVO member);//ȸ������
	public int idCheck(String memberId);    //���̵� �ߺ� �˻�
	public MemberVO memberLogin(MemberVO member);//�α���
	
}
