package com.examportal.exam.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table(name="student_competency")
public class StudentCompetency {
	

	@Id
@Column(name="student_id")
private long studentId;
	
@ManyToMany
@JoinTable(
		  name = "student_skill", 
		  joinColumns = @JoinColumn(name = "student_id"), 
		  inverseJoinColumns = @JoinColumn(name = "skillId"))
@Column(name="skillId")
private List<Skill> skillIdList;


@JoinTable(
		  name = "student_exp", 
		  joinColumns = @JoinColumn(name = "student_id"), 
		  inverseJoinColumns = @JoinColumn(name = "experienceId"))
@Column(name="experienceId")
private List<Experience> expIdList;


public StudentCompetency() {
	super();
	// TODO Auto-generated constructor stub
}


public StudentCompetency(long studentId, List<Skill> skillIdList, List<Experience> expIdList) {
	super();
	this.studentId = studentId;
	this.skillIdList = skillIdList;
	this.expIdList = expIdList;
}


public long getStudentId() {
	return studentId;
}


public void setStudentId(long studentId) {
	this.studentId = studentId;
}


public List<Skill> getSkillIdList() {
	return skillIdList;
}


public void setSkillIdList(List<Skill> skillIdList) {
	this.skillIdList = skillIdList;
}


public List<Experience> getExpIdList() {
	return expIdList;
}


public void setExpIdList(List<Experience> expIdList) {
	this.expIdList = expIdList;
}



}
