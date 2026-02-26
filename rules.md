Chapter I  
General Rules

- **Single source of truth**: Use this page as the only reference. Do not listen to rumors or unofficial specs about how to prepare your solution.
- **Java version**: You must use the latest LTS version of Java. Make sure that the compiler (`javac`) and interpreter (`java`) for this version are installed and available in your `PATH`.
- **Runtimes**: You must be able to run your code with both:
  - **Standard JVM** (the JDK that matches the LTS version), and
  - **GraalVM** (same major Java version as your JDK).
- **IDE**: You may use any IDE to write and debug the source code (IntelliJ IDEA is recommended).
- **Code style**: Code is read more often than written. Follow the official Oracle Java code conventions (naming, formatting, braces, etc.) for every exercise.
- **Permissions**: Pay attention to file and directory permissions. Your project must be readable by the grader.
- **Git repository**: To be assessed, your solution must live in your Git repository.
- **No extra files**: Do not leave in any exercise directory other files than those explicitly specified by the exercise instructions. Configure `.gitignore` to avoid committing IDE, build, and temporary files.
- **Real computation**: When precise output is required, it is forbidden to print a precalculated value instead of actually computing the result.
- **Ask for help smartly**: First, try to solve the problem yourself. If you are still stuck, ask another student. Learn how to ask clear, minimal, and precise questions (StackOverflow style).
- **Read examples slowly**: Examples may require details that are not otherwise specified in the text of the exercise. Always match the examples exactly.
- **Standard output only**: Use `System.out` for all output unless the exercise explicitly says otherwise.
- **Mindset**: And may the Force be with you! Never leave for tomorrow what you can do today. :)

---

Chapter II  
Project Structure (Java Modules)

- **Modules**: The project is split into **9 Java modules**, named for example:
  - `module00`, `module01`, `module02`, ..., `module08`
- **Exercises per module**:
  - Each module contains several exercises.
  - Each exercise lives in its own subdirectory: `ex00`, `ex01`, `ex02`, ...
  - Each exercise directory is completely independent.
- **Turn‑in directory**:
  - For an exercise `exNN`, your turn‑in directory is exactly `exNN/` inside the corresponding module.
  - Do **not** add extra nesting (no `src/` inside `ex00/` unless the exercise explicitly tells you to).
- **Main file name**:
  - When an exercise asks for a single program, the main file must be named `Program.java`, placed directly inside the exercise directory (for example `module00/ex00/Program.java`).
- **Packages**:
  - Unless an exercise explicitly requires packages, keep the default package (no `package` line) so that the grader can compile simply with `javac Program.java`.
- **Dependencies**:
  - Use **only the Java standard library** unless an exercise explicitly allows another dependency.
  - Do **not** use external frameworks or libraries (no Maven/Gradle dependencies) unless clearly specified.

---

Chapter III  
Technical Setup / How to Start

- **1. Install Java LTS**
  - Install the latest LTS JDK (for example Java 17 or Java 21, depending on school requirements).
  - Verify installation:
    - `java -version`
    - `javac -version`
- **2. Install GraalVM**
  - Install GraalVM for the same Java version.
  - Make sure the `java` from GraalVM is also available (check with `java -version` after switching to GraalVM).
- **3. Git**
  - Install `git` and configure your user name and email.
  - Initialize the repository (if not already done) and push to your remote regularly.
- **4. Directory layout**
  - At the root of the project: the Git repository with all 9 modules.
  - Inside each module: exercise directories (`ex00/`, `ex01/`, ...).
- **5. Compiling and running (JVM)**
  - Move into the exercise directory, for example:
    - `cd module00/ex00`
  - Compile:
    - `javac Program.java`
  - Run:
    - `java Program`
- **6. Running with GraalVM (if required)**
  - Use GraalVM’s `javac` / `java`, or the provided script in your environment, to compile and run the same `Program.java`.
  - The program must behave **identically** under both standard JVM and GraalVM.

---

Chapter IV  
Exercise Specification Template

Every exercise in this project must follow the same description template. When you create or read an exercise, expect a block like this:

- **Exercise XX : Title**
- **Exercise ID**: `XX` (for example `00`, `01`, `02`, ...)
- **Turn‑in directory**: `exXX/`
- **Files to turn in**: list of required files (for example `Program.java`)
- **Allowed functions / APIs**:
  - Which classes and methods from the standard library you may use (for example `System.out`, `java.lang.Math`, `String`, etc.)
- **Input / Output**:
  - How the program receives input (arguments, standard input) and how it prints output (usually `System.out`).
- **Types**:
  - Which Java types you are expected to use (primitive types, arrays, `String`, etc.).
- **Operators**:
  - Which operators are allowed (for example only standard primitive operators `+`, `-`, `*`, `/`, `%`, comparison operators, logical operators).
- **Expected behavior**:
  - A clear problem statement (what the program must compute).
  - One or more examples of input and output.

You must **respect this template** for all exercises and never add extra files or behaviors that are not listed.

---

Chapter V  
Example – Module 00 / Exercise 00 (Sum of Digits)

- **Exercise 00 : Sum of Digits**
- **Exercise 00**  
  Sum of Digits
- **Turn‑in directory**: `ex00/`
- **Files to turn in**: `Program.java`
- **Allowed functions**:
  - **Input/Output**: `System.out`
  - **Types**: primitive types and `String` if needed for input parsing
  - **Operators**: standard operations on primitive types (`+`, `-`, `*`, `/`, `%`, comparisons, logical operators)

- **Goal**:
  - Write a program that computes the sum of the digits of a non‑negative integer.

- **Behavior example (conceptual)**:
  - Input: `1234`  
    Output: `10`
  - Explanation: \(1 + 2 + 3 + 4 = 10\).

Implementation notes for this and similar exercises:

- Use only the allowed functions and operators.
- Do not use arrays, collections, or advanced APIs unless the exercise explicitly says so.
- Do not hard‑code answers; the program must work for any valid input that follows the specification.

---

Chapter VI  
Final Advice

- **Read the rules before starting each module.**
- **Re‑read the exercise and its examples before you submit.**
- **Keep commits small and meaningful.**
- **Test your program with multiple inputs, not only the given example.**

And again: may the Force be with you!