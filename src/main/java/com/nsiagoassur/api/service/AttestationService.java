package com.nsiagoassur.api.service;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.nsiagoassur.api.model.Subscription;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AttestationService {

    public Resource generateAttestation(Subscription subscription) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                // 🔹 Fond bleu ciel avec opacité (placé en premier)
                contentStream.setNonStrokingColor(new Color(135, 206, 250, 255)); // Bleu ciel semi-transparent
                contentStream.addRect(0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
                contentStream.fill();

                // 🔹 Vagues en haut
                contentStream.setNonStrokingColor(new Color(0, 102, 204)); // Bleu plus foncé
                contentStream.moveTo(0, 800);
                contentStream.curveTo(100, 850, 300, 750, 600, 800);
                contentStream.curveTo(400, 850, 700, 750, 900, 800);
                contentStream.lineTo(900, 850);
                contentStream.lineTo(0, 850);
                contentStream.fill();

                // 🔹 Vagues en bas
                contentStream.moveTo(0, 50);
                contentStream.curveTo(100, 10, 300, 90, 600, 50);
                contentStream.curveTo(400, 10, 700, 90, 900, 50);
                contentStream.lineTo(900, 0);
                contentStream.lineTo(0, 0);
                contentStream.fill();

                // 🔹 Ajouter un logo (placé après le fond bleu pour qu'il soit visible)
                PDImageXObject logo = PDImageXObject.createFromFile(
                        new ClassPathResource("logo.png").getFile().getAbsolutePath(), document);
                contentStream.drawImage(logo,30, 710, 150, 100); // (x, y, largeur, hauteur)

                // 🔹 Encadrer le titre (fond blanc)
                contentStream.setNonStrokingColor(Color.WHITE);
                contentStream.addRect(190, 740, 310, 30); // Position et dimensions du cadre
                contentStream.fill();

                // 🔹 Titre de l'attestation (ajouté après le rectangle blanc)
                contentStream.setNonStrokingColor(Color.BLACK); // Remettre en noir pour le texte
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.beginText();
                contentStream.newLineAtOffset(195, 750);
                contentStream.showText(" ATTESTATION ASSURANCE ");
                contentStream.endText();

                // 🔹 Numéro unique de l'attestation
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 650);
                contentStream.showText("Numéro unique : " + subscription.getQuoteReference());
                contentStream.endText();

                // 🔹 Informations du véhicule assuré
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 620);
                contentStream.showText("Véhicule : Numero Immatriculation " + subscription.getVehicule().getNumeroMatricule()
                        + " - Couleur : " + subscription.getVehicule().getCouleur()
                       +" - " + subscription.getVehicule().getNbrPortes() +" Portes " 
                       );
                
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 590);
                contentStream.showText(
                         subscription.getVehicule().getNbrSieges()+" Sièges" 
                        + " Valeur Neuve : " + subscription.getVehicule().getValeurNeuf() + " CFA");
                
                contentStream.endText();
                // 🔹 Informations du souscripteur
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 560);
                contentStream.showText("Souscripteur : " + subscription.getAssure().getNom() + " " + subscription.getAssure().getPrenoms()
                        + " - " + subscription.getAssure().getTelephone() + " " + subscription.getAssure().getVille());
                contentStream.endText();

                // 🔹 Nom du Produit souscrit
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 530);
                contentStream.showText("Produit souscrit : " + subscription.getProduitAssurance().getNomProduit());
                contentStream.endText();

                // 🔹 Date de génération
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 350);
                contentStream.showText("Date de génération : " + LocalDateTime.now());
                contentStream.endText();

                // 🔹 Ajouter une signature électronique
                contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(400, 100);
                contentStream.showText("Signature électronique");
                contentStream.endText();

                // 🔹 Simuler une signature par un texte stylisé
                contentStream.setFont(PDType1Font.HELVETICA_BOLD_OBLIQUE, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(400, 80);
                contentStream.showText("NSIAGO'ASSUR");
                contentStream.endText();
            }

            // 🔹 Sauvegarde du document dans un flux mémoire
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }
}
