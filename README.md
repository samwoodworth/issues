# Spring Boot API Service

# Introduction

This is the API service for the application, with the goal being to compare the framework against others.
API calls are only able to be made after the user is authenticated in the authentication service.

# Technologies

Java 17.0.1
Spring Boot 2.6.1

# Running the Application

The application can be run using 'mvn spring-boot:run' from the 'issues' directory and can be reached at 'localhost:8081/'.

# Usage

Calls to the API will not be allowed unless the user is authenticated in the authentication service.
The user is authenticated as long as teh 'JSESSIONID' cookie is present. In the home page,
links to each endpoint are available. There are links to view all the issues,
find a certain issue by ID, insert a custom issue with user input, insert an issue with arbitrary data,
and insert a specified number of issues with arbitrary data.

## Authentication 

Before the API call is made, an interceptor checks for the 'JSESSIONID' cookie. If it is present then the call continues.
If it does not, then a 401 unauthorized status code and no data will be returned.
