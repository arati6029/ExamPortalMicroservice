package com.examportal.exam.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Client
org_type
subscription_id(fk)(un)
exam_count
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "subscription")
public class Client extends BaseUser{
	
	public Client(Long id) {
		super(id);
		
	}

	public Client(String name, String email, String mobile, String password, RoleEnum role, String addressLine1, String addressLine2) {
		super(name, email, mobile, password, role, true,addressLine1,addressLine2);
		this.examCount = 0;
	}

	public Client(Long id, String name, String email, String mobile, String addressLine1, String addressLine2) {
		super(id, name, email, mobile,addressLine1,addressLine2);
	}

	public Client(String name, String email, String mobile, boolean accStatus,
			String addressLine1, String addressLine2, int examCount) {
		super(name, email, mobile, accStatus, addressLine1, addressLine2);
		//this.subscription = subscription;
		this.examCount = examCount;
	}

	@OneToOne
	@JoinColumn(name = "sub_id")
	private Subscription subscription;
	
	private int examCount;
}

