package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.ConfigManager;
import java.io.IOException;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
    }
    public String getPageTitle() {
        return driver.getTitle();
    }

    public void navigateToLiverpool() throws IOException {
        navigateToWebPage("home.url");
    }

    public void navigateToLogin() throws IOException {
        navigateToWebPage("login.url");
    }

    public void typeEnterOnSearchBar() throws IOException {
        System.out.println("DEBUG: Typing enter on searchbar");

        String searchbarLocator = ConfigManager.getLocator("searchbar");
        WebElement searchbar = driver.findElement(By.xpath(searchbarLocator));
        Actions actions = new Actions(driver);
        actions.sendKeys(searchbar, Keys.ENTER).perform();

        waitUtils.waitForPageToReload();
    }

    public void clickPupUpWarrentieButton(String locatorKey) throws IOException {
        System.out.println("DEBUG: Haciendo click en el boton de garantía");

        waitUtils.verifyElementIsVisible(locatorKey);
        String xpath = ConfigManager.getLocator(locatorKey);
        Find(xpath).click();
    }

    public void clickWithJS(String locator) throws IOException {
        String element = ConfigManager.getLocator(locator);
        WebElement webElement = Find(element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",webElement);
        System.out.println("DEBUG: Click con JavaScript ejecutado");
    }

}
