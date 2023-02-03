package com.examportal.user.dto;

import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.model.Client;

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
public class AuthAdmin {
	private String message;
	private String jwt;
	private AdminDTO admin;
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
	public AdminDTO getAdmin() {
		return admin;
	}
	public void setAdmin(AdminDTO admin) {
		this.admin = admin;
	}
	public AuthAdmin(String message, String jwt, AdminDTO admin) {
		super();
		this.message = message;
		this.jwt = jwt;
		this.admin = admin;
	}
	public AuthAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
