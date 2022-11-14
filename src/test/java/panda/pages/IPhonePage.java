package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IPhonePage {

    WebDriver driver;
    By phonePrice = By.xpath("//span[contains(@id, 'product-price-')]");

    public IPhonePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPhoneCostString() {
        return driver.findElement(phonePrice).getText();
    }
}
