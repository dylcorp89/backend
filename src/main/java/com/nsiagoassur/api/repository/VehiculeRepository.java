package com.nsiagoassur.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Vehicule;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
    Optional<Vehicule> findByNumeroMatricule(String numeroMatricule);
}
