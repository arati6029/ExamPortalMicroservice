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
@ToString(exclude = {"dateStamp", "exam", "student"})
public class Result extends BaseEntity {
	public Result(Student student, Exam exam, int studentMarks, int totalMarks, String grade) {
		super();
		this.student = student;
		this.exam = exam;
		this.studentMarks = studentMarks;
		this.totalMarks = totalMarks;
		this.grade = grade;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_id", nullable = false)
	private Student student;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ex_id", nullable = false)
	private Exam exam;
	
	@Column(nullable = false)
	private int studentMarks;
	
	@Column(nullable = false)
	private int totalMarks;
	
	@Column
	private String grade;
	
	@Column(nullable = false)
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
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
