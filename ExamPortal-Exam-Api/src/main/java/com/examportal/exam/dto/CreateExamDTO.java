package com.examportal.exam.dto;


import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

import com.examportal.exam.model.ExamAdmin;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateExamDTO {
@NotBlank(message = "Exam name  must be supplied")	
private String examName;
@NotBlank(message = "No of questions  must be supplied")
private int noOfQues;
@NotBlank(message = "marks Per questions must be supplied")
private int marksPerQues;
@NotBlank(message = "negMarks Per questions must be supplied")
private int negMarksPerQues;
@NotBlank(message = "Scheduled Time must be supplied")
private LocalTime scheduledTime;
@NotBlank(message = "Scheduled Date must be supplied")
private LocalDate scheduledDate;
@NotBlank(message = "Exam Status must be supplied")
private boolean examStatus;
@NotBlank(message = "Exam Admin ID must be supplied")
private Long examAdminID;
@NotBlank(message = "Exam Duration  must be supplied")
private int examDuration;
@NotBlank(message = "result Date must be supplied")
private LocalDate resultDate;
public String getExamName() {
	return examName;
}
public void setExamName(String examName) {
	this.examName = examName;
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
public LocalTime getScheduledTime() {
	return scheduledTime;
}
public void setScheduledTime(LocalTime scheduledTime) {
	this.scheduledTime = scheduledTime;
}
public LocalDate getScheduledDate() {
	return scheduledDate;
}
public void setScheduledDate(LocalDate scheduledDate) {
	this.scheduledDate = scheduledDate;
}
public boolean isExamStatus() {
	return examStatus;
}
public void setExamStatus(boolean examStatus) {
	this.examStatus = examStatus;
}
public Long getExamAdminID() {
	return examAdminID;
}
public void setExamAdminID(Long examAdminID) {
	this.examAdminID = examAdminID;
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



}
