package com.examportal.master.model;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import lombok.ToString;


@MappedSuperclass

@ToString(exclude = {"password", "dateStamp"})
/*
 * User
name
email
contactNo
password
address
role
accStatus
dateStamp
 */
public class BaseUser extends BaseEntity {
	
	
	public BaseUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseUser(Long id) {
		super(id);
	}

	public BaseUser(String name, String email, String mobile, String password, RoleEnum role, boolean accStatus,
			String addressLine1, String addressLine2) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
		this.accStatus = accStatus;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}
	
	public BaseUser(Long id, String name, String email, String mobile, String password, RoleEnum role, boolean accStatus,
			String addressLine1, String addressLine2) {
		super(id);
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
		this.accStatus = accStatus;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}

//	public User(Long id, String name, String email, String mobile, String password, RoleEnum role, boolean accStatus,
//			String addressLine1, String addressLine2) {
//		super(id);
//		this.name = name;
//		this.email = email;
//		this.mobile = mobile;
//		this.password = password;
//		this.role = role;
//		this.accStatus = accStatus;
//		this.addressLine1 = addressLine1;
//		this.addressLine2 = addressLine2;
//	}

	public BaseUser(Long id, String name, String email, String mobile, String addressLine1, String addressLine2) {
		super(id);
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		//this.accStatus = accStatus;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}

	

	public BaseUser(String name, String email, String mobile, boolean accStatus, String addressLine1,String addressLine2) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
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



	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 40,unique = true, nullable = false)
	private String email;
	
	@Column(length = 20)
	private String mobile;
	
	@Column(length = 200,nullable = false)
	private String password;
	
	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	private boolean accStatus;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dateStamp;
	
	@Column(name="address_line1",length = 50)
	private String addressLine1;
	
	@Column(name="address_line2",length = 50)
	private String addressLine2;
	
//	@ManyToOne
//	@JoinColumn(name = "pinCode", nullable = false)
//	private Address address;
}