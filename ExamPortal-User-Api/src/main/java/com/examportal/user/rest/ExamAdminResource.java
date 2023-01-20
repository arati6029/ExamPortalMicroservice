package com.examportal.user.rest;



import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.user.dto.ApiResponse;
import com.examportal.user.dto.ChangePasswordDTO;
import com.examportal.user.dto.ExamAdminRegisterDto;
import com.examportal.user.dto.StudentRegisterDto;
import com.examportal.user.dto.client.ClientRegisterDto;
import com.examportal.user.model.Client;
import com.examportal.user.model.ExamAdmin;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.model.Student;
import com.examportal.user.service.IClientService;
import com.examportal.user.service.IExamAdminService;
import com.examportal.user.service.IStudentService;

@RestController
@RequestMapping("/exam_admin")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class ExamAdminResource {
	@Autowired
	private IExamAdminService examAdminService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private IStudentService studentService;
	
	//change password
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PutMapping("/{id}/exam_admin")
	public ResponseEntity<?> editExamAdminPassword(@PathVariable long id,
			@RequestBody ChangePasswordDTO changePasswordDto) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(changePasswordDto.getEmail(),
				changePasswordDto.getOldPassword());
		try {
			// authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
		// long id=newStudentDto.getId();
		// String name, String email, String mobile, String password, RoleEnum role,
		// String addressLine1, String addressLine2, int rollNo, LocalDate dob, String
		// gender
		examAdminService.editPassword(id, encoder.encode(changePasswordDto.getOldPassword()),
				changePasswordDto.getNewPassword());
		
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("password changed"));
	
		
	} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
		// send back err resp code
		System.out.println("err " + e);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
		//return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("edit password failed"));
	}
	//update details
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PutMapping
	public ResponseEntity<?> updateExamAdmin(@RequestBody @Valid ExamAdminRegisterDto examAdminDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(examAdminService.updateExamAdmin(new ExamAdmin(examAdminDto.getId(),
						examAdminDto.getName(), examAdminDto.getEmail(), examAdminDto.getMobile(), RoleEnum.EXAM_ADMIN,
						examAdminDto.getAddressLine1(), examAdminDto.getAddressLine2(), examAdminDto.getDepartment(),
						examAdminDto.getDob(), examAdminDto.getGender()), examAdminDto.getPinCode()));
	}
	//add student
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PostMapping("/student")
	public ResponseEntity<?> registerStudent(@RequestBody @Valid StudentRegisterDto newStudentDto) {
		// String name, String email, String mobile, String password, RoleEnum role,
		// String addressLine1, String addressLine2, int rollNo, LocalDate dob, String
		// gender
		studentService.registerStudent(new Student(newStudentDto.getName(), newStudentDto.getEmail(),
				newStudentDto.getMobile(), encoder.encode(newStudentDto.getPassword()), RoleEnum.STUDENT,
				newStudentDto.getAddressLine1(), newStudentDto.getAddressLine2(), newStudentDto.getRollNo(),
				newStudentDto.getDob(), newStudentDto.getGender()), newStudentDto.getPinCode(),
				newStudentDto.getExamAdminId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User Registered successfully"));
	}
	//change status
	@PreAuthorize("hasAuthority('CLIENT')")
	@PutMapping("/{sId}/student")
	public ResponseEntity<?> activateDeactivateStudent(@PathVariable long sId) {
		// String name, String email, String mobile, String password, RoleEnum role,
		// String addressLine1, String addressLine2, int rollNo, LocalDate dob, String
		// gender
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ApiResponse(studentService.activateDeactivateStudent(sId)));
	}
	
	//get student list by exam admin id
	@PreAuthorize("hasAnyAuthority('EXAM_ADMIN','STUDENT','CLIENT')")
	@GetMapping("/{eaId}/students")
	public ResponseEntity<?> getStudentList(@PathVariable long eaId) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentList(eaId));
	}
	
}
