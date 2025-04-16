package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseUtils {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public BaseUtils(WebDriver driver) {
        BaseUtils.driver = driver;
        BaseUtils.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement Find(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void scrollToElement(String xpath) {
        WebElement el = Find(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
    }

    public void scrollBy(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
