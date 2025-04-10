package pages;

import org.junit.AfterClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BasePage {

    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    static {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public BasePage(WebDriver driver) {

        BasePage.driver = driver;
    }

    @AfterClass
    public static void closeBrowser() {

        driver.quit();
    }

    public void scrollToElement(String el) {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        WebElement element = Find(el);
        j.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String configFileLoad(String data) throws IOException {
        FileReader fr = new FileReader("/Users/mikeynadia/Documents/PROGRAMACION/UDEMY/IdeaProjects/LiverpoolApexTest/src/main/resources/configfiles/config.properties");
        Properties pr = new Properties();
        pr.load(fr);
        String element = pr.getProperty(data);
        return element;
    }

    public String locatorFileLoad(String data) throws IOException {
        FileReader fr = new FileReader("/Users/mikeynadia/Documents/PROGRAMACION/UDEMY/IdeaProjects/LiverpoolApexTest/src/main/resources/configfiles/locators.properties");
        Properties pr = new Properties();
        pr.load(fr);
        String element = pr.getProperty(data);
        return element;
    }

    public void navigateToWebPage(String url) throws IOException {
        String data = configFileLoad(url);
        driver.get(data);
    }

    public WebElement Find(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator) throws IOException {
        String element = locatorFileLoad(locator);
        Find(element).click();
    }

    public void write(String keysToSend, String locator) throws IOException {
        String element = locatorFileLoad(locator);
        scrollToElement(element);
        String text = configFileLoad(keysToSend);
        Find(element).sendKeys(text);
    }


    public void writePassword(String keysToSend, String locator) throws IOException {
        String element = locatorFileLoad(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement passwordBox = driver.findElement(By.xpath(element));
        js.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible'; arguments[0].focus();", passwordBox);
        wait.until(ExpectedConditions.visibilityOf(passwordBox));
        wait.until(ExpectedConditions.elementToBeClickable(passwordBox));
        Actions actions = new Actions(driver);
        actions.moveToElement(passwordBox).click().sendKeys(configFileLoad(keysToSend)).perform();
    }

    public void clearText(String locator) throws IOException {
        String element = locatorFileLoad(locator);
        Find(element).clear();
    }

    public void verifyPresenceOfElement(String locator) throws IOException {
        String el = locatorFileLoad(locator);
        WebElement we = driver.findElement(By.xpath(el));
        wait.until(ExpectedConditions.visibilityOf(we));
        if (we.isDisplayed()) {
            System.out.println("El elemento está visible.");
        } else {
            System.out.println("El elemento no está visible.");
        }
    }

    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    public void selectCheckbox(String locator) throws IOException {
        String el = locatorFileLoad(locator);
        WebElement checkbox = driver.findElement(By.xpath(el));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible'; arguments[0].focus();", checkbox);
        js.executeScript("arguments[0].click();", checkbox);
    }

    public void selectFromDropdownValue(String locator, String valueToSelect){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByValue((valueToSelect));
    }

    public void selectFromDropdownByIndex(String locator, int valueToSelect){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByIndex((valueToSelect));
    }

    public void selectFromDropdownByText(String locator, String valueToSelect){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByVisibleText((valueToSelect));
    }

    public void typeEnter(String locator) throws IOException {
        Actions actions = new Actions(driver);
        actions.sendKeys(Find(locator));
    }

    public void hoverOverElement(String locator) throws IOException {
        Actions action = new Actions(driver);
        action.moveToElement(Find(locator));
    }


    public void dobleClick(String locator) throws IOException {
        Actions action = new Actions(driver);
        action.doubleClick(Find(locator));

    }

    public void rightClick(String locator) throws IOException {
        Actions action = new Actions(driver);
        action.contextClick(Find(locator));
    }


}












