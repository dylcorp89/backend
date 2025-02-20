package com.nsiagoassur.api.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "subscription")
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer idSubscription;
    private String quoteReference;
    private String startDate;
    private String endDate;
    private Float price;
    private String status; // Ex: "ACTIVE", "PENDING", "EXPIRED"
    
  
    
    
    public Integer getIdSubscription() {
		return idSubscription;
	}
	public String getQuoteReference() {
		return quoteReference;
	}

	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_assure")  // Cette colonne pointe vers la clé primaire de Assure
	private Assure assure;
	
	
	@ManyToOne
	@JoinColumn(name = "id_vehicule")  // Cette colonne pointe vers la clé primaire de Vehicule
	private Vehicule vehicule;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_produit_assurance")  // Cette colonne pointe vers la clé primaire de Vehicule
	private ProduitAssurance assurance;
	
	
	public Assure getAssure() {
	    return assure;
	}

	public void setAssure(Assure assure) {
	    this.assure = assure;
	}

	
	public Vehicule getVehicule() {
	    return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
	    this.vehicule = vehicule;
	}

	

	public ProduitAssurance getProduitAssurance() {
	    return assurance;
	}

	public void setProduitAssurance(ProduitAssurance assurance) {
	    this.assurance = assurance;
	}

}
