package com.nsiagoassur.api.service ;

import com.nsiagoassur.api.model.Assure;
import com.nsiagoassur.api.model.ProduitAssurance;
import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.model.Vehicule;
import com.nsiagoassur.api.repository.AssureRepository;
import com.nsiagoassur.api.repository.ProduitAssuranceRepository;
import com.nsiagoassur.api.repository.SimulationRepository;
import com.nsiagoassur.api.repository.SubscriptionRepository;
import com.nsiagoassur.api.repository.VehiculeRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor

public class SubscriptionService {

	@Autowired
    private SubscriptionRepository subscriptionRepository;
    
	@Autowired
	private AssureRepository assureRepository;
	
	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@Autowired
	private ProduitAssuranceRepository produitAssuranceRepository;
	


   

	public Subscription createSubscription(Map<String, String> request) {

		ReferenceGenerator reference =new ReferenceGenerator();
		
		 String date_fin = "";
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
	        
		String dateCirculation =request.get("dateCirculation");
		String couleurVehicule =request.get("couleurVehicule");
		
		String  porteVehicule =request.get("nbrePorteVehicule");
		int porteVehiculeInt = Integer.parseInt(porteVehicule);
		
		String status ="Active";
		
		String immatVehicule =request.get("numImmatVehicule");
		
		String siegeVehicule =request.get("nbreSiegeVehicule");
		int siegeVehiculeInt = Integer.parseInt(siegeVehicule);
		
		String categorieVehicule =request.get("typeVehicule");
		
		String valeurVenaleVehicule = request.get("valeurVenaleVehicule");
		Float valeurVenaleVehiculeFloat = Float.parseFloat(valeurVenaleVehicule);
		
		String price = request.get("price");
		Float priceFloat = Float.parseFloat(price);
		
		String valeurNeufVehicule = request.get("valeurNeufVehicule");
		Float valeurNeufVehiculeFloat = Float.parseFloat(valeurNeufVehicule);
		
		String nomProduit =request.get("nomProduit");
		
		String adresseAssure =request.get("adresseAssure");
		String telephoneAssure =request.get("telephoneAssure");
		String nomAssure =request.get("nomAssure");
		String prenomsAssure =request.get("prenomsAssure");
		String numeCniAssure =request.get("numeCniAssure");
		String villeAssure =request.get("villeAssure");
		
		// enregistrer assure
		
		Assure assure = new Assure();
		Subscription subscription = new Subscription();
		Vehicule  vehicule = new Vehicule();
		ProduitAssurance produitAssurance = new ProduitAssurance();
		
		if ( numeCniAssure != null && telephoneAssure != null) {
	       assure = assureRepository.findByTelephone(telephoneAssure)
	                .orElseThrow(() -> new RuntimeException("Assuré non trouvé"));
	        
	        
	       subscription.setAssure(assure);
	    } else {
	    	
			 assure.setNom(nomAssure);
			 assure.setPrenoms(prenomsAssure);
			 assure.setAdresse(adresseAssure);
			 assure.setNumeroCarteIdentite(numeCniAssure);
			 assure.setTelephone(telephoneAssure);
			 assure.setVille(villeAssure);
			assureRepository.save(assure);
	    }
		
		//enregistrer vehicule
		
		
		if ( immatVehicule != null) {
			vehicule = vehiculeRepository.findByNumeroMatricule(immatVehicule)
		                .orElseThrow(() -> new RuntimeException("Assuré non trouvé"));
		        
		        
			subscription.setVehicule(vehicule);
		    } else {
		    	
		    
				vehicule.setCouleur(couleurVehicule);
				vehicule.setDateMiseEnCirculation(dateCirculation);
				vehicule.setNbrPortes(porteVehiculeInt);
				vehicule.setNbrSieges(siegeVehiculeInt);
				vehicule.setNumeroMatricule(immatVehicule);
				vehicule.setValeurNeuf(valeurNeufVehiculeFloat);
				vehicule.setValeurVenale(valeurVenaleVehiculeFloat);
				
				vehiculeRepository.save(vehicule);
		    }
		
		
		
		if ( nomProduit != null) {
			produitAssurance = produitAssuranceRepository.findByNomProduit(nomProduit)
		                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
		        
		        
			subscription.setProduitAssurance(produitAssurance);
		    }
		
		// enregistrer id vehicule et id assure dans la table de jointure
		
		
		//subscription.setAssure(assure.getIdAssure());
		//subscription.setVehicule(vehicule);
		subscription.setPrice(priceFloat);
		
		subscription.setQuoteReference(reference.generateReference());
		
		subscription.setStartDate(date_debut);
		subscription.setEndDate(date_fin);
		subscription.setStatus(status);
	
		
	    return subscriptionRepository.save(subscription);
	}


    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    public ResponseEntity<String> getSubscriptionStatus(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("L'ID de l'assuré est requis.");
        }

        Optional<Subscription> subscriptions = subscriptionRepository.findById(id);

        if (subscriptions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String status = subscriptions.get().getStatus();
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.noContent().build();
    }

}
