# Task Management REST API : Test technique Seomaniak 2026

**Candidat :** Athman Mezine
**Profil :** Développement Backend (Java / Spring Boot)
**Sujet :** API RESTful de gestion de liste de tâches (CRUD)

---

## 1. Stack utilisée

| Élément | Technologie |
|---|---|
| Langage | Java 17 |
| Framework | Spring Boot 3.5.6 |
| API | Spring Web (REST) |
| Persistance | Spring Data JPA / Hibernate |
| Base de données | PostgreSQL (base `seomaniak`, table `tasks`) |
| Validation | Bean Validation (`@NotBlank`, `@Size`) |
| Documentation | Swagger UI / OpenAPI 3 (springdoc) |
| Build | Maven |
| Tests manuels | Postman / navigateur / psql / pgAdmin |

## 2. Lancer le projet

Prérequis : JDK 17, Maven, PostgreSQL.

```bash
git clone https://github.com/athmanmezine44-afk/Seomaniak_Java_SpringBoot_Task_API.git
cd Seomaniak_Java_SpringBoot_Task_API
```

1. Créer la base : `CREATE DATABASE seomaniak;`
2. Renseigner l'utilisateur et le mot de passe PostgreSQL dans `src/main/resources/application.properties`
   (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`).
3. Lancer :

```bash
mvn spring-boot:run
```

L'API démarre sur **http://localhost:8081**.
La table `tasks` est créée automatiquement par Hibernate (`ddl-auto=update`).

Swagger UI : **http://localhost:8081/swagger-ui/index.html**

## 3. Routes

| Méthode | URL | Description | Code succès |
|---|---|---|---|
| GET | `/api/tasks` | Lister les tâches | 200 |
| GET | `/api/tasks/{id}` | Consulter une tâche | 200 |
| POST | `/api/tasks` | Créer une tâche | 201 |
| PUT | `/api/tasks/{id}` | Modifier une tâche | 200 |
| DELETE | `/api/tasks/{id}` | Supprimer une tâche | 204 |

### Exemple : création

`POST /api/tasks`

```json
{
  "title": "Apprendre Spring Boot",
  "description": "Tester mon API REST"
}
```

Réponse `201 Created` :

```json
{
  "id": 1,
  "title": "Apprendre Spring Boot",
  "description": "Tester mon API REST",
  "completed": false,
  "createdAt": "2026-10-29T19:31:51.9121755"
}
```

### Gestion des erreurs

- **400 Bad Request** (titre vide) :
```json
{
  "timestamp": "2026-10-29T19:41:34.2629153",
  "status": 400,
  "error": "Validation échouée",
  "details": { "title": "Le titre est obligatoire" }
}
```
- **404 Not Found** (id inexistant) :
```json
{
  "timestamp": "2026-10-29T19:42:32.3105508",
  "status": 404,
  "error": "Not Found",
  "message": "Aucune tâche trouvée avec l'identifiant 9999"
}
```

## 4. Modèle de données (`tasks`)

| Colonne | Type | Contraintes |
|---|---|---|
| id | bigint | clé primaire, auto-généré |
| title | varchar(100) | obligatoire |
| description | varchar(500) | optionnel |
| completed | boolean | obligatoire, `false` par défaut |
| created_at | timestamp | obligatoire, rempli à la création (`@PrePersist`) |

## 5. Structure du projet

```
src/main/java/ma/seomaniak/taskapi
├── TaskApiApplication.java          # point d'entrée
├── controller/TaskController.java   # endpoints REST
├── service/TaskService.java         # logique métier
├── repository/TaskRepository.java   # accès aux données (JPA)
├── model/Task.java                  # entité JPA + validations
└── exception/
    ├── TaskNotFoundException.java   # exception métier
    └── GlobalExceptionHandler.java  # réponses d'erreur JSON propres
src/main/resources/application.properties
pom.xml
```

Architecture en couches : **Controller → Service → Repository → Base de données**.

## 6. Captures d'écran fournies

1. `GET /api/tasks` : liste vide au démarrage (200 OK)
2. `POST /api/tasks` : création d'une tâche (201 Created)
3. `GET /api/tasks/1` : lecture d'une tâche dans le navigateur
4. PostgreSQL : structure de la table `tasks` + `SELECT * FROM tasks`
5. `PUT /api/tasks/1` : modification (200 OK)
6. `DELETE /api/tasks/1` : suppression (204 No Content)
7. `POST` avec titre vide : erreur de validation (400)
8. `GET /api/tasks/9999` : tâche introuvable (404)
9. Swagger UI : documentation interactive des 5 endpoints

## 7. Ce que j'ai appris / difficultés / améliorations

**Appris :** structurer une API REST en couches, valider les entrées avec Bean Validation, centraliser les erreurs avec `@RestControllerAdvice`, connecter Spring Boot à PostgreSQL et documenter l'API avec Swagger.

**Difficultés :** la configuration de la connexion PostgreSQL (port, identifiants) et l'adaptation du projet de H2 en mémoire vers une base persistante.

**À améliorer :**
- Ajouter des tests unitaires et d'intégration (JUnit / MockMvc)
- Utiliser des DTO au lieu d'exposer directement l'entité
- Ajouter la pagination et le filtrage (ex. tâches terminées / en cours)
- Ajouter une authentification (Spring Security / JWT)
- Conteneuriser avec Docker (API + PostgreSQL)

## 8. Livrables

- Dépôt GitHub : `https://github.com/athmanmezine44-afk/Seomaniak_Java_SpringBoot_Task_API`
- `App_Demo.mp4` : démonstration de l'application
- 5+ captures d'écran (.png)
- `Pitch_Dev_AthmanMezine.mp4` : présentation orale (1 min)
- `README.md` : ce fichier
