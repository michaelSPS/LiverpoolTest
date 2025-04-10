package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class LiverpoolPage extends BasePage {

    public LiverpoolPage() {
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
