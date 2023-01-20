package com.examportal.user.service;

import java.util.List;

import com.examportal.user.dto.SubscriptionDTO;
import com.examportal.user.model.Subscription;


public interface ISubscriptionService {
	List<String> getSubscriptionList();
	Subscription addSubscription(Subscription transientSubscription);
	Subscription editSubscription(long id,Subscription transientSubscription);
	String deleteSubscription(long id);
	List<SubscriptionDTO> getSubscriptionDetailsList();
	Subscription getSubscriptionDetails(long id);
}
