Feature: Testing rules

Scenario: The /registration, /events, /badges, /pointscales and /rules endpoint are linked together
Given a new (users) gamified application A9 with events for user U9 and a badge B9 attribution depeding on a rule R9 and the pointscale P9
When I POST 3 events to the /events endpoint for A9 for user U9 for rule R9
Then I receive a 202 status code (rulesTester) 
