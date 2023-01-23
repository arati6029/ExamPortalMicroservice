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
import org.springframework.util.CollectionUtils;
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
import com.examportal.user.dto.ExamAdminListDTO;
import com.examportal.user.dto.ExamAdminRegisterDto;
import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.dto.client.ClientRegisterDto;
import com.examportal.user.dto.client.ClientUpdateDto;
import com.examportal.user.model.Client;
import com.examportal.user.model.ExamAdmin;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.service.IAdminService;
import com.examportal.user.service.IClientService;
import com.examportal.user.service.IExamAdminService;

@RestController
@RequestMapping("/clients")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class ClientResource {
	@Autowired
	private IClientService clientService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IExamAdminService examAdminService;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private PasswordEncoder encoder;
	
	
	//change password
	@PreAuthorize("hasAuthority('CLIENT')")
	@PutMapping("/{cId}/client")
	public ResponseEntity<?> editClientPassword(@PathVariable long cId,
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
		clientService.editPassword(cId, encoder.encode(changePasswordDto.getOldPassword()),
				changePasswordDto.getNewPassword());
		
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("password changed"));
	
		
	} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
		// send back err resp code
		System.out.println("err " + e);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
		//return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("edit password failed"));
	}
	//update client
	@PreAuthorize("hasAuthority('CLIENT')")
	@PutMapping
	public ResponseEntity<?> updtateClient(@RequestBody @Valid ClientUpdateDto newClientDto) {
		// String name, String email, String mobile, String password, RoleEnum role
		ClientDTO client= clientService.updateClient(
		new Client(newClientDto.getId(), newClientDto.getName(), newClientDto.getEmail(),
				newClientDto.getMobile(), newClientDto.getAddressLine1(), newClientDto.getAddressLine2()),
		newClientDto.getPinCode());
		if(client!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(client);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClientDTO());
		}
	}
	//add examadmin
	@PreAuthorize("hasAuthority('CLIENT')")
	@PostMapping("/exam_admin")
	public ResponseEntity<?> registerExamAdmin(@RequestBody @Valid ExamAdminRegisterDto newExamAdminDto) {
		examAdminService.registerExamAdmin(
						new ExamAdmin(newExamAdminDto.getName(), newExamAdminDto.getEmail(),
								newExamAdminDto.getMobile(), encoder.encode(newExamAdminDto.getPassword()), RoleEnum.EXAM_ADMIN,
								newExamAdminDto.getAddressLine1(), newExamAdminDto.getAddressLine2(),
								newExamAdminDto.getDepartment(), newExamAdminDto.getDob(), newExamAdminDto.getGender()),
						newExamAdminDto.getPinCode(), newExamAdminDto.getClientId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User Registered successfully"));
	}
	//get exam admin list by client id
	@PreAuthorize("hasAuthority('CLIENT')")
	@GetMapping("/{cId}/exam_admins")
	public ResponseEntity<?> getExamAdminList(@PathVariable long cId) {
		List<ExamAdminListDTO> examAdminList=examAdminService.getExamAdminList(cId);
		if(CollectionUtils.isEmpty(examAdminList)) {
			return ResponseEntity.noContent().build();
		}else {
		return ResponseEntity.status(HttpStatus.OK).body(examAdminList);
		}
	}
	
	//change exam admin status
	@PreAuthorize("hasAuthority('CLIENT')")
	@PutMapping("/{id}/exam_admin")
	public ResponseEntity<?> activateDeactivateExamAdmin(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ApiResponse(examAdminService.activateDeactivateExamAdmin(id)));
	}
}
