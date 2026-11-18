# Star Wars API con Spring Boot

Backend REST que consume todas las páginas de SWAPI, persiste personajes y sus relaciones, protege los endpoints con JWT y registra las consultas para producir estadísticas. En el enunciado, "armas" se interpreta como `starships`, porque SWAPI no ofrece un recurso de armas.

## Tecnologías y decisiones

- Java 17, Spring Boot 4.1.2 y Maven Wrapper.
- Spring Web MVC, Data JPA, Security, Validation y Actuator.
- H2 persistente para ejecutar sin dependencias; perfil `postgres` para PostgreSQL.
- JWT HMAC con JJWT; secreto y expiración configurables por entorno.
- DTOs en la API para no exponer entidades JPA.
- Sincronización idempotente por el ID incluido en cada URL de SWAPI.
- Swagger UI en `http://localhost:8080/swagger-ui.html`.

## Ejecutar localmente

Requiere Java 17. Maven no necesita instalación global.

```powershell
.\mvnw.cmd spring-boot:run
```

La base H2 se guarda en `./data`. Credenciales iniciales de desarrollo:

- Usuario ADMIN: `admin`
- Contraseña: `ChangeMe123!`

Cambie la contraseña y el secreto JWT en cualquier ambiente compartido:

```powershell
$env:ADMIN_PASSWORD='otra-clave-segura'
$env:JWT_SECRET='un-secreto-aleatorio-de-al-menos-32-caracteres'
.\mvnw.cmd spring-boot:run
```

## Ejecutar con PostgreSQL y Docker

```powershell
.\mvnw.cmd clean package
docker compose up --build
```

También se puede activar el perfil manualmente con `SPRING_PROFILES_ACTIVE=postgres` y configurar `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`.

## Flujo de uso

1. Inicie sesión en `POST /api/auth/login` con el ADMIN.
2. Envíe el token como `Authorization: Bearer <token>`.
3. Ejecute `POST /api/admin/sync`. SWAPI puede tardar porque se recorren todas sus páginas y relaciones.
4. Consulte personajes, películas y estadísticas.

Un usuario normal se crea con `POST /api/auth/register`. La contraseña se almacena con BCrypt. La sincronización y las estadísticas requieren el rol ADMIN.

## Endpoints

| Método | Ruta | Rol |
|---|---|---|
| POST | `/api/auth/register` | Público |
| POST | `/api/auth/login` | Público |
| POST | `/api/admin/sync` | ADMIN |
| GET | `/api/characters?name=&page=&size=&sort=` | USER/ADMIN |
| GET | `/api/characters/{id}` | USER/ADMIN |
| GET | `/api/characters/{id}/films` | USER/ADMIN |
| GET | `/api/characters/{id}/vehicles` | USER/ADMIN |
| GET | `/api/characters/{id}/starships` | USER/ADMIN |
| GET | `/api/films?title=&page=&size=&sort=` | USER/ADMIN |
| GET | `/api/films/{id}` | USER/ADMIN |
| GET | `/api/films/{id}/characters` | USER/ADMIN |
| GET | `/api/films/{id}/vehicles` | USER/ADMIN |
| GET | `/api/films/{id}/starships` | USER/ADMIN |
| GET | `/api/admin/statistics` | ADMIN |

Los listados son paginados y aceptan filtros por nombre o título. Los errores tienen un formato consistente mediante `@RestControllerAdvice`.

## Modelo de datos

`CharacterEntity` se relaciona N:M con `Film`, `Vehicle` y `Starship`. `Film` también mantiene sus vehículos y naves. `AppUser` almacena usuarios/roles y `QueryLog` registra usuario, endpoint, recurso/ID, parámetros, fecha y código HTTP. Las estadísticas agrupan por usuario, recurso y endpoint.

## Tests

```powershell
.\mvnw.cmd test
```

## Colección Postman

Importe `postman/StarWars-API.postman_collection.json`. La colección contiene `baseUrl`, credenciales y una variable `token`; el login guarda automáticamente el JWT.

## Limitaciones y mejoras

- SWAPI es un tercero y puede estar lento o no disponible; el cliente usa timeout y la API devuelve error consistente.
- Para una carga grande convendría procesar la sincronización en segundo plano, agregar reintentos con backoff y cachear recursos relacionados.
- En producción se recomienda Flyway/Liquibase en vez de `ddl-auto=update`, rotación de secretos y TLS.
- El usuario ADMIN inicial es práctico para la evaluación; en producción debe reemplazarse por aprovisionamiento seguro.
