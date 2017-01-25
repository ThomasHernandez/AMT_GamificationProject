Feature: Events testing concurrency

Background:
 Given a new (eventsConcurrency) gamified application A2 with many events

Scenario: I can create some events
When I POST them to the /event endpoint for A2 for user U2
Then I receive a 202 status code for each of them