package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter

@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    
    private String nom;
    private String prenoms;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
        name = "utilisateur_role",
        joinColumns = @JoinColumn(name = "id_utilisateur"),
        inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles;

    public void setLogin(String login) {
        this.login = login.trim().toLowerCase(); // Ex: Nettoyage du login
    }

    public void setPassword(String password) {
        this.password = password; 
       
    }



    public String getPassword() {
        return this.password; //  risqué
    }

    public void setRole(Role role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

}
