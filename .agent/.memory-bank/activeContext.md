# Active Development Context

## Current Sprint Focus
**Sprint Period**: December 2025  
**Focus Area**: Dynamic Form Generation & Priority System Enhancement

---

## Recently Completed Features

### ✅ Category-Based Topic Filtering (2025-12-18)
**Services**: Categorization Service + Frontend Service
- Added backend endpoint `GET /api/topics/by-category/{categoryId}` in TopicsController
- Updated CreateTicket.vue to dynamically load topics based on selected category
- Topic field now disabled until category is selected
- Implemented watcher for automatic topic refresh on category change
- **Status**: Implemented, ready for manual testing

### ✅ Dynamic Ticket Form Fields (2025-12-18)
**Service**: Frontend Service
- Implemented dynamic form field rendering in `CreateTicket.vue` based on topic selection
- Enhanced payload to include `form_fields` data
- Disabled field type editing in `FormFieldsBuilderModal.vue`
- **Status**: Implemented, documentation added to `frontend-service/Markdowns/DynamicTopicFields.md`

### ✅ Backend API Parameter Parsing (2025-12-15)
**Service**: Categorization Service
- Created `InterventionApiController` with parsing endpoint
- Added `parseFieldsFromSchema()` method to `TopicFieldGenerator`
- New endpoint: `GET /interventions/{ids}/form-fields`
- Simplified frontend by removing 130+ lines of client-side parsing
- **Status**: Implemented, services restarted, ready for testing

---

## In Progress

### 🔄 Priority Service Repository Updates
- Local changes staged for commit
- Behind origin/main by 6 commits
- Files to be committed:
  - Modified: `app/Models/User.php`, `bootstrap/app.php`, `composer.json`, `routes/web.php`
  - New seeders: `PriorityMatrixSeeder`, `PrioritySeeder`, `SeveritySeeder`, `UrgencySeeder`
  - Deleted: `composer.lock` (to be regenerated)

---

## Upcoming Tasks

### 📋 Next Sprint Items
- [ ] Frontend integration for priority matrix UI
- [ ] API endpoints for priority calculation service
- [ ] Intervention execution tracking improvements
- [ ] Advanced analytics dashboard
- [ ] Performance optimization for permission caching

---

## Active Services Under Development

1. **Categorization Service** - Form builder enhancements
2. **Priority Service** - Matrix calculation engine
3. **Frontend Service** - Dynamic form rendering
4. **Intervention Service** - Execution audit improvements

---

## Known Issues / Technical Debt

### Priority Service
- Needs git commit for recent seeders
- Composer.lock regeneration required
- Migration testing pending

### General
- Documentation updates needed for new features
- Unit tests for `TopicFieldGenerator` service
- Integration tests for priority matrix calculations

---

## Environment Status

### Development Environment
- **All Services**: Running via Docker Compose
- **Database**: PostgreSQL (shared instance, schema-isolated)
- **Message Queue**: RabbitMQ active
- **Cache**: Redis active
- **Gateway**: Kong configured and routing

### Service Health
- ✅ Frontend Service (port 3000)
- ✅ Auth Service (port 8003)
- ✅ Ticket Service (port 8005)
- ✅ Categorization Service (port 8006)
- ✅ Priority Service (port 8004)
- ✅ Intervention Service (port 8011)
- ✅ ACL Service (port 8013)

---

## Recent Decisions

### Architecture Decisions
1. **Dynamic Forms**: Use API schemas as single source of truth
2. **Priority Matrix**: Implement as seeded data rather than hardcoded logic
3. **JSONB Fields**: Use for flexible data structures (options, actions)
4. **Service Communication**: REST APIs for sync, RabbitMQ for async

### Technology Choices
- **Tailwind CSS 4.0**: Adopted for frontend styling
- **Nuxt 3 Composition API**: Consistent frontend patterns
- **Laravel 11/12**: Latest LTS versions for backend services
- **Ollama**: On-premise LLM for AI features

---

## Team Notes

### Development Workflow
- Use `make` commands for all Docker operations
- Create markdown documentation in `Markdowns/` folder for each major update
- Update `.memory-bank` files after significant changes
- Follow microservices communication rules (no direct DB access)

### Git Workflow
- Feature branches for new development
- Commit seeders and migrations together
- Update composer.lock after dependency changes
- Document breaking changes in markdown files

---

## Quick Reference

### Most Used Commands
```bash
# Start all services
make quick-start

# View service logs
make logs-[service]

# Access service shell
make shell-[service]

# Run migrations
php artisan migrate

# Run seeders
php artisan db:seed
```

### Current Work Branches
- Main: `main` (stable)
- Development: Active on local branches

---

**Last Updated**: 2025-12-18 16:55
