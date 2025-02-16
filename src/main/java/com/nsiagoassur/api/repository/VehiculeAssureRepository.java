package com.nsiagoassur.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.VehiculeAssure;

@Repository
public interface VehiculeAssureRepository extends JpaRepository<VehiculeAssure, Integer> {
    List<VehiculeAssure> findByAssure_IdAssure(Integer idAssure);
}
