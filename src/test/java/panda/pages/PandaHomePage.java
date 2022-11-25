package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

@Slf4j
public class PandaHomePage extends CommonPageElements {

    By mobileLink = By.xpath("//*[text()='Mobile']");
    By tvXpath = By.xpath("//*[text()='TV']");
    By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
    By myAccountLink = RelativeLocator.with(By.cssSelector("[title='My Account']")).below(accountCssSelector);
    By advanceSearchLink = By.cssSelector("[title='Advanced Search']");

    public PandaHomePage(WebDriver driver) {
        super(driver);
    }

    public ProductListingPage clickMobileLink() {
        log.info("Click Mobile link");
        driver.findElement(mobileLink).click();
        return new ProductListingPage(driver);
    }

    public ProductListingPage clickTVLink() {
        log.info("Click TV link");
        driver.findElement(tvXpath).click();
        return new ProductListingPage(driver);
    }

    public CustomerLoginPage clickMyAccountLink() {
        log.info("Click My Account link");
        driver.findElement(accountCssSelector).click();
        driver.findElement(myAccountLink).click();
        return new CustomerLoginPage(driver);
    }

    public AdvanceSearchPage clickAdvanceSearchLink() {
        log.info("Click Advance Search Link");
        driver.findElement(advanceSearchLink).click();
        return new AdvanceSearchPage(driver);
    }
}
