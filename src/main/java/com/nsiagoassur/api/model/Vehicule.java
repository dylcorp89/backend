package com.nsiagoassur.api.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehicule;
    private String numeroMatricule;
    private String couleur;
    private Integer nbrSieges;
    private Integer nbrPortes;
    private String dateMiseEnCirculation;
    private Float valeurVenale;
    private Float valeurNeuf;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    @ToString.Exclude // Évite les boucles infinies
    @EqualsAndHashCode.Exclude
    private Categorie categorie;

	

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	public Integer getNbrSieges() {
		return nbrSieges;
	}

	public void setNbrSieges(Integer nbrSieges) {
		this.nbrSieges = nbrSieges;
	}

	public Integer getNbrPortes() {
		return nbrPortes;
	}

	public void setNbrPortes(Integer nbrPortes) {
		this.nbrPortes = nbrPortes;
	}

	public String getDateMiseEnCirculation() {
		return dateMiseEnCirculation;
	}

	public void setDateMiseEnCirculation(String dateMiseEnCirculation) {
		this.dateMiseEnCirculation = dateMiseEnCirculation;
	}

	public Float getValeurVenale() {
		return valeurVenale;
	}

	public void setValeurVenale(Float valeurVenale) {
		this.valeurVenale = valeurVenale;
	}

	public Float getValeurNeuf() {
		return valeurNeuf;
	}

	public void setValeurNeuf(Float valeurNeuf) {
		this.valeurNeuf = valeurNeuf;
	}
	
	public Categorie getCategorie() {
	    return categorie;
	}

	public void setCategorie(Categorie categorie) {
	    this.categorie = categorie;
	}

}
