## DermoPES - Backend

Frontend part of the application: https://github.com/Jakub112358/naukaPes

### Developer:

- Jakub Szymański

### Running application:
Application is ready to use.

### General app description:
The main purpose of the application is to make it easier to study for the PES (polish specialization exam for doctors) in dermatology.
The solution will be to create a personalized web application that allows user to make CRUD operations in data base with previous exams questions.
Next step is to make quiz mode for users.

### General technical description: 

**Backend:**
Spring Boot REST application. The application meets all the requirements of level 2 of the Richardson Maturity Model. 
I focus on creating clean code and using appropriate design patters.

**Frontend:**
Angular application with PrimeNG library.

### Tech Stack:
**Backend:**
- Java 17
- Spring Boot 3
- Testing: Unit and integration tests (singular and parameterized) using tools such as JUnit, MockMVC, Mockito.

**DataBase:**
- H2
- DB structure: autogenerated by Hibernate
- DB queries: 
  - JPARepository default methods,
  - JPA derived queries, 
  - JPA Criteria API,
  - JPQL custom queries

**Frontend:**
- Angular 15

### App features: 
- creating new questions
- searching questions:
  - all questions in database
  - by qustion ID
  - by criteria like: minimum exam date, maximum exam date, difficulties, categories
- updating questions
- deleting questions
- counting requests by URI, request type and answer code

### Features in-development:
- quiz mode
- login
- extracting user and admin roles

### Version control: 
- system: GIT
- branches:
  - main – stable and tested version of application
  - dev – version in development

