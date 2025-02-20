package com.nsiagoassur.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nsiagoassur.api.dto.SimulationResponseDTO;
import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.service.AttestationService;
import com.nsiagoassur.api.service.AuthService;
import com.nsiagoassur.api.service.CalculService;
import com.nsiagoassur.api.service.SimulationService;
import com.nsiagoassur.api.service.SubscriptionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/simulations")
@RequiredArgsConstructor
@Tag(name = "Simulation", description = "Endpoints pour la simulation d'un produit")
public class SimulationController {


    private final SimulationService simulationService ;
	
	    
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping
    public List<Simulation> getAllSimulations() {
        return simulationService.getAllSimulations();
    }
    
    
    @PostMapping
    @Operation(summary = "Création d'une simulation", description = "Permet de créer une simulation")
    @ApiResponse(responseCode = "200", description = "Simulation créée avec succès")
    public ResponseEntity<SimulationResponseDTO> createSimulation(@RequestBody Map<String, String> request) {
    	
    	
    	//Simulation simulation = new Simulation();
        SimulationResponseDTO response = simulationService.createSimulation(request);
        
        return ResponseEntity.ok(response);
    }

  

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une simulation", description = "Permet de récupérer une simulation par son ID")
    @ApiResponse(responseCode = "200", description = "Simulation trouvée")
    public ResponseEntity<Simulation> getSimulationByQuoteReference(@PathVariable("id") String id) {
        return simulationService.findByQuote(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    
}
