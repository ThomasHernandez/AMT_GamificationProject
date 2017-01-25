package ch.heigvd.gamification.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.GamifiedApplicationToClient;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/**
 * AMT - Gamification Project - Automated Tests
 *
 * @author Albasini Romain, Ciani Antony, Hernandez Thomas, Selimi Dardan
 */
public class ApplicationSteps {

   public NewGamifiedApplication newGamifiedApplication; //Application ajoutée
   private final DefaultApi api = new DefaultApi();
   public List<GamifiedApplicationToClient> applicationsList = new LinkedList<>(); // Liste des applications
   public List<NewGamifiedApplication> applicationsLocal = new LinkedList<>();
   private int applicationsCounter = 1;

   public String authToken = "1989";
   public String name = "GamifiedApplicationToClient 1";
   public Long id = 12L;

   public NewBadge nb = new NewBadge();
   public long nbApps = 0;
   public int statusCode = 0;
   public String token = "";

   @Given("^I have an application payload$")
   public void i_have_an_application_payload() throws Throwable {
      String randomApplicationName = "random-app-" + (applicationsCounter++) + "-" + System.currentTimeMillis();
      nbApps++;

      newGamifiedApplication = new NewGamifiedApplication();
      newGamifiedApplication.setName(randomApplicationName);
      newGamifiedApplication.setPassword("pwd");
   }

   @Given("^I have no application registered$")
   public void i_have_no_application_registered() throws Throwable {
      applicationsList.clear();
      nbApps = 0;
      assertEquals(true, applicationsList.isEmpty());
   }

   @When("^I POST it to the /registrations endpoint$")
   public void i_POST_it_to_the_registrations_endpoint() throws Throwable {
      try {
         applicationsLocal.add(newGamifiedApplication);
         ApiResponse response = api.registrationsPostWithHttpInfo(newGamifiedApplication);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^I receive a (\\d+) status code$")
   public void i_receive_a_status_code(int arg1) throws Throwable {
      assertEquals(arg1, statusCode);
   }

   @When("^I ask for a list of registered apps with a GET on the /applications endpoint$")
   public void i_ask_for_a_list_of_registered_apps_with_a_GET_on_the_registrations_endpoint() throws Throwable {
      applicationsList = api.applicationsGet();
   }

   @Then("^I see my app in the list$")
   public void i_see_my_app_in_the_list() throws Throwable {
      assertThat(applicationsList).extracting("name").contains(newGamifiedApplication.getName());
   }

   @When("^I GET to the /applications endpoint$")
   public void i_GET_to_the_applications_endpoint() throws Throwable {
      try {
         //applicationsLocal.add(newGamifiedApplication);
         ApiResponse response = api.applicationsGetWithHttpInfo();
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @Then("^a list of all applications$")
   public void a_list_of_all_applications() throws Throwable {
      api.applicationsGet();
   }

   @Given("^I already have an application registered$")
   public void i_already_have_an_application_registered() throws Throwable {
      assertNotEquals(api.applicationsGet().size(), 0);
   }

   @Then("^a list of all applications containing (\\d+) applications$")
   public void a_list_of_all_applications_containing_applications(int arg1) throws Throwable {
      assertEquals(api.applicationsGet().size(), arg1);
   }

   @When("^I GET to the /applications endpoint with /id (\\d+)$")
   public void i_GET_to_the_applications_endpoint_with_id(long arg1) throws Throwable {
      try {
         ApiResponse response = api.applicationsIdGetWithHttpInfo(arg1);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I DELETE to the /applications endpoint with /id (\\d+)$")
   public void i_DELETE_to_the_applications_endpoint_with_id(long arg1) throws Throwable {
      try {
         //applicationsLocal.add(newGamifiedApplication);
         ApiResponse response = api.applicationsIdDeleteWithHttpInfo(arg1);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I GET to the /applications endpoint with /id invalid$")
   public void i_GET_to_the_applications_endpoint_with_id_invalid() throws Throwable {
      try {
         long id = 9999;
         ApiResponse response = api.applicationsIdGetWithHttpInfo(id);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }

   @When("^I DELETE to the /applications endpoint with /id invalid$")
   public void i_DELETE_to_the_applications_endpoint_with_id_invalid() throws Throwable {
      try {
         long id = 9999;
         ApiResponse response = api.applicationsIdDeleteWithHttpInfo(id);
         statusCode = response.getStatusCode();
      } catch (ApiException e) {
         statusCode = e.getCode();
      }
   }
}
