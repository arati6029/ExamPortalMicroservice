package com.examportal.exam.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.examportal.exam.dto.AddGradingSchemeDTO;
//import comexamportal.exam.dto.ApiResponse;
//import com.examportal.exam.model.GradingScheme;
//import com.examportal.exam.rest.IGradingService;

@RestController
@RequestMapping("/exam")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class GradingSchemeResource {
	@Autowired
	private IGradingService gradingService;
	
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PostMapping("/create_Grsch")
	public ResponseEntity<?> createExam(@RequestBody AddGradingSchemeDTO newGrSchDto) {

		gradingService.addGradingScheme(new GradingScheme(newGrSchDto.getGradeA(), newGrSchDto.getGradeB(),
				newGrSchDto.getGradeC(), newGrSchDto.getGradeF()), newGrSchDto.getExamId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Grading Scheme Created  successfully"));
	}
}
