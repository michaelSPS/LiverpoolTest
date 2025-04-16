package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;


public class LiverpoolPage extends BasePage {

    public LiverpoolPage(WebDriver driver) {

        super(driver);
    }

    public void navigateToLiverpool() throws IOException {
        navigateToWebPage("home.url");
    }

    public void navigateToLogin() throws IOException {
        navigateToWebPage("login.url");
    }

    public void typeEnterOnSearchBar() throws IOException {
        String searchbarLocator = locatorFileLoad("searchbar");
        WebElement searchbar = driver.findElement(By.xpath(searchbarLocator));
        Actions actions = new Actions(driver);
        actions.sendKeys(searchbar, Keys.ENTER).perform();
    }

    public boolean verifyPlayStationImages() throws IOException {
        String resultsLocator = locatorFileLoad("searchResults");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(resultsLocator)));
        List<WebElement> images = driver.findElements(By.xpath(resultsLocator));
        String[] searchKeywords = {"PlayStation 5", "PlayStation", "Consolas"};

        for (WebElement image : images) {
            String altText = Objects.requireNonNull(image.getDomAttribute("alt")).toLowerCase();


            for (String keyword : searchKeywords) {
                if (altText.contains(keyword.toLowerCase())) {
                    System.out.println("Image found with keyword: " + altText); //
                    return true;
                }
            }
        }
        System.out.println("No PlayStation images found.");
        return false;
    }

    public void clickPupUpWarrentieButton(String locator) throws IOException {
        String pupUpButtonXpath = locatorFileLoad(locator);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(pupUpButtonXpath)));
        Find(pupUpButtonXpath).click();
    }

    public void applyPriceFilterGreaterThan10000() throws IOException {
        System.out.println("DEBUG: Localizando el input del filtro de precio > $10,000");

        String xpath = locatorFileLoad("filter.price.10000plus");
        WebElement priceInput = driver.findElement(By.xpath(xpath));
        WebElement radioButtonWrapper = priceInput.findElement(By.xpath("./ancestor::div[contains(@class, 'm-radioButton')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioButtonWrapper);
        sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceInput);

        System.out.println("DEBUG: Clic hecho al input del precio > $10,000");

        waitForPageToReload();
    }
    public void applyBrandFilterSony() throws IOException {
        System.out.println("DEBUG: Localizando el checkbox del filtro de marca SONY");

        String xpath = locatorFileLoad("filter.brand.sony");
        WebElement brandCheckbox = driver.findElement(By.xpath(xpath));
        WebElement checkboxWrapper = brandCheckbox.findElement(By.xpath("./ancestor::div[contains(@class, 'm-checkbox')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkboxWrapper);
        sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", brandCheckbox);

        System.out.println("DEBUG: Clic hecho al checkbox de marca SONY");

        waitForPageToReload();

    }

    public void applySizeFilter55Inches() throws IOException {

        System.out.println("DEBUG: Localizando el checkbox del filtro de tamaño - 55 pulgadas");

        String xpath = locatorFileLoad("filter.size.55");
        WebElement sizeCheckbox = driver.findElement(By.xpath(xpath));
        WebElement checkboxWrapper = sizeCheckbox.findElement(By.xpath("./ancestor::div[contains(@class, 'm-checkbox')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkboxWrapper);
        sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sizeCheckbox);

        System.out.println("DEBUG: Click hecho al checkbox de tamaño - 55 pulgadas");

        waitForPageToReload();
    }

    public int getProductResultCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[contains(@class, 'm-product__listingPlp')]/li[contains(@class, 'm-product__card')]")));
        List<WebElement> productResults = driver.findElements(By.xpath("//ul[contains(@class, 'm-product__listingPlp')]/li[contains(@class, 'm-product__card')]"));
        return productResults.size();

    }

}
