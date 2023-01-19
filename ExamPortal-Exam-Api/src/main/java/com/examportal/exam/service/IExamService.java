package com.examportal.exam.service;

import java.util.List;

import com.examportal.exam.dto.ExamListDTO;
import com.examportal.exam.dto.ExamScheduleDTO;
import com.examportal.exam.model.Exam;

public interface IExamService {

	Exam createExam(Exam transientExam, Long ExamAdminId);

	String activateDeactivateExam(Long id);

	List<ExamListDTO> getAllExamByExamAdminId(Long ExamAdminId);

	List<ExamScheduleDTO> getExamScheduleByClientId(long cId);

}
