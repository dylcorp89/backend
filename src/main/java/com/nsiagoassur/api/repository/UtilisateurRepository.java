package com.nsiagoassur.api.repository;

import com.nsiagoassur.api.model.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
	Optional<Utilisateur> findByLogin(String login);

}
