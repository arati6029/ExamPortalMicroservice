package com.examportal.user.service;

import java.util.List;

import com.examportal.user.dto.ExamScheduleDTO;
import com.examportal.user.dto.StudentDTO;
import com.examportal.user.dto.StudentListDTO;
import com.examportal.user.model.Student;



public interface IStudentService {
	Student registerStudent(Student transientStudent, String pinCode,  long examAdminId);
	StudentDTO updateStudent(Student transientStudent, String pinCode);
	StudentDTO authenticateStudent(String email, String password);
	//String updateStudent(long id);
	//Student findById();
	//String updateStudent(StudentDTO newstudentDto);
	Student editPassword(long id,String oldPassword,String newPassword);
	
	String activateDeactivateStudent(long id);
	String getPassword(String email);
	List<StudentListDTO> getStudentList(long eaId);
	long getStudentIdByEmail(String email);
//	List<ExamListDTO> getExamListByStudent(Long sId);
}
