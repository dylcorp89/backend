package com.nsiagoassur.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
    List<Paiement> findBySubscription_IdSubscription(Integer idSubscription);
}