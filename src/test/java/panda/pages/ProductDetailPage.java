package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ProductDetailPage {

    WebDriver driver;

    By productPrice = By.xpath("//span[contains(@id, 'product-price-')]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductCost() {
        log.info("Getting price from details page");
        return driver.findElement(productPrice).getText();
    }
}
