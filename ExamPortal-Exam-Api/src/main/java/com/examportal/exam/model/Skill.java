package com.examportal.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="skill")
@Entity
public class Skill extends BaseEntity{
	
	@Column(name="skill_name")
	private String skillName;
	@Column(name="description")
	private String description;
	
	
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Skill(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	public Skill(String skillName, String description) {
		super();
		this.skillName = skillName;
		this.description = description;
	}
	
	
}
