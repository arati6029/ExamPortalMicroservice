package com.examportal.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.examportal.user.dto.ExamScheduleDTO;
import com.examportal.user.dto.StudentDTO;
import com.examportal.user.dto.StudentListDTO;
import com.examportal.user.exceptionhandler.custom_exception.ResourceNotFoundException;
import com.examportal.user.exceptionhandler.custom_exception.UserNotFoundException;
import com.examportal.user.model.Student;
import com.examportal.user.repository.AddressRepository;
import com.examportal.user.repository.ClientRepository;
import com.examportal.user.repository.ExamAdminRepository;
import com.examportal.user.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ExamAdminRepository examAdminRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
//@Autowired
//private RestTemplate restTemplate;
	@Override
	public Student registerStudent(Student transientStudent, String pinCode, long examAdminId) {
		transientStudent.setExamAdmin(examAdminRepo.findById(examAdminId).orElseThrow(()->new UserNotFoundException("Invalid Exam Admin Id")));
		transientStudent.setAddress(
				addressRepo.findById(pinCode).orElseThrow(() -> new ResourceNotFoundException("Invalid Pincode")));
		return studentRepo.save(transientStudent);
	}

	@Override
	public StudentDTO authenticateStudent(String email, String password) {
		return mapper.map(studentRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new UserNotFoundException("Invalid email and password")), StudentDTO.class);
	}

	@Override
	public Student editPassword(long id, String oldPassword, String newPassword) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid Student Id"));
		//student.getEmail();
		System.out.println(oldPassword);
		
//		if (student.getPassword().equals(oldPassword))
			student.setPassword(encoder.encode(newPassword));
//			}
//		else
//			throw new IncorrectPasswordException("Old password is not correct");

		return studentRepo.save(student);
	}

	

//	@Override
//	public String updateStudent(StudentDTO newstudentDto) {
//		Student student = studentRepo.findById(newstudentDto.getId()).orElseThrow();
//		
//		return studentRepo.updateStudentDetails(newstudentDto.getId(),newstudentDto.getAddressLine1(),newstudentDto.getAddressLine2(),newstudentDto.getDob(),newstudentDto.getMobile());
//	}

	@Override
	public StudentDTO updateStudent(Student transientStudent, String pinCode) {
		Student student = studentRepo.findById(transientStudent.getId()).orElseThrow(() -> new UserNotFoundException("Invalid Student Id"));
		transientStudent.setAccStatus(student.isAccStatus());
		transientStudent.setExamAdmin(student.getExamAdmin());;
		transientStudent.setPassword(student.getPassword());
		transientStudent.setDateStamp(student.getDateStamp());
		transientStudent.setRole(student.getRole());
		transientStudent.setAddress(addressRepo.findById(pinCode).orElseThrow(() -> new UserNotFoundException("Invalid Student Id")));
		StudentDTO studentDTO = mapper.map(transientStudent, StudentDTO.class);
		studentRepo.save(transientStudent);
	   return studentDTO;
	}

	@Override
	public String activateDeactivateStudent(long id) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Student Id"));
		if (student.isAccStatus()) {
			student.setAccStatus(false);
			return "Student Account Deactivated SuccessFully";
		} else {
			student.setAccStatus(true);
			return "Student Account Activated SuccessFully";
		}
	}

	@Override
	public String getPassword(String email) {
		return studentRepo.findPasswordByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Invalid email"));
	}
	
	@Override
	public List<StudentListDTO> getStudentList(long eaId) {
		return studentRepo.findListByExamAdminId(eaId).orElseThrow(() -> new ResourceNotFoundException("Student List is Empty"));
	}
	
	@Override
	public long getStudentIdByEmail(String email) {
		return studentRepo.findIdByEmail(email).orElseThrow(() -> new UserNotFoundException("Invalid Email"));
	}

//	@Override
//	public List<ExamListDTO> getExamListByStudent(Long sId) {
//		
//		return studentRepo.findExamListByStudentId(sId).orElseThrow();
//	}
}
