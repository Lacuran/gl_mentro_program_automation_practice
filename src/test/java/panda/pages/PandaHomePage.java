package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
@Slf4j
public class PandaHomePage {
    WebDriver driver;
    By mobileLink = By.xpath("//*[text()='Mobile']");
    By tvXpath = By.xpath("//*[text()='TV']");
    By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
    By myAccountLink = RelativeLocator.with(By.cssSelector("[title='My Account']")).below(accountCssSelector);



    public PandaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public MobilePage clickMobileLink() {
        log.info("Click Mobile link");
        driver.findElement(mobileLink).click();
        return new MobilePage(driver);
    }


    public TVPage clickTVLink() {
        log.info("Click TV link");
        driver.findElement(tvXpath).click();
        return new TVPage(driver);
    }

    public CustomerLoginPage clickMyAccountLink(){
        log.info("Click My Account link");
        driver.findElement(accountCssSelector).click();
        driver.findElement(myAccountLink).click();
        return new CustomerLoginPage(driver);
    }

    public MobilePage getMobilePage(){
        log.info("Diving into Mobile Page");
        return new MobilePage(driver);
    }

    public MyAccountPage getMyAccountPage(){
        log.info("Diving into My Account Page");
        return new MyAccountPage(driver);
    }

    public String getHomePageTitle() {
        log.info("Getting Panda Home Page title");
        return driver.getTitle();
    }
}
