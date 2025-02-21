package com.nsiagoassur.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsiagoassur.api.model.Categorie;
import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.repository.CategorieRepository;


import lombok.RequiredArgsConstructor;

@Service

public class CategorieService {
	  
	@Autowired
	  private CategorieRepository categorieRepository ;
	  
	  public List<Categorie> getAllCategorie() {
	        return categorieRepository.findAll();
	    }

	    public Optional<Categorie> findByCategorie(String codecategorie) {
	        return categorieRepository.findByCodeCategorie(codecategorie);
	    }

}
