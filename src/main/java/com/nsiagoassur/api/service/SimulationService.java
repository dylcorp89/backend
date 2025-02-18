package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.repository.SimulationRepository;
import com.nsiagoassur.api.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class SimulationService {

	 @Autowired
	private SimulationRepository simulationRepository;
	 
	
    private final  JwtUtil jwtUtil = new JwtUtil() ;
    
    public Simulation createSimulation(Simulation simulation) {
    	
    	
        return simulationRepository.save(simulation);
    }

    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    public Optional<Simulation> getSimulationById(Integer id) {
        return simulationRepository.findById(id);
    }
}