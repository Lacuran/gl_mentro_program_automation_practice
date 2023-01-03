package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PlacedOrderPage {

    WebDriver driver;
    String placeOrderPageTitle = "Magento Commerce";
    By orderMSG = By.cssSelector(".page-title");

    public PlacedOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Assert Order is Generated")
    public PlacedOrderPage assertOrderIsGenerated(String expectedOrderMSG, String assertionErrorMessage) {
        log.info("Assert Order is Generated");
        explicitWait().until(ExpectedConditions.titleIs(placeOrderPageTitle));
        assertEquals(driver.findElement(orderMSG).getText(), expectedOrderMSG, assertionErrorMessage);
        return this;
    }

    @Step("Getting Placed Order page title")
    public String getPlaceOrderPageTitleTitle() {
        log.info("Getting Placed Order page title");
        return driver.getTitle();
    }

    @Step("Explicit wait come into play")
    private WebDriverWait explicitWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
