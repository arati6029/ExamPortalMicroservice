package com.examportal.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Experience extends BaseEntity{
	@Column(name="noOfYears", nullable=false)
private int noOfYears;
	@Column(name="speciality")
private String speciality;
	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Experience(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Experience(int noOfYears, String speciality) {
		super();
		this.noOfYears = noOfYears;
		this.speciality = speciality;
	}
	public int getNoOfYears() {
		return noOfYears;
	}
	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	
}
