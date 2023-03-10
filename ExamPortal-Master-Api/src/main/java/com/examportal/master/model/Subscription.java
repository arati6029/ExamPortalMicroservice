package com.examportal.master.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;

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
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "dateStamp")
public class Subscription extends BaseEntity{
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

	public Subscription(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Subscription(String planName, int noOfExams, double price, LocalDateTime dateStamp) {
		super();
		this.planName = planName;
		this.noOfExams = noOfExams;
		this.price = price;
		this.dateStamp = dateStamp;
	}
	
}
