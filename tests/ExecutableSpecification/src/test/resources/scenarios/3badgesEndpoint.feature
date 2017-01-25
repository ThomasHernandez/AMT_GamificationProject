Feature: Badges test

Background:
 Given a new (badges) gamified application A1

Scenario: I can create a badge
When I POST to the /badges endpoint for A1
Then I receive a 200 status code (badges)

Scenario: I can delete an existing badge
Given I already have a badge registered
When I DELETE to the /badges endpoint with /id 1
Then I receive a 404 status code (badges)

Scenario: I delete an non-existing badge
When I DELETE to the /badges endpoint with /id invalid
Then I receive a 404 status code (badges)

Scenario: Get badges list
When I GET to the /badges endpoint
Then I receive a 200 status code (badges)
And a list of all badges

Scenario: Get an existing badge by id
Given I already have a badge registered
When I GET to the /badges endpoint with /id 1
Then I receive a 200 status code (badges)

Scenario: Get a non-existing badge by id
When I GET to the /badges endpoint with /id invalid
Then I receive a 404 status code (badges)