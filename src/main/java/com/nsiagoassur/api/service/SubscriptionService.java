package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.repository.SimulationRepository;
import com.nsiagoassur.api.repository.SubscriptionRepository;
import com.nsiagoassur.api.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service

public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final JwtUtil jwtUtil;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, JwtUtil jwtUtil) {
        this.subscriptionRepository = subscriptionRepository;
        this.jwtUtil = jwtUtil;
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    public ResponseEntity<String> getSubscriptionStatus(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("L'ID de l'assuré est requis.");
        }

        List<Subscription> subscriptions = subscriptionRepository.findByAssure_IdAssure(id);

        if (subscriptions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String status = subscriptions.get(0).getStatus();
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.noContent().build();
    }

}
