package com.examportal.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.exam.model.StudentCompetency;
import com.examportal.exam.repository.StudentCompetencyRepository;

@Service
@Transactional
public class StudentCompetencyServiceImpl implements IStudentCompetencyService {

	@Autowired
	private StudentCompetencyRepository stCalibreRepo;

	// Add Skill and Experience in StudentCalibre Table
	@Override
	public void addStudentCompetency(StudentCompetency stCalibre) {
		stCalibreRepo.save(stCalibre);

	}

	// Get StudentCalibre By id
	@Override
	public StudentCompetency getStudentCompetencyById(Long id) {
		StudentCompetency studentCalibre = stCalibreRepo.findById(id).orElseThrow();
		return studentCalibre;
	}

	// Update Data in StudentCalibre Table By Id
	@Override
	public StudentCompetency updateStudentCompetencyById(Long id, StudentCompetency newStudentCalibre) {
		StudentCompetency studentCompetency = stCalibreRepo.findById(id).orElseThrow();
		studentCompetency.setSkillIdList(newStudentCalibre.getSkillIdList());
		studentCompetency.setExpIdList(newStudentCalibre.getExpIdList());

		return studentCompetency;
	}

	// Delete Data in StudentCalibre Table By Id
	@Override
	public void deleteStudentCompetencyById(Long id) {
		StudentCompetency studentCompetency = stCalibreRepo.findById(id).orElseThrow();
		stCalibreRepo.delete(studentCompetency);
	}

}
