# Ktor WebSockets Task App

Simple Ktor backend showcasing HTTP routes, JSON serialization, WebSockets, and SQLite persistence with Exposed.

## Tech stack
- Ktor (Netty engine)
- WebSockets + kotlinx.serialization (JSON)
- SQLite database at `./data/tasks.db`
- Exposed DAO for persistence

## Run locally
Default port: `8080` (see `src/main/resources/application.yaml`).

Commands:
- `./gradlew run` — start the server
- `./gradlew test` — run tests
- `./gradlew build` — assemble

When started you should see a line like:
```
Responding at http://0.0.0.0:8080
```

## Data model
`Task` (JSON):
```
{
  "name": "shopping",
  "description": "Buy the groceries",
  "priority": "High" // one of: Low, Medium, High, Vital
}
```

## Endpoints

HTTP
- GET `/` — health/sample endpoint; returns plain text "Healthy".
- Static files under `/static/**` — served from `src/main/resources/static/`.

WebSockets (JSON messages)
- WS `/tasks` — on connect, streams all tasks (as `Task` JSON frames) once, then closes.
- WS `/tasks2` — on connect, streams all tasks; then:
  - receive: accepts `Task` JSON frames to add new tasks to the DB
  - broadcast: relays each new task to all connected sessions

### Curl examples
- Hello world
```
curl -i http://localhost:8080/
```

### WebSocket examples
Using websocat:
```
# Stream all tasks once
websocat ws://localhost:8080/tasks

# Interactive stream + publish new tasks
websocat -t ws://localhost:8080/tasks2
{"name":"painting","description":"Paint the fence","priority":"Medium"}
```

## Notes
- On first start, the DB schema is created and a few sample tasks are seeded.
- See:
  - `Application.kt` for module wiring
  - `Routing.kt` for HTTP routes
  - `Sockets.kt` for WS behavior
  - `Databases.kt` and `db/mapping.kt` for DB configuration and Exposed models

