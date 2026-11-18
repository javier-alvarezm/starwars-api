# Star Wars API con Spring Boot

Backend REST que consume SWAPI, persiste personajes y relaciones, protege endpoints con JWT y registra consultas para estadísticas.

## Tecnologías

- Java 17, Spring Boot 4.1.2 y Maven Wrapper.
- Spring Web MVC, Data JPA, Security, Validation y Actuator.
- H2 para ejecutar sin dependencias y perfil PostgreSQL.
- JWT HMAC con secretos configurables por entorno.

## Ejecutar

Requiere Java 17. En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html

El usuario ADMIN inicial es `admin` y la contraseña de desarrollo `ChangeMe123!`. Cambie la contraseña y el secreto JWT antes de usar en ambientes compartidos.

## API principal

- `POST /api/auth/register`, `POST /api/auth/login`
- `POST /api/admin/sync` (ADMIN)
- `GET /api/characters`, `/api/films` y sus relaciones
- `GET /api/admin/statistics` (ADMIN)

Ejecute `./mvnw test` para los tests. La colección de Postman está en `postman/StarWars-API.postman_collection.json`.
