package com.examportal.user.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import com.examportal.user.model.RoleEnum;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDTO {
	@NotBlank(message = "Name must be supplied")
	private String name;
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email Format")
	private String email;
	private String mobile;
//	private String password;
	private RoleEnum role;
	private boolean accStatus;
	private LocalDateTime dateStamp;
	private String addressLine1;
	private String addressLine2;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public RoleEnum getRole() {
		return role;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}
	public boolean isAccStatus() {
		return accStatus;
	}
	public void setAccStatus(boolean accStatus) {
		this.accStatus = accStatus;
	}
	public LocalDateTime getDateStamp() {
		return dateStamp;
	}
	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public AdminDTO(@NotBlank(message = "Name must be supplied") String name,
			@NotBlank(message = "Email must be supplied") @Email(message = "Invalid Email Format") String email,
			String mobile, RoleEnum role, boolean accStatus, LocalDateTime dateStamp, String addressLine1,
			String addressLine2) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.accStatus = accStatus;
		this.dateStamp = dateStamp;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}
	public AdminDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
