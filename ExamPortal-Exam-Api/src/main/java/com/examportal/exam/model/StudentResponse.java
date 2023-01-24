package com.examportal.exam.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

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
@ToString
public class StudentResponse extends BaseEntity {
	

	
	@Column(name="student_id", nullable = false)
	private long StudentId;
	
	@Column(name="question_id")
	private long questionId;
	@Column(name="response")
	 @ElementCollection(targetClass=String.class)
	 private List<String> response;
	
	@Column(nullable = false,name="date_stamp")
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

	

	

	public StudentResponse(long studentId, long questionId, List<String> response, LocalDateTime dateStamp) {
		super();
		StudentId = studentId;
		this.questionId = questionId;
		this.response = response;
		this.dateStamp = dateStamp;
	}

	public long getStudentId() {
		return StudentId;
	}

	public void setStudentId(long studentId) {
		StudentId = studentId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	
	public List<String> getResponse() {
		return response;
	}

	public void setResponse(List<String> response) {
		this.response = response;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}
	
	
}
