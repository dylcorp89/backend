package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Utilisateur;
import com.nsiagoassur.api.dto.SimulationResponseDTO;
import com.nsiagoassur.api.repository.SimulationRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class SimulationService {

	 @Autowired
	private SimulationRepository simulationRepository;
	 
	
    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    public Optional<Simulation> findByQuote(String quote) {
        return simulationRepository.findByQuoteReference(quote);
    }

 // Méthodes utilitaires pour conversion sécurisée
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private float parseFloatOrDefault(String value, float defaultValue) {
        try {
            return value != null ? Float.parseFloat(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


	public SimulationResponseDTO createSimulation(Map<String, String> request) {
		// TODO Auto-generated method stub
		 float resultatPrime = 0.0f;
		 
		 
		 
		 // generation valeur aleatoire unique de 12 caractere commmencant pat QT
		   ReferenceGenerator reference =new ReferenceGenerator("QT");
		
		   String date_fin = "";
		   
		  
		   // element devrant servir au calcul
		   String typeVehicule =request.get("typeVehicule");
		   
		   String produitAssurance =request.get("produitAssurance");
		   
		   //convertir puissance en en float avant calcul
		   String valeurNeuf = request.get("valeurNeuf");
		   Float valeurNeufFloat = parseFloatOrDefault(valeurNeuf,0);
		   
		   String valeurVenal = request.get("valeurVenal");
		   Float valeurVenalFloat = parseFloatOrDefault(valeurVenal,0);
		   
		   
		    //convertir puissance en int
		   String puissance =request.get("puissance");
		   int puissanceInt = parseIntOrDefault(puissance,0);
		   
		   String dateCirculation = request.get("dateCirculation");
		   
		   String date_debut =request.get("startDate");
		   
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
	        
	        try {
	            Date date = sdf.parse(date_debut); // Convertir la chaîne en objet Date
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(date);
	            calendar.add(Calendar.WEEK_OF_YEAR, 2); // Ajouter 2 semaines

	             date_fin = sdf.format(calendar.getTime()); // Convertir la date en String
	            System.out.println("Date de fin : " + date_fin);
	        } catch (ParseException e) {
	            System.err.println("Erreur de parsing de la date");
	        }
		   
		   
		   
		  
		   
		 
		   
		   CalculService calculService = new CalculService();
		   
		   
		
		  switch (produitAssurance) {
		    case "Papillon":
		        resultatPrime = calculService.getPapillon(puissanceInt, valeurNeufFloat);
		        break;
		    case "Douby":
		        resultatPrime = calculService.getDouby(puissanceInt, valeurNeufFloat);
		        break;
		    case "Douyou":
		        resultatPrime = calculService.getDouyou(puissanceInt, valeurNeufFloat, valeurVenalFloat);
		        break;
		    case "Toutourisquou":
		        resultatPrime = calculService.getToutourisquou(puissanceInt, valeurNeufFloat, valeurVenalFloat);
		        break;
		    default:
		    	resultatPrime = 0.0f;
		}
		
        Simulation simulation = new Simulation();
        
        simulation.setPrice(resultatPrime);
        simulation.setQuoteReference(reference.generateReference());
        simulation.setStatus("Active");
        simulation.setEndDate(date_fin);
        simulation.setStartDate(date_debut);
        
        simulationRepository.save(simulation);
        
        return new SimulationResponseDTO( simulation.getQuoteReference(),
        		simulation.getEndDate(),
        		simulation.getPrice()
                );
	}
}



