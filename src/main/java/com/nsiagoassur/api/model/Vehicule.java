package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehicule;
    private String marque;
    private String numeroMatricule;
    private String couleur;
    private Integer nbrSieges;
    private Integer nbrPortes;
}