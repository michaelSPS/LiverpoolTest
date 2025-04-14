package steps;

import io.cucumber.java.be.I;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.LiverpoolPage;

import java.io.IOException;

import static pages.BasePage.driver;

public class LiverpoolSteps {

    LiverpoolPage liverpool = new LiverpoolPage();

    @Given("^(?:I|The Client) goes to the (.+)$")
    public void iNavigateToLiverpoolHome(String url) throws IOException {
        liverpool.navigateToLiverpool(url);
    }

    @When("^(?:I|The Client) write (.+) in the (.+)$")
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

    @And("^(?:I|The Client) clicks? on (.+) add to cart$")
    public void clickAddCart(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

    @And("^(?:I|The Client) clicks? add (.+)$")
    public void addWarrentie(String locator) throws IOException {
        liverpool.clickElement(locator);
    }

    @And("^(?:I|The Client) goes? to see the (.+)$")
    public void shoppingCart(String locator) throws IOException {
        liverpool.clickPupUpWarrentieButton(locator);
    }

    @Then("^(?:I|The Client) must be able to see the playstation 5 on the shopping cart")
    public void verifyProductInShoppingCart() throws IOException {
        CartPage cartPage = new CartPage(driver);
        boolean isProductPresent = cartPage.verifyProductInCart("PS5");
        Assert.assertTrue("El producto 'PlayStation 5' no se encontró en el carrito", isProductPresent);
    }

    @When("^(?:I|The Client) search for (.+) in the (.+)$")
    public void searchForSmartTv(String keysToSend, String locator) throws IOException {
        liverpool.write(keysToSend, locator);
    }

    @And("^(?:I|The Client) type ENTER on searchbar$")
    public void typeEnter() throws IOException {
        liverpool.typeEnterOnSearchBar();
    }

    @Then("^(?:I|The Client) validate that the Size and Price filters are displayed")
    public void  validateFiltersDisplayed() throws IOException {
        liverpool.scrollToElementByKey("sizeFilter");
        liverpool.verifyElementIsVisible("sizeFilter");

        liverpool.scrollToElementByKey("priceFilter");
        liverpool.verifyElementIsVisible("priceFilter");
    }

    @When("^(?:I|The Client) filters the results by size: 55 inches, price: > 10,000, brand: sony")
    public void filterResultsByMultipleOptions() throws IOException {
        liverpool.scrollToElementByKey("sizeOption55");
        liverpool.clickElement("sizeOption55");

        liverpool.scrollToElementByKey("priceOption10000");
        liverpool.clickElement("priceOption10000");

        liverpool.scrollToElementByKey("brandSony");
        liverpool.clickElement("brandSony");
    }

}