package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class MyWishlistPage {

    WebDriver driver;
    By shareWishListButton = By.cssSelector(".btn-share");
    By successMsgLocator = By.cssSelector(".success-msg");

    public MyWishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public ShareWishlistPage clickShareWishListLink() {
        driver.findElement(shareWishListButton).click();
        return new ShareWishlistPage(driver);
    }

    public MyWishlistPage assertSuccessSharing(String expectedSuccessSharingMsg, String assertionErrorMSG){
        assertEquals(driver.findElement(successMsgLocator).getText(), expectedSuccessSharingMsg
                , assertionErrorMSG);
        return this;
    }
}
