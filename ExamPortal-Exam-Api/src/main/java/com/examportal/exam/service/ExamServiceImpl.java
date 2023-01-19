package com.examportal.exam.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.exam.dto.ExamListDTO;
import com.examportal.exam.dto.ExamScheduleDTO;
import com.examportal.exam.model.Exam;
import com.examportal.exam.repository.ExamRepository;

//import com.app.dao.ClientRepository;
//import com.app.dao.ExamAdminRepository;
//import com.app.dao.ExamRepository;
//import com.app.dto.ExamListDTO;
//import com.app.exceptionhandler.custom_exception.SubscriptionExpiredException;
//import com.app.exceptionhandler.custom_exception.UserNotFoundException;
//import com.app.pojos.Client;
//import com.app.pojos.Exam;
//import com.app.pojos.ExamAdmin;
//import com.examportal.exam.dto.ExamScheduleDTO;



@Service
@Transactional
public class ExamServiceImpl implements IExamService {
	@Autowired
	private ExamRepository examRepo;

	@Override
	public Exam createExam(Exam transientExam, Long ExamAdminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String activateDeactivateExam(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamListDTO> getAllExamByExamAdminId(Long ExamAdminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamScheduleDTO> getExamScheduleByClientId(long cId) {
		// TODO Auto-generated method stub
		return examRepo.getExamSchedule(cId).orElseThrow();
		
	}

	//	@Autowired
//	ExamAdminRepository examAdminRepo;
//	@Autowired
//	ClientRepository clientRepo;

	
}
