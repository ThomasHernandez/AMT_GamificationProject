Feature: Users test

Background:
 Given a new (users) gamified application A1 with event for user U1

Scenario: I can get the events for a specific user
When I GET to the /users endpoint for A1 for user U1
Then I receive a 200 status code (users)

Scenario: I can get the events for a specific user with multiple events
Given a new (users) gamified application A2 with event for user U1
When I GET to the /users endpoint for A2 for user U1
Then I receive a 200 status code (users)

Scenario: I get no events for a specific user with no events
When I GET to the /users endpoint for A1 for user U2
Then I receive a 404 status code (users)