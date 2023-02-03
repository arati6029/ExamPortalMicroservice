package com.examportal.user.dto.client;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.examportal.user.model.Address;
import com.examportal.user.model.Subscription;

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
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class ClientListDTO {
	private long id;
	private String name;
	private String email;
	private String mobile;
	private boolean accStatus;
	private LocalDateTime dateStamp;
	private String addressLine1;
	private String addressLine2;
	private String pincode;
	private long subscriptionId;
	private int examCount;
	private int examAdminCount;
	private int studentCount;
	
//	public ClientListDTO() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
	public ClientListDTO(long id,String name, String email, String mobile, boolean accStatus, LocalDateTime dateStamp,
			String addressLine1, String addressLine2, String pincode, long subscriptionId, int examCount) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.accStatus = accStatus;
		this.dateStamp = dateStamp;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.subscriptionId = subscriptionId;
		this.examCount = examCount;
	}

	public ClientListDTO(long id, String name, String email, String mobile, boolean accStatus, LocalDateTime dateStamp,
			String addressLine1, String addressLine2, String pincode, long subscriptionId, int examCount,
			int examAdminCount, int studentCount) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.accStatus = accStatus;
		this.dateStamp = dateStamp;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.subscriptionId = subscriptionId;
		this.examCount = examCount;
		this.examAdminCount = examAdminCount;
		this.studentCount = studentCount;
	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	public boolean isAccStatus() {
//		return accStatus;
//	}
//
//	public void setAccStatus(boolean accStatus) {
//		this.accStatus = accStatus;
//	}
//
//	public LocalDateTime getDateStamp() {
//		return dateStamp;
//	}
//
//	public void setDateStamp(LocalDateTime dateStamp) {
//		this.dateStamp = dateStamp;
//	}
//
//	public String getAddressLine1() {
//		return addressLine1;
//	}
//
//	public void setAddressLine1(String addressLine1) {
//		this.addressLine1 = addressLine1;
//	}
//
//	public String getAddressLine2() {
//		return addressLine2;
//	}
//
//	public void setAddressLine2(String addressLine2) {
//		this.addressLine2 = addressLine2;
//	}
//
//
//
//	public String getPincode() {
//		return pincode;
//	}
//
//	public void setPincode(String pincode) {
//		this.pincode = pincode;
//	}
//
//
//
//	public long getSubscriptionId() {
//		return subscriptionId;
//	}
//
//	public void setSubscriptionId(long subscriptionId) {
//		this.subscriptionId = subscriptionId;
//	}
//
//	public int getExamCount() {
//		return examCount;
//	}
//
//	public void setExamCount(int examCount) {
//		this.examCount = examCount;
//	}
//
//	public int getExamAdminCount() {
//		return examAdminCount;
//	}
//
//	public void setExamAdminCount(int examAdminCount) {
//		this.examAdminCount = examAdminCount;
//	}
//
//	public int getStudentCount() {
//		return studentCount;
//	}
//
//	public void setStudentCount(int studentCount) {
//		this.studentCount = studentCount;
//	}
//
	
	
	
}