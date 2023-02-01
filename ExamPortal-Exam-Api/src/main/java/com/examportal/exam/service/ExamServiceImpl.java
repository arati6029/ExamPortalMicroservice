package com.examportal.exam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.exam.model.StudentCompetency;
import com.examportal.exam.repository.StudentCompetencyRepository;
@Service
@Transactional
public class ExamServiceImpl implements IExamService {
@Autowired
private StudentCompetencyRepository stCalibreRepo;
	@Override
	public void addStudentCalibre(StudentCompetency stCalibre) {
		stCalibreRepo.save(stCalibre);

	}

}
