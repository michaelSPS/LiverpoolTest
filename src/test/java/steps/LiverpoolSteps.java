package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.FilterPage;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.WaitUtils;

import java.io.IOException;
import java.time.Duration;

import static steps.Hooks.driver;

public class LiverpoolSteps {

    HomePage homePage;
    SearchResultsPage resultsPage;
    FilterPage filterPage;

    public LiverpoolSteps() {
        homePage = new HomePage(driver);
        resultsPage = new SearchResultsPage(driver);
        filterPage = new FilterPage(driver);
    }

    @Given("^(?:I|The Client) navigate to (.+)$")
    public void iNavigateToLiverpoolHome(String configKey) throws IOException {
        homePage.navigateToWebPage(configKey);
        System.out.println("✅ PASÓ");
    }

    @When("^(?:I|The Client) write (.+) in the (.+)$")
    public void writeText(String keysToSend, String locator) throws IOException {
        homePage.write(keysToSend, locator);
    }

    @And("^(?:I|The Client) type ENTER$")
    public void iTypeEnter() throws IOException {
        homePage.typeEnterOnSearchBar();
    }

    @Then("^(?:I|The Client) verify that the results displayed include games for PlayStation 5 and PlayStation consoles$")
    public void verifyResults() throws IOException {
        boolean resultsContainPlayStation = resultsPage.verifyPlayStationImages();

        if (!resultsContainPlayStation) {
            throw new AssertionError("The results do not include PlayStation 5 or PlayStation products.");
        }
    }

    @And("^(?:I|The Client) selects? a (.+) in the results listed$")
    public void clickConsoles(String locator) throws IOException {
        homePage.clickElement(locator);
    }

    @And("^(?:I|The Client) selects? a (.+) in the results$")
    public void clickPlaystation5(String locator) throws IOException {
        homePage.clickElement(locator);
    }

    @And("^(?:I|The Client) clicks? on (.+) add to cart$")
    public void clickAddCart(String locator) throws IOException {
        homePage.clickElement(locator);
    }

    @And("^(?:I|The Client) clicks? add (.+)$")
    public void addWarranty(String locator) throws IOException {
        homePage.clickElement(locator);
    }

    @And("^(?:I|The Client) goes? to see the (.+)$")
    public void shoppingCart(String locator) throws IOException {
        System.out.println("DEBUG: Haciendo click en el boton de ir al carrito de compras");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop")));
            System.out.println("DEBUG: Esperando que desaparezca el modal...");
        } catch (TimeoutException e) {
            System.out.println("DEBUG: No se detectó modal, continuando...");
        }

        try {
            homePage.clickElement(locator);
        } catch (ElementClickInterceptedException e) {
            System.out.println("DEBUG: Click interceptado, intentando con JS...");

            homePage.clickWithJS(locator);  // Esta función la agregamos abajo
        }

        homePage.sleep(1000);
    }

    @Then("^(?:I|The Client) must be able to see the playstation 5 on the shopping cart")
    public void verifyProductInShoppingCart() throws IOException {
        CartPage cartPage = new CartPage(driver);
        boolean isProductPresent = cartPage.verifyProductInCart("PS5");
        Assert.assertTrue("El producto 'PlayStation 5' no se encontró en el carrito", isProductPresent);
    }

    @When("^(?:I|The Client) search for (.+) in the (.+)$")
    public void searchForSmartTv(String keysToSend, String locator) throws IOException {
        homePage.write(keysToSend, locator);
    }

    @And("^(?:I|The Client) type ENTER on searchbar$")
    public void typeEnter() throws IOException {
        homePage.typeEnterOnSearchBar();

        System.out.println("DEBUG: Haciendo click y redirigiendo a la pagina de Smart-tv");
    }

    @Then("^(?:I|The Client) validate that the Size and Price filters are displayed")
    public void  validateFiltersDisplayed() throws IOException {
        homePage.scrollToElementByKey("sizeFilter");
        WaitUtils waitUtils = new WaitUtils(driver, 10);
        waitUtils.verifyElementIsVisible("sizeFilter");

        System.out.println("DEBUG: Verificando que aparezca el filtro de Tamaño");

        homePage.scrollToElementByKey("priceFilter");
        waitUtils.verifyElementIsVisible("priceFilter");

        System.out.println("DEBUG: Verificando que aparezca el filtro de Precios");
    }

    @When("^(?:I|The Client) filters the results by size: 55 inches, price: > 10,000, brand: sony")
    public void filterResultsByMultipleOptions() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        homePage.scrollBy(1000);
        homePage.sleep(1000);

        // ---  FILTRO DE PRECIO ----
        System.out.println("DEBUG: Haciendo scroll hacia la sección de Precios");
        homePage.scrollToElementByText("Precios");
        homePage.sleep(1000);

        System.out.println("DEBUG: Forzando visibilidad del filtro 'Mas de $10,000'");
        filterPage.applyPriceFilterGreaterThan10000();
        homePage.sleep(1000);

        // ---  FILTRO DE MARCA ----

        homePage.scrollBy(1000);
        System.out.println("DEBUG: Haciendo scroll hacia la sección de Marcas");
        homePage.scrollToElementByText("Marcas");
        homePage.sleep(800);

        System.out.println("DEBUG: Aplicando filtro de marca - Sony");

        homePage.scrollToElementByText("Marcas");
        homePage.sleep(800);
        homePage.scrollToElementByKey("brandSearchInput");
        homePage.sleep(500);
        homePage.writePlainText("sony", "brandSearchInput");
        homePage.sleep(800);
        filterPage.applyBrandFilterSony();
        homePage.sleep(1000);

        System.out.println("DEBUG: Filtro de marcas aplicado correctamente");

        // ---  FILTRO DE TAMAÑO ----

        homePage.scrollBy(1000);
        System.out.println("DEBUG: Haciendo scroll hacia la sección de Tamaño");
        homePage.scrollToElementByText("Tamaño");
        homePage.sleep(800);

        System.out.println("DEBUG: Aplicando filtro de tamaño - 55 pulgadas");

        filterPage.applySizeFilter55Inches();
        homePage.sleep(1000);

        System.out.println("DEBUG: Filtro de tamaño aplicado correctamente");

        System.out.println("Todos los filtros aplicados - Sony, > $10,000, 55 pulgadas");
    }

    @Then("^(?:I|The Client) validate the results count$")
    public void validateResultCount() throws IOException {
        homePage.sleep(1000);
        int resultCount = resultsPage.getProductResultCount();

        if (resultCount > 0) {
            System.out.println("Se encontraron " + resultCount + " productos tras aplicar los filtros.");
        } else {
            throw new AssertionError("No se encontraron productros");
        }
    }

}