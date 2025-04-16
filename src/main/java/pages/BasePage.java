package pages;

import org.junit.AfterClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    static {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    public BasePage(WebDriver driver) {

        BasePage.driver = driver;
    }

    @AfterClass
    public static void closeBrowser() {

        driver.quit();
    }

    public String configFileLoad(String data) throws IOException {
        FileReader fr = new FileReader("/Users/mikeynadia/Documents/PROGRAMACION/UDEMY/IdeaProjects/LiverpoolApexTest/src/main/resources/configfiles/config.properties");
        Properties pr = new Properties();
        pr.load(fr);
        String element = pr.getProperty(data);
        if (element == null) {
            throw new IllegalArgumentException("❌ Locator key '" + data + "' not found in locators.properties");
        }
        return element;
    }

    public String locatorFileLoad(String data) throws IOException {
        FileReader fr = new FileReader("/Users/mikeynadia/Documents/PROGRAMACION/UDEMY/IdeaProjects/LiverpoolApexTest/src/main/resources/configfiles/locators.properties");
        Properties pr = new Properties();
        pr.load(fr);
        String element = pr.getProperty(data);
        if (element == null) {
            throw new IllegalArgumentException("❌ Locator key '" + data + "' not found in locators.properties");
        }
        return element;
    }

    public void navigateToWebPage(String configKey) throws IOException {
        String url = configFileLoad(configKey);
        driver.get(url);
    }

    public WebElement Find(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator) throws IOException {
        String element = locatorFileLoad(locator);
        Find(element).click();

        System.out.println("DEBUG: Haciendo click en el elmento");
    }


    public void write(String keysToSend, String locator) throws IOException {
        String element = locatorFileLoad(locator);
        scrollToElement(element);
        String text = configFileLoad(keysToSend);
        Find(element).sendKeys(text);
    }

    public void writePlainText(String text, String locatorKey) throws IOException {
        String xpath = locatorFileLoad(locatorKey);
        scrollToElement(xpath);
        Find(xpath).sendKeys(text);
    }

    public void scrollToElementByKey(String locator) throws IOException {
        String xpath = locatorFileLoad(locator);
        scrollToElement(xpath);
    }

    public void scrollToElement(String xpath) {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        j.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollBy(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
        sleep(1000);
    }

    public void scrollToElementByText(String visibleText) {
        By labelLocator = By.xpath("//label[normalize-space()='" + visibleText + "']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(labelLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void verifyElementIsVisible(String locator) throws IOException {
        String element = locatorFileLoad(locator);
        System.out.println("DEBUG: Buscando elemento con XPath: " + element);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(element)));
    }

    public void waitForPageToReload() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-spinner")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));
        } catch (TimeoutException e) {
            System.out.println("DEBUG: Timeout al esperar la carga de la página");
        }
    }

}












