package com.examportal.exam.repository;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

//import com.app.dto.ExamListDTO;
//import com.app.dto.ExamScheduleDTO;
//import com.app.pojos.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.exam.dto.ExamListDTO;
import com.examportal.exam.dto.ExamScheduleDTO;
import com.examportal.exam.model.Exam;

//import com.app.dto.ExamScheduleDTO;
//import com.app.pojos.Exam;




public interface ExamRepository extends JpaRepository<Exam, Long>{
	@Query("SELECT new com.app.dto.ExamListDTO(e.id, e.examName, ea.id, e.scheduledDate, e.scheduledTime, e.examStatus, e.examDuration) FROM Exam e INNER JOIN ExamAdmin ea ON e.examAdmin=ea.id INNER JOIN Client c ON c.id=ea.client WHERE ea.client.id=?1")
	Optional<List<ExamListDTO>> findExamByClientId(Long id);//constructor expression
	@Query("SELECT new com.app.dto.ExamScheduleDTO(e.examName,e.scheduledDate,e.scheduledTime, e.resultDate) FROM Exam e INNER JOIN ExamAdmin ea ON e.examAdmin=ea.id WHERE ea.client.id=?1")
	Optional<List<ExamScheduleDTO>> getExamSchedule(long cId);
	@Query("SELECT new com.app.dto.ExamListDTO(e.id, e.examName,e.examAdmin.id, e.scheduledDate, e.scheduledTime,e.marksPerQues,e.negMarksPerQues,e.noOfQues) FROM Exam e where e.examAdmin.id=?1")
	Optional<List<ExamListDTO>> findExamByExamAdminId(Long examAdminId);
}
