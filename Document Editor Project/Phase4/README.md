# Phase 4 - Encapsulation & Information Hiding

## 📌 Objective

The goal of Phase 4 is to strengthen the object-oriented design of the Document Editor by applying **Encapsulation** and **Information Hiding**.

Instead of exposing the internal state of the `Document` object through public fields, the document now manages its own data and exposes only meaningful behaviors.

This ensures that the document always remains in a valid state while reducing unintended modifications from other classes.

---

# Problem in Phase 3

In the previous phase, the `Document` class exposed its internal data, allowing any class to modify it directly.

```java
doc.title = "Resume";
doc.content = "";
doc.author = "Unknown";
```

This created several problems:

* Any class could change the document's data.
* Invalid values could be assigned.
* Business rules could not be enforced.
* The `Document` acted only as a data container instead of a real object.

---

# Solution

The `Document` class was redesigned to become the **owner of its own data**.

The following improvements were made:

* Fields were changed from **public** to **private**.
* A constructor initializes the document with a valid state.
* Editing operations such as adding and deleting text were moved into the `Document` class.
* Input validation was added before modifying the document.
* Other classes now interact with the document through its public methods instead of directly accessing its fields.

---

# Changes Made

## 1️⃣ Private Fields

Before

```java
public String title;
public String content;
public String author;
```

After

```java
private String title;
private String content;
private String author;
```

The internal state of the document is now hidden from other classes.

---

## 2️⃣ Constructor Initialization

Instead of creating an empty document and setting values later, every document is created with its required information.

```java
Document document = new Document("Project Report", "Bhargavi");
```

This guarantees that every document starts in a valid state.

---

## 3️⃣ Behavior Moved into Document

Instead of allowing external classes to modify the document directly,

```java
doc.content += text;
```

the document now provides its own operations.

```java
document.appendText(text);
document.deleteText(startIndex, endIndex);
```

The object is now responsible for managing its own state.

---

## 4️⃣ Input Validation

Before updating the document, validation checks are performed.

Examples include:

* Prevent appending `null` or empty text.
* Ensure delete indices are within valid bounds.
* Reject invalid operations instead of corrupting the document.

---

## 5️⃣ Simplified DocumentEditor

`DocumentEditor` no longer manipulates the document's internal fields.

Instead, it simply delegates editing requests.

```java
editor.addText(document, text);
```

Internally,

```java
document.appendText(text);
```

The editor coordinates the action while the document performs it.

---

# Updated Class Responsibilities

## 📄 Document

* Stores document data.
* Controls modifications to its own state.
* Validates editing operations.
* Provides read-only access through getters.

---

## ✏️ DocumentEditor

* Delegates editing requests to the `Document`.
* Does not directly modify document fields.

---

## 🎯 DocumentManager

* Coordinates saving, exporting, printing, loading, and spell checking.
* Uses different services through abstractions.

---

# Architecture

```text
                Main
                  │
                  ▼
         DocumentManager
                  │
          DocumentEditor
                  │
                  ▼
              Document
          ┌──────────────────┐
          │ appendText()      │
          │ deleteText()      │
          │ getContent()      │
          │ getTitle()        │
          │ getAuthor()       │
          └──────────────────┘
```

The `Document` object now owns both its **data** and the **behavior** that modifies it.

---

# Benefits

* ✅ Improved encapsulation
* ✅ Better information hiding
* ✅ Prevents invalid state changes
* ✅ Cleaner object-oriented design
* ✅ Easier to maintain and extend
* ✅ Reduced coupling between classes
* ✅ Improved code readability

---

# Object-Oriented Design Principle Applied

## Encapsulation

> **Bundle data and the methods that operate on that data into a single unit while restricting direct access to the internal state.**

The `Document` class now exposes only meaningful operations instead of allowing unrestricted modification of its fields.

---

# Key Takeaway

Phase 4 transforms the `Document` from a simple data holder into a true domain object. Instead of exposing its internal state, the document manages and validates its own data through well-defined behaviors such as `appendText()` and `deleteText()`. This results in a safer, cleaner, and more maintainable object-oriented design.

---

# Progress So Far

| Phase     | Concept                                                             |
| --------- | ------------------------------------------------------------------- |
| ✅ Phase 1 | Identified SRP Violations                                           |
| ✅ Phase 2 | Single Responsibility Principle (SRP) + Open/Closed Principle (OCP) |
| ✅ Phase 3 | Dependency Inversion Principle (DIP) + Constructor Injection        |
| ✅ Phase 4 | Encapsulation + Information Hiding                                  |

---

## 🚀 Next Phase

**Phase 5 – Strategy Design Pattern**

The existing `Exporter` and `Storage` interfaces make the project ready for the **Strategy Pattern**, allowing different exporting and storage behaviors to be selected dynamically at runtime without modifying existing code.
