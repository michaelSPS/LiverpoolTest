package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyProductInCart(String expectedProductName) {
        String locatorKey = "verifyResults";
        try {
            waitUtils.verifyElementIsVisible(locatorKey);
            String locator = utils.ConfigManager.getLocator(locatorKey);
            List<WebElement> products = driver.findElements(By.xpath(locator));

            if (products.isEmpty()) {
                System.out.println("⚠️ No se encontraron productos en el carrito.");
                return false;
            }

            for (WebElement product : products) {
                String actualProductName = product.getText().trim();
                System.out.println("✅ Producto encontrado: " + actualProductName);
                if (actualProductName.toLowerCase().contains(expectedProductName.toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al verificar el producto en el carrito: " + e.getMessage());
        }

        return false;
    }

}

