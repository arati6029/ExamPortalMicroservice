package com.examportal.user.service;

import java.util.List;

import com.examportal.user.dto.AdminDTO;
import com.examportal.user.dto.client.ClientListDTO;



public interface IAdminService {
	AdminDTO authenticateAdmin(String email, String password);
	List<ClientListDTO> getClientDetails();
}
