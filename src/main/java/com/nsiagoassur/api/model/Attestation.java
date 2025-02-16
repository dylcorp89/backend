package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attestation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAttestation;
    private String numeroPolice;
    private String dateExpiration;
    
    @ManyToOne
    @JoinColumn(name = "id_subscription")
    private Subscription subscription;
}