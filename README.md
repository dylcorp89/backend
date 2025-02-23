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
   ```
   
   spring.datasource.url=jdbc:mysql://localhost:3306/ma_base
   
   spring.datasource.username=root
   
   spring.datasource.password=secret
   
   server.port=8081
   ```
   
3. **Creer votre base de données**  
   ```sh
   mysql -u root -p (Entre ton mot de passe MySQL lorsque demandé)
   
   CREATE DATABASE ma_base
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

 ```sh
 
 ROLE ADMIN
   {
   "username" :"admin",
   "password" :"chance@@@@##4599_"
   }

ROLE AMAZONE
   {
   "username" :"user",
    "password" :"user123"
     }
   ```

- **Register** : `POST /auth/register`  

 ```sh
 
 {
"username" :"test",
"password" :"test4458",
"role" : "Amazone",
"nom" : "JOHN",
"prenoms" : "DOE"

}
   ```

- **Accès sécurisé** : Ajouter `Authorization: Bearer <token>` dans les requêtes.

## 🛠️ Choix techniques
- **Spring Boot** : Pour sa robustesse et son écosystème mature.  
- **JWT** : Solution simple et efficace pour l'authentification.  
- **Rate Limiting** : Protection contre les abus et les attaques DDoS.  

## 🛠️ Documentation API

- **Endpoint** : /swagger-ui/index.html  

 


