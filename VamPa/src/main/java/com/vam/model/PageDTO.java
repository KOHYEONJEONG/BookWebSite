package com.vam.model;

public class PageDTO {
	/* ������ ���� ��ȣ */
    private int pageStart; //ȭ�鿡 ǥ�õǴ� ������ '���� ��ȣ' & '�� ��ȣ
    
    /* ������ �� ��ȣ */
    private int pageEnd;// ȭ�鿡 ǥ�õǴ� ������ '���� ��ȣ' & '�� ��ȣ
    
    /* ����, ���� ��ư ���� ���� */
    private boolean next, prev;//ȭ�鿡 ���̴� 10�� �������� '���� ������', '���� ������' ���� ����
    
    /* �� ��ü ���� */
    private int total;//'��ü ������' �����Դϴ�. �ش� ������ �־�� 'startPage', 'endPage', 'prev', 'next'�� ���� ���� �� �־ ����
    
    /* ���������� ��ȣ(pageNum), �� ǥ�� ��(amount), �˻� Ű����(keyword), �˻� ����(type) */
    /*���� ������, �������� �Խñ� ǥ�� �� ����*/
    private Criteria cri;//pageNum(���� ������) ���� ���� ��� ���� ����
    
    
    
    /* ������(Ŭ���� ȣ�� �� �� ���� �� �ʱ�ȭ) */
    public PageDTO(Criteria cri, int total) {
        
        /* cri, total �ʱ�ȭ */
        this.cri = cri;
        this.total = total;
        
        /* ������ �� ��ȣ */
        this.pageEnd = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
        
        /* ������ ���� ��ȣ */
        this.pageStart = this.pageEnd - 9;
        
        /* ��ü ������ ������ ��ȣ */
        int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
        
        /* ������ �� ��ȣ ��ȿ�� üũ */
        if(realEnd < pageEnd) {
        	/**/
            this.pageEnd = realEnd;
        }
        
        /* ���� ��ư �� �ʱ�ȭ */
        this.prev = this.pageStart > 1;
        
        /* ���� ��ư �� �ʱ�ȭ */
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
