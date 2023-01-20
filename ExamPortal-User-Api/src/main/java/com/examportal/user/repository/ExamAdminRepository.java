package com.examportal.user.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.user.dto.ExamAdminDTO;
import com.examportal.user.dto.ExamAdminListDTO;
import com.examportal.user.model.Client;
import com.examportal.user.model.ExamAdmin;
import com.examportal.user.model.Subscription;


public interface ExamAdminRepository extends JpaRepository<ExamAdmin, Long>{
	@Query("SELECT e FROM ExamAdmin e WHERE e.email =?1 AND e.password =?2")
	Optional<ExamAdmin> findByEmailAndPassword(String email,String password);
	
	@Query("SELECT e FROM ExamAdmin e WHERE e.id =?1")
	Optional<ExamAdmin> findById(Long id);
	
	@Query("SELECT e.password FROM ExamAdmin e WHERE e.email=?1")
	Optional<String> findPasswordByEmail(String email);
	
	@Query("SELECT e.id FROM ExamAdmin e WHERE e.email=?1")
	Optional<Long> findIdByEmail(String email);
	
	@Query("SELECT new com.examportal.user.dto.ExamAdminListDTO(e.id, e.name, e.email, e.department, e.accStatus) FROM ExamAdmin e WHERE e.clientId=?1")
	Optional<List<ExamAdminListDTO>> findByClientId(long clientId);
	
//	@Query("SELECT e FROM ExamAdmin e JOIN FETCH e.(new com.app.pojos.Client(c.id, c.name, c.email, c.mobile, c.addressLine1, c.addressLine2,c.subscription, c.examCount)) c WHERE e.email =?1 ")
	ExamAdmin findByEmail(String email);
	
	@Query("SELECT new com.examportal.user.dto.ExamAdminDTO(e.id, e.name, e.email, e.mobile, e.role, e.accStatus,e.dateStamp, e.addressLine1, e.addressLine2, e.pincode, e.department, e.clientId, e.dob, e.gender) FROM ExamAdmin e WHERE e.email =?1 ")
//	@Query("SELECT new com.app.dto.EADTO(e.id)  FROM ExamAdmin e INNER JOIN  e.client c WHERE e.email =?1")
	ExamAdminDTO findDTOByEmail(String email);
}
