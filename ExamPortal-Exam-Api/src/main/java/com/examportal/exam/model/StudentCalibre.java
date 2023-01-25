package com.examportal.exam.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="student_calibre")
public class StudentCalibre extends BaseEntity{
	@Column(name="student_id")
private long studentId;
@Column(name="skillId")
@ElementCollection(targetClass=Long.class)
private List<Long> skillIdList;
@Column(name="experienceId")
@ElementCollection(targetClass=Long.class)
private List<Long> expIdList;
public StudentCalibre() {
	super();
	// TODO Auto-generated constructor stub
}
public StudentCalibre(Long id) {
	super(id);
	// TODO Auto-generated constructor stub
}
public StudentCalibre(long studentId, List<Long> skillIdList, List<Long> expIdList) {
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
public List<Long> getSkillIdList() {
	return skillIdList;
}
public void setSkillIdList(List<Long> skillIdList) {
	this.skillIdList = skillIdList;
}
public List<Long> getExpIdList() {
	return expIdList;
}
public void setExpIdList(List<Long> expIdList) {
	this.expIdList = expIdList;
}


}
