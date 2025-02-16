package com.nsiagoassur.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Attestation;

import java.util.Optional;

@Repository
public interface AttestationRepository extends JpaRepository<Attestation, Integer> {
    Optional<Attestation> findByNumeroPolice(String numeroPolice);
}

