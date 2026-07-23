# Phase 3 - Dependency Inversion Principle (DIP)

## 📌 Objective

The goal of Phase 3 is to make the document editor **modular, flexible, and loosely coupled** by applying the **Dependency Inversion Principle (DIP)**.

Instead of allowing high-level classes to depend on concrete implementations such as `FileStorage` or `PDFExporter`, they now depend on **abstractions (interfaces)**.

---

# Problem in Phase 2

In Phase 2, the client (`main()`) was responsible for creating and coordinating every object.

```java
Storage storage = new FileStorage();
Exporter exporter = new PDFExporter();
Printer printer = new Printer();
SpellChecker spellChecker = new SpellChecker();
```

Whenever a new implementation (e.g., `CloudStorage` or `HTMLExporter`) was introduced, the client code had to be updated.

This tightly coupled the application to specific implementations.

---

# Solution

To reduce coupling, a new class called **`DocumentManager`** was introduced.

`DocumentManager` acts as a **coordinator** that delegates work to the appropriate service instead of implementing the functionality itself.

```
               DocumentManager
            /        |         \
      Storage    Exporter    Printer
                               |
                         SpellChecker
```

---

# Responsibilities of Each Class

## 📄 Document

* Stores document information.
* Contains document data such as title, author, and content.

## ✏️ DocumentEditor

* Creates new documents.
* Performs editing operations.

## 📂 Storage

* Saves documents.
* Loads documents.

## 📤 Exporter

* Exports documents into different file formats.

## 🖨 Printer

* Prints the document.

## ✅ SpellChecker

* Performs spell checking.

## 🎯 DocumentManager

* Coordinates all the above services.
* Delegates requests to the appropriate component.

---

# Applying Dependency Injection

Instead of creating dependencies inside `DocumentManager`, they are provided externally through the constructor.

### ❌ Before

```java
Storage storage = new FileStorage();
```

### ✅ After

```java
Storage storage = new FileStorage();
Exporter exporter = new PDFExporter();

DocumentManager manager =
        new DocumentManager(storage, exporter, printer, spellChecker);
```

This technique is called **Constructor Injection**.

---

# Dependency Flow

```
Main
 │
 ▼
DocumentManager
 │
 ├── Storage
 ├── Exporter
 ├── Printer
 └── SpellChecker
```

The client chooses which implementations to use, while `DocumentManager` works only with the interfaces.

---

# Dependency Inversion Principle (DIP)

> **High-level modules should not depend on low-level modules. Both should depend on abstractions.**

In this project:

* **High-level module:** `DocumentManager`
* **Low-level modules:** `FileStorage`, `CloudStorage`, `PDFExporter`, `HTMLExporter`
* **Abstractions:** `Storage`, `Exporter`

By depending on interfaces rather than concrete classes, the design becomes more flexible and easier to extend.

---

# Benefits

* ✅ Reduced coupling
* ✅ Improved maintainability
* ✅ Better scalability
* ✅ Easier to test using mock implementations
* ✅ New storage or export types can be added with minimal changes
* ✅ Cleaner and more modular architecture

---

# Key Takeaway

Phase 3 introduces a **DocumentManager** to coordinate different services and applies the **Dependency Inversion Principle** by making the system depend on abstractions (`Storage` and `Exporter`) rather than concrete implementations. Dependencies are supplied using **Constructor Injection**, resulting in a flexible, loosely coupled, and maintainable design.

---

## 📚 SOLID Principles Covered So Far

* ✅ Phase 1 – Identified SRP Violations
* ✅ Phase 2 – Applied **Single Responsibility Principle (SRP)** and **Open/Closed Principle (OCP)**
* ✅ Phase 3 – Applied **Dependency Inversion Principle (DIP)** using **Dependency Injection**

**Next Phase ➜ Encapsulation & Information Hiding**
