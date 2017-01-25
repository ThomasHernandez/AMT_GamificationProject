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
 *
 * @author romai
 */
public class UsersSteps {

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

   @Given("^a new \\(users\\) gamified application (.*) with event for user (.*)$")
   public void a_new_users_gamified_application_A_with_event_for_user_U(String applicationReference, String userReference) throws Throwable {
      String randomApplicationName = "app-name-" + (nbApps++) + '-' + System.currentTimeMillis();

      newGamifiedApplication = new NewGamifiedApplication();
      newGamifiedApplication.setName(randomApplicationName);
      newGamifiedApplication.setPassword(DEFAULT_PASSWORD);
      api.registrationsPost(newGamifiedApplication);

      AppCredentials ac = new AppCredentials();
      ac.setAppName(randomApplicationName);
      ac.setAppPassword(DEFAULT_PASSWORD);
      String token = api.authPost(ac);
      
      // Event
      NewGameEvent ge = new NewGameEvent();
      ge.setAppUserId(userReference);
      ge.setEventType("eEventType");
      api.eventsPost(token, ge);

      applications.put(applicationReference, newGamifiedApplication);
      applicationsTokens.put(applicationReference, token);
      applicationsCredentials.put(applicationReference, ac);
   }

   @When("^I GET to the /users endpoint for (.*) for user (.*)$")
   public void i_GET_to_the_users_endpoint_for_A_for_user_U(String applicationReference, String userReference) throws Throwable {
      String token = applicationsTokens.get(applicationReference);
      
      try {
         ApiResponse response = api.usersIdInApplicationGetWithHttpInfo(token, userReference);
         statusCode = response.getStatusCode();

      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^I receive a (\\d+) status code \\(users\\)$")
   public void i_receive_a_status_code_users(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }
}
