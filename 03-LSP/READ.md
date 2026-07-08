Before LSP
```mermaid
classDiagram

direction LR

class BankAccount {
    <<interface>>
    +deposit()
    +withdraw()
}

class SavingsAccount {
    +deposit()
    +withdraw()
}

class CurrentAccount {
    +deposit()
    +withdraw()
}

class FixedDepositeAccount {
    +deposit()
    +withdraw()
}

class BankService {
    +performTransaction(account : BankAccount)
}

BankAccount <|.. SavingsAccount : implements
BankAccount <|.. CurrentAccount : implements
BankAccount <|.. FixedDepositeAccount : implements

BankService --> BankAccount : uses

note for FixedDepositeAccount
withdraw() throws
UnsupportedOperationException
LSP Violation
end note
```

After LSP
```mermaid
classDiagram

direction LR

class NonWithdrawable {
    <<interface>>
    +deposit()
}

class Withdrawable {
    <<interface>>
    +withdraw()
}

class SavingsAccount {
    +deposit()
    +withdraw()
}

class CurrentAccount {
    +deposit()
    +withdraw()
}

class FixedDepositAccount {
    +deposit()
}

class BankService {
    +performTransaction(account : NonWithdrawable)
    +performTransaction(account : Withdrawable)
}

NonWithdrawable <|-- Withdrawable

Withdrawable <|.. SavingsAccount : implements
Withdrawable <|.. CurrentAccount : implements
NonWithdrawable <|.. FixedDepositAccount : implements

BankService --> NonWithdrawable : deposit()
BankService --> Withdrawable : withdraw()
```
