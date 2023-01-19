package com.examportal.exam.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exam.dto.ApiResponse;
import com.examportal.exam.dto.CreateExamDTO;
import com.examportal.exam.model.Exam;
import com.examportal.exam.service.IExamService;
import com.examportal.exam.service.IQuestionService;

//import com.app.dto.ApiResponse;
//import com.app.dto.CreateExamDTO;
//import com.app.pojos.Exam;
//import com.app.service.IClientService;
//import com.app.service.IExamService;
//import com.app.service.IQuestionService;
//import com.app.service.IStudentService;

@RestController
@RequestMapping("/exam")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class ExamResource {
	@Autowired
	private IExamService examService;
	//@Autowired
//    private IStudentService studentService;
	@Autowired
	private IQuestionService questionService;
//	@Autowired
//	private IClientService clientService;
	
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	
	/*
	 * @PostMapping("/create_exam") public ResponseEntity<?> createExam(@RequestBody
	 * CreateExamDTO newExamDto) { examService.createExam(new
	 * Exam(newExamDto.getExamName(), newExamDto.getNoOfQues(),
	 * newExamDto.getMarksPerQues(), newExamDto.getNegMarksPerQues(),
	 * newExamDto.getScheduledDate(), newExamDto.getScheduledTime(), true,
	 * newExamDto.getExamDuration(),newExamDto.getResultDate()),
	 * newExamDto.getExamAdminID()); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(new
	 * ApiResponse("Exam Created successfully")); }
	 * 
	 * @PreAuthorize("hasAuthority('CLIENT')")
	 * 
	 * @PutMapping("/toggle_exam_status/{id}") public ResponseEntity<?>
	 * activateDeactivateExam(@PathVariable long id) { // String name, String email,
	 * String mobile, String password, RoleEnum role, // String addressLine1, String
	 * addressLine2, int rollNo, LocalDate dob, String // gender return
	 * ResponseEntity.status(HttpStatus.ACCEPTED).body(new
	 * ApiResponse(examService.activateDeactivateExam(id))); }
	 * 
	 * @PreAuthorize("hasAnyAuthority('EXAM_ADMIN','STUDENT')")
	 * 
	 * @GetMapping("/get_questions/{id}") public ResponseEntity<?>
	 * getAllQuestionsByExam(@PathVariable long id) { return
	 * ResponseEntity.status(HttpStatus.ACCEPTED).body(questionService.
	 * getQuestionList(id)); }
	 * 
	 * @PreAuthorize("hasAnyAuthority('EXAM_ADMIN','STUDENT','CLIENT')")
	 * 
	 * @GetMapping("/get_exam_list/{id}") public ResponseEntity<?>
	 * getExamList(@PathVariable long id) { // String name, String email, String
	 * mobile, String password, RoleEnum role return
	 * ResponseEntity.status(HttpStatus.CREATED).body(clientService.
	 * getAllExamByClient(id)); }
	 * 
	 * @PreAuthorize("hasAnyAuthority('EXAM_ADMIN','STUDENT','CLIENT')")
	 * 
	 * @GetMapping("/get_exam_list_for_student/{id}") public ResponseEntity<?>
	 * getExamListForStudent(@PathVariable long id) { // String name, String email,
	 * String mobile, String password, RoleEnum role return
	 * ResponseEntity.status(HttpStatus.CREATED).body(studentService.
	 * getExamListByStudent(id)); }
	 * 
	 * @PreAuthorize("hasAuthority('EXAM_ADMIN')")
	 * 
	 * @GetMapping("/get_Exam_List/{id}") public ResponseEntity<?>
	 * getAllExamByExamAdminId(@PathVariable long id){ return
	 * ResponseEntity.status(HttpStatus.ACCEPTED).body(examService.
	 * getAllExamByExamAdminId(id)); }
	 */
	
	
	
	//API for getting exam schedule by client id 
	//return ExamScheduleDto
	
	@GetMapping("/{cId}")
	public ResponseEntity<?> getExamScheduleByClientId(@PathVariable long cId){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(examService.getExamScheduleByClientId(cId));
	}
	
}
