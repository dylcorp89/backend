package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "simulation")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer idSimulation;
    private String quoteReference;
    private String startDate;
    private String endDate;
    private Float price;
    
    @ManyToOne
    @JoinColumn(name = "id_assure")
    private Assure assure;
}
