package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Categorie;
import com.nsiagoassur.api.repository.CategorieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategorieInitializerService {

    @Autowired
    private CategorieRepository categorieRepository;

    @PostConstruct
    public void initCategories() {
    	System.out.println("Initialisation des catégories...");
        Map<String, String[]> defaultCategories = Map.of(
            "201", new String[]{"Promenade et Affaire", "Usage personnel"},
            "202", new String[]{"Véhicules Motorisés à 2 ou 3 roues", "Motocycle, tricycles"},
            "203", new String[]{"Transport public de voyage", "Véhicule transport de personnes"},
            "204", new String[]{"Véhicule de transport avec taximètres", "Taxis"}
        );
        
      /*  List<Categorie> existingCategories = categorieRepository.findAll();
        System.out.println("Catégories existantes en base : ");
        existingCategories.forEach(cat -> 
            System.out.println(cat.getCodeCategorie() + " - " + cat.getLibelleCategorie())
        );*/

      List<Categorie> categoriesToSave = defaultCategories.entrySet().stream().filter(entry -> categorieRepository.findByCodeCategorie(entry.getKey()).isEmpty())
            .map(entry -> {
                Categorie categorie = new Categorie();
                categorie.setCodeCategorie(entry.getKey());
                categorie.setLibelleCategorie(entry.getValue()[0]);
                categorie.setDescriptionCategorie(entry.getValue()[1]);
                return categorie;
            })
            .toList();

        if (!categoriesToSave.isEmpty()) {
            categorieRepository.saveAll(categoriesToSave);
            System.out.println(categoriesToSave.size() + " catégories ajoutées !");
        }
        
        
    }
}
