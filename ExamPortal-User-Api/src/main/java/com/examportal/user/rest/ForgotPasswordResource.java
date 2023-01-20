package com.examportal.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.user.dto.ApiResponse;
import com.examportal.user.dto.LoginRequestDTO;
import com.examportal.user.dto.RecoverPasswordDTO;
import com.examportal.user.dto.client.ClientRegisterDto;
import com.examportal.user.model.Client;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.service.IAdminService;
import com.examportal.user.service.IClientService;
import com.examportal.user.service.IExamAdminService;
import com.examportal.user.service.IStudentService;

@RestController
@RequestMapping("/")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class ForgotPasswordResource {
	@Autowired
	private IClientService clientService;
	@Autowired
	private IExamAdminService examAdminService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostMapping("/forgot_password")
	public ResponseEntity<?> forgotPassowrd(@RequestBody LoginRequestDTO user) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setFrom("chavanabhishek1910@gmail.com");
		message.setSubject("Subject : Forgot Password");

//		System.out.println("Mail successfully sent..");
		if (RoleEnum.valueOf(user.getRole().toUpperCase()) == RoleEnum.CLIENT) {
			System.out.println("Inside Client FP");
			message.setText("Your Password is : " + clientService.getPassword(user.getEmail()));
		} else if (RoleEnum.valueOf(user.getRole().toUpperCase()) == RoleEnum.EXAM_ADMIN)
			message.setText("Your Password is : " + examAdminService.getPassword(user.getEmail()));
//			else if (user.getRole() == RoleEnum.ADMIN)
//				message.setText("Your Password is : " + adminService.getPassword(user.getEmail()));
		else {
			message.setText("Your Password is : " + studentService.getPassword(user.getEmail()));
		}
		javaMailSender.send(message);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Check your Email for password"));

	}
	
	@PostMapping("/recover_password")
	public ResponseEntity<?> recoverPassowrd(@RequestBody RecoverPasswordDTO data) {
		if(RoleEnum.valueOf(data.getRole().toUpperCase()) == RoleEnum.CLIENT ) 
			clientService.editPassword(clientService.getClientIdByEmail(data.getEmail()), data.getOldPassword(), data.getNewPassword());
		else if(RoleEnum.valueOf(data.getRole().toUpperCase()) == RoleEnum.EXAM_ADMIN ) 
			examAdminService.editPassword(examAdminService.getExamAdminIdByEmail(data.getEmail()), data.getOldPassword(), data.getNewPassword());
		else if(RoleEnum.valueOf(data.getRole().toUpperCase()) == RoleEnum.STUDENT) 
			studentService.editPassword(studentService.getStudentIdByEmail(data.getEmail()), data.getOldPassword(), data.getNewPassword());		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Password Updated successfully"));
	}
}
