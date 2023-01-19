package com.examportal.exam.rest;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.examportal.exam.dto.ApiResponse;
//import com.examportal.exam.dto.ExamAdminRegisterDto;
//import com.examportal.exam.dto.client.ClientRegisterDto;
//import com.examportal.exam.model.Client;
//import com.examportal.exam.model.ExamAdmin;
//import com.examportal.exam.model.RoleEnum;
//import com.examportal.exam.service.IAddressService;
//import com.examportal.exam.service.IClientService;
//import com.examportal.exam.service.IExamAdminService;
//import com.examportal.exam.service.IResultService;

@RestController
@RequestMapping("/result")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class ResultResource {
	@Autowired
	private IResultService resultService;

	@PreAuthorize("hasAnyAuthority('EXAM_ADMIN','STUDENT','CLIENT')")
	@GetMapping("/get_result_data")
	public ResponseEntity<?> getResult(@RequestParam long sId, @RequestParam long exId) {
		return ResponseEntity.status(HttpStatus.OK).body(resultService.getResultData(sId, exId));
	}
	@PreAuthorize("hasAnyAuthority('EXAM_ADMIN','STUDENT','CLIENT')")
	@PostMapping("/compute_and_save_result")
	public ResponseEntity<?> computeAndSaveResult(@RequestParam long sId, @RequestParam long exId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(resultService.computeAndSaveResult(sId, exId)));
	}
}
