package com.examportal.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examportal.user.dto.SubscriptionDTO;
import com.examportal.user.exceptionhandler.custom_exception.ResourceNotFoundException;
import com.examportal.user.model.Subscription;
import com.examportal.user.repository.SubscriptionRepository;



@Service
@Transactional
public class SubscriptionServiceImpl implements ISubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepo;
	
	@Override
	public List<String> getSubscriptionList() {
		return subscriptionRepo.getAllSubscription().orElseThrow(() -> new ResourceNotFoundException("Subscription List is Empty"));
	}
	
	@Override
	public Subscription addSubscription(Subscription transientSubscription) {
		return subscriptionRepo.save(transientSubscription);
	}
	
	@Override
	public Subscription editSubscription(long id, Subscription transientSubscription) {
		Subscription subscription = subscriptionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid subscription Id"));
		transientSubscription.setDateStamp(subscription.getDateStamp());
		transientSubscription.setId(id);
		return subscriptionRepo.save(transientSubscription);
	}
	
	@Override
	public String deleteSubscription(long id) {
		Subscription subscription = subscriptionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid subscription Id"));
		subscriptionRepo.delete(subscription);
		
		return "Subscription deleted successfully";
	}
	
	@Override
	public List<SubscriptionDTO> getSubscriptionDetailsList() {
		
		return subscriptionRepo.getAllSubscriptionDetails().orElseThrow(() -> new ResourceNotFoundException(" Subscription list is empty"));
	}
	
	@Override
	public Subscription getSubscriptionDetails(long id) {
		
		return subscriptionRepo.findById(id).orElseThrow();
	}
}
