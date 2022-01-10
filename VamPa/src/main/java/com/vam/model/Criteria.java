package com.vam.model;

public class Criteria {
	 /* ���� ������ ��ȣ */
    private int pageNum;
    
    /* ������ ǥ�� ���� */
    private int amount;
    
    /*������ skip*/
    private int skip;
    
    /* �˻� Ÿ�� */
    private String type;
    
    /* �˻� Ű���� */
    private String keyword;
    
    /* Criteria ������ */
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum - 1)*amount;
    }
    
    /* Criteria �⺻ ������ */
    public Criteria(){
        this(1,10);//1�������� 10�� ��ǥ��
    }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		
		//Criteria Ŭ������ �����͸� ����� �� �Ź� ���ο� �����ڸ� ȣ���Ͽ�
		//����ϱ� ������ �ش� ������ �ʿ� ������ Ȥ�� �� ��츦 ���� �۾��Ͽ����ϴ�.
		this.skip = (pageNum - 1)* this.amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		
		//Criteria Ŭ������ �����͸� ����� �� �Ź� ���ο� �����ڸ� ȣ���Ͽ�
		//����ϱ� ������ �ش� ������ �ʿ� ������ Ȥ�� �� ��츦 ���� �۾��Ͽ����ϴ�.
		this.skip = (this.pageNum - 1)*amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}
	
	@Override
	public String toString() {
		return "Criteria [pageNum="+pageNum+", amount="+amount+", type="+type+", keyword="+keyword+"]";
	}
    
	 /* �˻� Ÿ�� ������ �迭 ��ȯ */
    public String[] getTypeArr() {
        return type == null? new String[] {}:type.split("");
    }
    
}
