package com.examportal.user.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.user.model.Admin;
import com.examportal.user.model.Client;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findByEmailAndPassword(String email,String password);
	Admin findByEmail(String email);
}
