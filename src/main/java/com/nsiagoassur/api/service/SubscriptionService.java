package com.nsiagoassur.api.service ;

import com.nsiagoassur.api.model.Assure;
import com.nsiagoassur.api.model.Categorie;
import com.nsiagoassur.api.model.ProduitAssurance;
import com.nsiagoassur.api.model.Simulation;
import com.nsiagoassur.api.model.Subscription;
import com.nsiagoassur.api.model.Vehicule;
import com.nsiagoassur.api.repository.AssureRepository;
import com.nsiagoassur.api.repository.CategorieRepository;
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
import java.util.logging.Logger;

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
	private CategorieRepository categorieRepository;
	
	@Autowired
	private ProduitAssuranceRepository produitAssuranceRepository;
	

	   private static final Logger logger = Logger.getLogger(SubscriptionService.class.getName());

   

public Subscription createSubscription(Map<String, String> request) {

    logger.info("Requête reçue pour la création d'une souscription : " + request);

    ReferenceGenerator reference = new ReferenceGenerator("ATT-");

    // Récupération des valeurs avec gestion des valeurs nulles
    String dateFin = request.get("expiration_date");
    String dateDebut = request.get("startDate");
    String dateCirculation = request.get("dateCirculation");
    String couleurVehicule = request.get("couleur");
    String immatVehicule = request.get("numeroImmat");
    String categorieVehicule = request.get("typeVehicule");
    
    int categorieVehiculeInt = parseIntOrDefault(request.get("categorieVehicule"),202);
    
    String nomProduit = request.get("produitAssurance");
    String adresseAssure = request.get("adresseAssure");
    String telephoneAssure = request.get("telAssure");
    String nomAssure = request.get("nomAssure");
    String prenomsAssure = request.get("prenomAssure");
    String numCniAssure = request.get("numCniAssure");
    String villeAssure = request.get("villeAssure");

    // Valeurs par défaut si null ou conversion sécurisée
    int porteVehiculeInt = parseIntOrDefault(request.get("nbrePorte"), 1);
    int siegeVehiculeInt = parseIntOrDefault(request.get("nbreSiege"), 1);
    float valeurVenaleVehiculeFloat = parseFloatOrDefault(request.get("valeurVenaleVehicule"), 0.0f);
    float valeurNeufVehiculeFloat = parseFloatOrDefault(request.get("valeurNeufVehicule"), 0.0f);
    float priceFloat = parseFloatOrDefault(request.get("prime"), 0.0f);

    String status = "Active";

    // Vérification ou création de l'assuré
    Assure assure = assureRepository.findByTelephone(telephoneAssure).orElseGet(() -> {
        Assure newAssure = new Assure();
        newAssure.setNom(nomAssure);
        newAssure.setPrenoms(prenomsAssure);
        newAssure.setAdresse(adresseAssure);
        newAssure.setNumeroCarteIdentite(numCniAssure);
        newAssure.setTelephone(telephoneAssure);
        newAssure.setVille(villeAssure);
        return assureRepository.save(newAssure);
    });
    
 // Récupération de la catégorie (ou valeur par défaut)
   
    Categorie categorie = categorieRepository.findByCodeCategorie(categorieVehicule)
        .orElseThrow(() -> new RuntimeException("Catégorie non trouvée avec l'ID : " + categorieVehicule));


    // Vérification ou création du véhicule
    Vehicule vehicule = vehiculeRepository.findByNumeroMatricule(immatVehicule).orElseGet(() -> {
        Vehicule newVehicule = new Vehicule();
        newVehicule.setCouleur(couleurVehicule);
        newVehicule.setDateMiseEnCirculation(dateCirculation);
        newVehicule.setNbrPortes(porteVehiculeInt);
        newVehicule.setNbrSieges(siegeVehiculeInt);
        newVehicule.setNumeroMatricule(immatVehicule);
        newVehicule.setValeurNeuf(valeurNeufVehiculeFloat);
        newVehicule.setValeurVenale(valeurVenaleVehiculeFloat);
        newVehicule.setCategorie(categorie);
        return vehiculeRepository.save(newVehicule);
    });

 // Si le véhicule existait déjà, mettre à jour sa catégorie
    if (!vehicule.getCategorie().equals(categorie)) {
        vehicule.setCategorie(categorie);
        vehiculeRepository.save(vehicule);
    }
    
    // Vérification du produit assurance
    ProduitAssurance produitAssurance = produitAssuranceRepository.findByNomProduit(nomProduit)
        .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

    // Création de la souscription
    Subscription subscription = new Subscription();
    subscription.setAssure(assure);
    subscription.setVehicule(vehicule);
    subscription.setProduitAssurance(produitAssurance);
    subscription.setPrice(priceFloat);
    subscription.setQuoteReference(reference.generateReference());
    subscription.setStartDate(dateDebut);
    subscription.setEndDate(dateFin);
    subscription.setStatus(status);
    subscriptionRepository.save(subscription);
    return subscription ;
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
