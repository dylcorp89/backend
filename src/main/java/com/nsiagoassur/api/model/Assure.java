package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;


@Entity
@Table(name = "assure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAssure;
    private String nom;
    private String prenoms;
    private String telephone;
    private String ville;
    private String adresse;
    private String numeroCarteIdentite;
    
    @OneToMany(mappedBy = "assure")
    private Set<VehiculeAssure> vehicules;
}