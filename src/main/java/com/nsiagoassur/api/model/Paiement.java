package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaiement;
    private Float montant;
    private String datePaiement;
    private String modePaiement;
    
    @ManyToOne
    @JoinColumn(name = "id_subscription")
    private Subscription subscription;
}