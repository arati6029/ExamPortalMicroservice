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

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.examportal.user.model.Address;
import com.examportal.user.model.Client;
import com.examportal.user.model.GenderEnum;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.model.Subscription;
import com.examportal.user.model.BaseUser;

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
public class StudentDTO {
	private long id;
	private String name;
	private String email;
	private String mobile;
//	private String password;
	private RoleEnum role;
	private boolean accStatus;
	private LocalDateTime dateStamp;
	private String addressLine1;
	private String addressLine2;
	private String pincode;
	private int rollNo;
	private long examAdminId;
	private LocalDate dob;
	private GenderEnum gender;
	//e.id, e.name, e.email, e.mobile, e.role, e.accStatus,e.dateStamp, e.addressLine1, e.addressLine2, e.address, e.rollNo, e.client.id, e.dob, e.gender
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDTO(long id, String name, String email, String mobile, RoleEnum role, boolean accStatus,
			LocalDateTime dateStamp, String addressLine1, String addressLine2, String pincode, int rollNo,
			long examAdminId, LocalDate dob, GenderEnum gender) {
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
		this.rollNo = rollNo;
		this.examAdminId = examAdminId;
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
	public String getAddress() {
		return pincode;
	}
	public void setAddress(String pincode) {
		this.pincode = pincode;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public long getExamAdminId() {
		return examAdminId;
	}
	public void setExamAdminId(long examAdminId) {
		this.examAdminId = examAdminId;
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
