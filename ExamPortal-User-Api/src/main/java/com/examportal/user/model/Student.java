package com.examportal.user.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Student
roleNumber
clientID
dob
gender
 */

@Getter
@Setter
@ToString(exclude = "client")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends BaseUser{
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, String email, String mobile, String password, RoleEnum role, String addressLine1, String addressLine2, int rollNo, LocalDate dob, String gender) {
		super(name, email, mobile, password, role, true,addressLine1,addressLine2);
		this.rollNo = rollNo;
		this.dob = dob;
		this.gender = GenderEnum.valueOf(gender);
	}
	public Student(long id, String name, String email, String mobile, RoleEnum student,
			String addressLine1, String addressLine2, int rollNo, LocalDate dob, String gender) {
		super(id, name, email, mobile,addressLine1,addressLine2);
		this.rollNo = rollNo;
		this.dob = dob;
		this.gender = GenderEnum.valueOf(gender);
	}
	private int rollNo;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="c_id",nullable = false)
//	private Client client;
	private long examAdminId;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private GenderEnum gender;

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
	public Student(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Student(long id, int rollNo, long examAdminId, LocalDate dob, GenderEnum gender) {
		super(id);
		this.rollNo = rollNo;
		this.examAdminId = examAdminId;
		this.dob = dob;
		this.gender = gender;
	}
	
	
	
}
