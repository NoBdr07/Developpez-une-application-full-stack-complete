# MDD MVP Project
## Description

This project is a comprehensive blogging platform allowing users to create accounts, log in, publish articles, comment on articles, and explore various topics. It includes both front-end (Angular) and back-end (Java 11, Spring Boot) components with REST APIs for data management. 

### Technologies
#### Front-end

**Framework:** Angular 14  
**UI Library:** Angular Material  

#### Back-end

**Language:** Java 11  
**Framework:** Spring Boot 2.7.3  
**Security:** Spring Security and JWT  
**Data Management:** Spring Data JPA and MySQL  

### Features

Authentication and Registration  
Feed Creation  
Article Management  
Responsive Design

## Installation

The detailled instructions for back and front are in their README.  
But these are the general steps : 

1. **Clone the repository from GitHub:**  
        - `git clone https://github.com/NoBdr07/Developpez-une-application-full-stack-complete/tree/main`
    
2. **Database Setup:** 
        - Create a MySQL database (fullapp).  
        - Import the database schema provided in the back directory.

3. **Set Up Back-end:**  
        - Navigate to the back directory.  
        - Ensure JDK 11+ and Maven are installed.  
        - Install dependencies using `mvn clean install`.  
        - Set up database configuration in application.properties.  
        - Launch the API with `mvn spring-boot:run`.


4. **Set Up Front-end:**  
        - Navigate to the front directory.  
        - Install dependencies using `npm install`.  
        - Start the app with npm start.

    
  

## Architecture

**Front-end**: Angular application structured into pages, components, services, and interceptors.  
**Back-end:** Spring Boot application structured with controllers, services, repositories, security, and utility classes.

## Definitions:

**Post:** A user's article on a topic.  
**Feed:** List of posts based on user-subscribed topics.  
**Topic:** Broad categories within computer development.  
**Comment:** User feedback on a post.  
**Subscription:** When a user follows a topic.
