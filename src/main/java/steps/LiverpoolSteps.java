package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.LiverpoolPage;

import java.io.IOException;
import java.time.Duration;

import static pages.BasePage.driver;

public class LiverpoolSteps {

    LiverpoolPage liverpool = new LiverpoolPage(driver);

    @Given("^(?:I|The Client) goes to the home$")
    public void iNavigateToLiverpoolHome() throws IOException {
        liverpool.navigateToLiverpool();
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

        System.out.println("DEBUG: Haciendo click y redirigiendo a la pagina de Smart-tv");
    }

    @Then("^(?:I|The Client) validate that the Size and Price filters are displayed")
    public void  validateFiltersDisplayed() throws IOException {
        liverpool.scrollToElementByKey("sizeFilter");
        liverpool.verifyElementIsVisible("sizeFilter");

        System.out.println("DEBUG: Verificando que aparezca el filtro de Tamaño");

        liverpool.scrollToElementByKey("priceFilter");
        liverpool.verifyElementIsVisible("priceFilter");

        System.out.println("DEBUG: Verificando que aparezca el filtro de Precios");
    }

    @When("^(?:I|The Client) filters the results by size: 55 inches, price: > 10,000, brand: sony")
    public void filterResultsByMultipleOptions() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        liverpool.scrollBy(1000);
        liverpool.sleep(1000);

        // ---  FILTRO DE PRECIO ----
        System.out.println("DEBUG: Haciendo scroll hacia la sección de Precios");
        liverpool.scrollToElementByText("Precios");
        liverpool.sleep(1000);

        System.out.println("DEBUG: Forzando visibilidad del filtro 'Mas de $10,000'");
        liverpool.applyPriceFilterGreaterThan10000();
        liverpool.sleep(1000);

        // ---  FILTRO DE MARCA ----

        liverpool.scrollBy(1000);
        System.out.println("DEBUG: Haciendo scroll hacia la sección de Marcas");
        liverpool.scrollToElementByText("Marcas");
        liverpool.sleep(800);

        System.out.println("DEBUG: Aplicando filtro de marca - Sony");

        liverpool.scrollToElementByText("Marcas");
        liverpool.sleep(800);
        liverpool.scrollToElementByKey("brandSearchInput");
        liverpool.sleep(500);
        liverpool.writePlainText("sony", "brandSearchInput");
        liverpool.sleep(800);
        liverpool.applyBrandFilterSony();
        liverpool.sleep(1000);

        System.out.println("DEBUG: Filtro de marcas aplicado correctamente");

        // ---  FILTRO DE TAMAÑO ----

        liverpool.scrollBy(1000);
        System.out.println("DEBUG: Haciendo scroll hacia la sección de Tamaño");
        liverpool.scrollToElementByText("Tamaño");
        liverpool.sleep(800);

        System.out.println("DEBUG: Aplicando filtro de tamaño - 55 pulgadas");

        liverpool.applySizeFilter55Inches();
        liverpool.sleep(1000);

        System.out.println("DEBUG: Filtro de tamaño aplicado correctamente");

        System.out.println("Todos los filtros aplicados - Sony, > $10,000, 55 pulgadas");
    }

    @Then("^(?:I|The Client) validate the results count$")
    public void validateResultCount() throws IOException {
        liverpool.sleep(1000);
        int resultCount = liverpool.getProductResultCount();

        if (resultCount > 0) {
            System.out.println("Se encontraron " + resultCount + " productos tras aplicar los filtros.");
        } else {
            throw new AssertionError("No se encontraron productros");
        }
    }

}