# 📘 Java Modules — Project Rules & AI Mentorship Guide

> This document is the **single source of truth** for this project.
> All interactions, solutions, and behaviors must conform to these rules.

---

## Chapter 0 — AI Mentorship & Learning Goals

| Principle | Description |
|---|---|
| **Mentor, not generator** | The AI is a tutor. The primary goal is for you to *learn and understand* Java deeply, not simply finish exercises. |
| **Comprehension over completion** | The AI will not write final answers to copy-paste. You are here to learn the **how** and the **why**. |
| **Step-by-step unblocking** | When stuck, expect hints, identification of logical flaws, and concept explanations — not direct solutions. |
| **Explain everything** | When code *is* written or corrected together, the AI must explain *why* it was written that way: the logic, the Java mechanics, and the best approach for the problem. |
| **Socratic method** | The AI may ask guiding questions to test your understanding before revealing answers. |

---

## Chapter I — General Rules

- **Single source of truth:** Use only this document as reference. Do not rely on unofficial specs or rumors.
- **Java version:** Use the latest LTS version of Java. Ensure `javac` and `java` for that version are installed and available in `PATH`.
- **Required runtimes:** Your code must run correctly on both:
  - Standard JVM (matching the LTS JDK)
  - GraalVM (same major Java version)
- **IDE:** Any IDE is allowed. IntelliJ IDEA is recommended.
- **Code style:** Follow [Oracle Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html) for all exercises (naming, formatting, braces, etc.).
- **Permissions:** Ensure your project is readable by the grader.
- **Git repository:** All assessed solutions must live in your Git repository.
- **No extra files:** Do not leave unrequested files in exercise directories. Configure `.gitignore` to exclude IDE, build, and temporary files.
- **Real computation:** When precise output is required, **compute the result** — do not hardcode precalculated values.
- **Ask for help smartly:** Try to solve the problem yourself first, then ask a peer. Ask clear, minimal, and precise questions (StackOverflow style).
- **Read examples carefully:** Examples often carry important details not stated in the exercise text. **Always match examples exactly.**
- **Standard output only:** Use `System.out` for all output unless the exercise explicitly says otherwise.
- **Mindset:** Never leave for tomorrow what you can do today. May the Force be with you! 🚀

---

## Chapter II — Project Structure (Java Modules)

### Module Layout

The project is split into **9 Java modules**:

```
module00/
module01/
...
module08/
```

### Exercise Layout

Each module contains several exercises, each in its own **independent** subdirectory:

```
moduleXX/
├── ex00/
│   └── Program.java
├── ex01/
│   └── Program.java
└── ex02/
    └── Program.java
```

### Rules

| Rule | Detail |
|---|---|
| **Turn-in directory** | For exercise `exNN`, turn in exactly `exNN/` inside the corresponding module. |
| **No extra nesting** | Do **not** add `src/` inside `ex00/` unless explicitly required. |
| **Main file name** | Single-program exercises must use **`Program.java`**, placed directly in the exercise directory (e.g., `module00/ex00/Program.java`). |
| **Packages** | Use the **default package** (no `package` line) unless the exercise requires otherwise, so the grader can compile with `javac Program.java`. |
| **Dependencies** | Use only the **Java standard library** unless explicitly stated otherwise. No Maven, Gradle, or external frameworks. |

---

## Chapter III — Technical Setup

### 1. Install Java LTS

Install the latest LTS JDK (Java 17 or Java 21 depending on requirements).

```bash
java -version
javac -version
```

### 2. Install GraalVM

Install GraalVM for the same Java major version. Verify it is available:

```bash
java -version   # after switching to GraalVM
```

Your program must behave **identically** under both standard JVM and GraalVM.

### 3. Configure Git

<!-- ```bash
git config --global user.name "Your Name"
git config --global user.email "your@email.com"
``` -->

Initialize the repository if not already done and **push regularly**.

### 4. Compile & Run (Standard JVM)

```bash
cd module00/ex00
javac Program.java
java Program
```

### 5. Compile & Run (GraalVM)

Use GraalVM's `javac` / `java` (or the provided environment script) to compile and run the same `Program.java`.

---

## Chapter IV — Exercise Specification Template

Every exercise follows this standard template:

```
Exercise XX : Title
─────────────────────────────────────
Turn-in directory : exXX/
Files to turn in  : Program.java (or other required files)

Allowed functions / APIs:
  - Specific classes and methods from the standard library allowed

Input / Output:
  - How input is received (args, stdin, etc.)
  - How output is printed (System.out, etc.)

Types:
  - Java types expected (primitives, arrays, String, etc.)

Operators:
  - Allowed operators (+, -, *, /, %, comparisons, logical, etc.)

Expected behavior:
  - Clear problem statement
  - One or more input/output examples

You must never add extra files or behaviors not listed here.
```

---

## Chapter V — Example: Module 00 / Exercise 00 (Sum of Digits)

```
Exercise 00 : Sum of Digits
─────────────────────────────────────
Turn-in directory : ex00/
Files to turn in  : Program.java

Allowed functions : System.out
Types             : primitive types (and String if needed for input parsing)
Operators         : +, -, *, /, %, comparisons, logical operators
```

**Goal:** Write a program that computes the sum of the digits of a non-negative integer.

**Example:**

| Input | Output | Explanation |
|---|---|---|
| `1234` | `10` | 1 + 2 + 3 + 4 = 10 |

**Implementation notes:**
- Use only allowed functions and operators.
- Do **not** use arrays, collections, or advanced APIs unless explicitly permitted.
- Do **not** hardcode answers — the program must work for any valid input.

---

## Chapter VI — Final Advice

- ✅ Read the rules **before starting each module**.
- ✅ Re-read the exercise and its examples **before submitting**.
- ✅ Keep commits **small and meaningful**.
- ✅ Test your program with **multiple inputs**, not only the given example.
- ✅ And again: **may the Force be with you!** 🌟