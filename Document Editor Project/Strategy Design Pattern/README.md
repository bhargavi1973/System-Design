# Phase 5 - Strategy Design Pattern

## 📌 Objective

The goal of Phase 5 is to implement the **Strategy Design Pattern** to make the document editor more flexible and extensible. Instead of hardcoding a single export or storage mechanism, the application can now switch between different algorithms **at runtime**.

## Implementation

The `Exporter` and `Storage` interfaces act as **Strategy** interfaces, while classes like `PDFExporter`, `HTMLExporter`, `FileStorage`, and `CloudStorage` are their concrete strategies.

`DocumentManager` acts as the **Context**, allowing the active strategy to be changed dynamically using setter methods.

```text
Client
   │
   ▼
DocumentManager (Context)
   │
   ├── Exporter
   │      ├── PDFExporter
   │      └── HTMLExporter
   │
   └── Storage
          ├── FileStorage
          └── CloudStorage
```

## Benefits

* ✅ Change export formats at runtime.
* ✅ Switch storage mechanisms without modifying existing code.
* ✅ Eliminates complex `if-else` or `switch` statements.
* ✅ Supports the Open/Closed Principle by allowing new strategies to be added without changing existing classes.
* ✅ Improves flexibility, maintainability, and scalability.

## Key Takeaway

The **Strategy Pattern** encapsulates different algorithms behind a common interface, allowing `DocumentManager` to change its exporting and storage behavior dynamically while keeping the client code simple and loosely coupled.
