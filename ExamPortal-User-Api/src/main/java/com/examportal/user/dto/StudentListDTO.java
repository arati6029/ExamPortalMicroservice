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
import com.examportal.user.model.BaseUser;
import com.examportal.user.model.Client;
import com.examportal.user.model.GenderEnum;
import com.examportal.user.model.RoleEnum;
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
@AllArgsConstructor
@ToString
public class StudentListDTO {
	private long id;
	private int rollNo;
	private String name;
	private String email;
	private LocalDate dob;
	private GenderEnum gender;
	private boolean status;
	public StudentListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	//StudentListDTO(s.id, s.rollNo, s.name, s.email,s.dob, s.gender, s.accStatus)
	public StudentListDTO(long id, int rollNo, String name, String email, LocalDate dob, GenderEnum gender,
			boolean status) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.status = status;
	}
	
	
}
