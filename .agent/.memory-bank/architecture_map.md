# Architecture & Port Mapping

## Infrastructure
- **Kong Gateway:** http://localhost:8000 (Proxy), 8001 (Admin)
- **RabbitMQ:** http://localhost:15672 (User/Pass: guest/guest)
- **Redis:** Port 6379
- **PostgreSQL:** Port 5432 (Shared DB Server)

## Core Microservices (Laravel 11 / PHP 8.2)
| Service | Port | Description |
| :--- | :--- | :--- |
| **auth-service** | 8003 | JWT, Login, Registration |
| **ticket-service** | 8005 | CRUD, Status, Lifecycle |
| **categorization-service** | 8006 | Categories, Topics, Dynamic Forms |
| **priority-service** | 8004 | SLAs, Priority Calculation |
| **assignment-service** | 8007 | Round-robin, Team assignment |
| **organization-service** | 8008 | Teams, Departments |
| **notification-service** | 8009 | Email, SMS, Push |
| **chat-service** | 8014 | Real-time messaging |
| **acl-service** | 8013 | RBAC, Permissions, Menus |

## Frontend
- **frontend-service:** http://localhost:3000 (Nuxt 3, Vue 3, Tailwind 4)

## AI & Realtime
- **llm-service:** Port 11434 (Ollama)
- **reverb-service:** Port 8010 / 8080 (WebSockets)

## Data Flow Rule
1. User Request -> Kong (8000) -> Microservice
2. Microservice -> RabbitMQ -> Worker (Async processing)
3. Microservice -> Redis (Cache)
