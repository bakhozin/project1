package com.sist.vo;

public class Cart {
	private String seq;
	private String code;	// FK �ڵ� 
	private String name;	// ��ǰ�̸�
	private String image;
	private String mid;    //FK 
	private int price;		//����
	private int orderQnt; //�ֹ�����?
	private String size;
	private int qnt ; // ���
	

	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setOrderQnt(int orderQnt) {
		this.orderQnt = orderQnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getOrderQnt() {
		return orderQnt;
	}
	public int getQnt() {
		return qnt;
	}
		
}
