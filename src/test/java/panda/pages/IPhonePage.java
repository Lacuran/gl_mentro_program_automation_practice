package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Slf4j
public class IPhonePage {

    WebDriver driver;
    By phonePrice = By.xpath("//span[contains(@id, 'product-price-')]");

    public IPhonePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPhoneCostString() {
        log.info("Getting IPhone price");
        return driver.findElement(phonePrice).getText();
    }
}
