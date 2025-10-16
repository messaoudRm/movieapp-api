# 🎬 MovieApp API

Une application backend Spring Boot pour la gestion de films, sécurisée par JWT, documentée via Swagger, et entièrement containerisée avec Docker Compose. 
Cette application utilise un jeu de données de **plus de 9000 films**, disponible sur [Hugging Face](https://huggingface.co/datasets/Pablinho/movies-dataset).

---

## ✅ Fonctionnalités

- ✅ API REST CRUD pour les films avec pagination
- ✅ Sécurité avec JWT
- ✅ Base de données MariaDB : [doc-Schéma](docs/DATABASE_SCHEMA.md)
- ✅ Les mots de passe sont hachés à l’aide de l’algorithme BCrypt
- ✅ Adminer pour interface DB (accessible en local) : [doc-Adminer](docs/ADMINER.md) 
- ✅ Documentation interactive avec Swagger / OpenAPI : [doc-Swagger](docs/SWAGGER.md)
- ✅ Logger Spring Boot Logback
- ✅ Monitoring complet avec Prometheus & Grafana : collecte et visualisation des métriques d’application (requêtes HTTP, performances, erreurs, ...) : [doc-Monitoring](docs/MONITORING.md)
- ✅ Pipeline de tests automatisés basé sur une collection Postman, permettant d’exécuter les endpoints et de valider les réponses de l’API : [doc-Postman](docs/POSTMAN.md)

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security + JJWT**
- **MariaDB**
- **Adminer**
- **Swagger (OpenAPI 3)**
- **Maven**
- **Docker & Docker Compose**
- **Prometheus**
- **Grafana**
- **Postman**
- **Logback**

---


## 🏗️ Build Docker optimisé avec Multi-stage Build

Le build Docker utilise un **multi-stage build** basé sur `openjdk:17-jdk-alpine` pour optimiser la taille de l’image finale :

- **Étape 1** : Compilation du projet avec Maven dans une image `maven` complète
- **Étape 2** : Création d’une image légère basée sur OpenJDK, contenant uniquement le JAR compilé copié depuis l’étape 1

Cela permet d’avoir une image finale plus légère, sans les fichiers sources ni les dépendances Maven.

---
## 🚀 Lancement rapide

Assurez-vous d’avoir Docker installé, puis :

**Cloner le dépôt**

```bash
  git clone https://github.com/messaoudRm/movieapp-api.git
  cd movieapp-api
```

**Lancer l’application avec Docker Compose**

```bash
docker-compose up --build
```

## 🛑 Arrêter et relancer l'application

Arrêter l'application :
  ```bash
  Ctrl + C
  ```

Relancer les conteneurs déjà créés :
  ```bash
  docker-compose start
  ```

## 🧹 Nettoyer les conteneurs, images et volumes

Arrêter et supprimer uniquement les conteneurs :
  ```bash
  docker-compose down
  ```

Supprimer les images Docker utilisées :
  ```bash
  docker rmi movieapp-api-app mariadb:11.8.2 adminer prom/prometheus:v2.44.0 grafana/grafana:9.5.2
  ```

Supprimer le volume de la base de données :
  ```bash
  docker volume rm movieapp-api_maria-db-data movieapp-api_prometheus-data movieapp-api_grafana-data
  ```

