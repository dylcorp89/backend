package com.nsiagoassur.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.ProduitAssurance;

@Repository
public interface ProduitAssuranceRepository extends JpaRepository<ProduitAssurance, Integer> {
    Optional<ProduitAssurance> findById(Integer id);
    
    Optional<ProduitAssurance> findByNomProduit(String nomProduit);
}
