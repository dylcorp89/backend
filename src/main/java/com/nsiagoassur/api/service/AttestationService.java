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

                // 🔹 Ajouter une bordure
                contentStream.setStrokingColor(Color.BLACK);
                contentStream.setLineWidth(2);
                contentStream.addRect(40, 700, 500, 100);
                contentStream.stroke();

                // 🔹 Détails de l’abonnement
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(60, 730);
                contentStream.showText("Numéro d'abonnement : " );
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Email : " );
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Date de génération : " + LocalDateTime.now());
                contentStream.endText();

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
                contentStream.showText("John Doe");
                contentStream.endText();
            }

            // 🔹 Sauvegarde du document dans un flux mémoire
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }
}
