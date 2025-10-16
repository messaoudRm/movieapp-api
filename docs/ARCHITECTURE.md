[⬅ Retour au README](../README.md)

# Architecture des Conteneurs - Application Spring, MariaDB, Adminer, Prometheus, grafana

```mermaid
flowchart TB
%% ==== SECTION CLIENT ====
    subgraph Client["Client"]
        client["Navigateur, OpenAPI (Swagger)"]
    end

%% ==== SECTION BACKEND ====
    subgraph Backend["Backend Services"]
        direction TB

    %% --- Application principale ---
        subgraph SpringBootApp["Spring Boot App (container)"]
            direction TB

            subgraph SecurityFilter["jwtFilter"]
                jwtFilter["JwtAuthenticationFilter"]
            end

            subgraph ControllerLayer["Controller"]
                controller["@RestController"]
            end

            subgraph ServiceLayer["Service"]
                service["@Service"]
            end
        end

    %% --- Base de données et outils ---
        db["MariaDB (container)"]
        adminer["Adminer UI (container)"]

    %% --- Monitoring ---
        prometheus["Prometheus (container)"]

        subgraph Dashboard["Dashboards et visualisation"]
            grafana["Grafana (container)"]
        end
    end

%% ==== SECTION STORAGE ====
    subgraph Storage["Persistent Storage"]
        vol1["Data Volume (MariaDB)"]
        vol2["Monitoring Volume (Prometheus / Grafana)"]
    end

%% ==== FLUX PRINCIPAL ====
    client -->|"Requête HTTP avec Authorization: Bearer <token_jwt>"| jwtFilter
    jwtFilter -->|"Token valide"| controller
    jwtFilter -.->|"Token invalide (401)"| client

    controller --> service
    service <-->|"JDBC / JPA"| db
    adminer <-->|"TCP/IP"| db
    db --> vol1

%% ==== FLUX MONITORING ====
    SpringBootApp -->|"Expose métriques"| prometheus
    prometheus -->|"Scrape et stocke les métriques"| vol2
    grafana -->|"Interroge Prometheus"| prometheus

```
