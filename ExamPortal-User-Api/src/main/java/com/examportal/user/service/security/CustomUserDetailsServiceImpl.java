package com.examportal.user.service.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examportal.user.model.BaseUser;
import com.examportal.user.repository.AdminRepository;
import com.examportal.user.repository.ClientRepository;
import com.examportal.user.repository.ExamAdminRepository;
import com.examportal.user.repository.StudentRepository;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	// dep : UserRepo
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private ExamAdminRepository examAdminRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private AdminRepository adminRepo;

	// As per Spring sec : this method should throw UsernameNotFoundException :
	// if an UserDetailsService implementation cannot locate a User by its
	// username.(currently email)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		BaseUser authenticatedUser=null;
		if((authenticatedUser=clientRepo.findByEmail(email))==null)
			if((authenticatedUser=examAdminRepo.findByEmail(email))==null)
				if((authenticatedUser=studentRepo.findByEmail(email))==null)
					if((authenticatedUser=adminRepo.findByEmail(email))==null)
						throw new UsernameNotFoundException("Invalid Email ID ");
		return new CustomUserDetails(authenticatedUser);
	}

}
