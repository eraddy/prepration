# Digital University Admissions & Application Management Platform
### Solution Design & Architecture Kata

> **Document type:** Architecture & Design Specification
> **Scope:** Functional analysis, domain model, service decomposition, API surface, and full UML/diagram set derived from the problem statement.
> **Audience:** Architects, engineering leads, product owners, reviewers.

---

## Table of Contents

1. [Solution Overview](#1-solution-overview)
2. [Functional Requirements](#2-functional-requirements)
3. [Non-Functional Requirements](#3-non-functional-requirements)
4. [Actors](#4-actors)
5. [Microservices — Identification & Catalogue](#5-microservices--identification--catalogue)
6. [Use-Case Diagram](#6-use-case-diagram)
7. [Class Diagram](#7-class-diagram)
8. [Object Diagram](#8-object-diagram)
9. [ER Diagram](#9-er-diagram)
10. [Sequence Diagram](#10-sequence-diagram)
11. [State Diagram](#11-state-diagram)
12. [Activity Diagram](#12-activity-diagram)
13. [Flow Chart](#13-flow-chart)
14. [API Specification](#14-api-specification)
15. [Guiding Principles — SOLID, KISS, YAGNI](#15-guiding-principles--solid-kiss-yagni)

---

## 1. Solution Overview

The platform is a **centralized, multi-tenant admissions hub** that connects prospective students with multiple higher-education institutions. It replaces the fragmented, institution-by-institution portal landscape with a single application surface, transparent real-time tracking, collaborative institution-side review, AI-assisted program matching, and integrated payment, verification, communication, and compliance.

**Core value drivers**

| Pain point (today) | Platform response |
|---|---|
| Students re-enter data per portal | One profile → many applications ("apply once, apply to many") |
| Opaque status | Real-time, event-driven status tracking |
| Manual review at peak load | Collaborative review workflow + elastic, independently scalable services |
| Disparate institution systems | Integration APIs (SIS, CRM, payment, credential services) |
| Reactive, fragmented process | Proactive notifications, analytics, data-driven decisions |

**Architectural stance:** event-driven microservices behind an API gateway, organized by **Domain-Driven Design (DDD) bounded contexts**, with each service owning its data (database-per-service) and communicating via synchronous REST for queries/commands and asynchronous events for state propagation.

---

## 2. Functional Requirements

Requirements are grouped into capability areas and given stable IDs (`FR-<area>.<n>`) so they can be traced to use cases, services, and APIs throughout the document.

### FR-1 — User Management & Access Control
- **FR-1.1** Multi-tiered self-registration for Students, University staff (Admissions Officers/Reviewers), and Counselors.
- **FR-1.2** Role-Based Access Control (RBAC) with least-privilege scopes per role and per tenant (institution).
- **FR-1.3** Authentication with MFA; SSO/SAML/OIDC federation for institutional users.
- **FR-1.4** Profile lifecycle: create, edit, verify (email/phone), deactivate, GDPR delete/export.
- **FR-1.5** Delegated access (e.g., a counselor acting on behalf of a student) with explicit consent and audit.

### FR-2 — Student Application Management
- **FR-2.1** Build a reusable student profile (academics, test scores, activities, essays).
- **FR-2.2** Create applications and target **multiple institutions/programs** from one workspace.
- **FR-2.3** Save drafts; validate completeness before submission.
- **FR-2.4** Attach documents per application/program requirement.
- **FR-2.5** Submit, withdraw, and resubmit (where allowed) applications.
- **FR-2.6** Real-time status tracking across all target institutions.
- **FR-2.7** Receive decisions; accept/decline offers; respond to waitlist.

### FR-3 — Institution Application Processing
- **FR-3.1** Configure institution profile, programs, intakes, deadlines, eligibility rules, and required documents.
- **FR-3.2** Receive and queue incoming applications per program.
- **FR-3.3** Assign applications to reviewers; support reviewer pools and load balancing.
- **FR-3.4** Collaborative review: scoring rubrics, comments, multi-reviewer aggregation.
- **FR-3.5** Shortlist, schedule interviews (optional), and record decisions.
- **FR-3.6** Issue offers (conditional/unconditional), rejections, and waitlist positions.
- **FR-3.7** Manage capacity and waitlist promotion.

> Note: institution-side configuration and decision-making (FR-3.1, FR-3.5, FR-3.6, FR-3.7) are performed by the **Admissions Officer / Reviewer** role.

### FR-4 — Intelligent Matching & Recommendations
- **FR-4.1** Recommend suitable programs from a student profile.
- **FR-4.2** Eligibility pre-checks against program rules.
- **FR-4.3** Admit-likelihood signals using historical outcomes (reach/match/safety banding).
- **FR-4.4** Explainable recommendations (why this program was suggested).

### FR-5 — Communication Hub
- **FR-5.1** In-app threaded messaging between students, institutions, counselors.
- **FR-5.2** Automated notifications for milestones, deadlines, and status changes.
- **FR-5.3** Multi-channel delivery (in-app, email, SMS, push) with user preferences.
- **FR-5.4** Templated, localized, and schedulable messages.

### FR-6 — Document Verification & Validation
- **FR-6.1** Secure upload with format, size, and malware (AV) checks.
- **FR-6.2** Automated structural validation (e.g., transcript fields present).
- **FR-6.3** Integration with external credential/identity verification services.
- **FR-6.4** Verification status lifecycle and re-request on failure.

### FR-7 — Analytics & Reporting Dashboard
- **FR-7.1** Student dashboard: application progress, deadlines, outcomes.
- **FR-7.2** Institution dashboard: funnel (received → reviewed → offered → accepted), reviewer throughput, demographics.
- **FR-7.3** Platform admin dashboard: usage, SLA, revenue, tenant health.
- **FR-7.4** Custom report builder and export (CSV/PDF).

### FR-8 — Integration Capabilities
- **FR-8.1** REST/webhook APIs for institutional SIS and CRM.
- **FR-8.2** Payment gateway integration.
- **FR-8.3** Credential verification service integration.
- **FR-8.4** Inbound/outbound webhooks for status events.

### FR-9 — Payment & Financial Management
- **FR-9.1** Configurable application fees per institution/program.
- **FR-9.2** Secure, PCI-compliant payment processing (tokenized).
- **FR-9.3** Multi-currency support with FX display.
- **FR-9.4** Refunds, fee waivers, receipts, and invoices.

### FR-10 — Compliance & Audit
- **FR-10.1** Immutable audit trail of all material actions.
- **FR-10.2** Data privacy compliance (GDPR/FERPA): consent, retention, right-to-erasure.
- **FR-10.3** Regulatory and statutory reporting.
- **FR-10.4** Configurable data residency per tenant.

### FR-11 — Multi-Channel Access
- **FR-11.1** Responsive web and mobile-optimized experiences.
- **FR-11.2** WCAG 2.1 AA accessibility.
- **FR-11.3** Internationalization/localization (i18n/l10n).

---

## 3. Non-Functional Requirements
*(Optional per the kata — included because they directly shape the architecture. Compliance/security/accessibility/residency requirements below are scoped to an **India** deployment.)*

| # | Category | Requirement (India-specific) |
|---|---|---|
| NFR-1 | **Performance** | P95 API latency < 500 ms for reads; < 1.5 s for writes under nominal load. Degrade gracefully on low-bandwidth (2G/3G) connectivity common in tier-2/3 regions. |
| NFR-2 | **Scalability** | Horizontal autoscaling; absorb 10× application volume during national admission windows (e.g., CUET / board-result season) without degradation. |
| NFR-3 | **Availability** | 99.9% monthly uptime; no single point of failure; graceful degradation of non-critical services. |
| NFR-4 | **Security** | Encryption in transit (TLS 1.2+) and at rest; OWASP Top-10 hardening; secrets management. Payments: **PCI-DSS** plus **RBI card-on-file tokenisation** — no raw card data stored by the platform (tokens only), with mandatory two-factor / Additional Factor of Authentication (AFA). |
| NFR-5 | **Privacy & Compliance** | Compliance with the **Digital Personal Data Protection (DPDP) Act, 2023** and **DPDP Rules, 2025**: consent-first processing, data-principal rights (access / correct / erase), **verifiable parental consent for minors**, and breach notification to the **Data Protection Board of India**. Phased readiness toward full enforcement on **13 May 2027**. (FERPA/GDPR do not apply in India; sector norms — UGC/AICTE/NEP 2020 — inform statutory reporting.) |
| NFR-6 | **Reliability/Consistency** | Eventual consistency across services via events; exactly-once side-effects via idempotency keys + outbox pattern. |
| NFR-7 | **Observability** | Centralized logs, metrics, distributed tracing; correlation IDs end-to-end. Maintain **personal-data flow lineage** to evidence DPDP accountability and support audit. |
| NFR-8 | **Maintainability** | Independently deployable services; contract-tested APIs; SOLID/KISS/YAGNI adherence. |
| NFR-9 | **Usability/Accessibility** | Conformance to the **RPwD Act, 2016** and **IS 17802 (BIS)**, with **WCAG 2.1 AA** as the baseline (and **GIGW 3.0** where government-linked). Provide ISL video, captions, and alt-text; multilingual UI across major Indian languages. |
| NFR-10 | **Interoperability** | Open REST + webhook contracts; standards-based auth (OIDC/SAML). Integrate with India digital public infrastructure: **DigiLocker / National Academic Depository** for credential verification, **Aadhaar e-KYC** (consent-based) for identity, **UPI** for payments, and **APAAR / Academic Bank of Credits** where applicable. |
| NFR-11 | **Data Residency & Localization** | Store payment/transaction data **within India** per RBI localization; apply DPDP cross-border transfer controls to personal data; per-tenant residency configuration. |

---

## 4. Actors

Actors are split into **primary** (initiate value), **supporting/system** (external systems the platform integrates with), and **offstage** (have an interest but don't directly interact).

### Primary (human) actors

| Actor | Description | Key goals |
|---|---|---|
| **Prospective Student / Applicant** | Person seeking admission. | Build profile, get recommendations, apply to many institutions, track status, pay, accept offers. |
| **Admissions Officer / Reviewer** | Institution staff who configure the institution's offering and evaluate applications. | Configure programs, intakes, deadlines, eligibility rules; assign reviewers and manage capacity; review, score, comment; record decisions and issue offers/rejections/waitlist positions; view institution analytics. |
| **Counselor / Advisor** | Guides students (school or independent). | Assist/track student applications (with consent), advise. |
| **Platform Administrator** | Operates the platform itself. | Onboard tenants, monitor health, manage compliance, configuration. |

### Supporting / external system actors

| System actor | Role |
|---|---|
| **Payment Gateway** | Processes fees, refunds, multi-currency settlement. |
| **Credential Verification Service** | Validates transcripts/degrees/identity. |
| **Notification Providers** | Email / SMS / Push delivery. |
| **Institutional SIS / CRM** | Receives admitted-student data; syncs leads. |
| **Identity Provider (IdP)** | SSO/SAML/OIDC for institutional users. |
| **Object Storage / AV Scanner** | Stores documents; scans for malware. |
| **AI / Matching Engine** | Generates program recommendations (internal service backed by ML). |

### Offstage actors
Regulators/Accreditation bodies (consume compliance reports), University departments/faculty (downstream consumers of decisions).

---

## 5. Microservices — Identification & Catalogue

### 5.1 How services were identified

The decomposition is **Domain-Driven Design first**, not technology-first. The method:

1. **Event Storming the admissions lifecycle** to surface domain events (`ApplicationSubmitted`, `DocumentVerified`, `DecisionMade`, `PaymentCaptured`, …).
2. **Group events/commands into bounded contexts** where the language and rules are internally consistent (e.g., "Review" means something specific inside the institution context, distinct from "Application" in the student context).
3. **Apply the Single Responsibility Principle at service granularity** — one service = one business capability with one reason to change.
4. **Data ownership / autonomy** — each service owns its schema (database-per-service); no shared tables. This avoids the distributed-monolith trap.
5. **Independent scalability & failure isolation** — capabilities with very different load/availability profiles are separated. Document processing and notifications spike on deadline days; payments need PCI isolation; analytics is read-heavy and can lag. These pressures justify separate services even where the domain might tolerate merging.
6. **Compliance isolation** — audit and PII-sensitive concerns are isolated so they can be hardened and governed independently.
7. **YAGNI guardrail** — we stop at the granularity the requirements justify; we do *not* split into nano-services speculatively (see §15).

### 5.2 Service catalogue

| # | Service | Bounded context | Core responsibility | Owns (key entities) | Why it is its own service |
|---|---|---|---|---|---|
| 1 | **Identity & Access Service (IAM)** | Identity & Access | AuthN, MFA, SSO federation, RBAC, tenancy | User, Role, Permission, Session | Cross-cutting security concern; must be hardened & independently audited. |
| 2 | **Student Profile Service** | Applicant | Reusable student profile, academics, achievements | StudentProfile, Education, TestScore | Distinct "apply-once" data, separate from any single application. |
| 3 | **Institution & Program Catalog Service** | Institution Setup | Institutions, programs, intakes, eligibility rules, requirements | Institution, Program, Intake, Requirement | Reference/config data with read-heavy access; changes on a different cadence. |
| 4 | **Application Service** | Application (core) | Application aggregate, drafts, submission, status orchestration | Application, ApplicationItem, ApplicationStatus | The transactional heart; high write volume; orchestrates other contexts. |
| 5 | **Document Service** | Documents | Upload, storage, format/AV validation | Document, DocumentMetadata | Heavy I/O + storage; scales independently; spikes on deadlines. |
| 6 | **Verification Service** | Verification | Credential/identity verification via external providers | VerificationRequest, VerificationResult | Wraps slow external calls; isolates third-party failure. |
| 7 | **Review & Decision Service** | Admissions Review | Reviewer assignment, scoring, decisions, offers, waitlist | Review, Score, Decision, Offer, ReviewerAssignment | Institution-side workflow with its own rules and collaboration model. |
| 8 | **Matching & Recommendation Service** | Recommendations | AI-driven program suggestions & eligibility scoring | RecommendationSet, MatchScore | ML/compute profile differs entirely from CRUD services. |
| 9 | **Communication / Notification Service** | Communication | Messaging, notifications, multi-channel delivery | Message, Notification, Template, Preference | Bursty fan-out workload; decoupled via events. |
| 10 | **Payment Service** | Payments | Fees, multi-currency, refunds, waivers, receipts | Payment, Invoice, Refund, FeeWaiver | PCI scope must be isolated; integrates a regulated external gateway. |
| 11 | **Analytics & Reporting Service** | Analytics | KPIs, dashboards, custom reports, exports | ReportDefinition, Metric, Dashboard | Read-optimized (CQRS read side); must not burden transactional stores. |
| 12 | **Integration / Gateway Service** | Integration | Outbound/inbound APIs & webhooks to SIS/CRM/external | IntegrationConfig, WebhookSubscription | Anti-corruption layer shielding the core from external schemas. |
| 13 | **Audit & Compliance Service** | Compliance | Immutable audit trail, consent ledger, retention, regulatory reports | AuditEvent, Consent, RetentionPolicy | Must be append-only, tamper-evident, separately governed. |

**Cross-cutting platform components (not domain services):** API Gateway, Service Discovery/Config, Message Broker (event bus), centralized Observability stack. These are infrastructure, deliberately kept out of the domain service list.

### 5.3 Communication style
- **Synchronous (REST/gRPC)** for client-facing queries and commands via the API Gateway.
- **Asynchronous (events over the broker)** for state propagation: e.g., `ApplicationSubmitted` → Document, Payment, Notification, Analytics, Audit react independently.
- **Outbox + idempotency** for reliable, exactly-once side-effects (NFR-6).

---

## 6. Use-Case Diagram

Mermaid has no native UML use-case notation, so actors are rendered as nodes and use cases as rounded nodes grouped inside the system boundary.

```mermaid
flowchart LR
  %% Actors
  STU([Student])
  REV([Admissions Officer / Reviewer])
  CNS([Counselor])
  PADM([Platform Admin])
  PAY[[Payment Gateway]]
  VER[[Credential Verifier]]

  subgraph SYS[Admissions Platform]
    UC1(Register / Authenticate)
    UC2(Manage Profile)
    UC3(Get Program Recommendations)
    UC4(Create & Submit Application)
    UC5(Upload Documents)
    UC6(Track Application Status)
    UC7(Pay Application Fee)
    UC8(Configure Programs & Rules)
    UC9(Review & Score Application)
    UC10(Make Decision / Issue Offer)
    UC11(Accept / Decline Offer)
    UC12(View Analytics Dashboard)
    UC13(Manage Tenants & Compliance)
    UC14(Verify Credentials)
  end

  STU --- UC1 & UC2 & UC3 & UC4 & UC5 & UC6 & UC7 & UC11
  CNS --- UC2 & UC6
  REV --- UC1 & UC8 & UC9 & UC10 & UC12
  PADM --- UC13 & UC12
  UC7 -.-> PAY
  UC14 -.-> VER
  UC5 -.-> UC14
```

---

## 7. Class Diagram

Domain model (core aggregates and relationships). Methods shown are representative, not exhaustive.

```mermaid
classDiagram
  class User {
    <<abstract>>
    +UUID id
    +String email
    +String name
    +Role role
    +login()
    +updateProfile()
  }
  class Student {
    +StudentProfile profile
    +createApplication()
    +submitApplication()
    +acceptOffer()
  }
  class Reviewer {
    +UUID institutionId
    +configureProgram()
    +assignReviewer()
    +scoreApplication()
    +addComment()
    +recordDecision()
  }
  class Counselor {
    +assistStudent()
  }

  class StudentProfile {
    +academics
    +testScores
    +activities
  }
  class Institution {
    +UUID id
    +String name
    +addProgram()
  }
  class Program {
    +UUID id
    +String title
    +EligibilityRule[] rules
    +Requirement[] requirements
  }
  class Application {
    +UUID id
    +ApplicationStatus status
    +DateTime submittedAt
    +addItem()
    +submit()
    +withdraw()
  }
  class ApplicationItem {
    +UUID id
    +ItemStatus status
    +Program targetProgram
  }
  class Document {
    +UUID id
    +DocType type
    +VerificationStatus vStatus
  }
  class Review {
    +UUID id
    +int score
    +String comments
  }
  class Decision {
    +DecisionType type
    +DateTime decidedAt
  }
  class Offer {
    +OfferType type
    +Date responseDeadline
  }
  class Payment {
    +UUID id
    +Money amount
    +PaymentStatus status
  }
  class Recommendation {
    +Program program
    +float matchScore
    +String rationale
  }
  class Notification {
    +Channel channel
    +String payload
  }

  User <|-- Student
  User <|-- Reviewer
  User <|-- Counselor

  Student "1" --> "1" StudentProfile
  Student "1" --> "*" Application
  Application "1" --> "*" ApplicationItem
  ApplicationItem "*" --> "1" Program
  Institution "1" --> "*" Program
  Application "1" --> "*" Document
  ApplicationItem "1" --> "*" Review
  Reviewer "1" --> "*" Review
  ApplicationItem "1" --> "0..1" Decision
  Decision "1" --> "0..1" Offer
  Application "1" --> "*" Payment
  Student "1" --> "*" Recommendation
  Recommendation "*" --> "1" Program
  User "1" --> "*" Notification
```

---

## 8. Object Diagram

A runtime snapshot: one student (*Jane*) with a single application targeting two programs, mid-process.

```mermaid
classDiagram
  class jane["jane : Student"]
  class janeProfile["janeProfile : StudentProfile"]
  class app1001["app1001 : Application (status=UNDER_REVIEW)"]
  class itemMIT["itemMIT : ApplicationItem (status=UNDER_REVIEW)"]
  class itemStan["itemStan : ApplicationItem (status=OFFER)"]
  class progCS["progCS : Program (MIT, MSc CS)"]
  class progDS["progDS : Program (Stanford, MSc DS)"]
  class transcript["transcript : Document (VERIFIED)"]
  class sop["sop : Document (PENDING)"]
  class payMIT["payMIT : Payment (CAPTURED, USD 75)"]
  class offerStan["offerStan : Offer (UNCONDITIONAL)"]

  jane --> janeProfile
  jane --> app1001
  app1001 --> itemMIT
  app1001 --> itemStan
  itemMIT --> progCS
  itemStan --> progDS
  app1001 --> transcript
  app1001 --> sop
  app1001 --> payMIT
  itemStan --> offerStan
```

---

## 9. ER Diagram

Logical data model. In the deployed system each bounded context owns its own schema (database-per-service); this ER view is the consolidated logical model for clarity.

```mermaid
erDiagram
  USER ||--o{ APPLICATION : creates
  USER ||--|| STUDENT_PROFILE : has
  USER ||--o{ NOTIFICATION : receives
  USER }o--o{ ROLE : assigned

  INSTITUTION ||--o{ PROGRAM : offers
  PROGRAM ||--o{ REQUIREMENT : defines
  PROGRAM ||--o{ APPLICATION_ITEM : targeted_by

  APPLICATION ||--o{ APPLICATION_ITEM : contains
  APPLICATION ||--o{ DOCUMENT : includes
  APPLICATION ||--o{ PAYMENT : has

  APPLICATION_ITEM ||--o{ REVIEW : receives
  APPLICATION_ITEM ||--o| DECISION : results_in
  DECISION ||--o| OFFER : may_issue
  USER ||--o{ REVIEW : authors

  USER {
    uuid id PK
    string email
    string name
    string role
    datetime created_at
  }
  STUDENT_PROFILE {
    uuid id PK
    uuid user_id FK
    json academics
    json test_scores
  }
  INSTITUTION {
    uuid id PK
    string name
    string country
  }
  PROGRAM {
    uuid id PK
    uuid institution_id FK
    string title
    date deadline
    money fee
  }
  REQUIREMENT {
    uuid id PK
    uuid program_id FK
    string type
    bool mandatory
  }
  APPLICATION {
    uuid id PK
    uuid student_id FK
    string status
    datetime submitted_at
  }
  APPLICATION_ITEM {
    uuid id PK
    uuid application_id FK
    uuid program_id FK
    string status
  }
  DOCUMENT {
    uuid id PK
    uuid application_id FK
    string type
    string verification_status
  }
  REVIEW {
    uuid id PK
    uuid item_id FK
    uuid reviewer_id FK
    int score
    text comments
  }
  DECISION {
    uuid id PK
    uuid item_id FK
    string type
    datetime decided_at
  }
  OFFER {
    uuid id PK
    uuid decision_id FK
    string type
    date response_deadline
  }
  PAYMENT {
    uuid id PK
    uuid application_id FK
    money amount
    string currency
    string status
  }
  NOTIFICATION {
    uuid id PK
    uuid user_id FK
    string channel
    string status
  }
  ROLE {
    uuid id PK
    string name
  }
```

---

## 10. Sequence Diagram

**Scenario:** Student submits an application (with payment, document validation, and event-driven downstream processing).

```mermaid
sequenceDiagram
  autonumber
  actor S as Student
  participant GW as API Gateway
  participant APP as Application Service
  participant DOC as Document Service
  participant PAY as Payment Service
  participant PG as Payment Gateway
  participant BUS as Event Bus
  participant NOT as Notification Service
  participant REV as Review & Decision Service
  participant AUD as Audit Service

  S->>GW: POST /applications/{id}/submit
  GW->>APP: submit(applicationId)
  APP->>APP: validate completeness (FR-2.3)
  APP->>DOC: verifyDocuments(applicationId)
  DOC-->>APP: documents valid
  APP->>PAY: initiatePayment(applicationId, fee)
  PAY->>PG: charge(token, amount, currency)
  PG-->>PAY: paymentCaptured
  PAY-->>APP: paymentConfirmed
  APP->>APP: status = SUBMITTED
  APP->>BUS: publish ApplicationSubmitted
  APP-->>GW: 200 OK (status=SUBMITTED)
  GW-->>S: confirmation + tracking link

  par Event fan-out
    BUS-->>NOT: ApplicationSubmitted
    NOT-->>S: notify (email/in-app)
  and
    BUS-->>REV: ApplicationSubmitted
    REV->>REV: queue for reviewer assignment
  and
    BUS-->>AUD: ApplicationSubmitted
    AUD->>AUD: append immutable audit record
  end
```

---

## 11. State Diagram

**Subject:** Lifecycle of an `ApplicationItem` (one program target within an application).

```mermaid
stateDiagram-v2
  [*] --> Draft
  Draft --> Submitted: submit (fee paid + valid)
  Submitted --> UnderReview: reviewer assigned
  UnderReview --> InterviewScheduled: interview required
  InterviewScheduled --> UnderReview: interview complete
  UnderReview --> Offered: decision = OFFER
  UnderReview --> Waitlisted: decision = WAITLIST
  UnderReview --> Rejected: decision = REJECT
  Waitlisted --> Offered: capacity opens
  Waitlisted --> Rejected: cycle closes
  Offered --> Accepted: student accepts
  Offered --> Declined: student declines
  Accepted --> Enrolled: enrollment confirmed
  Draft --> Withdrawn: student withdraws
  Submitted --> Withdrawn: student withdraws
  UnderReview --> Withdrawn: student withdraws
  Rejected --> [*]
  Declined --> [*]
  Withdrawn --> [*]
  Enrolled --> [*]
```

---

## 12. Activity Diagram

**Process:** End-to-end applicant journey, with decision points (swimlane-style grouping via subgraphs).

```mermaid
flowchart TD
  A([Start]) --> B[Register / Authenticate]
  B --> C[Build Student Profile]
  C --> D[Get AI Recommendations]
  D --> E{Eligible for chosen program?}
  E -- No --> D
  E -- Yes --> F[Create Application & add programs]
  F --> G[Upload Documents]
  G --> I{Application complete?}
  I -- No --> G
  I -- Yes --> J[Pay Application Fee]
  J --> K{Payment successful?}
  K -- No --> J
  K -- Yes --> L[Submit Application]
  L --> M[Institution: assign reviewer]
  M --> N[Review & Score]
  N --> O{Decision}
  O -- Offer --> P[Student responds]
  O -- Waitlist --> Q[Wait for capacity]
  O -- Reject --> R([End: Rejected])
  Q --> O
  P --> S{Accept?}
  S -- Yes --> T[Confirm Enrollment]
  S -- No --> U([End: Declined])
  T --> V([End: Enrolled])
```

---

## 13. Flow Chart

**Process:** Document Verification & Validation (FR-6) — distinct from the journey above, focused on a single subsystem's logic.

```mermaid
flowchart TD
  Start([Document uploaded]) --> F1{Format & size valid?}
  F1 -- No --> Rej[Reject upload + notify student]
  Rej --> End1([End])
  F1 -- Yes --> AV{Malware scan clean?}
  AV -- No --> Quar[Quarantine + flag + notify]
  Quar --> End1
  AV -- Yes --> Struct{Structural validation OK?}
  Struct -- No --> ReReq[Mark INVALID + request re-upload]
  ReReq --> End1
  Struct -- Yes --> Cred{Credential verification needed?}
  Cred -- No --> Mark1[Status = VALIDATED]
  Cred -- Yes --> Ext[Call external verifier]
  Ext --> Res{Verifier response}
  Res -- Verified --> Mark2[Status = VERIFIED]
  Res -- Failed --> Manual[Route to manual review]
  Res -- Timeout --> Retry{Retries left?}
  Retry -- Yes --> Ext
  Retry -- No --> Manual
  Manual --> MR{Manual decision}
  MR -- Approve --> Mark2
  MR -- Reject --> ReReq
  Mark1 --> Pub[Publish DocumentValidated event]
  Mark2 --> Pub
  Pub --> End2([End])
```

---

## 14. API Specification

REST over HTTPS, JSON bodies, OAuth2/OIDC bearer tokens, tenant-scoped, versioned under `/api/v1`. All write endpoints accept an `Idempotency-Key` header (NFR-6). Standard codes: `200/201` success, `400` validation, `401/403` auth, `404` not found, `409` conflict, `422` business rule, `429` rate limit.

### 14.1 Endpoint summary (by service)

| Service | Method & Path | Description | Auth (role) |
|---|---|---|---|
| IAM | `POST /api/v1/auth/register` | Register a user | Public |
| IAM | `POST /api/v1/auth/login` | Authenticate, returns tokens | Public |
| IAM | `POST /api/v1/auth/mfa/verify` | Verify MFA challenge | User |
| Profile | `GET /api/v1/students/{id}/profile` | Fetch profile | Student/Counselor |
| Profile | `PUT /api/v1/students/{id}/profile` | Update profile | Student |
| Catalog | `GET /api/v1/institutions` | List institutions | Any |
| Catalog | `GET /api/v1/programs?institutionId=` | List/filter programs | Any |
| Catalog | `POST /api/v1/institutions/{id}/programs` | Create a program | Admissions Officer |
| Application | `POST /api/v1/applications` | Create draft application | Student |
| Application | `POST /api/v1/applications/{id}/items` | Add a program target | Student |
| Application | `POST /api/v1/applications/{id}/submit` | Submit application | Student |
| Application | `GET /api/v1/applications/{id}` | Get application + status | Student/Counselor |
| Application | `POST /api/v1/applications/{id}/withdraw` | Withdraw | Student |
| Document | `POST /api/v1/applications/{id}/documents` | Upload document | Student |
| Document | `GET /api/v1/documents/{id}/status` | Verification status | Student/Inst. |
| Matching | `GET /api/v1/students/{id}/recommendations` | Get program recommendations | Student |
| Review | `GET /api/v1/institutions/{id}/queue` | Review queue | Reviewer |
| Review | `POST /api/v1/items/{id}/reviews` | Submit score/comments | Reviewer |
| Review | `POST /api/v1/items/{id}/decision` | Record decision/offer | Admissions Officer |
| Application | `POST /api/v1/offers/{id}/respond` | Accept/decline offer | Student |
| Payment | `POST /api/v1/applications/{id}/payments` | Pay application fee | Student |
| Payment | `POST /api/v1/payments/{id}/refund` | Refund | Admissions Officer |
| Notification | `GET /api/v1/users/{id}/notifications` | List notifications | User |
| Analytics | `GET /api/v1/institutions/{id}/analytics` | Institution KPIs | Admissions Officer |
| Integration | `POST /api/v1/webhooks/subscriptions` | Register a webhook | Admissions Officer |
| Audit | `GET /api/v1/audit?entityId=` | Query audit trail | Platform Admin |

### 14.2 Representative OpenAPI fragment

```yaml
openapi: 3.0.3
info:
  title: University Admissions Platform API
  version: 1.0.0
paths:
  /api/v1/applications/{id}/submit:
    post:
      summary: Submit an application for processing
      operationId: submitApplication
      parameters:
        - name: id
          in: path
          required: true
          schema: { type: string, format: uuid }
        - name: Idempotency-Key
          in: header
          required: true
          schema: { type: string }
      responses:
        '200':
          description: Submitted
          content:
            application/json:
              schema:
                type: object
                properties:
                  applicationId: { type: string, format: uuid }
                  status: { type: string, example: SUBMITTED }
                  trackingUrl: { type: string }
        '409': { description: Already submitted }
        '422': { description: Incomplete application or fee unpaid }
      security:
        - oauth2: [student]
components:
  securitySchemes:
    oauth2:
      type: oauth2
      flows:
        authorizationCode:
          authorizationUrl: /oauth/authorize
          tokenUrl: /oauth/token
          scopes:
            student: Student actions
            reviewer: Reviewer actions
            staff: Admissions officer (institution staff) actions
```

---

## 15. Guiding Principles — SOLID, KISS, YAGNI

### 15.1 SOLID

| Principle | How it is applied in this design |
|---|---|
| **S — Single Responsibility** | Each microservice maps to exactly one business capability with one reason to change (e.g., Payment ≠ Application). At class level, `Application` orchestrates application state while `Payment`, `Document`, `Review` own their own concerns. |
| **O — Open/Closed** | New notification channels, payment providers, or credential verifiers are added by implementing an interface (`NotificationChannel`, `PaymentProvider`, `Verifier`) — no change to existing code. New document types extend the validation strategy registry. |
| **L — Liskov Substitution** | `Student`, `Reviewer`, and `Counselor` all substitute for `User` wherever a `User` is expected (auth, notifications) without breaking behavior. Any `PaymentProvider` implementation is interchangeable behind the Payment Service. |
| **I — Interface Segregation** | Role-specific, narrow API contracts: a Reviewer client depends only on review endpoints, never on payment contracts it does not use. Internal ports are split (`DocumentValidator` vs `CredentialVerifier`) so consumers don't depend on methods they don't use. |
| **D — Dependency Inversion** | Services depend on abstractions (ports) not concretions: the Application Service depends on a `PaymentPort`, not on Stripe/PayPal; integrations are injected adapters (Hexagonal/Ports-and-Adapters). The external gateway is an implementation detail. |

### 15.2 KISS (Keep It Simple)
- **Synchronous REST for client commands, events only where decoupling pays off** — we don't event-source everything; only cross-service state propagation uses the bus.
- **One status model per aggregate** with a clear, documented state machine (§11) rather than scattered boolean flags.
- **Consolidated logical data model** with database-per-service ownership — simple to reason about, no shared mutable tables.
- **Reuse a single Notification Service** for all channels instead of bespoke notification logic in each service.

### 15.3 YAGNI (You Aren't Gonna Need It)
- **No speculative nano-services** — e.g., Verification and external credential checks live in one Verification Service until load proves a split is needed.
- **No premature multi-region/data-residency engine** — residency is a configurable policy hook (FR-10.4), implemented when a tenant actually requires it, not pre-built.
- **No custom ML platform** — Matching Service starts with rules + a hosted model; a bespoke training pipeline is deferred until recommendation quality demands it.
- **NFRs documented but implemented incrementally** — autoscaling and observability are foundational; exotic capabilities (e.g., real-time fraud scoring on payments) are deferred until justified.

---

### Document Notes
- Each diagram is authored in Mermaid so it renders in compatible viewers and remains version-controllable and editable.
- Requirement IDs (`FR-x.y`, `NFR-n`) are stable anchors for traceability across the use cases, services, and APIs.
- **Revision:** Removed the *Institution Administrator*, *Referee / Recommender*, and *Partner Organization* actors. Institution-administration duties were merged into the **Admissions Officer / Reviewer** role; the reference/recommendation capability (formerly FR-7, the Reference Service, and related entities/endpoints) was removed in full. FR and use-case IDs were renumbered to stay sequential.
