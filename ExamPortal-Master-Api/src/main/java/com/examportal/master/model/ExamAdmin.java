package com.examportal.master.model;

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



@ToString(exclude = "client")

@Entity
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

	public ExamAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamAdmin(Long id, String name, String email, String mobile, String password, RoleEnum role,
			boolean accStatus, String addressLine1, String addressLine2) {
		super(id, name, email, mobile, password, role, accStatus, addressLine1, addressLine2);
		// TODO Auto-generated constructor stub
	}

	public ExamAdmin(Long id, String name, String email, String mobile, String addressLine1, String addressLine2) {
		super(id, name, email, mobile, addressLine1, addressLine2);
		// TODO Auto-generated constructor stub
	}

	public ExamAdmin(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public ExamAdmin(String name, String email, String mobile, boolean accStatus, String addressLine1,
			String addressLine2) {
		super(name, email, mobile, accStatus, addressLine1, addressLine2);
		// TODO Auto-generated constructor stub
	}

	public ExamAdmin(String name, String email, String mobile, String password, RoleEnum role, boolean accStatus,
			String addressLine1, String addressLine2) {
		super(name, email, mobile, password, role, accStatus, addressLine1, addressLine2);
		// TODO Auto-generated constructor stub
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

