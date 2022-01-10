package com.vam.model;

public class PageDTO {
	/* 페이지 시작 번호 */
    private int pageStart; //화면에 표시되는 페이지 '시작 번호' & '끝 번호
    
    /* 페이지 끝 번호 */
    private int pageEnd;// 화면에 표시되는 페이지 '시작 번호' & '끝 번호
    
    /* 이전, 다음 버튼 존재 유무 */
    private boolean next, prev;//화면에 보이는 10개 페이지의 '이전 페이지', '다음 페이지' 존재 유무
    
    /* 행 전체 개수 */
    private int total;//'전체 페이지' 정보입니다. 해당 정보가 있어야 'startPage', 'endPage', 'prev', 'next'의 값을 구할 수 있어서 선언
    
    /* 현재페이지 번호(pageNum), 행 표시 수(amount), 검색 키워드(keyword), 검색 종류(type) */
    /*현재 페이지, 페이지당 게시글 표시 수 정보*/
    private Criteria cri;//pageNum(현재 페이지) 변수 값을 얻기 위해 선언
    
    
    
    /* 생성자(클래스 호출 시 각 변수 값 초기화) */
    public PageDTO(Criteria cri, int total) {
        
        /* cri, total 초기화 */
        this.cri = cri;
        this.total = total;
        
        /* 페이지 끝 번호 */
        this.pageEnd = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
        
        /* 페이지 시작 번호 */
        this.pageStart = this.pageEnd - 9;
        
        /* 전체 마지막 페이지 번호 */
        int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
        
        /* 페이지 끝 번호 유효성 체크 */
        if(realEnd < pageEnd) {
        	/**/
            this.pageEnd = realEnd;
        }
        
        /* 이전 버튼 값 초기화 */
        this.prev = this.pageStart > 1;
        
        /* 다음 버튼 값 초기화 */
        this.next = this.pageEnd < realEnd;
        
    }

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "PageDTO [pageStart= "+pageStart+", pageEnd= "+pageEnd+", next= "+next+", prev="+prev+", total= "+total+", cri= "+cri+"]";
    }
}
