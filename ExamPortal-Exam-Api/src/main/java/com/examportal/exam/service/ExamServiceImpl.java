package com.examportal.exam.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.examportal.exam.model.StudentCalibre;
import com.examportal.exam.repository.StudentCalibreRepository;

public class ExamServiceImpl implements IExamService {
@Autowired
private StudentCalibreRepository stCalibreRepo;
	@Override
	public void addStudentCalibre(StudentCalibre stCalibre) {
		stCalibreRepo.save(stCalibre);

	}

}
