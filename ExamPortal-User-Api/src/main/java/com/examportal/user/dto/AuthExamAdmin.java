package com.examportal.user.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class AuthExamAdmin {
	private String message;
	private String jwt;
	private ExamAdminDTO examAdmin;
	public AuthExamAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthExamAdmin(String message, String jwt, ExamAdminDTO examAdmin) {
		super();
		this.message = message;
		this.jwt = jwt;
		this.examAdmin = examAdmin;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public ExamAdminDTO getExamAdmin() {
		return examAdmin;
	}
	public void setExamAdmin(ExamAdminDTO examAdmin) {
		this.examAdmin = examAdmin;
	}
	
}
