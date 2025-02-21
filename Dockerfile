# Étape 1 : Utiliser une image JDK légère
FROM openjdk:17-jdk-slim

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR de l’application dans le conteneur
COPY target/api-*.jar nsiaassur.jar

# Étape 4 : Exposer le port sur lequel l’application écoute
EXPOSE 8081

# Étape 5 : Démarrer l’application
ENTRYPOINT ["java", "-jar", "nsiaassur.jar"]
