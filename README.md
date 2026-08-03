# EventBooking API

Backend REST API untuk aplikasi EventBooking, sebuah platform Event Booking yang dibangun menggunakan Quarkus.

## Features

- Authentication menggunakan JWT
- Authorization berdasarkan Role (ADMIN & USER)
- CRUD Event
- CRUD User
- Booking Event
- Melihat daftar peserta event
- Melihat event yang telah didaftarkan
- Pagination
- PostgreSQL Database

---

## Tech Stack

- Java 21
- Quarkus
- Hibernate ORM (Panache)
- PostgreSQL
- SmallRye JWT
- BCrypt Password Hashing
- Maven

---

## Project Structure

```
src
├── main
│   ├── java
│   │   └── id.alif
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── enums
│   │       ├── repository
│   │       └── services
│   └── resources
│       ├── application.properties
│       └── keys
└── test
```

---

## Requirements

Pastikan sudah menginstall

- Java 21
- Maven 3.9+
- PostgreSQL 16+
- Git

---

## Clone Repository

```bash
git clone https://github.com/USERNAME/eventure-api.git

cd eventure-api
```

---

## Database Setup

Masuk ke PostgreSQL

```sql
CREATE DATABASE event_booking;
```

---

## Configure Environment

Edit file

```
src/main/resources/application.properties
```

contoh

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=your_password
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/event_booking

quarkus.hibernate-orm.database.generation=update
```

---

## JWT Configuration

Generate RSA Key

```
src/main/resources/keys
├── privateKey.pem
└── publicKey.pem
```

Kemudian tambahkan

```properties
mp.jwt.verify.publickey.location=keys/publicKey.pem
smallrye.jwt.sign.key.location=keys/privateKey.pem
mp.jwt.verify.issuer=event-booking-api
```

---

## Install Dependencies

```bash
./mvnw clean install
```

atau

```bash
mvn clean install
```

---

## Run Application

```bash
./mvnw quarkus:dev
```

atau

```bash
mvn quarkus:dev
```

API akan berjalan di

```
http://localhost:8080
```

---

## API Endpoints

### Authentication

| Method | Endpoint       |
| ------ | -------------- |
| POST   | /auth/register |
| POST   | /auth/login    |

---

### User

| Method | Endpoint    |
| ------ | ----------- |
| GET    | /users      |
| GET    | /users/{id} |
| PUT    | /users/{id} |
| DELETE | /users/{id} |

---

### Event

| Method | Endpoint     |
| ------ | ------------ |
| GET    | /events      |
| GET    | /events/{id} |
| POST   | /events      |
| PUT    | /events/{id} |
| DELETE | /events/{id} |

---

### Booking

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | /bookings           |
| DELETE | /bookings/{id}      |
| GET    | /bookings/my-events |

---

## Authentication

Login

```
POST /auth/login
```

Response

```json
{
  "status": 200,
  "message": "Login success",
  "data": {
    "token": "..."
  }
}
```

Gunakan token pada Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Database

Entity

- User
- Event
- Booking

Relationship

```
User
  |
  | 1
  |
  | *
Booking
  |
  | *
  |
  | 1
Event
```

---

## Development

Project menggunakan arsitektur sederhana

```
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```
