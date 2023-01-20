package com.examportal.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthStudent {
	private String message;
	private String jwt;
	private StudentDTO student;
	public AuthStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthStudent(String message, String jwt, StudentDTO student) {
		super();
		this.message = message;
		this.jwt = jwt;
		this.student = student;
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
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
}
