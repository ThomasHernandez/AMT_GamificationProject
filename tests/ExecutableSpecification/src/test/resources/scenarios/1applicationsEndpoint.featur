Feature: Application registration

Scenario: Check that no application has been registered
Given I have no application registered
When I GET to the /applications endpoint
Then I receive a 200 status code
And a list of all applications containing 0 applications

Scenario: Register a new application
Given I have an application payload
When I POST it to the /registrations endpoint
Then I receive a 201 status code
And a list of all applications containing 1 applications

Scenario: Check that the application has been registered
Given I have an application payload
When I POST it to the /registrations endpoint
And I ask for a list of registered apps with a GET on the /applications endpoint
Then I see my app in the list

Scenario: Get applications list
When I GET to the /applications endpoint
Then I receive a 200 status code
And a list of all applications

Scenario: Get an existing application by id
Given I already have an application registered
When I GET to the /applications endpoint with /id 1
Then I receive a 200 status code

Scenario: Get an non-existing application by id
When I GET to the /applications endpoint with /id invalid
Then I receive a 404 status code

Scenario: Delete an existing application
Given I already have an application registered
When I DELETE to the /applications endpoint with /id 1
Then I receive a 204 status code

Scenario: Delete an non-existing application
When I DELETE to the /applications endpoint with /id invalid
Then I receive a 404 status code