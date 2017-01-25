Feature: Events test

Background:
 Given a new (events) gamified application A1

Scenario: I can create an event
When I POST to the /event endpoint for A1 for user U1
Then I receive a 202 status code (events)

Scenario: I can't create an event for a non-existing application
When I POST to the /event endpoint of a non-existing application for user U1
Then I receive a 422 status code (events)

Scenario: I can create multiple events
When I POST 3 times to the /event endpoint for A1 for user U2
Then I receive a 202 status code (events)