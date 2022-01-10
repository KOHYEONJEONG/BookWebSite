package com.vam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.mapper.MemberMapper;
import com.vam.model.MemberVO;

@Service //중요함!!! 의존성 오류 뜨면 이거 빠져서 그럼
public class MemberServiceImp implements MemberService {

	//MemberService에 정의한 메소드 구현
	//src/main/resources/com/vam/mapper/MemberMapper.xml에 입력한 SQL문 ID
	
	@Autowired
	MemberMapper membermapper; //root-context.xml에 등록한 bean
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		//회원가입
		membermapper.memberJoin(member);
	}

	@Override
	public int idCheck(String memberId) throws Exception  {
		//아이디 중복체크
		return membermapper.idCheck(memberId);
	}

	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		// 로그인
		return membermapper.memberLogin(member);
	}
	
	

}
