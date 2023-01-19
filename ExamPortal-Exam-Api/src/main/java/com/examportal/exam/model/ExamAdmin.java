package com.examportal.exam.model;


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


@Getter
@Setter
@ToString(exclude = "client")
@NoArgsConstructor
@AllArgsConstructor

public class ExamAdmin extends BaseUser{
	
	public ExamAdmin(String name, String email, String mobile, String password, RoleEnum role, String addressLine1, String addressLine2, String department, LocalDate dob, String gender) {
		super(name, email, mobile, password, role, true,addressLine1,addressLine2);
		this.department = department;
		this.dob = dob;
		this.gender = GenderEnum.valueOf(gender);
	}
	
	public ExamAdmin(Long id, String name, String email, String mobile, RoleEnum examAdmin, String addressLine1,
			String addressLine2, String department, LocalDate dob, String gender) {
		super(id, name, email, mobile,addressLine1,addressLine2);
		this.department = department;
		this.dob = dob;
		this.gender = GenderEnum.valueOf(gender);
	}

	@Column(length = 40)
	private String department;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="c_id",nullable = false)
	private Client client;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private GenderEnum gender;
}
