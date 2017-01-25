package ch.heigvd.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.AppCredentials;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewRule;
import ch.heigvd.gamification.api.dto.NewGameEvent;
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
 * AMT - Gamification Project - Automated Tests
 *
 * @author Albasini Romain, Ciani Antony, Hernandez Thomas, Selimi Dardan
 */
public class EventsSteps {

   private final DefaultApi api = new DefaultApi();

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée à l'API
   private final String DEFAULT_PASSWORD = "1234";
   private final String INVALID_TOKEN = "asdf";
   private final long INVALID_ID = 999999;
   private ApiResponse lastApiResponse = null;
   private int nbApps = 0;
   private int nbPointScales = 0;
   private int statusCode = 0;

   private final Map<String, NewGamifiedApplication> applications = new HashMap<>();
   private final Map<String, String> applicationsTokens = new HashMap<>();
   private final Map<String, AppCredentials> applicationsCredentials = new HashMap<>();
   private final Map<String, NewPointScale> pointscalesList = new HashMap<>();
   private final Map<String, NewBadge> badgesList = new HashMap<>();

   @Given("^a new \\(events\\) gamified application (.*)$")
   public void a_new_events_gamified_application_A(String applicationReference) throws Throwable {
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

   @When("^I POST to the /event endpoint for (.*) for user (.*)$")
   public void i_POST_to_the_event_endpoint_for_A_for_user_U(String applicationReference, String userReference) throws Throwable {
      NewGameEvent ge = new NewGameEvent();
      ge.setAppUserId(userReference);
      ge.setEventType("eEventType");

      String token = applicationsTokens.get(applicationReference);

      try {
         ApiResponse response = api.eventsPostWithHttpInfo(token, ge);
         statusCode = response.getStatusCode();

      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I POST (\\d+) times to the /event endpoint for (.*) for user (.*)$")
   public void i_POST_times_to_the_event_endpoint_for_A_for_user_U(int nbEvents, String applicationReference, String userReference) throws Throwable {
      for (int i = 0; i < nbEvents; ++i) {
         NewGameEvent ge = new NewGameEvent();
         ge.setAppUserId(userReference);
         ge.setEventType("eEventType");
         String token = applicationsTokens.get(applicationReference);
         try {
            ApiResponse response = api.eventsPostWithHttpInfo(token, ge);
            statusCode = response.getStatusCode();
         } catch (ApiException e) {
            statusCode = e.getCode();
         }
      }
   }

   @When("^I POST to the /event endpoint of a non-existing application for user (.*)$")
   public void i_POST_to_the_event_endpoint_of_a_non_existing_application_for_user_U(String userReference) throws Throwable {
      NewGameEvent ge = new NewGameEvent();
      ge.setAppUserId(userReference);
      ge.setEventType("eEventType");

      String token = "wrong";

      try {
         ApiResponse response = api.eventsPostWithHttpInfo(token, ge);
         statusCode = response.getStatusCode();

      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^I receive a (\\d+) status code \\(events\\)$")
   public void i_receive_a_status_code_events(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }
}
