package com.nsiagoassur.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsiagoassur.api.model.Utilisateur;

import java.util.Optional;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
	Optional<Utilisateur> findByLogin(String login);

}
