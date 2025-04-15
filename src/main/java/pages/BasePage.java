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

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

    public void clickElementByJS(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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

    public void clickCheckbox(String locatorKey) throws IOException {
        String xpath = locatorFileLoad(locatorKey);
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        if (!checkbox.isSelected()) {
            checkbox.click();
        } else {
            System.out.println("DEBUG: Checkbox '" + locatorKey + "' ya estaba seleccionado.");
        }
    }

    public void checkIfCheckboxVisibleAndClick(String locatorKey) throws IOException {
        String xpath = locatorFileLoad(locatorKey);

        try {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            if (!checkbox.isSelected()) {
                System.out.println("DEBUG: Haciendo clic en el checkbox: " + locatorKey);
                checkbox.click();
            } else {
                System.out.println("DEBUG: El checkbox '" + locatorKey + "' ya está seleccionado.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("DEBUG: No se encontró el checkbox para: " + locatorKey + ". Continuando.");
        } catch (TimeoutException e) {
            System.out.println("DEBUG: Timeout esperando la visibilidad del checkbox para: " + locatorKey + ". Continuando.");
        }
    }

    public void clickIfLinkVisible(String locatorKey) throws IOException {
        String xpath = locatorFileLoad(locatorKey);
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            if (element.isDisplayed()) {
                System.out.println("DEBUG: Click en 'Ver más' para: " + locatorKey);
                element.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("DEBUG: No se encontró 'Ver más' para " + locatorKey + ". Continuando.");
        }
    }

    public void applyFilter(String locatorKey) throws IOException {
        String xpath = locatorFileLoad(locatorKey);

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        scrollToElement(xpath);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        if (!element.isSelected()) {
            element.click();
        }
        wait.until(ExpectedConditions.stalenessOf(element));
        sleep(1000);
    }


    public void waitUntilElementIsVisible(String key) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = locatorFileLoad(key);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitForPageToReload() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-spinner")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));
        } catch (TimeoutException e) {
            System.out.println("DEBUG: Timeout al esperar la carga de la página");
        }
    }


    public void safeCheckCheckboxWithScroll(String locatorKey) throws IOException {
        String xpath = locatorFileLoad(locatorKey);
        try {

            System.out.println("DEBUG: Buscando checkbox con key: " + locatorKey);

            for (int i = 0; i < 3; i++) {
                scrollBy(500);
                try {
                    WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                        System.out.println("✅ Checkbox marcado: " + locatorKey);
                    } else {
                        System.out.println("⚠️ Checkbox ya estaba marcado: " + locatorKey);
                    }
                    return;
                } catch (TimeoutException e) {
                    System.out.println("⏳ Intento " + (i+1) + ": Checkbox aún no visible...");
                }
            }
            throw new TimeoutException("❌ No se pudo encontrar el checkbox visible tras varios intentos: " + locatorKey);
        } catch (Exception e) {
            System.out.println("❌ Error al intentar hacer click en el checkbox '" + locatorKey + "': " + e.getMessage());
            throw e;
        }
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












