package com.examportal.exam.service;

import com.examportal.exam.model.StudentCompetency;

public interface IStudentCompetencyService {

	void addStudentCompetency(StudentCompetency stCalibre);


	StudentCompetency getStudentCompetencyById(Long id);


	StudentCompetency updateStudentCompetencyById(Long id, StudentCompetency newStudentCalibre);


	void deleteStudentCompetencyById(Long id);


}
