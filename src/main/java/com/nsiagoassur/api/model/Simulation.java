package com.nsiagoassur.api.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "simulation")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer idSimulation;
    private String quoteReference;
   
    private Float price;
    private String status;
    private String startDate;
    private String  endDate;
    
	public String getQuoteReference() {
		return quoteReference;
	}
	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
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
  
}
