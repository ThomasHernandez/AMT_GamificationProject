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
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author romai
 */
public class AuthenticationSteps {

   private final DefaultApi api = new DefaultApi();

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée à l'API
   private final String DEFAULT_PASSWORD = "1234";
   private ApiResponse lastApiResponse = null;
   private int nbApps = 0;
   private int statusCode = 0;

   private final Map<String, NewGamifiedApplication> applications = new HashMap<>();
   private final Map<String, String> applicationsTokens = new HashMap<>();
   private final Map<String, AppCredentials> applicationsCredentials = new HashMap<>();

   @Given("^a new \\(auth\\) gamified application (.*)$")
   public void a_new_auth_gamified_application_A(String applicationReference) throws Throwable {
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

   @When("^I POST (..) to the /auth endpoint$")
   public void i_POST_A_to_the_auth_endpoint(String applicationReference) throws Throwable {
      try {
         ApiResponse response = api.authPostWithHttpInfo(applicationsCredentials.get(applicationReference));
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I POST (..) to the /auth endpoint with a wrong password$")
   public void i_POST_A_to_the_auth_endpoint_with_a_wrong_password(String applicationReference) throws Throwable {
      try {
         AppCredentials ac = new AppCredentials();
         ac.setAppName(newGamifiedApplication.getName());
         ac.setAppPassword("fake");

         ApiResponse response = api.authPostWithHttpInfo(ac);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I POST a non-existing application to the /auth endpoint$")
   public void i_POST_a_non_existing_application_to_the_auth_endpoint() throws Throwable {
      try {
         AppCredentials ac = new AppCredentials();
         ac.setAppName(newGamifiedApplication.getName()+"asldjf");
         ac.setAppPassword(applicationsTokens.get(newGamifiedApplication.getName()));

         ApiResponse response = api.authPostWithHttpInfo(ac);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^it receives a (\\d+) status code$")
   public void it_receives_a_status_code(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }

   /*@When("^I POST (.*)$ to the /auth endpoint$")
   public void i_POST_(String applicationReference)_to_the_auth_endpoint() throws Throwable {
      try {
         AppCredentials ac = new AppCredentials();
         ac.setAppName(newGamifiedApplication.getName());
         ac.setAppPassword("pwd");

         ApiResponse response = api.authPostWithHttpInfo(applicationsCredentials.get(applicationReference));
         statusCode = response.getStatusCode();
         //token = api.authPost(ac);
      } catch (ApiException e) {
         statusCode = e.getCode();
      }

   }*/
}
