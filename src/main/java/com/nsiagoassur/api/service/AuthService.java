package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Utilisateur;
import com.nsiagoassur.api.repository.UtilisateurRepository;
import com.nsiagoassur.api.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

@RequiredArgsConstructor

public class AuthService  {
    
    @Autowired
    private final UtilisateurRepository utilisateurRepository = null;
    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private final PasswordEncoder passwordEncoder = null;

    @Transactional
    public String register(String login, String password) {
        if (utilisateurRepository.findByLogin(login).isPresent()) {
            throw new IllegalArgumentException("Utilisateur déjà existant");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin(login);
        utilisateur.setPassword(passwordEncoder.encode(password));
        utilisateurRepository.save(utilisateur);

        return jwtUtil.generateToken(login);
    }

    public String login(String login, String password) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByLogin(login);

        if (utilisateur.isEmpty() || !passwordEncoder.matches(password, utilisateur.get().getPassword())) {
            throw new IllegalArgumentException("Identifiants incorrects");
        }

        return jwtUtil.generateToken(login);
    }
}
