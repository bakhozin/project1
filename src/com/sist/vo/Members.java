package com.sist.vo;

public class Members {
	
	private String mid;    // PK 
	private String pwd;
	private String name;
	private String birthday;
	private String gender;
	private String email;    //selected? 
	private String question; //selected? 
	private String answer;
	private String phone;
	private int mg;
	private String regdate;
	private String locations;  //fk order ? 지난 주문 

	
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getMg() {
		return mg;
	}

	public void setMg(int mg) {
		this.mg = mg;
	}

	public String getRegdate() {
		return regdate;
	}
	
	public void setRegdate(String string) {
		
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


}
