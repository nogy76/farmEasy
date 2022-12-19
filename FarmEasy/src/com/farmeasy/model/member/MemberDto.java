package com.farmeasy.model.member;

public class MemberDto {
	
	 private int m_seq;
	 private String m_name;
	 private String m_id;
	 private String m_pw;
	 private String m_email;
	 private String m_mobile;
	 private String m_authority;
	 private String m_date;
	 private String m_score;
	 

	public MemberDto() {

	}
	
	public MemberDto(String m_id, String m_pw) {
		this.m_id = m_id;
		this.m_pw = m_pw;
	}	 
	
	public MemberDto(int m_seq, String m_score) {
		this.m_seq = m_seq;
		this.m_score = m_score;
	}	 
	
	
	public MemberDto(String m_name, String m_id, String m_email) {
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_email = m_email;
	}	 
	 
	
	public MemberDto(int m_seq , String m_pw, String m_email, String m_mobile) {
		this.m_seq = m_seq;
		this.m_pw = m_pw;
		this.m_email = m_email;
		this.m_mobile = m_mobile;
	}	 
	 
	
	public MemberDto(int m_seq, String m_name, String m_id, String m_pw, String m_email, String m_mobile,
			String m_authority, String m_date, String m_score) {
		
		this.m_seq = m_seq;
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_email = m_email;
		this.m_mobile = m_mobile;
		this.m_authority = m_authority;
		this.m_date = m_date;
		this.m_score = m_score;
	}
	
	
	
	
	public int getM_seq() {
		return m_seq;
	}
	public void setM_seq(int m_seq) {
		this.m_seq = m_seq;
	}

	public String getM_score() {
		return m_score;
	}
	public void setM_score(String m_score) {
		this.m_score = m_score;
	}
	
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	public String getM_Id() {
		return m_id;
	}
	public void setM_Id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	
	public String getM_mobile() {
		return m_mobile;
	}
	public void setM_mobile(String m_mobile) {
		this.m_mobile = m_mobile;
	}
	
	public String getM_authority() {
		return m_authority;
	}
	public void setM_authority(String m_authority) {
		this.m_authority = m_authority;
	}
	
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	 
}
