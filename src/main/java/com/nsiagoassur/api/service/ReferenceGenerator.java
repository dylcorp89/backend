package com.nsiagoassur.api.service;

import java.security.SecureRandom;

public class ReferenceGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 12;
    private static final SecureRandom random = new SecureRandom();
    
    private final String prefix; // Préfixe fourni par l'utilisateur

    // Constructeur qui prend le préfixe en paramètre
    
    public ReferenceGenerator(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            throw new IllegalArgumentException("Le préfixe ne peut pas être null ou vide.");
        }
        this.prefix = prefix;
    }

    // Génération de la référence en utilisant le préfixe fourni
    public String generateReference() {
        StringBuilder ref = new StringBuilder(prefix); // Utilisation du préfixe fourni

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            ref.append(CHARACTERS.charAt(index));
        }

        return ref.toString();
    }
}
