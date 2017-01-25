Feature: Testing concurrency

Scenario: Register some new applications
Given I have many application payloads
When I POST them to the /registrations endpoint
Then I receive a 201 status code for each of them (concurrency)

Scenario: Check that the applications have been registered
Given I have many application payloads
When I ask for a list of the registered applications with a GET on the /applications endpoint
Then I see my applications in the list