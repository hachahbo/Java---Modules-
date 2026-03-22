# Memory Bank

## Concept
AI assistants have no memory between sessions. This folder serves as the **external memory** containing all project context, architecture, and current status.

After every reset, the AI relies ENTIRELY on the Memory Bank to understand the project and continue work effectively.

## Critical Files (Read EVERY Session)

```
.memory-bank/
├── activeContext.md          # 🔥 START HERE - Current work status
├── project_brief.md          # 📚 Service documentation (all 15 services)
├── tech_stack.md             # 💻 Coding standards and constraints
├── architecture_map.md       # 🗺️ Ports, infrastructure, data flow
├── productContext.md         # 🎯 Product vision and business context
├── progress.md               # 📊 Progress tracking and metrics
└── agent-memory-guide.md     # 📖 Complete memory bank protocol
```

## Reading Protocol

**At session start, read in this order:**
1. **activeContext.md** - What's happening NOW
2. **project_brief.md** (relevant sections) - Service details
3. **tech_stack.md** - Coding rules and constraints
4. Others as needed

## When to Update

| Trigger | Files to Update |
|---------|----------------|
| Feature completed | `activeContext.md`, `progress.md` |
| Service architecture changed | `project_brief.md`, `architecture_map.md` |
| Tech stack changed | `tech_stack.md` |
| End of sprint | `progress.md`, `activeContext.md` |

## Memory-Bank-First Philosophy

This project uses a **memory-bank-first** approach:
- Memory Bank = Project knowledge (what)
- `.agent/rules/` = AI behavior (how)
- The AI MUST consult the memory bank before any work

## Quick Reference

| Need to know... | Read this file |
|----------------|----------------|
| Current work status | `activeContext.md` |
| Service ports | `architecture_map.md` |
| Service details | `project_brief.md` |
| Coding standards | `tech_stack.md` |
| Product context | `productContext.md` |
| Overall progress | `progress.md` |

---

**Remember**: The Memory Bank is the single source of truth. Keep it accurate, concise, and up-to-date.
