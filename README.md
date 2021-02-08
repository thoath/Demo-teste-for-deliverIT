# Simple DeliverIT demo test, module version

## Pre requisites
 - Maven
 - Java 11
 - Docker compose
 - Insomnia/Postman
 - Some database admin like Dbeaver
 
 ## To run the project
 - In root folder, run cmd command: <code>docker-compose up -d</code>
 - In root folder, run cmd command : <code>mvn clean install</code>
 - Configure your application to run the application(i use Intellij)
 - In insomnia import the json configuration from /doc folder
 - In insomnia, register the rules :<code>{"minDays" : 6, "maxDays": 99999, "penalty": 5,"interestDay": 0.3}{"minDays" : 1, "maxDays" : 3, "penalty": 2,"interestDay": 0.1}{"minDays" : 4, "maxDays" : 5, "penalty": 3,"interestDay": 0.2}</code>
 - In insomnia register the invoices
 - In insomnia list the invoices and test the output
 - If you need to migrate your database, run in root/service/: <code>mvn liquibase:generateChangeLog</code> and use this file to migrate
 ## Business Rules
 - Invoice registry
 - Invoice list with processed data with calculated fields
 - Rules to payment registry
 - The fixed amount are not cumulative from daily interests.

 ## Doc
  - http://localhost:8082/swagger-ui.html

 ## Development Process
 - TDD
 - DDD
 - SOLID
 - KISS
 
 Thank you and feel free to contribute.
