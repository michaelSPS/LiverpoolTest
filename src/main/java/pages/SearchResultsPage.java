package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigManager;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SearchResultsPage extends BasePage {

public SearchResultsPage(WebDriver driver) {
    super(driver);
}

    public boolean verifyPlayStationImages() throws IOException {
        System.out.println("DEBUG: Verificando resultados de playstation, consolas o playstation 5");

        String resultsLocator = ConfigManager.getLocator("searchResults");
        waitUtils.verifyElementIsVisible("searchResults");
        List<WebElement> images = driver.findElements(By.xpath(resultsLocator));
        String[] searchKeywords = {"PlayStation 5", "PlayStation", "Consolas"};

        for (WebElement image : images) {
            String altText = Objects.requireNonNull(image.getDomAttribute("alt")).toLowerCase();


            for (String keyword : searchKeywords) {
                if (altText.contains(keyword.toLowerCase())) {
                    System.out.println("Image found with keyword: " + altText); //
                    return true;
                }
            }
        }
        System.out.println("DEBUG: No se encontraron imagenes de PlayStation.");
        return false;
    }

    public int getProductResultCount() throws IOException {
        String productListLocator = ConfigManager.getLocator("productList");
        waitUtils.waitForProductList(productListLocator);
        List<WebElement> productResults = driver.findElements(By.xpath(productListLocator));
        return productResults.size();

    }


}
