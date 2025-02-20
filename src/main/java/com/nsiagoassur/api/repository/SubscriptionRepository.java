package com.nsiagoassur.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.model.Vehicule;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
  
	Optional<Subscription> findByQuoteReference(String quoteReference);
   
    @Query("SELECT s FROM Subscription s WHERE s.startDate <= :date AND s.endDate >= :date")
    
    List<Subscription> findActiveSubscriptions(@Param("date") String date);
}