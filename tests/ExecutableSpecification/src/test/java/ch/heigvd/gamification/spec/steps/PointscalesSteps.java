package ch.heigvd.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.AppCredentials;
import ch.heigvd.gamification.api.dto.NewPointScale;
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
public class PointscalesSteps {

   private final DefaultApi api = new DefaultApi();

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée à l'API
   private final String DEFAULT_PASSWORD = "1234";
   private final long INVALID_ID = 999999;
   private ApiResponse lastApiResponse = null;
   private int nbApps = 0;
   private int nbPointScales = 0;
   private int statusCode = 0;

   private final Map<String, NewGamifiedApplication> applications = new HashMap<>();
   private final Map<String, String> applicationsTokens = new HashMap<>();
   private final Map<String, AppCredentials> applicationsCredentials = new HashMap<>();
   private final Map<String, NewPointScale> pointscalesList = new HashMap<>();

   @Given("^a new \\(pointscales\\) gamified application (.*)$")
   public void a_new_pointscales_gamified_application_A(String applicationReference) throws Throwable {
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

   @Given("^I already have a pointscale registered$")
   public void i_already_have_a_pointscale_registered() throws Throwable {
      assertNotEquals(api.pointscalesGet().size(), 0);
   }

   @When("^I POST to the /pointscales endpoint for (.*)$")
   public void i_POST_to_the_pointscales_endpoint_for_A(String applicationReference) throws Throwable {
      NewPointScale b = new NewPointScale();
      b.setName("pointscaleB");
      b.setDescription("pointscaleDesc");
      b.unit("pointscaleUnit");
      String token = applicationsTokens.get(applicationReference);

      try {
         ApiResponse response = api.pointscalesPostWithHttpInfo(token, b);
         statusCode = response.getStatusCode();
         pointscalesList.put(applicationReference, b);
         nbPointScales++;
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I DELETE to the /pointscales endpoint with /id (\\d+)$")
   public void i_DELETE_to_the_pointscales_endpoint_with_id(long arg1) throws Throwable {
      try {
         ApiResponse response = api.pointscalesIdDeleteWithHttpInfo(INVALID_ID);
         nbPointScales--;
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I DELETE to the /pointscales endpoint with /id invalid$")
   public void i_DELETE_to_the_pointscales_endpoint_with_id_invalid() throws Throwable {
      try {
         ApiResponse response = api.pointscalesIdDeleteWithHttpInfo(INVALID_ID);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /pointscales endpoint$")
   public void i_GET_to_the_pointscales_endpoint() throws Throwable {
      try {
         ApiResponse response = api.pointscalesGetWithHttpInfo();
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /pointscales endpoint with /id (\\d+)$")
   public void i_GET_to_the_pointscales_endpoint_with_id(long arg1) throws Throwable {
      try {
         ApiResponse response = api.pointscalesIdGetWithHttpInfo(arg1);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /pointscales endpoint with /id invalid$")
   public void i_GET_to_the_pointscales_endpoint_with_id_invalid() throws Throwable {
      long id = 99999;
      try {
         ApiResponse response = api.pointscalesIdGetWithHttpInfo(id);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^a list of all pointscales$")
   public void a_list_of_all_pointscales() throws Throwable {
      api.pointscalesGet();
   }

   @Then("^I receive a (\\d+) status code \\(pointscales\\)$")
   public void i_receive_a_status_code_pointscales(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }
}
