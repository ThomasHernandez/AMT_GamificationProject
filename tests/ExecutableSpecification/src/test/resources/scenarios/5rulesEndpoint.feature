Feature: Rules test

Scenario: I can add a rule
Given a new (rules) gamified application F1 with a badge B1 and a pointscale P1
When I POST to the /rules endpoint for F1 with a badge B1 and a pointscale P1
Then I receive a 201 status code (rules)

Scenario: I can't add a rule with a wrong token
Given a new (rules) gamified application F2 with a badge B2 and a pointscale P2
When I POST to the /rules endpoint with wrong token for F2
Then I receive a 422 status code (rules)