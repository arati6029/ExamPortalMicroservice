package com.examportal.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@Entity
@ToString
public class Address {
	
	@Id
	@Column(length = 20)
	private String pinCode;
	
	@Column(length = 20)
	private String  city;
	
	@Column(length = 20)
	private String state;
	
	@Column(length = 20)
	private String country;

	
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
