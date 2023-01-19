package com.examportal.master.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * 
Grading_scheme

ex_id(fk)(un)
grade_A
grade_B
grade_C
grade_F
date_timestamp

 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "dateStamp")
public class GradingScheme extends BaseEntity {
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="ex_id",nullable = false)
	@MapsId 
	private Exam exam;
	
	// public GradingScheme(int gradeA, int gradeB, int gradeC, int gradeF) {
	// 	super();
	// 	this.gradeA = gradeA;
	// 	this.gradeB = gradeB;
	// 	this.gradeC = gradeC;
	// 	this.gradeF = gradeF;
	// }

	@Column(nullable = false)
	private int gradeA;
	
	@Column(nullable = false)
	private int gradeB;
	
	@Column(nullable = false)
	private int gradeC;
	
	@Column(nullable = false)
	private int gradeF;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dateStamp;
	
	public GradingScheme(int gradeA, int gradeB, int gradeC, int gradeF) {
		super();
		this.gradeA = gradeA;
		this.gradeB = gradeB;
		this.gradeC = gradeC;
		this.gradeF = gradeF;
	}

	public GradingScheme() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GradingScheme(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public int getGradeA() {
		return gradeA;
	}

	public void setGradeA(int gradeA) {
		this.gradeA = gradeA;
	}

	public int getGradeB() {
		return gradeB;
	}

	public void setGradeB(int gradeB) {
		this.gradeB = gradeB;
	}

	public int getGradeC() {
		return gradeC;
	}

	public void setGradeC(int gradeC) {
		this.gradeC = gradeC;
	}

	public int getGradeF() {
		return gradeF;
	}

	public void setGradeF(int gradeF) {
		this.gradeF = gradeF;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}
	
	
}
