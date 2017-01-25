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

[http://localhost:8080/api/](http://localhost:8080/api/)

Here, the documentation of our API is available, generated from swagger.

## Test

todo
