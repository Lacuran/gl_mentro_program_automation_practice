package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage {

    WebDriver driver;

    By productPrice = By.xpath("//span[contains(@id, 'product-price-')]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductCost() {
        return driver.findElement(productPrice).getText();
    }
}
