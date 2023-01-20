package com.examportal.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.user.repository.AddressRepository;

import exceptionhandler.custom_exception.ResourceNotFoundException;



@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public List<String> getAllPincodes() {
		return addressRepo.getAllPincode().orElseThrow(() -> new ResourceNotFoundException("Pincode List is Empty"));
	}
}
