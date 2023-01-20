package com.examportal.user.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.user.dto.AdminDTO;
import com.examportal.user.dto.ExamAdminListDTO;
import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.dto.client.ClientListDTO;
import com.examportal.user.model.Address;
import com.examportal.user.model.BaseUser;
import com.examportal.user.model.Client;
import com.examportal.user.model.Student;
import com.examportal.user.model.Subscription;
import com.examportal.user.repository.AddressRepository;
import com.examportal.user.repository.AdminRepository;
import com.examportal.user.repository.ClientRepository;
import com.examportal.user.repository.ExamAdminRepository;
import com.examportal.user.repository.StudentRepository;
import com.examportal.user.repository.SubscriptionRepository;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ExamAdminRepository examAdminRepo;
	@Autowired
	private ModelMapper mapper;


	@Override
	public AdminDTO authenticateAdmin(String email, String password) {
		return mapper.map(adminRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new com.examportal.user.exceptionhandler.custom_exception.UserNotFoundException("Invalid email or password")), AdminDTO.class);
	}
	
	@Override
	public List<ClientListDTO> getClientDetails() {
		List<ClientListDTO> clientList = clientRepo.getClientDetails().orElseThrow();
		 
		for(ClientListDTO c: clientList)
		{
			List<ExamAdminListDTO> examAdminList=examAdminRepo.findByClientId(c.getId()).orElseThrow();
			 c.setExamAdminCount(examAdminList.size());
			 for(ExamAdminListDTO e: examAdminList) {
				 List<Student> studentList=studentRepo.findByExamAdminId(e.getId()).orElseThrow();
				 c.setStudentCount(studentList.size());
			 }
//			 
//			 c.setStudentCount(studentList.size());
			 
		}
		return clientList;
	}
}
