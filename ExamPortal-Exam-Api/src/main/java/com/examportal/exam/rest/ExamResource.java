package com.examportal.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exam.dto.SkillAndExpDTO;
import com.examportal.exam.model.StudentCalibre;
import com.examportal.exam.service.IExamService;

@RestController
@RequestMapping("/exams")
public class ExamResource {
	@Autowired
	private IExamService examService;
	
	@PostMapping("/")
	ResponseEntity<?> addStudentSkillAndExp(@RequestBody SkillAndExpDTO skAndExpDto){
		StudentCalibre stCalibre= new StudentCalibre(skAndExpDto.getStudentId(),skAndExpDto.getSkillId(),skAndExpDto.getExperienceId());
		examService.addStudentCalibre(stCalibre);
		return ResponseEntity.ok().body("Student Calibres Area Added....");
	}

}
