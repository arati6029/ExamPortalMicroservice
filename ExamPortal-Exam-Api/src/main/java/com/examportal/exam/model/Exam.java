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

@ToString(exclude = {"examAdmin", "dateStamp"})
@Entity
public class Exam extends BaseEntity {
	@Column(length = 60, nullable = false)
	private String examName;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ea_id", nullable = false)
//	private ExamAdmin examAdmin;
	private long examAdminId;
	
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

	public Exam(Long id, String examName, long examAdminId, int noOfQues, int marksPerQues, int negMarksPerQues,
			LocalDate scheduledDate, LocalTime scheduledTime, boolean examStatus, LocalDateTime dateStamp) {
		super(id);
		this.examName = examName;
		this.examAdminId = examAdminId;
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

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	

	public long getExamAdminId() {
		return examAdminId;
	}

	public void setExamAdminId(long examAdminId) {
		this.examAdminId = examAdminId;
	}

	public int getNoOfQues() {
		return noOfQues;
	}

	public void setNoOfQues(int noOfQues) {
		this.noOfQues = noOfQues;
	}

	public int getMarksPerQues() {
		return marksPerQues;
	}

	public void setMarksPerQues(int marksPerQues) {
		this.marksPerQues = marksPerQues;
	}

	public int getNegMarksPerQues() {
		return negMarksPerQues;
	}

	public void setNegMarksPerQues(int negMarksPerQues) {
		this.negMarksPerQues = negMarksPerQues;
	}

	public LocalDate getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public LocalTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public boolean isExamStatus() {
		return examStatus;
	}

	public void setExamStatus(boolean examStatus) {
		this.examStatus = examStatus;
	}

	public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}

	public LocalDate getResultDate() {
		return resultDate;
	}

	public void setResultDate(LocalDate resultDate) {
		this.resultDate = resultDate;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}

	
	
}


