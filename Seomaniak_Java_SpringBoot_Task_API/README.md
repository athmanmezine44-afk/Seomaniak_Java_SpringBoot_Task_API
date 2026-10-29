# Task Management REST API

Mini-projet Java Spring Boot pour un test technique de développement backend.

## Technologies
- Java 17
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Maven

## Lancer le projet
Prérequis : JDK 17 et Maven.

```bash
mvn spring-boot:run
```

L'API démarre sur `http://localhost:8080`.

## Endpoints

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/tasks` | Lister les tâches |
| GET | `/api/tasks/{id}` | Consulter une tâche |
| POST | `/api/tasks` | Créer une tâche |
| PUT | `/api/tasks/{id}` | Modifier une tâche |
| DELETE | `/api/tasks/{id}` | Supprimer une tâche |

## Exemple de création
POST `http://localhost:8080/api/tasks`
Content-Type: `application/json`

```json
{
  "title": "Préparer le test Seomaniak",
  "description": "Développer et tester une API REST",
  "completed": false
}
```

## Base H2
Console : `http://localhost:8080/h2-console`
JDBC URL : `jdbc:h2:mem:taskdb`
User : `sa`
Password : laisser vide.

## Structure
- `controller` : endpoints REST
- `service` : logique métier
- `repository` : accès aux données
- `model` : entité JPA
- `exception` : gestion des erreurs

## Limites connues
La base H2 est en mémoire : les données disparaissent lorsque l'application s'arrête.
Pour une application réelle, on pourrait utiliser PostgreSQL/MySQL, ajouter des tests d'intégration, la pagination et une authentification.
