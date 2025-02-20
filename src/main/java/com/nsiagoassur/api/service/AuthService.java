package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Role;
import com.nsiagoassur.api.model.Utilisateur;
import com.nsiagoassur.api.repository.RoleRepository;
import com.nsiagoassur.api.repository.UtilisateurRepository;
import com.nsiagoassur.api.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Map;

@Service

@RequiredArgsConstructor

public class AuthService  {
    
	 
	    private final UtilisateurRepository utilisateurRepository ;
	  

	    private final RoleRepository roleRepository ;
	  
		   
	    private final JwtUtil jwtUtil;
	 
	    private final PasswordEncoder passwordEncoder;

	    public AuthService(JwtUtil jwtUtil,
	    		PasswordEncoder passwordEncoder,UtilisateurRepository utilisateurRepository,RoleRepository roleRepository) {
	        this.jwtUtil = jwtUtil;
	      
	        this.passwordEncoder = passwordEncoder;
	        
	        this.utilisateurRepository = utilisateurRepository;
	        this.roleRepository = roleRepository;
	      
	    }
	    
	
	 

    @Transactional
    public String register(String login, String password,String role , String nom , String prenoms) throws IllegalArgumentException {
        if (utilisateurRepository.findByLogin(login).isPresent()) {
            throw new IllegalArgumentException("Utilisateur déjà existant");
        }

        //Récupérer un rôle
        Role defaultRole = roleRepository.findByLibelle(role).orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé"));
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin(login);
        utilisateur.setNom(nom);;
        utilisateur.setPrenoms(prenoms);;
        utilisateur.setPassword(passwordEncoder.encode(password));
        utilisateur.setRole(defaultRole);;
        utilisateurRepository.save(utilisateur);

        return "ok";
    }

    public Map<String, String> login(String login, String password) {
    	  Optional<Utilisateur> utilisateur = utilisateurRepository.findByLogin(login);

          if (utilisateur.isEmpty() || !passwordEncoder.matches(password, utilisateur.get().getPassword())) {
              throw new IllegalArgumentException("Identifiants incorrects");
          }

        String token = jwtUtil.generateToken(login);
        return Map.of("token", token);
        
    }
}
