package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigManager;
import utils.WaitUtils;

import java.io.IOException;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    public void navigateToWebPage(String configKey) throws IOException {
        String url = ConfigManager.getConfig(configKey);
        driver.get(url);
    }

    public WebElement Find(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By by;
        if (locator.startsWith("//") || locator.startsWith("(//")) {
            by = By.xpath(locator);
        } else {
            by = By.id(locator);
        }

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        return element;
    }

    public void clickElement(String locator) throws IOException {
        String element = ConfigManager.getLocator(locator);
        WebElement webElement = Find(element);

        try {
            webElement.click();
            System.out.println("DEBUG: Click normal ejecutado");
        } catch (ElementClickInterceptedException e) {
            System.out.println("DEBUG: Click interceptado, usando click con JS");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
        }
    }

    public void write(String keysToSend, String locator) throws IOException {
        String element = ConfigManager.getLocator(locator);
        scrollToElement(element);
        String text = ConfigManager.getConfig(keysToSend);
        Find(element).sendKeys(text);
    }

    public void writePlainText(String text, String locatorKey) throws IOException {
        String xpath = ConfigManager.getLocator(locatorKey);
        scrollToElement(xpath);
        Find(xpath).sendKeys(text);
    }

    public void scrollToElementByKey(String locator) throws IOException {
        String xpath = ConfigManager.getLocator(locator);
        scrollToElement(xpath);
    }

    public void scrollToElement(String xpath) {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(xpath));
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

    public void clickUsingJS(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        sleep(500); // si prefieres algo más robusto, puedes usar waitUtils en su lugar
        js.executeScript("arguments[0].click();", element);
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

