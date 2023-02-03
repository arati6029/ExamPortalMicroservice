package com.examportal.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {
	private long id;
	private String planName;
	private int noOfExams;
	private double price;
	public SubscriptionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public SubscriptionDTO(long id, String planName, int noOfExams, double price) {
//		super();
//		this.id = id;
//		this.planName = planName;
//		this.noOfExams = noOfExams;
//		this.price = price;
//	}
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getPlanName() {
//		return planName;
//	}
//	public void setPlanName(String planName) {
//		this.planName = planName;
//	}
//	public int getNoOfExams() {
//		return noOfExams;
//	}
//	public void setNoOfExams(int noOfExams) {
//		this.noOfExams = noOfExams;
//	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
	
	
}
