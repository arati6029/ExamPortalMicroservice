package com.examportal.user.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.user.dto.ExamAdminDTO;
import com.examportal.user.dto.ExamAdminListDTO;
import com.examportal.user.exceptionhandler.custom_exception.ResourceNotFoundException;
import com.examportal.user.exceptionhandler.custom_exception.UserNotFoundException;
import com.examportal.user.model.ExamAdmin;
import com.examportal.user.repository.AddressRepository;
import com.examportal.user.repository.ClientRepository;
import com.examportal.user.repository.ExamAdminRepository;

import net.bytebuddy.implementation.bytecode.Throw;


@Service
@Transactional
public class ExamAdminServiceImpl implements IExamAdminService {
	
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private ExamAdminRepository examAdminRepo;
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
	public ExamAdmin registerExamAdmin(ExamAdmin transientExamAdmin, String pinCode, long clientId) {
		transientExamAdmin.setClient(clientRepo.findById(clientId).orElseThrow(() -> new UserNotFoundException("Invalid Client Id")));
		transientExamAdmin.setAddress(addressRepo.findById(pinCode).orElseThrow(() -> new ResourceNotFoundException("Invalid Pincode")));
		return examAdminRepo.save(transientExamAdmin);
	}
	
	@Override
	public ExamAdminDTO authenticateExamAdmin(String email, String password) {
		return mapper.map(examAdminRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new UserNotFoundException("Invalid email or password")), ExamAdminDTO.class);
	}
	
	@Override
	public ExamAdminDTO updateExamAdmin(ExamAdmin transientExamAdmin, String pinCode) {
		ExamAdmin examAdmin = examAdminRepo.findById(transientExamAdmin.getId()).orElseThrow(() -> new UserNotFoundException("Invalid Exam Admin Id"));
		transientExamAdmin.setClient(examAdmin.getClient());
		transientExamAdmin.setPassword(examAdmin.getPassword());
		transientExamAdmin.setAccStatus(examAdmin.isAccStatus());
		transientExamAdmin.setDateStamp(examAdmin.getDateStamp());
		transientExamAdmin.setRole(examAdmin.getRole());
		transientExamAdmin.setAddress(addressRepo.findById(pinCode).orElseThrow());
		examAdminRepo.save(transientExamAdmin);
		return mapper.map(transientExamAdmin, ExamAdminDTO.class);
	}
//	@Override
//	public  ExamAdmin editPassword(long id, String newPassword) {
//		ExamAdmin examAdmin=examAdminRepo.findById(id).orElseThrow();
//		examAdmin.setPassword(newPassword);
//		return examAdminRepo.save(examAdmin);
//	}

	@Override
	public ExamAdmin editPassword(long id, String oldPassword, String newPassword) {
		ExamAdmin examadmin=examAdminRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid Exam Admin Id"));
		//if(examadmin.getPassword().equals(oldPassword) )
			examadmin.setPassword(encoder.encode(newPassword));
	//	else
			//throw new IncorrectPasswordException("Old Pasword did not Match");
		return examAdminRepo.save(examadmin);
	}
	
	@Override
	public String getPassword(String email) {
		return examAdminRepo.findPasswordByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Invalid email"));
	}
	
	@Override
	public List<ExamAdminListDTO> getExamAdminList(long cId) {
		return examAdminRepo.findByClientId(cId).orElseThrow(() -> new ResourceNotFoundException("Exam Admin List is Empty"));
	}

	@Override
	public String activateDeactivateExamAdmin(long id) {
		ExamAdmin examAdmin = examAdminRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid Client Id"));
		if (examAdmin.isAccStatus()) {
			examAdmin.setAccStatus(false);
			return "Exam Admin Account Deactivated SuccessFully";
		} else {
			examAdmin.setAccStatus(true);
			return "Exam Admin Account Activated SuccessFully";
		}
	}
	
	@Override
	public long getExamAdminIdByEmail(String email) {
		return examAdminRepo.findIdByEmail(email).orElseThrow(() -> new UserNotFoundException("Invalid Email"));
	}
}
