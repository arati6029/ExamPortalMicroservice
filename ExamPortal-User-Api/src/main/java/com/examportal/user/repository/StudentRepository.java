package com.examportal.user.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.user.dto.StudentDTO;
import com.examportal.user.dto.StudentListDTO;
import com.examportal.user.model.Address;
import com.examportal.user.model.Client;
import com.examportal.user.model.ExamAdmin;
import com.examportal.user.model.GenderEnum;
import com.examportal.user.model.RoleEnum;
import com.examportal.user.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long>{
	@Query("SELECT s FROM Student s WHERE s.email =?1 AND s.password =?2")
	Optional<Student> findByEmailAndPassword(String email,String password);
	
	@Query("SELECT s.password FROM Student s WHERE s.email=?1")
	Optional<String> findPasswordByEmail(String email);
	
	@Query("SELECT s.id FROM Student s WHERE s.email=?1")
	Optional<Long> findIdByEmail(String email);
	
	@Query("SELECT new com.examportal.user.dto.StudentListDTO(s.id, s.rollNo, s.name, s.email,s.dob, s.gender, s.accStatus) FROM Student s WHERE s.examAdminId=?1 ORDER BY s.rollNo")
	Optional<List<StudentListDTO>> findListByExamAdminId(long eaId);
	
//	@Query("SELECT s FROM Student s JOIN FETCH s.client c WHERE c.id=?1")
//	Optional<List<Student>> findByClientId(Long cId);
	@Query("SELECT s FROM Student s WHERE s.examAdminId=?1")
	Optional<List<Student>> findByExamAdminId(long examAdminId);
	@Query("SELECT s FROM Student s WHERE s.id=?1")
	Optional<Student> findById(long id);
	
	Student findByEmail(String email);
	
	@Query("SELECT new com.examportal.user.dto.StudentDTO(e.id, e.name, e.email, e.mobile, e.role, e.accStatus,e.dateStamp, e.addressLine1, e.addressLine2, e.pincode, e.rollNo, e.examAdminId, e.dob, e.gender) FROM Student e WHERE e.email =?1 ")
	StudentDTO findDTOByEmail(String email);
	//@Query("SELECT new com.app.dto.ExamListDTO() FROM Student s INNER JOIN Client c on s.client.id=c.id INNER JOIN Result r on s.id=r.student.id where ")
//	 @Query("select new com.app.dto.ExamListDTO(e.id, e.examName, ea.id, e.scheduledDate, e.scheduledTime, e.examStatus, e.examDuration) from Exam e inner join ExamAdmin ea on e.examAdmin.id=ea.id inner join Client c on ea.client.id=c.id where e.id not in( select r.exam.id from Result r where r.student.id=?1 and r.exam.id in (select e.id from Exam e inner join ExamAdmin ea on e.examAdmin.id=ea.id inner join Client c on ea.client.id=c.id))")
//	Optional<List<ExamListDTO>> findExamListByStudentId(Long id);

}
