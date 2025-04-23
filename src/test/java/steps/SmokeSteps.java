package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.HomePage;

import static steps.Hooks.driver;

public class SmokeSteps {

    HomePage homePage;

    public SmokeSteps() {
        homePage = new HomePage(driver);
    }

    @Given("The user navigates to the homepage")
    public void theUserNavigatesToTheHomepage() {
        driver.get("https://www.liverpool.com.mx/");
    }

    @Then("The homepage should be displayed")
    public void theHomepageShouldBeDisplayed() {
        Assert.assertTrue("Page title should contain 'Liverpool'", homePage.getPageTitle().toLowerCase().contains("liverpool"));
    }
}
