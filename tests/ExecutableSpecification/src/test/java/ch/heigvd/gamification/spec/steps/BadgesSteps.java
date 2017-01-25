/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.AppCredentials;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * @author romai
 */
public class BadgesSteps {

   private final DefaultApi api = new DefaultApi();

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée à l'API
   private final String DEFAULT_PASSWORD = "1234";
   private final long INVALID_ID = 999999;
   private ApiResponse lastApiResponse = null;
   private int nbApps = 0;
   private int nbBadges = 0;
   private int statusCode = 0;

   private final Map<String, NewGamifiedApplication> applications = new HashMap<>();
   private final Map<String, String> applicationsTokens = new HashMap<>();
   private final Map<String, AppCredentials> applicationsCredentials = new HashMap<>();
   private final Map<String, NewBadge> badgesList = new HashMap<>();

   @Given("^a new \\(badges\\) gamified application (.*)$")
   public void a_new_badges_gamified_application_A(String applicationReference) throws Throwable {
      String randomApplicationName = "app-name-" + (nbApps++) + '-' + System.currentTimeMillis();

      newGamifiedApplication = new NewGamifiedApplication();
      newGamifiedApplication.setName(randomApplicationName);
      newGamifiedApplication.setPassword(DEFAULT_PASSWORD);
      api.registrationsPost(newGamifiedApplication);

      AppCredentials ac = new AppCredentials();
      ac.setAppName(randomApplicationName);
      ac.setAppPassword(DEFAULT_PASSWORD);
      String token = api.authPost(ac);

      applications.put(applicationReference, newGamifiedApplication);
      applicationsTokens.put(applicationReference, token);
      applicationsCredentials.put(applicationReference, ac);
   }

   @Given("^I already have a badge registered$")
   public void i_already_have_a_badge_registered() throws Throwable {
      assertNotEquals(api.badgesGet().size(), 0);
   }

   @When("^I POST to the /badges endpoint for (.*)$")
   public void i_POST_to_the_badges_endpoint_for_A(String applicationReference) throws Throwable {
      NewBadge b = new NewBadge();
      b.setName("badgeB");
      b.setDescription("badgeDesc");
      b.setImageURI("badgeURI");
      String token = applicationsTokens.get(applicationReference);

      try {
         ApiResponse response = api.badgesPostWithHttpInfo(token, b);
         statusCode = response.getStatusCode();
         badgesList.put(applicationReference, b);
         nbBadges++;
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I DELETE to the /badges endpoint with /id (\\d+)$")
   public void i_DELETE_to_the_badges_endpoint_with_id(long arg1) throws Throwable {
      try {
         ApiResponse response = api.badgesIdDeleteWithHttpInfo(INVALID_ID);
         nbBadges--;
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I DELETE to the /badges endpoint with /id invalid$")
   public void i_DELETE_to_the_badges_endpoint_with_id_invalid() throws Throwable {
      try {
         ApiResponse response = api.badgesIdDeleteWithHttpInfo(INVALID_ID);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /badges endpoint$")
   public void i_GET_to_the_badges_endpoint() throws Throwable {
      try {
         ApiResponse response = api.badgesGetWithHttpInfo();
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /badges endpoint with /id (\\d+)$")
   public void i_GET_to_the_badges_endpoint_with_id(long arg1) throws Throwable {
      try {
         ApiResponse response = api.badgesIdGetWithHttpInfo(arg1);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /badges endpoint with /id invalid$")
   public void i_GET_to_the_badges_endpoint_with_id_invalid() throws Throwable {
      long id = 99999;
      try {
         ApiResponse response = api.badgesIdGetWithHttpInfo(id);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^a list of all badges$")
   public void a_list_of_all_badges() throws Throwable {
      api.badgesGet();
   }

   @Then("^I receive a (\\d+) status code \\(badges\\)$")
   public void i_receive_a_status_code_badges(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }
}
