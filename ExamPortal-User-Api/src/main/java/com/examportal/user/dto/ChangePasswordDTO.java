package com.examportal.user.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordDTO {
	private String oldPassword;
	private String newPassword;
	private String email;
	public ChangePasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePasswordDTO(String oldPassword, String newPassword, String email) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.email = email;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
