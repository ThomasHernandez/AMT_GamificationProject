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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * AMT - Gamification Project - Automated Tests
 *
 * @author Albasini Romain, Ciani Antony, Hernandez Thomas, Selimi Dardan
 */
public class EventsConcurrencySteps {

   private final DefaultApi api = new DefaultApi();

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée à l'API
   private final String DEFAULT_PASSWORD = "1234";
   private final String INVALID_TOKEN = "asdf";
   private final long INVALID_ID = 999999;
   private ApiResponse lastApiResponse = null;
   private int nbApps = 0;
   private int nbPointScales = 0;
   private List<Integer> statusCodes = new LinkedList<>();

   private final Map<String, NewGamifiedApplication> applications = new HashMap<>();
   private final Map<String, String> applicationsTokens = new HashMap<>();
   private final Map<String, AppCredentials> applicationsCredentials = new HashMap<>();
   private final Map<String, NewPointScale> pointscalesList = new HashMap<>();
   private final Map<String, NewBadge> badgesList = new HashMap<>();

   @Given("^a new \\(eventsConcurrency\\) gamified application A(.*) with many events$")
   public void a_new_eventsConcurrency_gamified_application_A_with_many_events(String applicationReference) throws Throwable {
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

   @When("^I POST them to the /event endpoint for A(.*) for user U(.*)$")
   public void i_POST_them_to_the_event_endpoint_for_A_for_user_U(String applicationReference, String userReference) throws Throwable {
      for (int i = 0; i < 100; i++) {
         NewGameEvent ge = new NewGameEvent();
         ge.setAppUserId(userReference);
         ge.setEventType("eEventType");

         String token = applicationsTokens.get(applicationReference);

         try {
            ApiResponse response = api.eventsPostWithHttpInfo(token, ge);
            statusCodes.add(response.getStatusCode());
         } catch (ApiException e) {
            statusCodes.add(e.getCode());
         }
      }
   }

   @Then("^I receive a (\\d+) status code for each of them$")
   public void i_receive_a_status_code_for_each_of_them(int arg1) throws Throwable {
      for (int i = 0; i < 100; ++i) {
         assertEquals(Integer.valueOf(arg1), Integer.valueOf(statusCodes.get(i)));
      }

   }
}
