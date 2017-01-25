Feature: Authentication test

Background:
 Given a new (auth) gamified application A1 

Scenario: Authenticate an application
When I POST A1 to the /auth endpoint
Then it receives a 200 status code

Scenario: Deny access when wrong password
Given a new (auth) gamified application A2
When I POST A2 to the /auth endpoint with a wrong password
Then it receives a 400 status code

Scenario: Deny acces when wrong application
When I POST a non-existing application to the /auth endpoint
Then it receives a 400 status code