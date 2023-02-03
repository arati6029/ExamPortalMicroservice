package com.examportal.user.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class ExamScheduleDTO {
	
//public ExamScheduleDTO(String examName, LocalDate scheduledDate, LocalTime scheduledTime) {
//		this.examName = examName;
//		this.scheduledDate = scheduledDate;
//		this.scheduledTime = scheduledTime;
//	}
private String examName;
private LocalDate scheduledDate;
private LocalTime scheduledTime;
private LocalDate resultDate;
public ExamScheduleDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public String getExamName() {
	return examName;
}
public void setExamName(String examName) {
	this.examName = examName;
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
public LocalDate getResultDate() {
	return resultDate;
}
public void setResultDate(LocalDate resultDate) {
	this.resultDate = resultDate;
}

}
