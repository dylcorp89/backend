package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "subscription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubscription;
    private String quoteReference;
    private String startDate;
    private String endDate;
    private Float price;
    private String status; // Ex: "ACTIVE", "PENDING", "EXPIRED"
    @ManyToOne
    @JoinColumn(name = "id_assure")
    private Assure assure;

    
}
