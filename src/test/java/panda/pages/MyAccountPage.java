package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
    By logoutCssSelector = RelativeLocator
            .with(By.cssSelector("[title='Log Out']"))
            .below(accountCssSelector);
    By welcomeMsg = By.cssSelector(".hello");
    By accInfoXPath = By.xpath("//a[contains(text(), 'Account Information')]");
    By successMsgCssSelector = By.cssSelector(".success-msg");
    By reorderButton = RelativeLocator
            .with(By.cssSelector(".link-reorder"))
            .below(By.cssSelector(".first.odd"));

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyAccountPage assertRegistration(String expectedRegistrationMsg, String assertionErrorMSG) {
        log.info("Assert Registration");
        assertEquals(driver.findElement(registrationDoneText).getText(), expectedRegistrationMsg
                , assertionErrorMSG);
        return this;
    }

    public MobilePage clickMobileLink() {
        log.info("Click Mobile Link");
        driver.findElement(mobileLink).click();
        return new MobilePage(driver);
    }

    public TVPage clickTVLink() {
        log.info("Click TV Link");
        driver.findElement(tvXpath).click();
        return new TVPage(driver);
    }

    public MyWishlistPage clickMyWishListLink() {
        log.info("Click My Wishlist");
        driver.findElement(myWishlistSelector).click();
        return new MyWishlistPage(driver);
    }

    public PandaHomePage logOut() {
        log.info("Click Log Out");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logoutCssSelector).click();
        explicitWait().until(ExpectedConditions.titleIs("Home page"));
        return new PandaHomePage(driver);
    }

    public AccountInformationPage clickAccountInformationLink() {
        log.info("Click Account Information");
        driver.findElement(accInfoXPath).click();
        return new AccountInformationPage(driver);
    }

    public String getWelcomeMSG() {
        log.info("Getting Welcome message");
        return driver.findElement(welcomeMsg).getText();
    }

    public String getSuccessEditInfoMSG() {
        log.info("Getting Success MSG");
        return driver.findElement(successMsgCssSelector).getText();
    }

    public ShoppingCartPage clickReorderLink() {
        log.info("Click Reorder Link");
        driver.findElement(reorderButton).click();
        return new ShoppingCartPage(driver);
    }

    private WebDriverWait explicitWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
