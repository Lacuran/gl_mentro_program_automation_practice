package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
@Slf4j
public class MyWishlistPage {

    WebDriver driver;
    By shareWishListButton = By.cssSelector(".btn-share");
    By successMsgLocator = By.cssSelector(".success-msg");
    By addToCartCssSelector = By.cssSelector(".btn-cart");

    public MyWishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public ShareWishlistPage clickShareWishListLink() {
        log.info("Click Share Wishlist link");
        driver.findElement(shareWishListButton).click();
        return new ShareWishlistPage(driver);
    }

    public ShoppingCartPage addToCartButton(){
        log.info("Click Add To Cart button");
        driver.findElement(addToCartCssSelector).click();
        return new ShoppingCartPage(driver);
    }

    public MyWishlistPage assertSuccessSharing(String expectedSuccessSharingMsg, String assertionErrorMSG){
        log.info("Assert Success Sharing Wishlist");
        assertEquals(driver.findElement(successMsgLocator).getText(), expectedSuccessSharingMsg
                , assertionErrorMSG);
        return this;
    }
}
