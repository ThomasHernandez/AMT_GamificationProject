# AMT_GamificationProject

## Authors: ALBASINI Romain - CIANI Antony - HERNANDEZ Thomas - SELIMI Dardan

## Description
This project is part of the AMT course taught at HEIG-VD by professor Olivier Liechti.

It consists in a gamification platform which potentially can be used by a third-party application to implement basic gamification concepts such as counting points and awarding badges to their users when they perform a certain action or have earned enough points to receive a badge.

The platform relies on a Spring-Boot application acting as an application server which provides a REST API. The setup for the application wanting to use the platform has  to be used via scripting or using the swagger-ui GUI (not really suited for large setups). Data is stored in a MySQL Database.

The whole setup relies on Docker images and on a Docker Compose topology so it can be easily deployed.

The API documentation can be found at the following address: 
[Gamification API Documentation](https://antonyciani.github.io/AMT_GamificationProject/ "[API Documentation]")



## Deployment Instructions for Docker

Clone the repo to your machine, open your preferred terminal where you can use docker commands.

Move to **topology-amt** directory. There should be the **docker-compose.yml** file describing the topology.

Run the 

	docker-compose down 

command to remove previous running containers.

Run the 

	docker-compose up --build 

command. This should build the mysql, phpmyadmin and springserver images and start them.

To access the main application go to:
[http://192.168.99.100:8080/api/swagger-ui.html](http://192.168.99.100:8080/api/swagger-ui.html)

**N.B: The IP adress may vary depending on your docker machine configuration**


## Tests


We developed automated tests to check the correct and expected behavior of our API.  
The tests have been implemented with [Cucumber](https://cucumber.io/). Multiple scenarios were written using the [Gherkin](https://github.com/cucumber/cucumber/wiki/Gherkin) syntax. 

Every endpoint of our API has been tested independently through the following features :  

- **/application**, **/registrations** (`01_applicationEndpoint.feature`, `ApplicationSteps.java`)  
- **/auth** (`02_authenticationEndpoint.feature`, `AuthenticationSteps.java`)  
- **/badges** (`03_badgesEndpoint.feature`, `BadgesSteps.java`)  
- **/pointscales** (`04_pointscalesEndpoint.feature`, `PointscalesSteps.java`)  
- **/rules** (`05_rulesEndpoint.feature`, `RulesSteps.java`)  
- **/events** (`06_eventsEndpoint.feature`, `EventsSteps.java`)  
- **/users** (`07_usersEndpoint.feature`, `UsersSteps.java`)  

Finally, we tested the interaction between multiple endpoints through the following feature :

- `08_multipleEndpoints.feature`, `MultipleEndpointsSteps.java`  

Additionally, two features have been written to test our API's concurrency behavior :  

- `09_concurrencyTesting.feature`, `ConcurrencyTestingSteps.java`
- `10_eventsEndpointConcurrency.feature`, `EventsConcurrencySteps.java`

All those feature result in executing **149 tests** over our API, with a success rate of **100.0%**.

### How to run the automated tests
- Open in `NetBeans` the `GamificationAPI` project. 
- Open in `NetBeans` the `ExecutableSpecification` project.
- Run the server : *Right-clic on the GamificationAPI project -> Run Maven -> spring-boot:run*
- Run the tests : 
    - *Right-click on the ExecutableSpecification project -> Clean*
    - *Right-click on the ExecutableSpecification project -> Test*
- Satisfyingly observe the **100.0%** success rate.

