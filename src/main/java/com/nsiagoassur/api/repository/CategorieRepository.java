package com.nsiagoassur.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Categorie;




@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
	
	Optional<Categorie> findByCodeCategorie(String codeCategorie);

}
