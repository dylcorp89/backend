package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.ProduitAssurance;
import com.nsiagoassur.api.repository.ProduitAssuranceRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service

public class ProduitAssuranceInitializerService {

	@Autowired
    private  ProduitAssuranceRepository produitAssuranceRepository;

	
    @PostConstruct
    public void initProduitsAssurance() {
        List<String> defaultProduits = Arrays.asList("Papillon", "Douby", "Douyou", "Toutourisquou");

       
        
        List<ProduitAssurance> produitsToSave = defaultProduits.stream()
            .filter(nom -> produitAssuranceRepository.findByNomProduit(nom).isEmpty())
            .map(nom ->{
                ProduitAssurance produit = new ProduitAssurance();
                produit.setNomProduit(nom);
                return produit;
            })
            .toList();
        

        if (!produitsToSave.isEmpty()) {
            produitAssuranceRepository.saveAll(produitsToSave); // Enregistrement en une seule transaction
        }
    }
}
