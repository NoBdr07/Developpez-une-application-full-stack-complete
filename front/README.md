# MDD MVP Front-end

## Description

This Angular application is a blogging platform that allows users to create accounts, log in, publish articles, comment on articles, and navigate through different topics. 
It uses a REST API for managing user data, articles, and comments.

### Technologies

This app use the framework Angular and was developped on VS Code.
The library Angular Material is used for visuals.

### Features

1. Authentication and Registration: Users can sign up and log in to the application.
2. Feed creation : Users can subscribe to topics and generate a feed of articles related to this topics.
3. Article Management: Users can create, view, and comment on articles.
4. Responsive Design: The user interface is responsive and adapts to different types of screens.

## Configuration

Follow these steps to launch app :

1. Fork the project from : https://github.com/NoBdr07/Developpez-une-application-full-stack-complete
2. Clone the projet using `git clone` in your chosen directory.
3. Open the directory named front.
1. Install dependencies using : `npm install`.
2. This project need the API running for the back-end, its code is in the directory back. 
You can start it by following the readme of the back or change configuration to use something else for the back by changing the environments file. 
3. Launch the app with `npm start`.

## Architecture

Definition :  
    - Post : a post written by a user on a topic  
    - Feed : list of posts on user's chosen topics  
    - Topic : broad topics in the field of computer development
    - Comment : posted by a user about a post

```
───src  
    │
    ├───app  
    │   │
    │   ├───interceptors  
    │   │       jwt.interceptor.ts  //add token in the header of requests
    │   │
    │   ├───interfaces  
    │   │       comment.interface.ts  
    │   │       post.interface.ts  
    │   │       topic.interface.ts  
    │   │       user.interface.ts  
    │   │
    │   ├───pages  
    │   │   ├───auth  
    │   │   │   ├───interfaces  //requests interfaces 
    │   │   │   │
    │   │   │   ├───layout  //enable to add a header 
    │   │   │   │
    │   │   │   ├───login   
    │   │   │   │
    │   │   │   ├───me  //account page
    │   │   │   │
    │   │   │   ├───register  
    │   │   │   │
    │   │   │   └───services  
    │   │   │           auth.service.ts  
    │   │   │
    │   │   ├───header  
    │   │   │
    │   │   ├───home  
    │   │   │
    │   │   ├───posts  
        │   │   │   
    │   │   │   ├───feed  
    │   │   │   │
    │   │   │   ├───new-post  
    │   │   │   │
    │   │   │   ├───post  
    │   │   │   │
    │   │   │   └───services      
    │   │   │           comments.service.ts      
    │   │   │           posts.service.ts   
    │   │   │
    │   │   └───topics    
    │   │       ├───list    
    │   │       │
    │   │       └───services  
    │   │               topic.service.ts  
    │   │
    │   ├───security  
    │   │       auth.guard.ts  //control path access 
    │   │
    │   └───services  
    │           session.service.ts  //control the user state
    │
    ├───assets  //contain some images and icons
    │
    ├───environments
    │
    └───styles 
    ```
    