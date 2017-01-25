package ch.heigvd.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.AppCredentials;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewRule;
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
public class RulesSteps {

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
   
   @Given("^a new \\(rules\\) gamified application (.*) with a badge (.*) and a pointscale (.*)$")
   public void a_new_rules_gamified_application_A_with_a_badge_B_and_a_pointscale_P(String applicationReference, String badgeReference, String pointscaleReference) throws Throwable {
      String randomApplicationName = "app-name-rules-" + (nbApps++) + '-' + System.currentTimeMillis();

      newGamifiedApplication = new NewGamifiedApplication();
      newGamifiedApplication.setName(randomApplicationName);
      newGamifiedApplication.setPassword(DEFAULT_PASSWORD);
      api.registrationsPost(newGamifiedApplication);

      AppCredentials ac = new AppCredentials();
      ac.setAppName(randomApplicationName);
      ac.setAppPassword(DEFAULT_PASSWORD);
      String token = api.authPost(ac);

      
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

      applications.put(applicationReference, newGamifiedApplication);
      applicationsTokens.put(applicationReference, token);
      applicationsCredentials.put(applicationReference, ac);
      badgesList.put(badgeReference,b);
      pointscalesList.put(pointscaleReference,p);
   }

   @When("^I POST to the /rules endpoint for (.*) with a badge (.*) and a pointscale (.*)$")
   public void i_POST_to_the_rules_endpoint_for_A_with_a_badge_B_and_a_pointscale_P(String applicationReference, String badgeReference, String pointscaleReference) throws Throwable {
      NewPointScale p = pointscalesList.get(pointscaleReference);
      String ps = p.getName();
      NewBadge b = badgesList.get(badgeReference);
      String bs = b.getName();
      
      
      NewRule r = new NewRule();
      r.setName("rName");
      r.setDescription("rDesc");
      r.setEventType("rEvent");
      r.setPointScaleName(ps);
      r.setBadgeName(bs);
      r.setValueToReach(3.0);
      r.setPointsToAdd(3.0); 
      
      try {
         ApiResponse response = api.rulesPostWithHttpInfo(applicationsTokens.get(applicationReference), r);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
      
   }
   
   @When("^I POST to the /rules endpoint with wrong token for (.*)$")
public void i_POST_to_the_rules_endpoint_with_wrong_token_for_A(String applicationReference) throws Throwable {
    NewRule r = new NewRule();
      r.setName("rName");
      r.setDescription("rDesc");
      r.setEventType("rEvent");
      r.setPointScaleName("rPointScale");
      r.setBadgeName("rBadge");
      r.setValueToReach(3.0);
      //r.setPointsToAdd(3.0); Méthode n'existe pas
      
      try {
         ApiResponse response = api.rulesPostWithHttpInfo(INVALID_TOKEN, r);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
}

   @Then("^I receive a (\\d+) status code \\(rules\\)$")
   public void i_receive_a_status_code_rules(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }
}
