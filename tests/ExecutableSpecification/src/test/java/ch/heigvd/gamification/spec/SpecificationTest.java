package ch.heigvd.gamification.spec;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/scenarios/", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {

    public SpecificationTest() {
    }

  /**
   * Test of main method, of class Specification.
   */
  @Test
  public void testMain() {
  }

}