**Restaurant Review API**

   This is a RESTful API designed to manage basic restaurant information, customer reviews, and some basic analytics, such as average ratings and top-rated restaurants by      cuisine.

**Table of Contents**
  Features

  Technical Stack

  Setup Instructions

  Running the Application

  API Endpoints

  Testing

  License

**Features**
  Restaurant Management:

  Create and retrieve restaurant information including name, cuisine type, address, and price range.

  Review Management:

  Submit and retrieve reviews for restaurants including ratings, comments, visit date, and status (PENDING/APPROVED).

  Basic Analytics:

  Calculate and expose average rating per restaurant and provide the top 3 restaurants by cuisine type.

**Technical Stack**
  Backend:
  
  Spring Boot 3.x for the backend API
  
  Java 17 or higher for the runtime environment
  
  Spring Data JPA for database interaction
  
  H2 Database for an in-memory database (for simplicity)
  
  Maven or Gradle for dependency management

**Security:**

  Basic authentication for securing the API using Spring Security.

**Testing:**

  JUnit 5 and Mockito for unit and integration tests.
  
  Spring Boot Test for integration testing.

**API Documentation:**

    Swagger/OpenAPI to document and test the API endpoints.
    
    Setup Instructions
    Follow the steps below to set up and run the project locally.
    
    Prerequisites
    Java 17 or higher installed on your machine.
    
    Maven (or Gradle) for managing dependencies.
    
    Git to clone the project repository.
    
    IDE: Preferably IntelliJ IDEA, Eclipse, or VSCode.
    
    1. Clone the Repository
      Clone the repository to your local machine:
      
      bash
      Copy
      Edit
      git clone https://github.com/balram-jat-fullstack/restaurant-review-api.git
    2. Install Dependencies
      Navigate to the project directory and run the following command to install the necessary dependencies:

For Maven:
    bash
    Copy
    Edit
    cd restaurant-review-api
    mvn clean install
    For Gradle (if you're using Gradle):
    bash
    Copy
    Edit
    cd restaurant-review-api
    gradle build
3. Configure Application Properties
    The application is configured to use an in-memory H2 database by default. If you want to use another database (like MySQL or PostgreSQL), you’ll need to modify the     
    application.yml or application.properties accordingly.

For local development, the default H2 database configuration in application.yml should work fine:

    yaml
    Copy
    Edit
    spring:
      datasource:
        url: jdbc:h2:mem:testdb
        driver-class-name: org.h2.Driver
        username: sa
        password:
        platform: h2
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
      h2:
        console:
          enabled: true
4. Run the Application
    After building the project, run the application with the following command:

For Maven:
      bash
      Copy
      Edit
      mvn spring-boot:run
      For Gradle:
      bash
      Copy
      Edit
      gradle bootRun
      Once the application is running, you can access the API at:

      arduino
      Copy
      Edit
      http://localhost:8080
      Running the Application with Docker (Optional)
      To run the application inside a Docker container, follow these steps:

      Build the Docker image:
      
      bash
      Copy
      Edit
      docker build -t restaurant-review-api .
      Run the Docker container:
      
      bash
      Copy
      Edit
      docker run -p 8080:8080 restaurant-review-api
      The application will be accessible at http://localhost:8080.

**API Endpoints**
    1. Restaurant Management
      Create a restaurant:
      
      POST /api/restaurants
      
      Request Body:
      
      json
      Copy
      Edit
      {
        "name": "Italian Bistro",
        "cuisineType": "Italian",
        "address": "1234 Pasta St, Rome",
        "priceRange": "MEDIUM"
      }
      Get all restaurants:
      
      GET /api/restaurants
      
      Get top 3 restaurants by cuisine:
      
      GET /api/restaurants/top/{cuisineType}
      
      Example: /api/restaurants/top/Italian
      
   2. Review Management
      Submit a review:
      
      POST /api/reviews

    **Request Body:**
      
      json
      Copy
      Edit
      {
        "restaurantId": 1,
        "rating": 5,
        "comment": "Great food and atmosphere!",
        "visitDate": "2025-04-15",
        "status": "APPROVED"
      }
      Get average rating for a restaurant:
      
      GET /api/reviews/average/{restaurantId}
      
      Example: /api/reviews/average/1

  3. Basic Analytics
      Get top 3 restaurants by cuisine:
      
      GET /api/restaurants/top/{cuisineType}
      
      Security
      This application uses Basic Authentication to secure the API.
      
      Username: user
      
      Password: userpass
      
      Use Basic Auth in tools like Postman to authenticate API requests.

**Testing**
Unit and Integration Tests
The project includes unit tests for the service layer and integration tests for the REST API endpoints. You can run the tests with:

For Maven:
bash
Copy
Edit
mvn test
For Gradle:
bash
Copy
Edit
gradle test
Test coverage for the project is set to 70% or higher. The tests include mock data and validate business logic and API responses.

Testing with Postman
You can import the Postman collection to quickly test the API. The collection includes all the endpoints and sample data.
