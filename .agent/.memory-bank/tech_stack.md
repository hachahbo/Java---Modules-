# Tech Stack & Coding Rules

## Backend (Laravel Microservices)
- **Framework:** Laravel 11 (or 12 for ACL)
- **PHP Version:** 8.2+
- **Database:** PostgreSQL with `pgvector` for AI features.
- **Communication:**
  - REST API for synchronous calls.
  - RabbitMQ for async events (Use `php artisan rabbit:consume`).
- **Code Style:**
  - Use Service Pattern (Controller -> Service -> Repository).
  - Strict Types: `declare(strict_types=1);`
  - Return Types: Always define return types for methods.

## Frontend (Nuxt)
- **Framework:** Nuxt 3 with Vue 3 (Composition API).
- **Styling:** Tailwind CSS 4.0.
- **State:** Use Pinia for state management.
- **Data Fetching:** Use `useFetch` or custom composables.

## Critical Constraints
- **NO Direct DB Access:** Services cannot access each other's databases directly. They must use API calls or Event Bus.
- **Docker:** All services run in Docker. Use `make up-service_name` to start.
