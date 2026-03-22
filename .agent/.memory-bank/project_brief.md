# System Tickets Services Documentation

## Overview

This document provides comprehensive documentation for all microservices in the **System Tickets Services** ecosystem. This is a microservices-based ticketing and support system built with Laravel (backend services), Nuxt.js/Vue 3 (frontend), and PostgreSQL databases.

---

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Infrastructure Services](#infrastructure-services)
- [Core Services](#core-services)
- [Worker/Consumer Services](#worker-consumer-services)
- [Makefile Commands Reference](#makefile-commands-reference)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)

---

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                       Kong API Gateway                          │
│                    (Ports: 8000, 8001, 8002)                    │
└────────────────────────┬────────────────────────────────────────┘
                         │
        ┌────────────────┴────────────────┐
        │                                 │
┌───────▼──────┐                 ┌───────▼──────────┐
│  Frontend    │                 │   Microservices  │
│  Service     │                 │   (Backend)      │
│  (Nuxt/Vue)  │                 └──────────────────┘
└──────────────┘                          │
                                          │
                    ┌─────────────────────┼──────────────────────┐
                    │                     │                      │
            ┌───────▼───────┐    ┌───────▼────────┐    ┌───────▼──────┐
            │   RabbitMQ    │    │     Redis      │    │  PostgreSQL  │
            │ (Message Bus) │    │    (Cache)     │    │  (Databases) │
            └───────────────┘    └────────────────┘    └──────────────┘
```

---

## Infrastructure Services

### Kong API Gateway
**Container**: `kong`  
**Ports**: 
- `8000` - Proxy
- `8001` - Admin API
- `8002` - Manager
- `8443` - Proxy SSL
- `8444` - Admin SSL

**Purpose**: API Gateway for routing, authentication, rate limiting, and request/response transformation.

**Database**: `kong-database` (PostgreSQL)

**Make Commands**:
```bash
make up-kong          # Start Kong gateway
make restart-kong     # Restart Kong
make logs-kong        # View Kong logs
make shell-kong       # Access Kong container
```

---

### RabbitMQ
**Container**: `rabbitmq`  
**Ports**: 
- `5672` - AMQP Broker
- `15672` - Management UI

**Purpose**: Message broker for asynchronous communication between services, event streaming, and queue management.

**Credentials**: 
- Username: `guest` (configurable via `RABBITMQ_USER`)
- Password: `guest` (configurable via `RABBITMQ_PASS`)

**Make Commands**:
```bash
make logs-rabbitmq    # View RabbitMQ logs
make shell-rabbitmq   # Access RabbitMQ shell
```

---

### Redis
**Container**: `redis`  
**Port**: `6379`

**Purpose**: In-memory cache for session storage, permission caching, and data caching across services.

**Make Commands**:
```bash
make up-redis         # Start Redis
make restart-redis    # Restart Redis
make logs-redis       # View Redis logs
make shell-redis      # Access Redis CLI
```

---

## Core Services

### 1. Frontend Service
**Container**: `frontend-service`  
**Port**: `3000`  
**Technology**: Nuxt.js 3 + Vue 3 + TypeScript + Tailwind CSS 4

**Purpose**: User-facing web application providing the UI for the ticketing system. Includes dynamic data tables, form builders, ticket management interfaces, and responsive design.

**Key Features**:
- Server-side rendering (SSR) with Nuxt 3
- Dynamic data table components
- Real-time WebSocket connections (via Reverb)
- Custom form builder for topics
- Responsive UI with Tailwind CSS
- API schema parsing for dynamic forms

**API Endpoints**: Communicates with all backend services via Kong Gateway

**Make Commands**:
```bash
make up-frontend         # Start frontend service
make restart-frontend    # Restart frontend
make logs-frontend       # View frontend logs
make shell-frontend      # Access frontend container
```

**Key Directories**:
- `pages/` - Nuxt pages (routes)
- `components/` - Vue components
- `composables/` - Vue composables
- `assets/` - Static assets and styles

---

### 2. Auth Service
**Container**: `auth-service`  
**Port**: `8003`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Authentication and user management service. Handles user registration, login, JWT token generation, password management, and user profile operations.

**Key Features**:
- JWT-based authentication
- User registration and login
- Password reset functionality
- User profile management
- Gateway authentication support
- Queue-based email notifications

**Database Schema**: `auth` schema in PostgreSQL

**Worker**: `auth-worker` - Processes authentication-related queue jobs

**Make Commands**:
```bash
make up-auth          # Start auth service
make restart-auth     # Restart auth service
make logs-auth        # View auth logs
make shell-auth       # Access auth container
```

---

### 3. Ticket Service
**Container**: `ticket-service`  
**Port**: `8005`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Core ticket management service. Handles ticket creation, updates, assignments, status changes, and ticket lifecycle management.

**Key Features**:
- Ticket CRUD operations
- Ticket status management
- Ticket assignment tracking
- Ticket history and audit trail
- Comment and attachment support
- Integration with categorization and priority services

**Database Schema**: `ticket` schema in PostgreSQL

**Consumer**: `ticket-consumer` - Processes ticket-related events from RabbitMQ

**Make Commands**:
```bash
make up-ticket         # Start ticket service
make restart-ticket    # Restart ticket service
make logs-ticket       # View ticket logs
make shell-ticket      # Access ticket container
```

---

### 4. Categorization Service
**Container**: `categorization-service`  
**Port**: `8006`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Manages ticket categories, topics, and dynamic form field generation for tickets based on API schemas.

**Key Features**:
- Category and topic management
- Topic-to-intervention mapping
- Dynamic form field generation
- API schema parsing
- Form field customization
- Seeder for topics and form fields

**Database Schema**: `categorization` schema in PostgreSQL

**Models**:
- `Category` - Ticket categories
- `Topic` - Ticket topics
- `TopicFormField` - Dynamic form fields per topic
- `FormField` - Form field definitions

**Make Commands**:
```bash
make up-categorization         # Start categorization service
make restart-categorization    # Restart service
make logs-categorization       # View logs
make shell-categorization      # Access container
```

---

### 5. Intervention Service
**Container**: `intervention-service`  
**Port**: `8011`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Automates remediation actions for tickets through API-driven interventions. Provides complete audit trails and configurable retry logic.

**Key Features**:
- API endpoint configuration for remediation actions
- Intervention group management
- Topic-based intervention mapping
- Automated and manual intervention execution
- Complete audit trail with request/response tracking
- Retry logic with configurable delays
- Version tracking for compliance

**Database Schema**: `intervention` schema in PostgreSQL

**Core Tables**:
- `apis` - API endpoint configurations
- `intervention_groups` - Intervention categories
- `interventions` - Remediation actions
- `intervention_topics` - Topic-intervention mappings
- `intervention_executions` - Execution audit trail

**Make Commands**:
```bash
make up-intervention         # Start intervention service
make restart-intervention    # Restart service
make logs-intervention       # View logs
make shell-intervention      # Access container
```

**Documentation**: See [intervention-service/README.md](intervention-service/README.md) for detailed documentation.

---

### 6. Priority Service
**Container**: `priority-service`  
**Port**: `8004`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Manages ticket priority calculations, priority rules, and automatic priority adjustments based on SLA, urgency, and impact factors.

**Key Features**:
- Dynamic priority calculation
- Priority rule engine
- SLA tracking
- Automatic priority escalation
- Priority matrix configuration

**Database Schema**: `priority` schema in PostgreSQL

**Workers**:
- `priority-consumer` - Consumes priority-related messages
- `priority-worker` - Processes priority calculation jobs

**Make Commands**:
```bash
make up-priority         # Start priority service
make restart-priority    # Restart service
make logs-priority       # View logs
make shell-priority      # Access container
```

---

### 7. Assignment Service
**Container**: `assignment-service`  
**Port**: `8007`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Manages ticket assignment to users, groups, and teams. Handles load balancing, skill-based routing, and assignment rules.

**Key Features**:
- Ticket assignment to users/teams
- Round-robin assignment logic
- Skill-based routing
- Workload balancing
- Assignment history tracking

**Database Schema**: `assignment` schema in PostgreSQL

**Make Commands**:
```bash
make up-assignment         # Start assignment service
make restart-assignment    # Restart service
make logs-assignment       # View logs
make shell-assignment      # Access container
```

---

### 8. ACL Service
**Container**: `acl-service`  
**Port**: `8013`  
**Technology**: Laravel 12 + PHP 8.2+ + PostgreSQL

**Purpose**: Comprehensive Role-Based Access Control (RBAC) service providing centralized authorization, permission management, and hierarchical menu systems.

**Key Features**:
- Role-based access control (RBAC)
- Direct user permissions
- Permission inheritance
- Action-level permissions (JSONB)
- Ability levels with custom rules
- Hierarchical menu system
- Permission caching (Redis)
- Gateway authentication
- Dynamic permission validation
- Materialized views for performance

**Database Schema**: `access` schema in PostgreSQL

**Core Tables**:
- `users` - User accounts
- `roles` - Role definitions
- `permissions` - Permission definitions with JSONB actions
- `role_user` - User-role assignments
- `role_permission` - Role-permission mappings
- `user_permission` - Direct user permissions
- `menus` - Hierarchical menu structure
- `ability_levels` - Resource-level access rules

**Make Commands**:
```bash
make up-acl         # Start ACL service
make restart-acl    # Restart service
make logs-acl       # View logs
make shell-acl      # Access container
```

**Documentation**: See [acl-service/README.md](acl-service/README.md) for comprehensive API documentation.

---

### 9. Organization Service
**Container**: `organization-service`  
**Port**: `8008`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Manages organizational structure including departments, teams, locations, and hierarchical relationships.

**Key Features**:
- Department management
- Team management
- Organizational hierarchy
- User-organization mapping
- Location management

**Database Schema**: `organization` schema in PostgreSQL

**Make Commands**:
```bash
make up-organization         # Start organization service
make restart-organization    # Restart service
make logs-organization       # View logs
make shell-organization      # Access container
```

---

### 10. Notification Service
**Container**: `notification-service`  
**Port**: `8009`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Centralized notification service for email, SMS, in-app notifications, and webhook integrations.

**Key Features**:
- Multi-channel notifications (email, SMS, push)
- Notification templates
- Notification preferences per user
- Notification history
- Webhook integrations

**Database Schema**: `notification` schema in PostgreSQL

**Make Commands**:
```bash
make up-notification         # Start notification service
make restart-notification    # Restart service
make logs-notification       # View logs
make shell-notification      # Access container
```

---

### 11. Escalation Service
**Container**: `escalation-service`  
**Port**: `8015`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Manages ticket escalation rules, SLA violations, and automatic escalation workflows.

**Key Features**:
- Escalation rule engine
- SLA monitoring
- Automatic escalation triggers
- Escalation path configuration
- Escalation notifications

**Database Schema**: `escalation` schema in PostgreSQL

**Make Commands**:
```bash
make up-escalation         # Start escalation service
make restart-escalation    # Restart service
make logs-escalation       # View logs
make shell-escalation      # Access container
```

---

### 12. Review Service
**Container**: `review-service`  
**Port**: `8012`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Manages ticket reviews, quality assurance, feedback collection, and rating systems.

**Key Features**:
- Ticket review workflows
- Quality scoring
- Feedback collection
- Rating systems
- Review analytics

**Database Schema**: `review` schema in PostgreSQL

**Make Commands**:
```bash
make up-review         # Start review service
make restart-review    # Restart service
make logs-review       # View logs
make shell-review      # Access container
```

---

### 13. Chat Service
**Container**: `chat-service`  
**Port**: `8014`  
**Technology**: Laravel 11 + PHP 8.2+ + PostgreSQL

**Purpose**: Real-time chat and messaging service for ticket conversations and team collaboration.

**Key Features**:
- Real-time messaging
- Ticket conversation threads
- File attachments
- Read receipts
- Typing indicators

**Database Schema**: `chat` schema in PostgreSQL

**Make Commands**:
```bash
make up-chat         # Start chat service
make restart-chat    # Restart service
make logs-chat       # View logs
make shell-chat      # Access container
```

---

### 14. Reverb Service
**Container**: `reverb-service`  
**Port**: `8010`  
**Technology**: Laravel 11 + PHP 8.2+ + Laravel Reverb

**Purpose**: WebSocket server for real-time event broadcasting, live updates, and bidirectional communication.

**Key Features**:
- WebSocket server (Laravel Reverb)
- Real-time event broadcasting
- Channel subscriptions
- Presence channels
- Private channels

**WebSocket Server**: `reverb-server` on port `8080`

**Make Commands**:
```bash
make up-reverb         # Start Reverb service
make restart-reverb    # Restart service
make logs-reverb       # View logs
make shell-reverb      # Access container
```

---

### 15. LLM Service
**Container**: `llm-service`  
**Port**: `11434`  
**Technology**: Ollama (AI/LLM)

**Purpose**: AI and Large Language Model service for intelligent ticket analysis, categorization suggestions, and automated responses.

**Key Features**:
- AI-powered ticket categorization
- Sentiment analysis
- Automated response suggestions
- Text summarization
- Entity extraction

**Base Image**: `ollama/ollama:latest`

**Make Commands**:
```bash
make up-llm         # Start LLM service
make restart-llm    # Restart service
make logs-llm       # View logs
make shell-llm      # Access container
```

---

## Worker/Consumer Services

### Auth Worker
**Container**: `auth-worker`  
**Command**: `php artisan queue:work rabbitmq`

**Purpose**: Processes authentication-related queue jobs such as welcome emails, password reset emails, and verification emails.

---

### Priority Consumer
**Container**: `priority-consumer`  
**Command**: `php artisan rabbit:consume`

**Purpose**: Consumes priority-related messages from RabbitMQ for real-time priority calculations.

---

### Priority Worker
**Container**: `priority-worker`  
**Command**: `php artisan queue:work rabbitmq`

**Purpose**: Processes priority calculation jobs, SLA checks, and automatic escalation tasks.

---

### Ticket Consumer
**Container**: `ticket-consumer`  
**Command**: `php artisan rabbit:consume`

**Purpose**: Consumes ticket-related events from RabbitMQ for asynchronous ticket processing.

---

### Reverb Server
**Container**: `reverb-server`  
**Port**: `8080`  
**Command**: `php artisan reverb:start --debug`

**Purpose**: WebSocket server for real-time event broadcasting.

---

## Makefile Commands Reference

### Docker Compose Operations

| Command | Description |
|---------|-------------|
| `make up` | Start all services (with build) |
| `make up-d` | Start all services in detached mode (background) |
| `make down` | Stop and remove all containers |
| `make stop` | Stop all services (without removing containers) |
| `make start` | Start existing containers (no build) |
| `make restart` | Restart all services |
| `make rebuild` | Rebuild and restart everything |
| `make rebuild-service SERVICE="service-name"` | Rebuild specific service(s) |

### Service Groups

| Command | Description |
|---------|-------------|
| `make up-core` | Start core infrastructure (Kong, DB, RabbitMQ, Redis) |
| `make up-ticket-services` | Start all ticket-related services |
| `make up-communication` | Start communication services (chat, notification, reverb) |
| `make up-workers` | Start all worker/consumer services |
| `make up-ai` | Start AI/LLM services |

### Monitoring & Status

| Command | Description |
|---------|-------------|
| `make ps` | Show status of all containers |
| `make logs` | Show logs for all services |
| `make logs-[service]` | Show logs for specific service |
| `make stats` | Show container resource usage statistics |
| `make health` | Check health status of all services |
| `make ports` | Show all exposed ports |

### Individual Service Management

| Command | Description |
|---------|-------------|
| `make up-[service]` | Start specific service |
| `make restart-[service]` | Restart specific service |
| `make logs-[service]` | View logs for specific service |
| `make shell-[service]` | Access service shell |

**Available services**: `kong`, `frontend`, `auth`, `ticket`, `categorization`, `intervention`, `priority`, `assignment`, `acl`, `organization`, `notification`, `escalation`, `review`, `chat`, `reverb`, `llm`, `redis`

### Cleanup Commands

| Command | Description |
|---------|-------------|
| `make clean` | Stop and clean containers and images |
| `make clean-all` | Stop and clean everything (containers, images, volumes) |
| `make rm-containers` | Remove all containers |
| `make rm-images` | Remove all images |
| `make rm-volumes` | Remove all volumes |
| `make nuke` | Nuclear option: Remove everything and system prune |

### Development Helpers

| Command | Description |
|---------|-------------|
| `make setup-env` | Copy .env.example to .env for all services |
| `make watch` | Watch container status (auto-refresh) |
| `make tail` | Tail logs for all services |
| `make pull` | Pull latest images |
| `make build` | Build all images without starting |
| `make build-nc` | Build all images without cache |
| `make build-nc-service SERVICE="service-name"` | Build specific service without cache |
| `make validate` | Validate docker-compose.yml syntax |

### Quick Actions

| Command | Description |
|---------|-------------|
| `make list-services` | List all available services |
| `make dev` | Start in detached mode and show logs |
| `make quick-start` | Quick start all services in background |
| `make reset` | Full reset: clean everything and rebuild |
| `make help` | Show all available commands |

---

## Technology Stack

### Backend Services
- **Framework**: Laravel 11/12
- **Language**: PHP 8.2+
- **Database**: PostgreSQL 13+
- **Cache**: Redis 7
- **Queue**: RabbitMQ 3
- **WebSocket**: Laravel Reverb
- **ORM**: Eloquent

### Frontend Service
- **Framework**: Nuxt.js 3
- **Language**: TypeScript
- **UI Library**: Vue 3 (Composition API)
- **CSS Framework**: Tailwind CSS 4.0
- **HTTP Client**: Axios
- **Build Tool**: Vite

### Infrastructure
- **API Gateway**: Kong Gateway
- **Containerization**: Docker + Docker Compose
- **Message Broker**: RabbitMQ
- **AI/LLM**: Ollama

---

## Getting Started

### Prerequisites
- Docker and Docker Compose
- Make (for Makefile commands)
- Git

### Quick Start

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd system-tickets-services
   ```

2. **Set up environment files**:
   ```bash
   make setup-env
   ```

3. **Start all services**:
   ```bash
   make quick-start
   ```

4. **Check service status**:
   ```bash
   make ps
   ```

5. **View exposed ports**:
   ```bash
   make ports
   ```

6. **Access services**:
   - **Frontend**: http://localhost:3000
   - **Kong Admin API**: http://localhost:8001
   - **RabbitMQ Management**: http://localhost:15672 (guest/guest)
   - **Auth Service**: http://localhost:8003
   - **Ticket Service**: http://localhost:8005
   - **Other services**: See `make ports` for complete list

### Running Individual Services

```bash
# Start only core infrastructure
make up-core

# Start frontend
make up-frontend

# Start ticket-related services
make up-ticket-services

# Start communication services
make up-communication
```

### Viewing Logs

```bash
# All services
make logs

# Specific service
make logs-frontend
make logs-auth
make logs-ticket

# Ticket-related services
make logs-ticket-services
```

### Accessing Service Shells

```bash
# Access Laravel service shell
make shell-auth
make shell-ticket
make shell-categorization

# Access frontend shell
make shell-frontend

# Access database
make shell-db

# Access Redis CLI
make shell-redis
```

### Database Migrations

For Laravel services, access the shell and run migrations:

```bash
# Example: Auth service
make shell-auth
php artisan migrate
php artisan db:seed
```

### Rebuilding Services

```bash
# Rebuild all services
make rebuild

# Rebuild specific service
make rebuild-service SERVICE="auth-service"
make rebuild-service SERVICE="frontend-service categorization-service"
```

---

## Service Port Reference

| Service | Port | URL |
|---------|------|-----|
| **Frontend** | 3000 | http://localhost:3000 |
| **Kong Proxy** | 8000 | http://localhost:8000 |
| **Kong Admin** | 8001 | http://localhost:8001 |
| **Kong Manager** | 8002 | http://localhost:8002 |
| **Auth Service** | 8003 | http://localhost:8003 |
| **Priority Service** | 8004 | http://localhost:8004 |
| **Ticket Service** | 8005 | http://localhost:8005 |
| **Categorization Service** | 8006 | http://localhost:8006 |
| **Assignment Service** | 8007 | http://localhost:8007 |
| **Organization Service** | 8008 | http://localhost:8008 |
| **Notification Service** | 8009 | http://localhost:8009 |
| **Reverb Service** | 8010 | http://localhost:8010 |
| **Intervention Service** | 8011 | http://localhost:8011 |
| **Review Service** | 8012 | http://localhost:8012 |
| **ACL Service** | 8013 | http://localhost:8013 |
| **Chat Service** | 8014 | http://localhost:8014 |
| **Escalation Service** | 8015 | http://localhost:8015 |
| **Reverb WebSocket** | 8080 | ws://localhost:8080 |
| **LLM Service (Ollama)** | 11434 | http://localhost:11434 |
| **RabbitMQ AMQP** | 5672 | amqp://localhost:5672 |
| **RabbitMQ Management** | 15672 | http://localhost:15672 |
| **Redis** | 6379 | redis://localhost:6379 |

---

## Additional Resources

- **ACL Service Documentation**: [acl-service/README.md](acl-service/README.md)
- **Intervention Service Documentation**: [intervention-service/README.md](intervention-service/README.md)
- **Frontend Service Documentation**: [frontend-service/README.md](frontend-service/README.md)
- **Docker Compose Configuration**: [docker-compose.yml](docker-compose.yml)
- **Makefile**: [Makefile](Makefile)

---

## Support

For issues, questions, or contributions, please contact the development team or create an issue in the project repository.

---

**Last Updated**: 2025-12-11  
**Version**: 1.0.0
