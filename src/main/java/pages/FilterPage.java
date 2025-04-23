package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigManager;

import java.io.IOException;

public class FilterPage extends BasePage {

    public FilterPage(WebDriver driver) {super(driver); }

    public void applyPriceFilterGreaterThan10000() throws IOException {
        System.out.println("DEBUG: Aplicando filtro de precio > $10,000");
        String xpath = ConfigManager.getLocator("filter.price.10000plus");
        clickUsingJS(xpath);
        waitUtils.waitForPageToReload();
    }

    public void applyBrandFilterSony() throws IOException {
        System.out.println("DEBUG: Aplicando filtro de marca SONY");
        String xpath = ConfigManager.getLocator("filter.brand.sony");
        clickUsingJS(xpath);
        waitUtils.waitForPageToReload();
    }

    public void applySizeFilter55Inches() throws IOException {
        System.out.println("DEBUG: Aplicando filtro de tamaño - 55 pulgadas");
        String xpath = ConfigManager.getLocator("filter.size.55");
        clickUsingJS(xpath);
        waitUtils.waitForPageToReload();
    }

}
