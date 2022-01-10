package com.vam.model;

public class MemberVO {
	//mysql�� sysdate()�� ��¥�� �־����.
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberMail;
	private String memberAddr1;//�����ּ�
	private String memberAddr2;//ȸ���ּ�
	private String memberAddr3;//ȸ�����ּ�
	private int adminCk;//������ ����(0:�Ϲݻ����, 1:������)
	private int regDate;//�������(���Գ�¥)
	private int money; //ȸ����
	private int point;//ȸ�� ����Ʈ
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(String memberId, String memberPw, String memberName, String memberMail, String memberAddr1,
			String memberAddr2, String memberAddr3, int adminCk, int regDate, int money, int point) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberMail = memberMail;
		this.memberAddr1 = memberAddr1;
		this.memberAddr2 = memberAddr2;
		this.memberAddr3 = memberAddr3;
		this.adminCk = adminCk;
		this.regDate = regDate;
		this.money = money;
		this.point = point;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public String getMemberAddr1() {
		return memberAddr1;
	}
	public void setMemberAddr1(String memberAddr1) {
		this.memberAddr1 = memberAddr1;
	}
	public String getMemberAddr2() {
		return memberAddr2;
	}
	public void setMemberAddr2(String memberAddr2) {
		this.memberAddr2 = memberAddr2;
	}
	public String getMemberAddr3() {
		return memberAddr3;
	}
	public void setMemberAddr3(String memberAddr3) {
		this.memberAddr3 = memberAddr3;
	}
	public int getAdminCk() {
		return adminCk;
	}
	public void setAdminCk(int adminCk) {
		this.adminCk = adminCk;
	}
	public int getRegDate() {
		return regDate;
	}
	public void setRegDate(int regDate) {
		this.regDate = regDate;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	


}
