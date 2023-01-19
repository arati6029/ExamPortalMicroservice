package com.examportal.exam.model;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Exam

ex_id(pk)(un)
ex_name
ea_id(fk)(un)
no_of_ques
mark_per_ques
negmarks_per_ques
schedule_date
schedule_time

ex_status
date_timestamp

 */
@Getter
@Setter
@ToString(exclude = {"examAdmin", "dateStamp"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exam extends BaseEntity {
	@Column(length = 60, nullable = false)
	private String examName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ea_id", nullable = false)
	private ExamAdmin examAdmin;
	
	
	@Column(nullable = false)
	private int noOfQues;
	
	@Column(nullable = false)
	private int marksPerQues;
	
	@Column(nullable = false)
	private int negMarksPerQues;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate scheduledDate;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime scheduledTime;
	
	private boolean examStatus;
	
	private int examDuration;
	
	private LocalDate resultDate;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dateStamp;
	
	public Exam(String examName, int noOfQues, int marksPerQues, int negMarksPerQues, LocalDate scheduledDate,
			LocalTime scheduledTime, boolean examStatus, int examDuration, LocalDate resultDate) {
		super();
		this.examName = examName;
		this.noOfQues = noOfQues;
		this.marksPerQues = marksPerQues;
		this.negMarksPerQues = negMarksPerQues;
		this.scheduledDate = scheduledDate;
		this.scheduledTime = scheduledTime;
		this.examStatus = examStatus;
		this.examDuration = examDuration;
		this.resultDate=resultDate;
	}

	public Exam(Long id, String examName, ExamAdmin examAdmin, int noOfQues, int marksPerQues, int negMarksPerQues,
			LocalDate scheduledDate, LocalTime scheduledTime, boolean examStatus, LocalDateTime dateStamp) {
		super(id);
		this.examName = examName;
		this.examAdmin = examAdmin;
		this.noOfQues = noOfQues;
		this.marksPerQues = marksPerQues;
		this.negMarksPerQues = negMarksPerQues;
		this.scheduledDate = scheduledDate;
		this.scheduledTime = scheduledTime;
		this.examStatus = examStatus;
		this.dateStamp = dateStamp;
	}

	public Exam(Long id, String examName, LocalDate scheduledDate, LocalTime scheduledTime) {
		super(id);
		this.examName = examName;
		this.scheduledDate = scheduledDate;
		this.scheduledTime = scheduledTime;
	}

	
}


