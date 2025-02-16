package com.nsiagoassur.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsiagoassur.api.model.Assure;

import java.util.List;
import java.util.Optional;
@Repository
public interface AssureRepository extends JpaRepository<Assure, Integer> {
    List<Assure> findByVille(String ville);
    Optional<Assure> findByNumeroCarteIdentite(String numeroCarteIdentite);
}
