package utils;

import org.openqa.selenium.*;

import java.io.IOException;

public class CheckboxUtils extends BaseUtils {

    public CheckboxUtils(WebDriver driver) {
        super(driver);
    }

    public void clickCheckbox(String xpath) {
        WebElement checkbox = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        if (!checkbox.isSelected()) checkbox.click();
    }

    public void safeCheckCheckboxWithScroll(String xpath) {
        for (int i = 0; i < 3; i++) {
            scrollBy(500);
            try {
                WebElement checkbox = wait.until(driver -> driver.findElement(By.xpath(xpath)));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                return;
            } catch (TimeoutException ignored) {}
        }
        throw new TimeoutException("Checkbox no visible tras 3 intentos: " + xpath);
    }
}
