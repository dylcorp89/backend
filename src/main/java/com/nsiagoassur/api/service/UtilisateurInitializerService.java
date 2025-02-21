package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Role;
import com.nsiagoassur.api.model.Utilisateur;
import com.nsiagoassur.api.repository.RoleRepository;
import com.nsiagoassur.api.repository.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service

public class UtilisateurInitializerService {

	@Autowired
    private UtilisateurRepository utilisateurRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private  PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUtilisateurs() {
        createUtilisateurIfNotExists("admin", "chance@@@@##4599_", "Administrateur", "Administrateur", "Admin");
        createUtilisateurIfNotExists("user", "user123", "Utilisateur", "Simple", "Amazone");
    }

    private void createUtilisateurIfNotExists(String login, String password, String nom, String prenoms, String roleName) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findByLogin(login);
        if (existingUser.isEmpty()) {
            Utilisateur user = new Utilisateur();
            user.setNom(nom);
            user.setPrenoms(prenoms);
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password)); // Encodage sécurisé

            Role role = roleRepository.findByLibelle(roleName).orElse(null);
            if (role != null) {
                user.setRole(role); // Assigne le rôle
            }

            utilisateurRepository.save(user);
        }
    }
}
