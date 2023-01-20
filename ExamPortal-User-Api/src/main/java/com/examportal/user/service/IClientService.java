package com.examportal.user.service;

import java.util.List;

import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.model.Client;



public interface IClientService {
	Client registerClient(Client transientClient, String pinCode,  String subscriptionName);
	ClientDTO authenticateClient(String email, String password);
	Client editPassword(long id, String oldPassword, String newPassword);
	ClientDTO updateClient(Client transientClient, String pinCode);
//	List<ExamListDTO> getAllExamByClient(Long clientId);
	String activateDeactivateClient(long cId);
	String getPassword(String email);
	long getClientIdByEmail(String email);
}
