package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

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
        driver.findElement(mobileLink).click();
        return new MobilePage(driver);
    }


    public TVPage clickTVLink() {
        driver.findElement(tvXpath).click();
        return new TVPage(driver);
    }

    public CustomerLoginPage clickMyAccountLink(){
        driver.findElement(accountCssSelector).click();
        driver.findElement(myAccountLink).click();
        return new CustomerLoginPage(driver);
    }
    public MobilePage getMobilePage(){
        return new MobilePage(driver);
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }
}
