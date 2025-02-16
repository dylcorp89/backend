package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicule_assure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeAssure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;
    
    @ManyToOne
    @JoinColumn(name = "id_assure")
    private Assure assure;
}