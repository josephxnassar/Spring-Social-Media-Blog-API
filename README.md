# Spring Social Media Blog API

## Project Description

This social media application will serve as a backend API with no frontend interface. Like before, the application will include core features such as user account management and the ability for users to submit, update, and delete messages. However, this iteration of the project must be developed using the Spring Framework, specifically leveraging Spring Boot and Spring Web. As with the earlier version, the backend should be capable of delivering all necessary data to support a frontend (if added later), while also handling key user actions such as registration, login, and full message lifecycle management.

## Technologies Used

* Java - java 23.0.2
* Maven - apache maven 3.0.0-M7
* h2database - h2 2.1.214
* Javalin - javalin 5.0.1
* JUnit - junit 4.13.2
* Mockito - mockito 4.9.0
* springframework.boot - spring-boot-starter-web

## Features

List of features
* Adding a user
* Login
* Creating a message
* Get all messages
* Get a specific message
* Delete a specific message
* Update a specific message
* Get messages for a user

To-do list:
* Implement frontend GUI

## Getting Started

Windows:
1. Install Git (if not already installed)
    Download from: https://git-scm.com/download/win

2. You can either:
    a. Open Git Bash and navigate to the directory where you want to clone the repo.
    b. Navigate to the directory where you want to clone the repo, right-click inside the folder, and select “Git Bash Here.”
Note: Use "cd /c/Users/YourName/Path/To/Folder" to change directories.

3. Clone the repository
git clone https://github.com/josephxnassar/Social-Media-Blog-API.git

Linux:
1. Install Git (if not already installed)
sudo apt update && sudo apt install git

2. Open terminal and navigate to the directory where you want to clone the repo
Note: Use "cd /c/Users/YourName/Path/To/Folder" to change directories.

3. Clone the repository
git clone https://github.com/josephxnassar/Social-Media-Blog-API.git

## Usage

After installation, it's important to understand the structure of the project:

SocialMediaController.java represents the controller layer. It defines the REST API endpoints that the client can send requests to, using Spring annotations like @RestController, @GetMapping, @PostMapping, and so on. If you want to add new endpoints or handle different types of HTTP requests, this is where you'll do it.

AccountService.java and MessageService.java make up the service layer. These classes are annotated with @Service, making them Spring-managed components. They contain the business logic and act as a bridge between the controller and repository layers. Any functionality added in the controller should be routed through the appropriate service class.

AccountRepository.java and MessageRepository.java represent the data access layer. These interfaces extend JpaRepository, which is part of Spring Data JPA. This gives you built-in methods for interacting with the database, like saving, updating, and deleting records. You can also define custom queries using method names or the @Query annotation.

Account.java and Message.java are model classes representing an account and a message in the database. They’re annotated with @Entity and map to database tables using JPA. If you want to introduce a new database feature or entity, this is where you'd define its structure and field mappings.


## License

Copyright (c) 2025 Joseph nassar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
THE SOFTWARE.