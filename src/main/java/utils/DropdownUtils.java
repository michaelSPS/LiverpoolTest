package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtils extends BaseUtils {

    public DropdownUtils(WebDriver driver) {
        super(driver);
    }

    public void selectByValue(String locator, String value) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(value);
    }

    public void selectByIndex(String locator, int index) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(index);
    }

    public void selectByText(String locator, String text) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByVisibleText(text);
    }

    public int dropdownSize(String locator) {
        Select dropdown = new Select(Find(locator));
        return dropdown.getOptions().size();
    }
}
