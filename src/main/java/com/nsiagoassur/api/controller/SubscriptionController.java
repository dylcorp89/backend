package com.nsiagoassur.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsiagoassur.api.model.Subscription;
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
    private final SubscriptionService subscriptionService = null;

    @PostMapping
    @Operation(summary = "Creation d'une souscription", description = "Permet de créer une souscription")
    @ApiResponse(responseCode = "200", description = "Souscription créee avec succès")
    @ApiResponse(responseCode = "401", description = "Erreur lors de la creation de la souscription")
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
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
}
