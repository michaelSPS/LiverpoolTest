package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {super(driver); }

    public boolean verifyProductInCart(String expectedProductName) {
        By productTitleLocator = By.xpath("(//div[@class='o-myBag__description'])[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitleLocator));
        List<WebElement> products = driver.findElements(productTitleLocator);
        for (WebElement product : products) {
            String actualProductName = product.getText().trim();
            System.out.println("Producto encontrado: " + actualProductName);
            if (actualProductName.toLowerCase().contains(expectedProductName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}

