package com.examportal.exam.model;



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
}
