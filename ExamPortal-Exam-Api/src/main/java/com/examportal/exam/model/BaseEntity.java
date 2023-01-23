package com.examportal.exam.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // To tell hibernate , following is a base class for all other entities ,// containing common features BUT without any table associated with it


public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseEntity(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
