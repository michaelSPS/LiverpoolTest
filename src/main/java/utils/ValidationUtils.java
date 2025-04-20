package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;


public class ValidationUtils extends BaseUtils {

    public ValidationUtils(WebDriver driver) {
        super(driver);
    }

    public void verifyPageTitle(String expected) {
        String actual = driver.getTitle();
        assertEquals(expected, actual);

    }

    public void verifyUrl(String expected) {
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }

    public void verifyPresenceOfElement(String xpath) {
        WebElement el = Find(xpath);
        wait.until(driver -> el.isDisplayed());
    }
}
