package com.nsiagoassur.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Simulation;


@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Integer> {
    List<Simulation> findByAssure_IdAssure(Integer idAssure);
}

