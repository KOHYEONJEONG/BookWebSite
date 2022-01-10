package com.vam.service;

import com.vam.model.MemberVO;

public interface MemberService {
	//�곌껐怨�由�
	//MemberMapper.xml
	//MemberMapper.java
	
	
	public void memberJoin(MemberVO member) throws Exception;

	public int idCheck(String memberId) throws Exception;//ajax
	
	public MemberVO memberLogin(MemberVO member) throws Exception;

	
}
