# Spring Boot Car Parking System (Event-Driven with Slot Checking)

Create a Spring Boot application for a **Car Parking System** using **Spring Event Publisher and Consumer**.  
The system should be simple but must include **parking slot checking logic**.

---

## Functional Flow

### Entry Process
1. **Check Slot Availability**
    - If a slot is available → proceed.
    - If full → deny entry and send a rejection notification.

2. **Car Entry Actions**
    - Record car plate number and entry time.
    - Assign an available slot.
    - Trigger an **EntryEvent** to notify consumers.
    - Consumer actions:
        - Open gate (simulation).
        - Send entry notification.

### Exit Process
1. **Car Exit Actions**
    - Record car plate number and exit time.
    - Calculate parking fee based on duration.
    - Process payment (mock).
    - Mark the slot as available.
    - Trigger an **ExitEvent** to notify consumers.
    - Consumer actions:
        - Open gate (simulation).
        - Send exit notification with billing details.

---

## Technical Requirements
- Use **Spring Boot** with **Spring Events** (`ApplicationEventPublisher` + `@EventListener`).
- Define **events** (`EntryEvent`, `ExitEvent`) and **listeners** (gate operations, notifications, billing).
- Maintain a simple in-memory data structure for **slots** (available/occupied).
- Use **separate services** for:
    - Parking slot management
    - Billing calculation
    - Notifications
    - Gate operations
- Log all major steps to the console for demonstration.

---

## Design Principles (Important)

Follow the **SOLID principles** to ensure clean, maintainable, and extensible code:

- **S**ingle Responsibility: each class handles only one responsibility (e.g., `SlotService`, `BillingService`).
- **O**pen/Closed: system should be open for extension but closed for modification (e.g., adding new notification channels).
- **L**iskov Substitution: design abstractions so implementations can be swapped without breaking logic.
- **I**nterface Segregation: use small, focused interfaces (e.g., `PaymentProcessor`, `Notifier`).
- **D**ependency Inversion: depend on abstractions, not concrete implementations (e.g., inject services via interfaces).

---

⚡ The focus should be on demonstrating **how Spring Events decouple the publisher (entry/exit actions) from consumers (notifications, billing, gate operations)** while keeping the architecture aligned with **SOLID principles**.
