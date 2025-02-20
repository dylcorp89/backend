package com.nsiagoassur.api.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "garantie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Garanties {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idGarantie;
	 	private String codeGarantie;
	    private String libelle;
	    
}
