package com.examportal.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.user.dto.LoginRequestDTO;
import com.examportal.user.dto.client.ClientDTO;
import com.examportal.user.dto.client.ClientListDTO;
import com.examportal.user.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByEmailAndPassword(String email, String password);

	@Query("SELECT new com.app.dto.client.ClientListDTO(c.id, c.name, c.email, c.mobile, c.accStatus,\r\n"
			+ "c.dateStamp, c.addressLine1, c.addressLine2, c.address,\r\n"
			+ "c.subscription, c.examCount) FROM Client c")
	Optional<List<ClientListDTO>> getClientDetails();

	
	@Query("SELECT c.password FROM Client c WHERE c.email=?1")
	Optional<String> findPasswordByEmail(String email);
	
	@Query("SELECT c.id FROM Client c WHERE c.email=?1")
	Optional<Long> findIdByEmail(String email);
	
	
	Client findByEmail(String email);
	
}
