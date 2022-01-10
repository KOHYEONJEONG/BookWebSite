package com.vam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.mapper.MemberMapper;
import com.vam.model.MemberVO;

@Service //�߿���!!! ������ ���� �߸� �̰� ������ �׷�
public class MemberServiceImp implements MemberService {

	//MemberService�� ������ �޼ҵ� ����
	//src/main/resources/com/vam/mapper/MemberMapper.xml�� �Է��� SQL�� ID
	
	@Autowired
	MemberMapper membermapper; //root-context.xml�� ����� bean
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		//ȸ������
		membermapper.memberJoin(member);
	}

	@Override
	public int idCheck(String memberId) throws Exception  {
		//���̵� �ߺ�üũ
		return membermapper.idCheck(memberId);
	}

	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		// �α���
		return membermapper.memberLogin(member);
	}
	
	

}
