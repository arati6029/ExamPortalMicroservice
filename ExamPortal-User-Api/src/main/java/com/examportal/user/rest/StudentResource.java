package com.examportal.user.rest;

import java.util.List;

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
import com.examportal.user.dto.StudentDTO;
import com.examportal.user.dto.StudentRegisterDto;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.model.Student;
import com.examportal.user.service.IStudentService;

@RestController
@RequestMapping("/students")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class StudentResource {
	@Autowired
	private IStudentService studentService;
//	@Autowired
//	private IStudentResponseService studentResponseService;
//	@Autowired
//	private IResultService resultService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager manager;
//	@Autowired
//	private IQuestionService questionService;
	//change password
	@PreAuthorize("hasAuthority('STUDENT')")
	@PutMapping("/{id}/student")
	public ResponseEntity<?> editStudentPassword(@PathVariable long id,
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
		Student student = studentService.editPassword(id, encoder.encode(changePasswordDto.getOldPassword()),
				changePasswordDto.getNewPassword());
		if (student != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("password changed"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("edit password failed"));
		
	} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
		// send back err resp code
		System.out.println("err " + e);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
		//return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("edit password failed"));
	}
	//update student
	@PreAuthorize("hasAuthority('STUDENT')")
	@PutMapping
	public ResponseEntity<?> updateStudent(@RequestBody @Valid StudentRegisterDto newStudentDto) {
		System.out.println("inside update student "+newStudentDto.getId());
		// String name, String email, String mobile, String password, RoleEnum role,
		// String addressLine1, String addressLine2, int rollNo, LocalDate dob, String
		// gender
		StudentDTO studentDto=studentService.updateStudent(new Student(newStudentDto.getId(), newStudentDto.getName(),
				newStudentDto.getEmail(), newStudentDto.getMobile(), RoleEnum.STUDENT, newStudentDto.getAddressLine1(),
				newStudentDto.getAddressLine2(), newStudentDto.getRollNo(), newStudentDto.getDob(),
				newStudentDto.getGender()), newStudentDto.getPinCode());
		return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
	}

}
