package com.examportal.user.rest;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.user.dto.ApiResponse;
import com.examportal.user.dto.ChangePasswordDTO;
import com.examportal.user.dto.LoginRequestDTO;
import com.examportal.user.dto.SubscriptionDTO;
import com.examportal.user.dto.client.ClientListDTO;
import com.examportal.user.dto.client.ClientRegisterDto;
import com.examportal.user.model.Client;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.model.Student;
import com.examportal.user.model.Subscription;
import com.examportal.user.service.IAdminService;
import com.examportal.user.service.IClientService;
import com.examportal.user.service.ISubscriptionService;

import ch.qos.logback.core.util.StringCollectionUtil;

@RestController
@RequestMapping("/admins")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class AdminResource {
	@Autowired
	private IClientService clientService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private ISubscriptionService subscriptionService;
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/client")
	public ResponseEntity<?> registerClient(@RequestBody @Valid ClientRegisterDto newClientDto) {
		// String name, String email, String mobile, String password, RoleEnum role
		clientService.registerClient(new Client(newClientDto.getName(), newClientDto.getEmail(),
				newClientDto.getMobile(), encoder.encode(newClientDto.getPassword()), RoleEnum.CLIENT, newClientDto.getAddressLine1(),
				newClientDto.getAddressLine2()), newClientDto.getPinCode(), newClientDto.getSubscriptionName());
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User Registered successfully"));
	
	}
	//Client list
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/clients")
	public ResponseEntity<?> getClientList() {
		List<ClientListDTO> clientList=adminService.getClientDetails();
		if(CollectionUtils.isEmpty(clientList)) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientList);
		}
	}
	//change client status
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{cId}/client")
	public ResponseEntity<?> activateDeactivateClient(@PathVariable long cId) {
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ApiResponse(clientService.activateDeactivateClient(cId)));
	}
	//crud operations on subscription
	//get subscription list
	@GetMapping("/subscriptions")
	public ResponseEntity<?> getAllSubscriptions() {
		List<String> subscriptionList=subscriptionService.getSubscriptionList();
		if(CollectionUtils.isEmpty(subscriptionList)) {
			return ResponseEntity.noContent().build();
		}else {
		return ResponseEntity.status(HttpStatus.OK).body(subscriptionList);
		}
	}
	//add new subscription
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/subscription")
	public ResponseEntity<?> addSubscription(@RequestBody SubscriptionDTO newSubscriptionDto) {
		// String name, String email, String mobile, String password, RoleEnum role
		subscriptionService.addSubscription(new Subscription(newSubscriptionDto.getPlanName(),
				newSubscriptionDto.getNoOfExams(), newSubscriptionDto.getPrice()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Subscription added successfully"));
	}
	//update subscription details from admin
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/subscription")
	public ResponseEntity<?> editSubscription(@RequestBody SubscriptionDTO newSubscriptionDto) {
		// String name, String email, String mobile, String password, RoleEnum role
		subscriptionService.editSubscription(newSubscriptionDto.getId(), new Subscription(
				newSubscriptionDto.getPlanName(), newSubscriptionDto.getNoOfExams(), newSubscriptionDto.getPrice()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Subscription edited successfully"));
	}
	//delete subscription
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{subId}/subscription")
	public ResponseEntity<?> deleteSubscription(@PathVariable long subId) {
		// String name, String email, String mobile, String password, RoleEnum role
		return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.deleteSubscription(subId));
	}
	//get subscription by id
	@GetMapping("/{subId}/subscription")
	public ResponseEntity<?> getSubscriptionDetails(@PathVariable long subId) {
		//System.out.println("Inside @GetMapping get_sub_list" + subscriptionService.getSubscriptionList());
		Subscription subscription=subscriptionService.getSubscriptionDetails(subId);
		if(subscription!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(subscription);
		}else {
			return ResponseEntity.noContent().build();
		}
	}

}
