package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class LiverpoolPage extends BasePage {

    public LiverpoolPage(WebDriver driver) {

        super(driver);
    }

    public void navigateToLiverpool(String url) throws IOException {
        navigateToWebPage(url);
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

    public void applyPriceFilterGreaterThan10000() {
        System.out.println("DEBUG: Localizando el input del filtro de precio > $10,000");

        WebElement priceInput = driver.findElement(By.id("variants.prices.sortPrice-10000-700000"));
        WebElement radioButtonWrapper = priceInput.findElement(By.xpath("./ancestor::div[contains(@class, 'm-radioButton')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioButtonWrapper);
        sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceInput);

        System.out.println("DEBUG: Clic hecho al input del precio > $10,000");

        waitForPageToReload();
    }
    public void applyBrandFilterSony() {
        System.out.println("DEBUG: Localizando el checkbox del filtro de marca SONY");

        WebElement brandCheckbox = driver.findElement(By.id("brand-SONY"));
        WebElement checkboxWrapper = brandCheckbox.findElement(By.xpath("./ancestor::div[contains(@class, 'm-checkbox')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkboxWrapper);
        sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", brandCheckbox);

        System.out.println("DEBUG: Clic hecho al checkbox de marca SONY");

        waitForPageToReload();

    }

    public void applySizeFilter55Inches() {

        System.out.println("DEBUG: Localizando el checkbox del filtro de tamaño - 55 pulgadas");

        WebElement sizeCheckbox = driver.findElement(By.id("variants.normalizedSize-55 pulgadas"));
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

//    public void verifyPageTitle(String title) throws IOException {
//        String actualTitle = driver.getTitle();
//        Assert.assertEquals(actualTitle, title);
//    }

//    public String buildUrl(String url) throws IOException {
//        FileReader fr = new FileReader("src/main/resources/configfiles/config.properties");
//        Properties pr = new Properties();
//        pr.load(fr);
//        String baseUrl = pr.getProperty("url");
//        String homeUrl = pr.getProperty(url);
//        return baseUrl + homeUrl;
//    }

//    public void getProductPrice(String locator) throws IOException {
//        String expectedPrice = configFileLoad(locator);
//        WebElement actualPrice = driver.findElement(By.xpath(locator));
//        Assert.assertEquals(actualPrice, expectedPrice);
//
//        actualPrice.getText();
//    }

//    public void validateUrl(String url) throws IOException, InterruptedException {
//        Thread.sleep(1000);
//        String expectedUrl = buildUrl(url);
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);
//    }
//    public void verifyUrl(String url) throws IOException {
//        String expectedUrl = configFileLoad(url);
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);
//    }
//
//    public void clickDropDownOption(String section, String locator) throws IOException {
//        String xpathLink = locatorFileLoad(locator);
//        String xpath = String.format(xpathLink, section);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement dropDownMenu = driver.findElement(By.xpath(xpath));
//        js.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible'; arguments[0].focus();", dropDownMenu);
//        wait.until(ExpectedConditions.visibilityOf(dropDownMenu));
//        wait.until(ExpectedConditions.elementToBeClickable(dropDownMenu)).click();
//    }
//


}
