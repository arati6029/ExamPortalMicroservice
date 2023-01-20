package com.examportal.user.rest;


import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.examportal.user.dto.AdminDTO;
import com.examportal.user.dto.ApiResponse;
import com.examportal.user.dto.AuthAdmin;
import com.examportal.user.dto.AuthClient;
import com.examportal.user.dto.AuthExamAdmin;
import com.examportal.user.dto.AuthStudent;
import com.examportal.user.dto.ExamAdminDTO;
import com.examportal.user.dto.LoginRequestDTO;
import com.examportal.user.dto.StudentDTO;
import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.dto.client.ClientRegisterDto;
import exceptionhandler.custom_exception.AccountDeactivatedException;
import exceptionhandler.custom_exception.ResourceNotFoundException;
import com.examportal.user.jwt_utils.JwtUtils;
import com.examportal.user.model.Admin;
import com.examportal.user.model.Client;
import com.examportal.user.model.ExamAdmin;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.model.Student;
import com.examportal.user.repository.AdminRepository;
import com.examportal.user.repository.ClientRepository;
import com.examportal.user.repository.ExamAdminRepository;
import com.examportal.user.repository.StudentRepository;
import com.examportal.user.service.IAdminService;
import com.examportal.user.service.IClientService;
import com.examportal.user.service.IExamAdminService;
import com.examportal.user.service.IStudentService;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class LoginResource {
	@Autowired
	private IClientService clientService;
	@Autowired
	private IExamAdminService examAdminService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private JwtUtils utils;

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private ExamAdminRepository examAdminRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private ModelMapper mapper;
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO user) {
//		System.out.println("test");
//		if (RoleEnum.valueOf(user.getRole().toUpperCase()) == RoleEnum.CLIENT) {
//			ClientDTO client = clientService.authenticateClient(user.getEmail(), user.getPassword());
//			if (!client.isAccStatus())
//				throw new RuntimeException("Account deactivated... Please Contact Admin");
//			return ResponseEntity.status(HttpStatus.OK).body(client);
//		} else if (RoleEnum.valueOf(user.getRole().toUpperCase()) == RoleEnum.EXAM_ADMIN) {
//			ExamAdminDTO examAdmin = examAdminService.authenticateExamAdmin(user.getEmail(), user.getPassword());
//			if (!examAdmin.isAccStatus() && !examAdmin.getClient().isAccStatus())
//				throw new RuntimeException("Account deactivated... Please Contact Client");
//			return ResponseEntity.status(HttpStatus.OK).body(examAdmin);
//		} else if (RoleEnum.valueOf(user.getRole().toUpperCase()) == RoleEnum.ADMIN) {
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(adminService.authenticateAdmin(user.getEmail(), user.getPassword()));
//		} else {
//			StudentDTO student = studentService.authenticateStudent(user.getEmail(), user.getPassword());
//			if (!student.isAccStatus() && !student.getClient().isAccStatus())
//				throw new RuntimeException("Account deactivated.. Please Contact Client");
//			return ResponseEntity.status(HttpStatus.OK).body(student);
//		}
//	}

	@PostMapping("/login")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody LoginRequestDTO request) {
		// store incoming user details(not yet validated) into Authentication object
		// Authentication i/f ---> implemented by UserNamePasswordAuthToken
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		// log.info("auth token before {}",authToken);
		try {
			// authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
//			ArrayList<? extends GrantedAuthority> authorities = (ArrayList<? extends GrantedAuthority>) authenticatedDetails
//					.getAuthorities();
//			System.out.println(authorities.get(0));
//			GrantedAuthority grantedAuthority = authorities.get(0);
			Client client=null;
			ExamAdminDTO examAdmin=null;
			StudentDTO student=null;
			Admin admin=null;
			if ((client =clientRepo.findByEmail(request.getEmail()))!=null &&  client.isAccStatus()) 
				return ResponseEntity.ok(new AuthClient("Auth successful!", utils.generateJwtToken(authenticatedDetails), mapper.map(client,ClientDTO.class)));
			else if ((examAdmin =examAdminRepo.findDTOByEmail(request.getEmail()))!=null &&  examAdmin.isAccStatus()) 
				return ResponseEntity.ok(new AuthExamAdmin("Auth successful!", utils.generateJwtToken(authenticatedDetails), examAdmin));
			else if ((student =studentRepo.findDTOByEmail(request.getEmail()))!=null &&  student.isAccStatus()) 
				return ResponseEntity.ok(new AuthStudent("Auth successful!", utils.generateJwtToken(authenticatedDetails),student ));
			else if ((admin =adminRepo.findByEmail(request.getEmail()))!=null) 
				return ResponseEntity.ok(new AuthAdmin("Auth successful!", utils.generateJwtToken(authenticatedDetails), mapper.map(admin,AdminDTO.class)));
				
			throw new AccountDeactivatedException("Account Deactivated Please Contact Respective Authority ");
		} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
			// send back err resp code
			System.out.println("err " + e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}

	}
}
