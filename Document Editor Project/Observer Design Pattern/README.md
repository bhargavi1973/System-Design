# Phase 8 - Observer Design Pattern

## 📌 Objective

The goal of Phase 8 is to introduce the **Observer Design Pattern** so that multiple components can automatically react whenever the document changes.

This makes the Document Editor **event-driven and loosely coupled**.

---

## Problem Before Observer Pattern

Previously, the `Document` could be modified, but there was no clean way for other components to know when a change occurred.

For example, features such as:

* Auto-save
* Word count
* Activity logging
* Document preview

would need to be called manually.

This creates unnecessary coupling between the document and its dependent features.

---

## Solution

The `Document` now acts as the **Subject**, while interested components implement a common `DocumentObserver` interface.

Whenever the document changes, it automatically notifies all registered observers.

```text
                    Document
                   (Subject)
                       │
                notifyObservers()
                       │
          ┌────────────┼────────────┐
          ▼            ▼            ▼
     AutoSaver     WordCounter    Logger
     Observer       Observer     Observer
```

---

## Implementation

### Observer Interface

```java
interface DocumentObserver {
    void update(Document doc);
}
```

Every observer implements the `update()` method.

---

### Concrete Observers

Two observers were introduced:

#### AutoSaverObserver

Automatically reacts to document changes.

```text
Document Changed
       ↓
AutoSaverObserver
       ↓
Auto-save
```

#### WordCountObserver

Calculates the current number of words whenever the document changes.

```text
Document Changed
       ↓
WordCountObserver
       ↓
Calculate Word Count
```

---

## Subject - Document

The `Document` maintains a list of observers and provides methods to:

```java
addObserver()
removeObserver()
notifyObservers()
```

Observers are notified whenever operations such as:

```java
appendText()
deleteText()
insertText()
removeLastCharacters()
```

successfully modify the document.

---

## Registration

Observers are created and registered by the client rather than by the `Document`.

```java
DocumentObserver autoSaver =
        new AutoSaverObserver();

DocumentObserver wordCounter =
        new WordCountObserver();

doc.addObserver(autoSaver);
doc.addObserver(wordCounter);
```

This is important because the `Document` does not depend on concrete observer classes.

---

## Execution Flow

```text
Client
  │
  ▼
DocumentEditor
  │
  ▼
Command
  │
  ▼
Document
  │
  │ State Changed
  ▼
notifyObservers()
  │
  ├── AutoSaverObserver
  │
  └── WordCountObserver
```

The Observer Pattern now works together with the previously implemented **Command Pattern**.

---

## Command + Observer Integration

When a command modifies the document:

```text
AddTextCommand
      ↓
Document.appendText()
      ↓
Document State Changes
      ↓
notifyObservers()
      ↓
Observers React
```

The same mechanism works when an operation is undone:

```text
Undo Command
      ↓
Document State Changes
      ↓
notifyObservers()
      ↓
Observers React
```

This ensures that the application remains consistent even after undo operations.

---

## Design Pattern Roles

| Observer Pattern Role | Implementation                           |
| --------------------- | ---------------------------------------- |
| Subject               | `Document`                               |
| Observer              | `DocumentObserver`                       |
| Concrete Observers    | `AutoSaverObserver`, `WordCountObserver` |
| Client                | `main()`                                 |

---

## Benefits

* ✅ Loose coupling between `Document` and observers
* ✅ Multiple components can react to the same document change
* ✅ Observers can be added or removed dynamically
* ✅ New observers can be introduced without modifying `Document`
* ✅ Supports event-driven architecture
* ✅ Works naturally with the Command Pattern
* ✅ Improves extensibility and maintainability

---

## Example

When the user adds:

```text
hello world
```

the following happens:

```text
Document Updated
       │
       ├── AutoSaverObserver
       │       └── Auto-saving document
       │
       └── WordCountObserver
               └── Word count = 2
```

---

## Key Takeaway

The **Observer Pattern** allows the `Document` to notify multiple independent components whenever its state changes without directly depending on those components.

This keeps the document loosely coupled and makes it easy to add features such as auto-save, word counting, activity logging, previews, and real-time collaboration.

---

## Progress So Far

| Phase     | Concept                              |
| --------- | ------------------------------------ |
| ✅ Phase 1 | Identified SRP Violation             |
| ✅ Phase 2 | SRP + OCP                            |
| ✅ Phase 3 | Dependency Inversion Principle (DIP) |
| ✅ Phase 4 | Encapsulation & Information Hiding   |
| ✅ Phase 5 | Strategy Design Pattern              |
| ✅ Phase 6 | Factory Method Pattern               |
| ✅ Phase 7 | Command Pattern + Undo               |
| ✅ Phase 8 | Observer Design Pattern              |

---

## 🚀 Next Phase

### Phase 9 - Redo Support

The next phase will extend the **Command Pattern** by implementing **Redo** functionality.

A second command stack will be introduced to allow users to:

```text
Undo
 ↓
Redo
```

This will complete the basic Undo/Redo functionality of the Document Editor and make the Command Pattern more practical.
