package com.nsiagoassur.api.service;

public class CalculService {

    // Méthodes de garantie déjà définies dans le frontend
    public Float getGarantieRC(Integer puissance) {
        if (puissance == null || puissance <= 0) {
        	  return 0f;
        }
        if (puissance == 2) return 37601f;
        if (puissance >= 3 && puissance <= 6) return 45181f;
        if (puissance >= 7 && puissance <= 10) return 51078f;
        if (puissance >= 11 && puissance <= 14) return 65677f;
        if (puissance >= 15 && puissance <= 23) return 86456f;
        return 104143f; // puissance > 24
    }

    public Float getGarantieDommages(Float valeurNeuf) {
        if (valeurNeuf == null || valeurNeuf <= 0) {
           return 0f;
        }
        return valeurNeuf * 0.026f;
    }

    public Float getTierceCollision(Float valeurNeuf) {
        if (valeurNeuf == null || valeurNeuf <= 0) {
        	  return 0f;
        }
        return valeurNeuf * 0.0165f;
    }

    public Float getTiercePlafonnee(Float valeurVenale) {
        if (valeurVenale == null || valeurVenale <= 0) {
        	  return 0f;
        }
        float prime = (valeurVenale * 0.5f) * 0.042f;
        return Math.max(prime, 100000f);
    }

    public Float getGarantieVol(Float valeurVenale) {
        if (valeurVenale == null || valeurVenale <= 0) {
        	  return 0f;
        }
        return valeurVenale * 0.0014f;
    }

    public Float getGarantieIncendie(Float valeurVenale) {
        if (valeurVenale == null || valeurVenale <= 0) {
        	  return 0f;
        }
        return valeurVenale * 0.0015f;
    }

    // PRODUITS
    public Float getPapillon(Integer puissance, Float valeurNeuf) {
        return Float.sum(getGarantieRC(puissance), getGarantieDommages(valeurNeuf));
    }

    public Float getDouby(Integer puissance, Float valeurNeuf) {
        return Float.sum(getGarantieRC(puissance), 
                         Float.sum(getGarantieDommages(valeurNeuf), getTierceCollision(valeurNeuf)));
    }

    public Float getDouyou(Integer puissance, Float valeurNeuf, Float valeurVenale) {
        return Float.sum(getGarantieRC(puissance), 
                         Float.sum(getGarantieDommages(valeurNeuf), 
                         Float.sum(getTierceCollision(valeurNeuf), getGarantieIncendie(valeurVenale))));
    }

    public Float getToutourisquou(Integer puissance, Float valeurNeuf, Float valeurVenale) {
        return Float.sum(getGarantieRC(puissance), 
                         Float.sum(getGarantieDommages(valeurNeuf), 
                         Float.sum(getTierceCollision(valeurNeuf), 
                         Float.sum(getTiercePlafonnee(valeurVenale), 
                         Float.sum(getGarantieVol(valeurVenale), getGarantieIncendie(valeurVenale))))));
    }
}
