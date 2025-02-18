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

                // 🔹 Ajouter un logo
                PDImageXObject logo = PDImageXObject.createFromFile(
                        new ClassPathResource("logo.png").getFile().getAbsolutePath(), document);
                contentStream.drawImage(logo, 50, 750, 100, 50); // (x, y, largeur, hauteur)

                // 🔹 Titre de l'attestation
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.beginText();
                contentStream.newLineAtOffset(200, 750);
                contentStream.showText("Attestation d'Abonnement");
                contentStream.endText();

                // 🔹 Numéro unique de l'attestation
                String numeroAttestation = UUID.randomUUID().toString();
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 700);
                contentStream.showText("Numéro unique : " + numeroAttestation);
                contentStream.endText();

                // 🔹 Informations du véhicule assuré
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 670);
                contentStream.showText("Véhicule : " + "" + 
                                      " - " + "");
                contentStream.endText();

                // 🔹 Informations du souscripteur
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 640);
                contentStream.showText("Souscripteur : " + "" + 
                                      " - " + "");
                contentStream.endText();

                // 🔹 Nom du Produit souscrit
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 610);
                contentStream.showText("Produit souscrit : " + "");
                contentStream.endText();

                // 🔹 Date de génération
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 580);
                contentStream.showText("Date de génération : " + LocalDateTime.now());
                contentStream.endText();

                // 🔹 QR Code
               /* PDImageXObject qrCodeImage = generateQRCodeImage(numeroAttestation);
                contentStream.drawImage(qrCodeImage, 400, 500, 100, 100);*/

                // 🔹 Ajouter une signature électronique
                contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(400, 100);
                contentStream.showText("Signature électronique");
                contentStream.endText();

                // Simuler une signature par un texte stylisé
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

   /* private PDImageXObject generateQRCodeImage(String data) throws IOException {
        // Générer un QR Code avec ZXing
        return PDImageXObject.createFromFile("qrcode.png", new PDDocument()); // Remplace avec la logique de génération réelle
    }*/
}
