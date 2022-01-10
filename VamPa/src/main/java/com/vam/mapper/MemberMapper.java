package com.vam.mapper;

import com.vam.model.MemberVO;

public interface MemberMapper {//DAO 인터페이스
	
	//MemberMapper.java 연결고리
	
	public void memberJoin(MemberVO member);//회원가입
	public int idCheck(String memberId);    //아이디 중복 검사
	public MemberVO memberLogin(MemberVO member);//로그인
	
}
