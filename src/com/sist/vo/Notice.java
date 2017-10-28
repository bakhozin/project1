package com.sist.vo;

public class Notice {
	
	private String kind; // 게시판 분류로 게시판 구분
	private String seq;  // PK 
	private String title;
	private String writer; //FK? 
	private String content;
	private String regdate;
	private String FileSrc;
	private int hit;


	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getFileSrc() {
		return FileSrc;
	}
	public void setFileSrc(String fileSrc) {
		FileSrc = fileSrc;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	

}
