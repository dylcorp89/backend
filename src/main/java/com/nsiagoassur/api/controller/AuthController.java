package com.nsiagoassur.api.controller;


import com.nsiagoassur.api.model.Utilisateur;
import com.nsiagoassur.api.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentification", description = "Endpoints pour l'authentification des utilisateurs")

public class AuthController {
	
	  
	 private final AuthService authService;
	 private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	   
	  
	    public AuthController(AuthService authService) {
	     
	        this.authService = authService;
	    }
	    
    @PostMapping("/register")
    @Operation(summary = "Inscription d'un utilisateur", description = "Permet de créer un compte utilisateur")
    @ApiResponse(responseCode = "200", description = "Utilisateur inscrit avec succès")
   
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
        String token = authService.register(request.get("username"), request.get("password"), request.get("role")
        		, request.get("nom"), request.get("prenoms")
        		);
        
        return ResponseEntity.ok(Map.of("token", token));
     
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion d'un utilisateur", description = "Permet de se connecter avec un compte existant")
    @ApiResponse(responseCode = "200", description = "Connexion réussie")
    @ApiResponse(responseCode = "401", description = "Identifiants incorrects")
     public Map<String, String> login(@RequestBody Map<String, String> request) {
    	
    	Map<String, String> token = authService.login(request.get("username"), request.get("password"));
        return token;
    }
    
    @GetMapping("/liste")
    @Operation(summary = "Liste des utilisateurs", description = "Récupère la liste des utilisateurs")
    @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès")
    public List<Utilisateur> getUsersList() {
        return  authService.findAll();
    }
}