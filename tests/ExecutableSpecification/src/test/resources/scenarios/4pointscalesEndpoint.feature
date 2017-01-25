Feature: Pointscales test

Background:
 Given a new (pointscales) gamified application A1

Scenario: I can create a pointscale
When I POST to the /pointscales endpoint for A1
Then I receive a 200 status code (pointscales)

Scenario: I can delete an existing pointscale
Given I already have a pointscale registered
When I DELETE to the /pointscales endpoint with /id 1
Then I receive a 404 status code (pointscales)

Scenario: I delete an non-existing pointscale
When I DELETE to the /pointscales endpoint with /id invalid
Then I receive a 404 status code (pointscales)

Scenario: Get pointscales list
When I GET to the /pointscales endpoint
Then I receive a 200 status code (pointscales)
And a list of all pointscales

Scenario: Get an existing badge by id
Given I already have a pointscale registered
When I GET to the /pointscales endpoint with /id 1
Then I receive a 200 status code (pointscales)

Scenario: Get a non-existing pointscale by id
When I GET to the /pointscales endpoint with /id invalid
Then I receive a 404 status code (pointscales)