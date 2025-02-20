# Backend - NSIAGO'ASSUR

## 🚀 Technologies utilisées
- **Spring Boot 3.4.2** - Framework backend
- **MySQL** - Base de données
- **JWT** - Authentification sécurisée
- **Rate Limiting** - Protection contre les abus

## 📦 Installation

1. **Cloner le repo**  
   ```sh
   git clone https://github.com/dylcorp89/backend.git
   cd backend
   ```

2. **Configurer la base de données**  
   Modifier `application.properties` :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ma_base
   spring.datasource.username=root
   spring.datasource.password=secret
   server.port=8081
   ```

3. **Lancer l'application**  
   ```sh
   mvn spring-boot:run
   ```

## 📌 Fonctionnalités principales

✔️ CRUD utilisateurs  
✔️ Gestion des rôles et permissions  
✔️ Système de rate limiting  
✔️ Simulation de prime 

✔️ Souscription à un produit d'assurance  
✔️ Établissement d'attestation  

## 🔑 Authentification JWT
- **Login** : `POST /auth/login`  
- **Register** : `POST /auth/register`  
- **Accès sécurisé** : Ajouter `Authorization: Bearer <token>` dans les requêtes.

## 🛠️ Choix techniques
- **Spring Boot** : Pour sa robustesse et son écosystème mature.  
- **JWT** : Solution simple et efficace pour l'authentification.  
- **Rate Limiting** : Protection contre les abus et les attaques DDoS.  

