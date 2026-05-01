# Étape 1 : Builder le projet avec Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
ARG TMDB_API_TOKEN
ENV TMDB_API_TOKEN=${TMDB_API_TOKEN}
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Créer une image légère avec juste le JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
