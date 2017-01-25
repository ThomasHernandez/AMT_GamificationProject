package ch.heigvd.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.AppCredentials;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.NewGameEvent;
import ch.heigvd.gamification.api.dto.NewRule;
import ch.heigvd.gamification.api.dto.ApplicationUserToClient;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;

/**
 * AMT - Gamification Project - Automated Tests
 * @author Albasini Romain, Ciani Antony, Hernandez Thomas, Selimi Dardan
 */
public class MultipleEndpointsSteps {

   private final DefaultApi api = new DefaultApi();

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée à l'API
   private final String DEFAULT_PASSWORD = "1234";
   private ApiResponse lastApiResponse = null;
   private int nbApps = 0;
   private int nbPointScales = 0;
   private int statusCode = 0;
   private int nbBadges = 0;
   private Scenario scenario;
   String token2 = "";

   private final Map<String, NewGamifiedApplication> applications = new HashMap<>();
   private final Map<String, String> applicationsTokens = new HashMap<>();
   private final Map<String, AppCredentials> applicationsCredentials = new HashMap<>();
   private final Map<String, NewPointScale> pointscalesList = new HashMap<>();
   private final Map<String, NewBadge> badgesList = new HashMap<>();
   private final Map<String, NewRule> rulesList = new HashMap<>();

   @Before
   public void before(Scenario scenario){
      this.scenario = scenario;
   }
   
   @Given("^a new \\(users\\) gamified application (.*) with events for user (.*) and a badge (.*) attribution depeding on a rule (.*) and the pointscale (.*)$")
   public void a_new_users_gamified_application_A_with_events_for_user_U_and_a_badge_B_attribution_depeding_on_a_rule_R_and_the_pointscale_P(String applicationReference, String userReference, String badgeReference, String rulesReference, String pointscaleReference) throws Throwable {
      String randomApplicationName = "app-name-rules-" + (nbApps++) + '-' + System.currentTimeMillis();

      newGamifiedApplication = new NewGamifiedApplication();
      newGamifiedApplication.setName(randomApplicationName);
      newGamifiedApplication.setPassword(DEFAULT_PASSWORD);
      api.registrationsPost(newGamifiedApplication);

      AppCredentials ac = new AppCredentials();
      ac.setAppName(randomApplicationName);
      ac.setAppPassword(DEFAULT_PASSWORD);
      String token = api.authPost(ac);
      token2 = token;

      // Pointscale
      NewPointScale p = new NewPointScale();
      p.setName(pointscaleReference);
      p.setDescription("pointscaleDesc");
      p.setUnit("pointscaleUnit");
      api.pointscalesPost(token, p);

      // Badge
      NewBadge b = new NewBadge();
      b.setName(badgeReference);
      b.setDescription("badgeDesc");
      b.setImageURI("badgeURI");
      api.badgesPost(token, b);

      // Rule
      NewRule r = new NewRule();
      r.setName("rName");
      r.setDescription("rDesc");
      r.setEventType("eEventType");
      r.setPointScaleName(p.getName());
      r.setBadgeName("");
      r.setValueToReach(3.0);
      r.setPointsToAdd(3.0);

      applications.put(applicationReference, newGamifiedApplication);
      applicationsTokens.put(applicationReference, token);
      applicationsCredentials.put(applicationReference, ac);
      badgesList.put(badgeReference, b);
      pointscalesList.put(pointscaleReference, p);
      rulesList.put(rulesReference, r);
      api.rulesPost(applicationsTokens.get(applicationReference), r);
   }

   @When("^I POST (\\d+) events to the /events endpoint for (.*) for user (.*) for rule (.*)$")
   public void i_POST_events_to_the_events_endpoint_for_A_for_user_U(int arg1, String applicationReference, String userReference, String ruleReference) throws Throwable {
      NewRule r = rulesList.get(ruleReference);
      r.setBadgeName("B1");

      api.rulesPost(applicationsTokens.get(applicationReference), r);
      for (int i = 0; i < arg1; i++) {
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

   @Then("^I receive a (\\d+) status code \\(rulesTester\\)$")
   public void i_receive_a_status_code_badges(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }

   @Then("^the user (.*) received a badge$")
   public void the_user_received_a_badge(String userReference) throws Throwable {
      try {
         ApiResponse response = api.usersIdInApplicationGetWithHttpInfo(token2, userReference);
         Object resp = response.getData();
         String s = resp.toString();

         scenario.write("alkdsjf");         
         assertEquals(true, s.contains("flafla"));
         //statusCode = response.getStatusCode();

      } catch (ApiException e) {
         //statusCode = e.getCode();
      }

   }

}
