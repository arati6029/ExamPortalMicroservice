package com.examportal.master.model;


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
 * student_response

s_id(un)(fk)
ques_id(fk)(un)
response
date_timestamp

 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"dateStamp", "question", "student"})
public class StudentResponse extends BaseEntity {
	

	public StudentResponse(Student student, Question question, String response) {
		super();
		this.student = student;
		this.question = question;
		this.response = response;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_id", nullable = false)
	private Student student;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_id", nullable = false)
	private Question question;
	
	@Column(length = 20)
	private String response;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dateStamp;

	public StudentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentResponse(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}
	
	
}
