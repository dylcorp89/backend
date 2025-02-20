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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsiagoassur.api.dto.SimulationResponseDTO;
import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.service.AttestationService;
import com.nsiagoassur.api.service.SimulationService;
import com.nsiagoassur.api.service.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/subscriptions")

@RequiredArgsConstructor
@Tag(name = "Souscription", description = "Endpoints pour la souscription")
public class SubscriptionController {

	@Autowired
    private final  SubscriptionService subscriptionService ;
	
	@Autowired
    private final  AttestationService attestationService;
	
	 public SubscriptionController(SubscriptionService subscriptionService,AttestationService attestationService) {
	        this.subscriptionService = subscriptionService;
	        this.attestationService = attestationService;
	    }
	
	@GetMapping
    public List<Subscription> getAllSimulations() {
        return subscriptionService.getAllSubscriptions();
    }

	@PostMapping
	@Operation(summary = "Création d'une souscription", description = "Permet de créer une souscription")
	@ApiResponse(responseCode = "200", description = "Souscription créée avec succès")
	@ApiResponse(responseCode = "400", description = "Erreur lors de la création de la souscription")
	public ResponseEntity<Subscription> createSubscription( @RequestBody Map<String, String> request) {
		
	    Subscription createdSubscription = subscriptionService.createSubscription(request);
	    return ResponseEntity.ok(createdSubscription);
	}

    @GetMapping("/{id}")
     @Operation(summary = "Recuperation d'une souscription", description = "Permet de recupérer une souscription")
    @ApiResponse(responseCode = "200", description = "Souscription trouvée")
    @ApiResponse(responseCode = "401", description = "Souscription non trouvée")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Integer id) {
        return subscriptionService.getSubscriptionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{id}")
    @Operation(summary = "Recuperation du status de la souscription", description = "Permet de recupérer le status d'une souscription")
    @ApiResponse(responseCode = "200", description = "Succès")

    public ResponseEntity<String> getSubscriptionStatus(@PathVariable Integer id) {
        return subscriptionService.getSubscriptionStatus(id);
    }
    
    
    @GetMapping("/{id}/attestation")
    @Operation(summary = "Récupération d'une attestation", description = "Permet de récupérer une attestation")
    @ApiResponse(responseCode = "200", description = "Attestation récupérée avec succès")
    @ApiResponse(responseCode = "404", description = "Ressource non trouvée")
    public ResponseEntity<Resource> getAttestation(@PathVariable("id") Integer id) {
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
