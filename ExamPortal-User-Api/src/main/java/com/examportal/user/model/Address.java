package com.examportal.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
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

	public Address(String pinCode, String city, String state, String country) {
		super();
		this.pinCode = pinCode;
		this.city = city;
		this.state = state;
		this.country = country;
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
