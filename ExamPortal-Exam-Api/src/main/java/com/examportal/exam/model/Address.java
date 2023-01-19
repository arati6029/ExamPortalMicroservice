package com.examportal.exam.model;



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
@NoArgsConstructor
@AllArgsConstructor
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
	
	
}
