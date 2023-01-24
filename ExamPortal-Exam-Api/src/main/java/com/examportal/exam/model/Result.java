package com.examportal.exam.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * result

result_id (pk)
s_id (fk) (un)
ex_id  (fk) (un)
s_student_marks
s_total_marks
s_grade
date_timestamp

 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result extends BaseEntity {
	

	@Column(name="student_id",nullable = false )
	private long studentId;
	
	
	
	@Column(nullable = false,name="student_marks")
	private int studentMarks;
	
	@Column(nullable = false,name="total_marks")
	private int totalMarks;
	
	@Column(name="grade")
	private String grade;
	
	@Column(nullable = false,name="date_stamp")
	@CreationTimestamp
	private LocalDateTime dateStamp;

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Result(long studentId, int studentMarks, int totalMarks, String grade, LocalDateTime dateStamp) {
		super();
		this.studentId = studentId;
		this.studentMarks = studentMarks;
		this.totalMarks = totalMarks;
		this.grade = grade;
		this.dateStamp = dateStamp;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public int getStudentMarks() {
		return studentMarks;
	}

	public void setStudentMarks(int studentMarks) {
		this.studentMarks = studentMarks;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}

	
	
	
}
