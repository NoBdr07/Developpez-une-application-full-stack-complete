# MDD MVP Back-end

## Description

This API provide back-end for a blogging platform that allows users to create accounts, log in, publish articles, comment on articles, and navigate through different topics.

### Technologies

This API was developed on IntelliJ, with Java 11 and Spring Boot.  
Security is managed by Spring Security and JWT.  
Data is managed with Spring Data JPA and MySQL.

### Features

1. Authentication and Registration: Users can sign up and log in to the application.
2. Feed creation : Users can subscribe to topics and generate a feed of articles related to this topics.
3. Article Management: Users can create, view, and comment on articles.
4. Responsive Design: The user interface is responsive and adapts to different types of screens.

## Configuration

Follow these steps to launch app :

### Database creation
Create the app database in your MySQL Command Line Client :
```
CREATE DATABASE fullapp;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `unique_username` (`username`));
  
CREATE TABLE `topics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `posts_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`));
  
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`));
  
CREATE TABLE `subscriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `subscriptions_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`));
```

If you use another name that fullapp you have to change the database name in application.properties.

Then you can add data directly in MySQL command or use Postman :   
- A Postman collection that document all the routes is present in the directory resources.  
- Launch postman or use online application : https://www.postman.com/  
- Import the collections  
- Then you can try routes and add data easily.

## Install
1. Clone the project from https://github.com/NoBdr07/Developpez-une-application-full-stack-complete using `git clone` in your chosen directory.
2. Open the "back" directory from your IDE.
3. Make sure you are using a JDK 11 or more recent.
4. Make sure you have maven installed.
5. Use `mvn clean install`
6. Set up your database info in application.properties
7. Launch the API using `mvn spring-boot:run`
8. Then you can try to launch request on Postman or run the front-end projet by following the README in the "Front " directory.

## Architecture

Definition :  
- Post : a post written by a user on a topic  
- Feed : list of posts on user's chosen topics  
- Topic : broad topics in the field of computer development
- Comment : posted by a user about a post  
- Subscription : when a user subscribe to a topic

```
├───resources //here you can find the postman collection
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───openclassrooms
│   │   │           └───mddapi
│   │   │               ├───controllers //auth, comment, post, topic and user controllers
│   │   │               ├───exceptions
│   │   │               ├───mappers //to convert model to their DTO
│   │   │               ├───models
│   │   │               │   ├───dtos
│   │   │               │   ├───entities //comment, post, subscription, topic and user
│   │   │               │   └───payload  //response and request models
│   │   │               ├───repositories
│   │   │               ├───security
│   │   │               │   ├───jwt  //Controlling and creating tokens
│   │   │               │   └───services  //Global security configuration
│   │   │               ├───serviceImpls
│   │   │               ├───services
│   │   │               └───utils //Tool to manage dates
│   │   └───resources


    ```
    
    
    