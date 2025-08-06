[⬅ Retour au README](../README.md)

## Endpoints disponibles

| Méthode | URL            | Description                         |
|---------|----------------|-------------------------------------|
| POST    | /auth/login    | Authentification utilisateur        |
| POST    | /auth/register | cree un utilisateur                 |
| GET     | /movies        | Récupérer la liste des films (JWT)  |
| GET     | /movies/{id}   | Récupérer un film par son ID  (JWT) |
| POST    | /movies        | Ajouter un nouveau film       (JWT) |
| PUT     | /movies/{id}   | Modifier un film existant   (JWT)   |
| DELETE  | /movies/{id}   | Supprimer un film        (JWT)      |
| POST    | /users/        | Enregistrer un nouvel utilisateur (JWT)   |
| GET     | /users/{id}    | Récupérer les infos utilisateur  (JWT)    |

Pour plus de détails, consultez la documentation Swagger :  
http://localhost:8080/api/swagger-ui/index.html#/

