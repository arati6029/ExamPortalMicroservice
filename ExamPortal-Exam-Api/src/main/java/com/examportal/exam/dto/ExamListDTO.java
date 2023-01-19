package com.examportal.exam.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExamListDTO {
	
	private Long id;
	private String examName;
	private Long examAdminId;
	private LocalDate scheduledDate;
	private LocalTime scheduledTime;
	private int marksPerQues;
	private int negMarksPerQues;
	private int noOfQues;
	private boolean examStatus;
	private int examDuration;
	
	public ExamListDTO(Long id, String examName, Long examAdminId, LocalDate scheduledDate, LocalTime scheduledTime, boolean examStatus, int examDuration) {
		super();
		this.id = id;
		this.examName = examName;
		this.examAdminId = examAdminId;
		this.scheduledDate = scheduledDate;
		this.scheduledTime = scheduledTime;
		this.examStatus = examStatus;
		this.examDuration = examDuration;
	}
	
	public ExamListDTO(Long id, String examName, Long examAdminId, LocalDate scheduledDate, LocalTime scheduledTime,
			int marksPerQues, int negMarksPerQues, int noOfQues) {
		super();
		this.id = id;
		this.examName = examName;
		this.examAdminId = examAdminId;
		this.scheduledDate = scheduledDate;
		this.scheduledTime = scheduledTime;
		this.marksPerQues = marksPerQues;
		this.negMarksPerQues = negMarksPerQues;
		this.noOfQues = noOfQues;
	}

	public ExamListDTO(Long id) {
		super();
		this.id = id;
	}
	
	
}

