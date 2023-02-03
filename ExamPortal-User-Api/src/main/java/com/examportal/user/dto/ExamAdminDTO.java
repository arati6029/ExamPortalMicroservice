package com.examportal.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.examportal.user.model.Address;
import com.examportal.user.model.GenderEnum;
import com.examportal.user.model.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Client
org_type
subscription_id(fk)(un)
exam_count
 */

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class ExamAdminDTO {
	public ExamAdminDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private long id;
	@NotBlank(message = " name  must be supplied")
	private String name;
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid Email Format")
	private String email;
	private String mobile;
//	private String password;
	private RoleEnum role;
	private boolean accStatus;
	private LocalDateTime dateStamp;
	@NotBlank(message = " address  must be supplied")
	private String addressLine1;
	private String addressLine2;
	@NotBlank(message = " address  must be supplied")
	private String pincode;
	@NotBlank(message = " department  must be supplied")
	private String department;
	@NotBlank(message = " Client ID  must be supplied")
	private long clientId;
	@Future(message = "Birth of Date should not in future...")
	private LocalDate dob;
	@NotBlank(message = " Gender  must be supplied")
	private GenderEnum gender;
	//c.id, c.name, c.email, c.mobile, c.accStatus, c.dateStamp, c.addressLine1, c.addressLine2,c.address,c.department, c.client, c.dob, ,c.gender
	
//	e.id, e.name, e.email, e.mobile, e.role, e.accStatus,e.dateStamp, e.addressLine1, e.addressLine2, e.address, e.department, e.client, e.dob, e.gender
	public ExamAdminDTO(String name, String email, String department) {
		super();
		this.name = name;
		this.email = email;
		this.department = department;
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



	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public ExamAdminDTO(long id, @NotBlank(message = " name  must be supplied") String name,
			@NotBlank(message = "Email must be supplied") @Email(message = "Invalid Email Format") String email,
			String mobile, RoleEnum role, boolean accStatus, LocalDateTime dateStamp,
			@NotBlank(message = " address  must be supplied") String addressLine1, String addressLine2,
			@NotBlank(message = " address  must be supplied") String pincode,
			@NotBlank(message = " department  must be supplied") String department,
			@NotBlank(message = " Client ID  must be supplied") long clientId,
			@Future(message = "Birth of Date should not in future...") LocalDate dob,
			@NotBlank(message = " Gender  must be supplied") GenderEnum gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.accStatus = accStatus;
		this.dateStamp = dateStamp;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.department = department;
		this.clientId = clientId;
		this.dob = dob;
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	
	
}
