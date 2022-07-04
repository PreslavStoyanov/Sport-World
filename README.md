# Sport-world

Full stack website for the sport world built with React, Spring, and MySQL.

## Features
- Commenting on futured matches
- Create and delete custom categories
- User passwords handling for not losing account
- Receive daily top leagues matches

### Technical features
- JWT authentication
- REST API
- Multiple third-party API integrations to deliver live football scores
- Exception handling and error messaging
- Full unit test coverage of all controller classes

## Screenshots

![](sport-world.png)

## Installation
- If you want to run the app via DOCKER you can visit my DockerHub account [click here.](https://hub.docker.com/repositories)

- Otherwise
1. Clone this repository

2. Set up a MySQL server and execute ```sport-world.sql``` to create the database

3. Edit the config file ```src/main/resources/application.properties``` to point to your MySQL server

4. Build with java 17 and run
