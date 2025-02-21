package com.nsiagoassur.api.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.nsiagoassur.api.service.AuthService;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;
    private String libelle;
    
    
    
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}