package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ValidationUtils extends BaseUtils {

    public ValidationUtils(WebDriver driver) {
        super(driver);
    }

    public void verifyPageTitle(String expected) {
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    public void verifyUrl(String expected) {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    public void verifyPresenceOfElement(String xpath) {
        WebElement el = Find(xpath);
        wait.until(driver -> el.isDisplayed());
    }
}
