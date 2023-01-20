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
@AllArgsConstructor
@ToString
public class ExamAdminListDTO {
	private long id;
	private String name;
	private String email;
	private String department;
	private boolean accStatus;
	public ExamAdminListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamAdminListDTO(long id, String name, String email, String department, boolean accStatus) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.accStatus = accStatus;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public boolean isAccStatus() {
		return accStatus;
	}
	public void setAccStatus(boolean accStatus) {
		this.accStatus = accStatus;
	}
	
	
}
