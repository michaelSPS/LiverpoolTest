package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public void verifyElementIsVisible(String locator) throws IOException {
        String element = ConfigManager.getLocator(locator);
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
    public void waitForProductList(String xpath) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

}
