package com.nsiagoassur.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.ProduitAssurance;

@Repository
public interface ProduitAssuranceRepository extends JpaRepository<ProduitAssurance, Integer> {
    List<ProduitAssurance> findByAssure_IdAssure(Integer idAssure);
}
