package com.examportal.exam.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass // To tell hibernate , following is a base class for all other entities ,// containing common features BUT without any table associated with it
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public BaseEntity(Long id) {
		super();
		this.id = id;
	}

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
