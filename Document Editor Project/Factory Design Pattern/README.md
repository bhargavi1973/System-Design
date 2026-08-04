# Phase 6 - Factory Method Pattern

## 📌 Objective

The goal of Phase 6 is to implement the **Factory Method Pattern** to centralize object creation and reduce the client's dependency on concrete classes.

Instead of directly creating objects such as `PDFExporter` or `FileStorage`, the client now delegates object creation to dedicated factory classes.

---

# Problem Before Factory Pattern

In the previous phase, the client was responsible for creating concrete implementations.

```java
Storage storage = new FileStorage();
Exporter exporter = new PDFExporter();
```

This tightly coupled the client with specific classes.

Whenever a new exporter or storage implementation was added, the client code had to be modified.

---

# Solution

The **Factory Method Pattern** moves object creation into dedicated factory classes.

Two factory classes were introduced:

* `ExporterFactory`
* `StorageFactory`

These factories create the appropriate implementation based on the requested type.

```text
Client
   │
   ▼
ExporterFactory
   │
   ├── PDFExporter
   ├── HTMLExporter
   └── MarkdownExporter

StorageFactory
   │
   ├── FileStorage
   └── CloudStorage
```

The client now requests an object instead of creating it directly.

---

# Implementation

## Exporter Factory

```java
Exporter exporter =
    ExporterFactory.createExporter(ExportType.PDF);
```

## Storage Factory

```java
Storage storage =
    StorageFactory.createStorage(StorageType.FILE);
```

Both factories use **Enums** instead of raw strings to provide type safety and avoid invalid input.

---

# Factory Components

## ExporterFactory

Responsible for creating exporter implementations.

Supports:

* PDF Exporter
* HTML Exporter
* Markdown Exporter

---

## StorageFactory

Responsible for creating storage implementations.

Supports:

* File Storage
* Cloud Storage

---

# Why Enums?

Instead of passing strings like:

```java
createExporter("PDF");
```

Enums are used:

```java
createExporter(ExportType.PDF);
```

### Benefits

* Compile-time type safety
* No spelling mistakes
* Better IDE auto-completion
* Easier maintenance and refactoring

---

# Architecture

```text
                    Client
                       │
          ┌────────────┴────────────┐
          │                         │
  ExporterFactory           StorageFactory
          │                         │
          ▼                         ▼
   PDFExporter               FileStorage
   HTMLExporter              CloudStorage
   MarkdownExporter
          │
          ▼
    DocumentManager
          │
          ▼
       Document
```

---

# Benefits

* ✅ Centralized object creation
* ✅ Reduced coupling between client and concrete classes
* ✅ Easier to add new exporters or storage implementations
* ✅ Cleaner client code
* ✅ Improved maintainability
* ✅ Follows the **Open/Closed Principle (OCP)**

---

# Design Pattern Roles

| Factory Pattern Role | Implementation                                                                   |
| -------------------- | -------------------------------------------------------------------------------- |
| Factory              | `ExporterFactory`, `StorageFactory`                                              |
| Product              | `Exporter`, `Storage`                                                            |
| Concrete Products    | `PDFExporter`, `HTMLExporter`, `MarkdownExporter`, `FileStorage`, `CloudStorage` |
| Client               | `main()`                                                                         |

---

# Key Takeaway

The **Factory Method Pattern** encapsulates object creation inside dedicated factory classes. Instead of directly instantiating concrete implementations, the client requests objects from the factory. This reduces coupling, improves maintainability, and makes it easier to introduce new exporters or storage mechanisms without changing the client code.

---

# Progress So Far

| Phase     | Concept                            |
| --------- | ---------------------------------- |
| ✅ Phase 1 | Identified SRP Violation           |
| ✅ Phase 2 | SRP + OCP                          |
| ✅ Phase 3 | DIP + Dependency Injection         |
| ✅ Phase 4 | Encapsulation & Information Hiding |
| ✅ Phase 5 | Strategy Design Pattern            |
| ✅ Phase 6 | Factory Method Pattern             |

---

## 🚀 Next Phase

**Phase 7 – Command Pattern (Undo/Redo)**

The next phase will implement the **Command Pattern** by encapsulating editing operations (such as adding and deleting text) into command objects. This will enable features like **Undo**, **Redo**, and command history while further improving the flexibility and maintainability of the document editor.
