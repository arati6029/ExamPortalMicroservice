package com.examportal.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exam.dto.SkillAndExpDTO;
import com.examportal.exam.model.StudentCompetency;
import com.examportal.exam.service.IStudentCompetencyService;

@RestController
@RequestMapping("/studentcompetencies")
public class StudentCompetencyResource {

	@Autowired
	private IStudentCompetencyService studentCompetencyService;

	// Add Skill and Experience in StudentCalibre Table
	@PostMapping("/studentcompetency")
	ResponseEntity<?> addStudentSkillAndExp(@RequestBody SkillAndExpDTO skAndExpDto) {
		StudentCompetency stCompetency= new StudentCompetency(skAndExpDto.getStudentId(), skAndExpDto.getSkillId(),
				skAndExpDto.getExperienceId());
		studentCompetencyService.addStudentCompetency(stCompetency);
		return ResponseEntity.ok().body("Student Calibres Are Added....");
	}

	// Get Student Calibre By id
	@GetMapping("/{id}/studentcompetency")
	ResponseEntity<?> getStudentCompetencyById(@PathVariable Long id) {

		StudentCompetency studentCompetency = studentCompetencyService.getStudentCompetencyById(id);

		if (studentCompetency != null) {
			return ResponseEntity.ok().body(studentCompetency);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Update Data in StudentCalibre Table By Id
	@PutMapping("/{id}/studentcalibre")
	ResponseEntity<?> updateStudentCompetencyById(@PathVariable Long id,@RequestBody SkillAndExpDTO skAndExpDto ){
		
		StudentCompetency newStudentCompetency=new StudentCompetency(skAndExpDto.getStudentId(),skAndExpDto.getSkillId(),skAndExpDto.getExperienceId());
		StudentCompetency studentCompetency=studentCompetencyService.updateStudentCompetencyById(id,newStudentCompetency);
		
				if (studentCompetency!=null) {
					return ResponseEntity.ok().body(studentCompetency);
				}else {
					return ResponseEntity.notFound().build();
				}
	}
	
	
	// Delete Data in StudentCalibre Table By Id
		@DeleteMapping("/{id}/studentCompetency")
		ResponseEntity<?> deleteStudentCompetencyById(@PathVariable Long id){
			studentCompetencyService.deleteStudentCompetencyById(id);
			
			return ResponseEntity.ok().body("studentCompetency data of id :" +id+ " Deleted ....");

		}
	
	

}
