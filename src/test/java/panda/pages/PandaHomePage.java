package panda.pages;

import io.qameta.allure.Step;
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

    @Step
    public ProductListingPage clickMobileLink() {
        log.info("Click Mobile link");
        driver.findElement(mobileLink).click();
        return new ProductListingPage(driver);
    }

    @Step
    public ProductListingPage clickTVLink() {
        log.info("Click TV link");
        driver.findElement(tvXpath).click();
        return new ProductListingPage(driver);
    }

    @Step
    public CustomerLoginPage clickMyAccountLink() {
        log.info("Click My Account link");
        driver.findElement(accountCssSelector).click();
        driver.findElement(myAccountLink).click();
        return new CustomerLoginPage(driver);
    }

    @Step
    public AdvanceSearchPage clickAdvanceSearchLink() {
        log.info("Click Advance Search Link");
        driver.findElement(advanceSearchLink).click();
        return new AdvanceSearchPage(driver);
    }
}
