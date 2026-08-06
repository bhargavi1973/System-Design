# Phase 7 - Command Pattern (Undo / Redo)

## 📌 Objective

The goal of Phase 7 is to implement the **Command Design Pattern** to encapsulate document editing operations as objects. Instead of directly invoking editing methods, each operation is represented as a command that can be executed, stored, and undone.

This design lays the foundation for features such as **Undo**, **Redo**, command history, macros, and action logging used in modern document editors.

---

# Problem Before Command Pattern

In the previous phases, editing operations were executed directly.

```java id="vz9itc"
document.appendText("Hello");
document.deleteText(0, 5);
```

Once executed, these operations were lost.

There was:

* ❌ No command history
* ❌ No undo support
* ❌ No way to replay operations
* ❌ Tight coupling between the client and editing logic

---

# Solution

The **Command Pattern** encapsulates every editing request inside a command object.

Instead of calling editing methods directly, the client creates command objects and asks the editor to execute them.

```text id="mjlwmu"
Client
   │
   ▼
DocumentEditor
 (Invoker)
   │
   ▼
Command
   │
   ▼
Document
(Receiver)
```

Each command knows:

* What action to perform
* Which document to operate on
* How to undo that action

---

# Command Pattern Participants

## Client

Creates command objects and submits them to the editor.

---

## Invoker – DocumentEditor

Responsible for:

* Executing commands
* Maintaining command history
* Performing undo operations

```text id="htqdbf"
executeCommand()

undo()
```

---

## Command

Defines a common interface for all editing operations.

```java id="cn29zj"
interface Command {

    void execute();

    void undo();
}
```

---

## Concrete Commands

Implemented commands include:

* `AddTextCommand`
* `DeleteTextCommand`

Each command encapsulates the data required to execute and undo its operation.

---

## Receiver – Document

The receiver performs the actual work.

Examples:

* `appendText()`
* `deleteText()`
* `insertText()`
* `removeLastCharacters()`

The command simply tells the receiver **what** to do.

---

# Architecture

```text id="0hbhbp"
                 Client
                    │
                    ▼
            DocumentEditor
              (Invoker)
                    │
          executeCommand()
                    │
                    ▼
                Command
           ┌────────┴────────┐
           │                 │
   AddTextCommand   DeleteTextCommand
           │                 │
           ▼                 ▼
              Document
             (Receiver)
```

---

# Command Execution Flow

```text id="g09t5u"
Create Command
       │
       ▼
DocumentEditor.executeCommand()
       │
       ▼
Command.execute()
       │
       ▼
Document
```

The executed command is stored inside a history stack.

---

# Undo Flow

```text id="51wwa8"
Undo
 │
 ▼
Pop Last Command
 │
 ▼
command.undo()
 │
 ▼
Restore Previous State
```

A **Stack** is used because undo follows the **Last In, First Out (LIFO)** principle.

---

# Why a Stack?

Consider the following operations:

```text id="1oijly"
Add "Hello"

↓

Delete "World"

↓

Add "!"
```

Undo should occur in reverse order:

```text id="mlyrwl"
Undo "!"

↓

Undo Delete

↓

Undo Add
```

This behavior naturally matches a stack.

---

# Benefits

* ✅ Supports Undo functionality
* ✅ Encapsulates each operation into an independent object
* ✅ Decouples the invoker from editing logic
* ✅ Makes commands reusable
* ✅ Enables command history
* ✅ Simplifies adding future commands

---

# Design Pattern Roles

| Command Pattern Role | Implementation                        |
| -------------------- | ------------------------------------- |
| Client               | `main()`                              |
| Invoker              | `DocumentEditor`                      |
| Command              | `Command`                             |
| Concrete Commands    | `AddTextCommand`, `DeleteTextCommand` |
| Receiver             | `Document`                            |

---

# Key Takeaway

The **Command Pattern** transforms editing operations into objects that encapsulate both the action and the information required to reverse it. `DocumentEditor` acts as the **Invoker**, maintaining a history of executed commands, while `Document` serves as the **Receiver**, performing the actual modifications. This design enables powerful features like **Undo**, keeps the editor loosely coupled from editing logic, and makes the system easier to extend with new commands.

---

# Progress So Far

| Phase     | Concept                              |
| --------- | ------------------------------------ |
| ✅ Phase 1 | Identified SRP Violation             |
| ✅ Phase 2 | SRP + OCP                            |
| ✅ Phase 3 | Dependency Inversion Principle (DIP) |
| ✅ Phase 4 | Encapsulation & Information Hiding   |
| ✅ Phase 5 | Strategy Design Pattern              |
| ✅ Phase 6 | Factory Method Pattern               |
| ✅ Phase 7 | Command Pattern (Undo)               |

---

# 🚀 Next Phase

**Phase 8 – Observer Pattern**

The next phase will introduce the **Observer Pattern**, allowing multiple components to react automatically whenever a document changes.

Possible observers include:

* Auto Save
* Word Count
* Document Preview
* Activity Logger
* Real-Time Collaboration Notifications

This will make the document editor event-driven and closer to the architecture of modern editors like Google Docs and Microsoft Word.
