package com.examportal.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.examportal.user.model.Address;
import com.examportal.user.model.GenderEnum;
import com.examportal.user.model.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class ExamAdminRegisterDto {
//	private Long id;
//	private String name;
//	private String email;
//	private String mobile;
//	private String password;
//	private long clientId;
//	private String pinCode;
//	private String addressLine1;
//	private String addressLine2;
//	private String department;
//	private LocalDate dob;
//	private String gender;
	
	private long id;
	@NotBlank(message = " name  must be supplied")
	private String name;
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email Format")
	private String email;
	private String mobile;
	@NotBlank(message = "password must be supplied")
	@JsonProperty(access =Access.WRITE_ONLY)
    private String password;
	@NotBlank(message = "Pincode must be supplied")
	private String pinCode;
	@NotBlank(message = " address  must be supplied")
	private String addressLine1;
	private String addressLine2;
	@NotBlank(message = " department  must be supplied")
	private String department;
	//@NotNull(message = " Client ID  must be supplied")
	private long clientId;
	@Past(message = "  Date of Birth should not in future...")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	@NotBlank(message = " Gender  must be supplied")
	private String gender;
	public ExamAdminRegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamAdminRegisterDto(long id, @NotBlank(message = " name  must be supplied") String name,
			@NotBlank(message = "Email must be supplied") @Email(message = "Invalid Email Format") String email,
			String mobile, @NotBlank(message = "password must be supplied") String password,
			@NotBlank(message = "Pincode must be supplied") String pinCode,
			@NotBlank(message = " address  must be supplied") String addressLine1, String addressLine2,
			@NotBlank(message = " department  must be supplied") String department, long clientId,
			@Past(message = "  Date of Birth should not in future...") LocalDate dob,
			@NotBlank(message = " Gender  must be supplied") String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.pinCode = pinCode;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.department = department;
		this.clientId = clientId;
		this.dob = dob;
		this.gender = gender;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
