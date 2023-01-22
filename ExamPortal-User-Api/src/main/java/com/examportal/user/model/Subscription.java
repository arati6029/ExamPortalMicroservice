package com.examportal.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * subscription
subscription_id(pk)(un)
no_of_exams
price
 */

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "dateStamp")
@Entity
public class Subscription extends BaseEntity{
	
	public Subscription(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private String planName;
	private int noOfExams;
	private double price;
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dateStamp;
	
	public Subscription(String planName, int noOfExams, double price) {
		super();
		
		this.planName = planName;
		this.noOfExams = noOfExams;
		this.price = price;
	}

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getNoOfExams() {
		return noOfExams;
	}

	public void setNoOfExams(int noOfExams) {
		this.noOfExams = noOfExams;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}
	
}
