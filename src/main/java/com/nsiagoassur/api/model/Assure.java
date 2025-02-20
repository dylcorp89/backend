package com.nsiagoassur.api.model;



import java.util.Set;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "assure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAssure;
    private String nom;
    private String prenoms;
    private String telephone;
    private String ville;
    private String adresse;
    private String numeroCarteIdentite;
    
 // Getters et Setters
    public Integer getIdAssure() {
        return idAssure;
    }

    public void setIdAssure(Integer idAssure) {
        this.idAssure = idAssure;
    }
    
    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumeroCarteIdentite() {
		return numeroCarteIdentite;
	}

	public void setNumeroCarteIdentite(String numeroCarteIdentite) {
		this.numeroCarteIdentite = numeroCarteIdentite;
	}

	@OneToMany(mappedBy = "assure")
    private Set<VehiculeAssure> vehicules;
}