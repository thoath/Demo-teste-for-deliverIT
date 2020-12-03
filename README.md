#Simple DeliverIT demo test

##Pre requisites
 - Maven
 - Java 11
 - Docker compose
 - Insomnia/Postman
 - Some database admin like Dbeaver
 
 ##To run the project
 - In root folder, run cmd command: <code>docker-compose up -d</code>
 - In root folder, run cmd command : <code>mvn clean install</code>
 - Configure your application to run the application(i use Intellij)
 - In insomnia import the json configuration from /doc folder
 - In insomnia, register the rules : 
 <code>
{
    "delayInDays" : 99999,
    "penalty": 5,
    "interestDay": 0.3
}
{
	"delayInDays" : 3,
	"penalty": 2,
	"interestDay": 0.1
}
{
	"delayInDays" : 5,
	"penalty": 3,
	"interestDay": 0.2
}
</code>
- In insomnia register the invoices
- In insomnia list the invoices and test the output