package com.examportal.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.user.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findByPinCode(String pinCode);
	
	@Query("SELECT pinCode FROM Address")
	Optional<List<String>> getAllPincode();
	
	
}
