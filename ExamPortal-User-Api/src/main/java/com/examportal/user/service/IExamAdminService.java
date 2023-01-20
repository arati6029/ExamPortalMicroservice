package com.examportal.user.service;

import java.util.List;

import com.examportal.user.dto.ExamAdminDTO;
import com.examportal.user.dto.ExamAdminListDTO;
import com.examportal.user.model.ExamAdmin;



public interface IExamAdminService {
	ExamAdmin registerExamAdmin(ExamAdmin transientExamAdmin, String pinCode,  long clientId);
	ExamAdminDTO authenticateExamAdmin(String email, String password);
	
	ExamAdmin editPassword(long id, String oldPassword, String newPassword);
	ExamAdminDTO updateExamAdmin(ExamAdmin transientExamAdmin, String pinCode);
	String getPassword(String email);
	List<ExamAdminListDTO> getExamAdminList(long cId);
	String activateDeactivateExamAdmin(long id);
	long getExamAdminIdByEmail(String email);
}
