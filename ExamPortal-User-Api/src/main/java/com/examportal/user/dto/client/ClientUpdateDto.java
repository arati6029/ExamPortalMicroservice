package com.examportal.user.dto.client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.examportal.user.model.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(exclude = "password")
public class ClientUpdateDto {
	private long id;
	@NotBlank(message = " name  must be supplied")
	private String name;
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email Format")
	private String email;
	private String mobile;
	
	
	
	@NotBlank(message = "Pincode must be supplied")
	private String pinCode;
	@NotBlank(message = " address  must be supplied")
	private String addressLine1;
	private String addressLine2;
	public ClientUpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientUpdateDto(long id, @NotBlank(message = " name  must be supplied") String name,
			@NotBlank(message = "Email must be supplied") @Email(message = "Invalid Email Format") String email,
			String mobile, @NotBlank(message = "Pincode must be supplied") String pinCode,
			@NotBlank(message = " address  must be supplied") String addressLine1, String addressLine2) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.pinCode = pinCode;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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
	
	
}
