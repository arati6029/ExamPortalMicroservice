package com.quiqbook.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiqbook.Response.APIResponse;
import com.quiqbook.Response.ApiResponseService;
import com.quiqbook.models.Authority;
import com.quiqbook.repository.AuthorityRepository;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/qbkuser")
@ApiIgnore
public class RoleResource {

	@Autowired
	ApiResponseService apiResponseService;

	@Autowired
	APIResponse apiResponse;

	@Autowired
	AuthorityRepository authorityRepository;
	@PostMapping("/role")
	@PreAuthorize("@authentication.hasPermisionToAdmin('','','ROLE')")
	public Object createUser(@RequestBody Authority authority) {
		if ("".equalsIgnoreCase(authority.getName())) {
			return apiResponseService.failure(400, "Name not be empty");
		}
		Optional<Authority> authorityCheck = authorityRepository.findOneByName(authority.getName());
		if (authorityCheck.isPresent()) {
			return apiResponseService.failure(400, "Role already present");
		}

		authority = authorityRepository.save(authority);

		if (authority == null) {
			return apiResponseService.failure(400, "Role not created successfully");
		}
		return apiResponseService.success("Role created successfully", authority);

	}

	@GetMapping("/role")
	public Object gelRole() {

		List<Authority> authoritySet = authorityRepository.findAll();
		if (authoritySet.isEmpty()) {
			return apiResponseService.failure(404, "Role not found");
		}

		return apiResponseService.success("Role found", authoritySet);
	}

	@GetMapping("/role/{role}")
	public Object gelRoleByName(@PathVariable String role) {

		Optional<Authority> authorityCheck = authorityRepository.findOneByName(role);
		if (authorityCheck.isPresent()) {
			return apiResponseService.success("Role found", authorityCheck.get());

		}
		return apiResponseService.failure(400, "Role not found");
	}

	@DeleteMapping("/role/{role}")
	@PreAuthorize("@authentication.hasPermisionToAdmin('','','ROLE')")
	public Object deleteRoleByName(@PathVariable String role) {

		Optional<Authority> authorityCheck = authorityRepository.findOneByName(role);
		if (authorityCheck.isPresent()) {
			return apiResponseService.failure(400, "Role not found");
		}
		try {
			authorityRepository.deleteById(role);
			return apiResponseService.success("Role delete successfully", authorityCheck.get());
		} catch (Exception e) {
			return apiResponseService.failure(400, "Role not delete");
		}

	}
}
