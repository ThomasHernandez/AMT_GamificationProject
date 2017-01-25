package ch.heigvd.gamification.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.LinkedList;
import java.util.List;
import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.GamifiedApplicationToClient;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * AMT - Gamification Project - Automated Tests
 *
 * @author Albasini Romain, Ciani Antony, Hernandez Thomas, Selimi Dardan
 */
public class ConcurrencyTestingSteps {

   private final DefaultApi api = new DefaultApi();
   public List<GamifiedApplicationToClient> applicationsList = new LinkedList<>(); // Liste des applications
   public LinkedList<NewGamifiedApplication> applicationsLocal = new LinkedList<>();
   private int applicationsCounter = 1;
   int nbApps = 0;
   public List<Integer> statusCodes = new LinkedList<>();

   @Given("^I have many application payloads$")
   public void i_have_many_application_payloads() throws Throwable {
      String randomApplicationName;
      for (int i = 0; i < 100; ++i) {
         randomApplicationName = "random-app-" + i;
         NewGamifiedApplication newGamifiedApplication = new NewGamifiedApplication();
         newGamifiedApplication.setName(randomApplicationName);
         newGamifiedApplication.setPassword("pwd");
         applicationsLocal.add(newGamifiedApplication);
      }
   }

   @When("^I POST them to the /registrations endpoint$")
   public void i_POST_them_to_the_registrations_endpoint() throws Throwable {
      for (int i = 0; i < applicationsLocal.size(); ++i) {
         try {
            ApiResponse response;
            response = api.registrationsPostWithHttpInfo(applicationsLocal.get(i));
            statusCodes.add(response.getStatusCode());
         } catch (ApiException e) {
            statusCodes.add(e.getCode());
         }
      }
   }

   @Then("^I receive a (\\d+) status code for each of them \\(concurrency\\)$")
   public void i_receive_a_status_code_for_each_od_them(int arg1) throws Throwable {
      for (int i = 0; i < statusCodes.size(); ++i) {
         assertEquals(arg1, statusCodes.get(i).intValue());
      }
   }

   @When("^I ask for a list of the registered applications with a GET on the /applications endpoint$")
   public void i_ask_for_a_list_of_the_registered_applications_with_a_GET_on_the_applications_endpoint() throws Throwable {
      applicationsList = api.applicationsGet();
   }

   @Then("^I see my applications in the list$")
   public void i_see_my_applications_in_the_list() throws Throwable {
      for (int i = 0; i < applicationsLocal.size(); ++i) {
         assertThat(applicationsList).extracting("name").contains(applicationsLocal.get(i).getName());
      }
   }
}
