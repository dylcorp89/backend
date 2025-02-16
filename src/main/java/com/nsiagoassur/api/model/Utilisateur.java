package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
        //this.password = encodePassword(password); // Ex: Encodage du mot de passe
    }

    private String encodePassword(String rawPassword) {
        // Implémentez votre logique d'encodage (ex: BCrypt)
        return rawPassword; // À remplacer par un encodeur
    }

    public String getPassword() {
        return this.password; //  risqué
    }
}
