package com.vam.model;

public class Criteria {
	 /* 현재 페이지 번호 */
    private int pageNum;
    
    /* 페이지 표시 개수 */
    private int amount;
    
    /*페이지 skip*/
    private int skip;
    
    /* 검색 타입 */
    private String type;
    
    /* 검색 키워드 */
    private String keyword;
    
    /* Criteria 생성자 */
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum - 1)*amount;
    }
    
    /* Criteria 기본 생성자 */
    public Criteria(){
        this(1,10);//1페이지에 10개 행표시
    }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		
		//Criteria 클래스의 데이터를 사용할 때 매번 새로운 생성자를 호출하여
		//사용하기 때문에 해당 과정이 필요 없지만 혹시 모를 경우를 위해 작업하였습니다.
		this.skip = (pageNum - 1)* this.amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		
		//Criteria 클래스의 데이터를 사용할 때 매번 새로운 생성자를 호출하여
		//사용하기 때문에 해당 과정이 필요 없지만 혹시 모를 경우를 위해 작업하였습니다.
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
    
	 /* 검색 타입 데이터 배열 변환 */
    public String[] getTypeArr() {
        return type == null? new String[] {}:type.split("");
    }
    
}
