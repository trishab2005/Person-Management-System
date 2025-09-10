# Person API with Spring Boot

This project is a simple RESTful API for managing `Person` and `AadharCard` entities. It demonstrates a **one-to-one relationship** between a `Person` and their `AadharCard` using Spring Data JPA, along with basic CRUD operations.

## ✨ Features

* **RESTful API:** Provides a clean set of endpoints for resource manipulation.
* **One-to-One Mapping:** Implements a `OneToOne` relationship between `Person` and `AadharCard` entities.
* **Data JPA:** Uses Spring Data JPA for data persistence, reducing boilerplate code.
* **CRUD Operations:** Supports **C**reate, **R**ead, **U**pdate, and **D**elete functionality.
* **Exception Handling:** Includes custom exception handling for `ResourceNotFoundException`.
* **Validation:** Uses `@Valid` for request body validation.

---

## 🛠️ Technologies Used

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA**
* **Lombok**
* **Maven**
* **Oracle Database** (or any other relational database configured via `application.properties`)

---

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* Apache Maven 3.6.3 or higher
* An Oracle Database instance (or another database like H2, MySQL, or PostgreSQL)

### Project Setup

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd <your-project-directory>
    ```

2.  **Configure the database:**
    Open `src/main/resources/application.properties` and update the database connection details.

    ```properties
    spring.datasource.url=jdbc:oracle:thin:@<hostname>:<port>:<database_name>
    spring.datasource.username=<your_username>
    spring.datasource.password=<your_password>
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
    ```

3.  **Run the application:**
    You can run the application directly from your IDE or use Maven from the command line.

    ```bash
    ./mvnw spring-boot:run
    ```

    The application will start on `http://localhost:8080`.

---

## 💻 API Endpoints

You can use a tool like **Postman** or **cURL** to interact with the API endpoints.

### **1. Create a New Person**

* **URL:** `POST /api/persons`
* **Headers:** `Content-Type: application/json`
* **Body (JSON):**

    ```json
    {
        "name": "Ravi",
        "age": 25,
        "aadharCard": {
            "aadharNumber": "123456789012",
            "address": "Delhi"
        }
    }
    ```
* **Response:** `201 Created`

### **2. Get All Persons**

* **URL:** `GET /api/persons`
* **Response:** `200 OK` with a list of all persons.

### **3. Get a Person by ID**

* **URL:** `GET /api/persons/{id}` (e.g., `/api/persons/1`)
* **Response:** `200 OK` with the person's details or `404 Not Found` if the ID does not exist.

### **4. Update a Person**

* **URL:** `PUT /api/persons/{id}` (e.g., `/api/persons/1`)
* **Headers:** `Content-Type: application/json`
* **Body (JSON):**

    ```json
    {
        "name": "Ravi Kumar",
        "age": 28,
        "aadharCard": {
            "aadharNumber": "123456789012",
            "address": "Mumbai"
        }
    }
    ```
* **Response:** `200 OK` with the updated person's details or `404 Not Found`.

### **5. Delete a Person**

* **URL:** `DELETE /api/persons/{id}` (e.g., `/api/persons/1`)
* **Response:** `200 OK` with a confirmation message or `404 Not Found`.

---

## 📸 Project Screenshots

## Frontend-

![WhatsApp Image 2025-09-10 at 21 23 28](https://github.com/user-attachments/assets/5c46632b-84ed-4cd4-85e7-28bc886cd49a)

![WhatsApp Image 2025-09-10 at 21 23 29](https://github.com/user-attachments/assets/df794789-9b0b-486c-ab94-ab87808c1a1f)

## Postman API checking-

![WhatsApp Image 2025-09-10 at 12 35 44](https://github.com/user-attachments/assets/6727fc57-8294-4768-b058-4419b2efd7ac)

![WhatsApp Image 2025-09-10 at 12 46 10](https://github.com/user-attachments/assets/e9b2c6bd-5bab-43c0-9c80-91c2a818e003)

![WhatsApp Image 2025-09-10 at 12 46 11](https://github.com/user-attachments/assets/f5bf85ec-071e-4469-a18f-330be25c18b4)

![WhatsApp Image 2025-09-10 at 14 09 16](https://github.com/user-attachments/assets/07526e0d-1ca9-4562-af71-154919ec6362)

![WhatsApp Image 2025-09-10 at 14 15 04](https://github.com/user-attachments/assets/9d0fcbf5-4300-42ac-84f6-deb49fb6ec9d)

## Oracle DB-

![WhatsApp Image 2025-09-10 at 12 50 32](https://github.com/user-attachments/assets/ac610e78-0b7a-4fc9-a50d-5dc91911c72e)

## Testing-

![WhatsApp Image 2025-09-10 at 12 57 45](https://github.com/user-attachments/assets/6b5f0dcb-6a7a-4caf-854a-4cdad7272b81)

---

## 🧪 Running Tests

To run the unit and integration tests, use the following Maven command:

```bash
./mvnw test
