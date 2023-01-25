package com.examportal.exam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.exam.model.StudentCalibre;
import com.examportal.exam.repository.StudentCalibreRepository;
@Service
@Transactional
public class ExamServiceImpl implements IExamService {
@Autowired
private StudentCalibreRepository stCalibreRepo;
	@Override
	public void addStudentCalibre(StudentCalibre stCalibre) {
		stCalibreRepo.save(stCalibre);

	}

}
