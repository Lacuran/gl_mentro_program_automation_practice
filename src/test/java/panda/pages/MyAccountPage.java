package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.testng.Assert.assertEquals;
@Slf4j
public class MyAccountPage {
    WebDriver driver;
    By registrationDoneText = By.cssSelector(".success-msg");
    By mobileLink = By.xpath("//*[text()='Mobile']");
    By tvXpath = By.xpath("//*[text()='TV']");
    By myWishlistSelector = RelativeLocator
            .with(By.xpath("//a[contains(text(), 'My Wishlist')]"))
            .below(By.cssSelector(".current"));


    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyAccountPage assertRegistration(String expectedRegistrationMsg, String assertionErrorMSG){
        log.info("Assert Registration");
        assertEquals(driver.findElement(registrationDoneText).getText(), expectedRegistrationMsg
                , assertionErrorMSG);
        return this;
    }

    public MobilePage clickMobileLink(){
        log.info("Click Mobile Link");
        driver.findElement(mobileLink).click();
        return new MobilePage(driver);
    }

    public TVPage clickTVLink() {
        log.info("Click TV Link");
        driver.findElement(tvXpath).click();
        return new TVPage(driver);
    }

    public MyWishlistPage clickMyWishListLink(){
        log.info("Click My Wishlist");
        driver.findElement(myWishlistSelector).click();
        return new MyWishlistPage(driver);
    }
}
