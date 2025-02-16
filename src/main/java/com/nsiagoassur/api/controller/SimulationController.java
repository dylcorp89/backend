package com.nsiagoassur.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.service.AttestationService;
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

    private final SimulationService simulationService = null;
    private final AttestationService attestationService = new AttestationService();
    private final SubscriptionService subscriptionService = null;

    
    @GetMapping
    
    public List<Simulation> getAllSimulations() {
        return simulationService.getAllSimulations();
    }

    @PostMapping
    @Operation(summary = "Creation d'une simulation", description = "Permet de créer une simulation")
    @ApiResponse(responseCode = "200", description = "Simulation créee avec succès")
    public Simulation createSimulation(@RequestBody Simulation simulation) {
        return simulationService.createSimulation(simulation);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer une simulation", description = "Permet de recuperer une simulation ")
    @ApiResponse(responseCode = "200", description = "Simulation trouvée")
    public ResponseEntity<Simulation> getSimulationById(@PathVariable Integer id) {
        return simulationService.getSimulationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/attestation")
    @Operation(summary = "Recuperation d'une attestation", description = "Permet de recupérer une attestation")
    @ApiResponse(responseCode = "200", description = "Attestion recupérée avec succès")
    @ApiResponse(responseCode = "401", description = "Attestion non trouvée")
public ResponseEntity<Resource> getAttestation(@PathVariable Integer id) {
    Optional<Subscription> subscriptionOpt = subscriptionService.getSubscriptionById(id);

    if (subscriptionOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Subscription subscription = subscriptionOpt.get();
    try {
        Resource attestation = attestationService.generateAttestation(subscription);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attestation.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(attestation);
    } catch (IOException e) {
        return ResponseEntity.internalServerError().build();
    }
}

}
