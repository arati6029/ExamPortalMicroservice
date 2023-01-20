package com.examportal.user.dto;


import com.examportal.user.dto.client.*;

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
public class AuthClient {
	private String message;
	private String jwt;
	private ClientDTO client;
	public AuthClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthClient(String message, String jwt, ClientDTO client) {
		super();
		this.message = message;
		this.jwt = jwt;
		this.client = client;
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
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	
}
