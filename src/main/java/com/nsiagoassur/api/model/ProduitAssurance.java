package com.nsiagoassur.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produit_assurance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduitAssurance;
    private String nomProduit;
    
    
    
  public String getNomProduit() {
		return nomProduit;
	}



	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
}