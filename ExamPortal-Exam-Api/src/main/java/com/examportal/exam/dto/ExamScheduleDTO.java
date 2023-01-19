package com.examportal.exam.dto;


import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamScheduleDTO {
	
public ExamScheduleDTO(String examName, LocalDate scheduledDate, LocalTime scheduledTime) {
		this.examName = examName;
		this.scheduledDate = scheduledDate;
		this.scheduledTime = scheduledTime;
	}
private String examName;
private LocalDate scheduledDate;
private LocalTime scheduledTime;
private LocalDate resultDate;
}
