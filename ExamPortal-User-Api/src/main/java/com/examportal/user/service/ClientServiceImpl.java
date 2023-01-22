package com.examportal.user.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.exceptionhandler.custom_exception.ResourceNotFoundException;
import com.examportal.user.exceptionhandler.custom_exception.UserNotFoundException;
import com.examportal.user.model.Client;
import com.examportal.user.model.Subscription;
import com.examportal.user.repository.AddressRepository;
import com.examportal.user.repository.ClientRepository;
import com.examportal.user.repository.SubscriptionRepository;



@Service
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private SubscriptionRepository subscriptionRepo;
//	@Autowired
//	private ExamRepository examRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
//	@Override
//	public User registerUser(User transientUser,Client transientClient,Address transientAddress) {
//		
//		 clientRepo.save(transientClient);
//		 addressRepo.save(transientAddress);
//		 return  new User();
//	}

	@Override
	public Client registerClient(Client transientClient, String pinCode, String subscriptionName) {
		Subscription subscription = subscriptionRepo.findByPlanName(subscriptionName).orElseThrow(() -> new ResourceNotFoundException("Subscription Not Found"));
		transientClient.setAddress(addressRepo.findByPinCode(pinCode).orElseThrow(()->new ResourceNotFoundException("Invalid Pincode")));
		transientClient.setSubscription(subscriptionRepo.findByPlanName(subscriptionName).orElseThrow(()->new ResourceNotFoundException("Invalid Subscription Name")));
		return clientRepo.save(transientClient);
	}

	@Override
	public ClientDTO authenticateClient(String email, String password) {
		return mapper.map(clientRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new UserNotFoundException("Invalid email or password")), ClientDTO.class);
	}

	@Override
	public ClientDTO updateClient(Client transientClient, String pinCode) {
		Client client = clientRepo.findById(transientClient.getId()).orElseThrow(() -> new UserNotFoundException("Invalid Client Id"));
		transientClient.setAddress(addressRepo.findByPinCode(pinCode).orElseThrow(()->new ResourceNotFoundException("Invalid Pincode")));
		transientClient.setSubscription(transientClient.getSubscription());
		transientClient.setAccStatus(client.isAccStatus());
		transientClient.setDateStamp(client.getDateStamp());
		transientClient.setPassword(client.getPassword());
		transientClient.setRole(client.getRole());
		clientRepo.save(transientClient);
		return mapper.map(transientClient, ClientDTO.class);
	}
//	
//
//	@Override
//	public Address registerAddress(Address transientAddress) {
//		// TODO Auto-generated method stub
//		return addressRepo.save(transientAddress);
//		
//	}
//	
//	@Override
//	public Client editPassword(long id, String newPassword) {
//		Client client=clientRepo.findById(id).orElseThrow();
//		client.setPassword(newPassword);
//		return clientRepo.save(client);
//	}

	@Override
	public Client editPassword(long id, String oldPassword, String newPassword) {
		Client client = clientRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid Client Id"));
	//	if (client.getPassword().equals(oldPassword))
			client.setPassword(encoder.encode(newPassword));
		//else
		//	throw new IncorrectPasswordException("R did not Match");
		return clientRepo.save(client);
	}

//	@Override
//	public List<ExamListDTO> getAllExamByClient(Long clientId) {
//		return examRepo.findExamByClientId(clientId).orElseThrow(() -> new UserNotFoundException("Invalid Client Id"));
//	}

//	@Override
//	public String activate(Long id) {
//		Student student = studentRepo.findById(id).orElseThrow();
//		if (student.isAccStatus()) {
//			student.setAccStatus(false);
//			return "Student Account Deactivated SuccessFully";
//		} else {
//			student.setAccStatus(true);
//			return "Student Account Activated SuccessFully";
//		}
//	}

	@Override
	public String activateDeactivateClient(long id) {
		Client client = clientRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid Client Id"));
		if (client.isAccStatus()) {
			client.setAccStatus(false);
			return "Client Account Deactivated SuccessFully";
		} else {
			client.setAccStatus(true);
			return "Client Account Activated SuccessFully";
		}
	}

	@Override
	public String getPassword(String email) {
		String pass = clientRepo.findPasswordByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Invalid email"));
		return pass;
	}
	
	@Override
	public long getClientIdByEmail(String email) {
		return clientRepo.findIdByEmail(email).orElseThrow(() -> new UserNotFoundException("Invalid Email"));
	}
}
