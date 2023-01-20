package com.examportal.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.user.dto.SubscriptionDTO;
import com.examportal.user.model.Address;
import com.examportal.user.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	@Query("SELECT planName FROM Subscription")
	Optional<List<String>> getAllSubscription();
	
	Optional<Subscription> findByPlanName(String subscriptionName);
	
	@Query("SELECT new com.examportal.user.dto.SubscriptionDTO(s.id,s.planName,s.noOfExams,s.price) FROM Subscription s")
	Optional<List<SubscriptionDTO>> getAllSubscriptionDetails();
}
