package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LiverpoolPage;

import java.io.IOException;

public class LiverpoolSteps {

    LiverpoolPage liverpool = new LiverpoolPage();

    @Given("^(?:I|The Client) goes to the (.+)$")
    public void iNavigateToLiverpoolHome(String url) throws IOException {
        liverpool.navigateToLiverpool(url);
    }

    @And("^(?:I|The Client) write (.+) in the (.+)$")
    public void writeText(String keysToSend, String locator) throws IOException {
        liverpool.write(keysToSend, locator);
    }

    @And("^(?:I|The Client) type ENTER$")
    public void iTypeEnter() throws IOException {
        liverpool.typeEnterOnSearchBar();
    }

    @Then("^(?:I|The Client) verify that the results displayed include games for PlayStation 5 and PlayStation consoles$")
    public void verifyResults() throws IOException {
        boolean resultsContainPlayStation = liverpool.verifyPlayStationImages();

        if (!resultsContainPlayStation) {
            throw new AssertionError("The results do not include PlayStation 5 or PlayStation products.");
        }
    }

    @And("^(?:I|The Client) selects? a (.+) in the results listed$")
    public void clickConsoles(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

    @And("^(?:I|The Client) selects? a (.+) in the results$")
    public void clickPlaystation5(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

    @Then("^(?:I|The Client) clicks? on (.+) add to cart$")
    public void clickAddCart(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

    @And("^(?:I|The Client) clicks? on the (.+) add warranty$")
    public void addWarranty(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

    @And("^(?:I|The Client) goes? to see the (.+)$")
    public void shoppingCart(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

//    @When("^(?:I|The Client) looks? at the (.+)$")
//    public void lookAtThePrice(String locator) throws IOException {
//        liverpool.getProductPrice(locator);
//    }
//
//    @Then("^(?:I|The Client) validate that the (.+) of the product is correct")
//    public void validatePrice() throws IOException {
//    }
//
//    @Then("^(?:I|The Client) validate that the title of the page is (.+)$")
//    public void validateTitle(String title) throws IOException {
//        liverpool.verifyPageTitle(title);
//
//    }

//    @When("^El usuario hace click en el botón de (.+)$")
//    public void clickButton(String locator) throws IOException, InterruptedException {
//        liverpool.clickElement(locator);
//    }
//
//    @Then("^El usuario es dirigido a la página con el título de (.+)$")
//    public void verifyAccountsTitle(String title) throws IOException {
//        liverpool.verifyPageTitle(title);
//    }

}
