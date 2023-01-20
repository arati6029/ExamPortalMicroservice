package com.examportal.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.examportal.user.model.RoleEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecoverPasswordDTO {
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email Format")
	private String email;
	@NotBlank(message = "Old Password must be supplied")
	private String oldPassword;
	@NotBlank(message = "New Password must be supplied")
	private String newPassword;
	private String role;
	public RecoverPasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecoverPasswordDTO(
			@NotBlank(message = "Email must be supplied") @Email(message = "Invalid Email Format") String email,
			@NotBlank(message = "Old Password must be supplied") String oldPassword,
			@NotBlank(message = "New Password must be supplied") String newPassword, String role) {
		super();
		this.email = email;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
