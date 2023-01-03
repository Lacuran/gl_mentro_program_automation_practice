package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.User;

@Slf4j
public class ShareWishlistPage {

    WebDriver driver;
    By submitShareButton = By.cssSelector("[title='Share Wishlist']");
    By shareWishlistEmail = By.id("email_address");

    public ShareWishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Sharing My Wishlist using User class")
    public MyWishlistPage shareWishlist(User user) {
        log.info("Sharing My Wishlist using User class");
        driver.findElement(shareWishlistEmail).sendKeys(user.getEmail());
        driver.findElement(submitShareButton).click();
        return new MyWishlistPage(driver);
    }

    @Step("Sharing My Wishlist using provided email")
    public MyWishlistPage shareWishlist(String email) {
        log.info("Sharing My Wishlist using provided email");
        driver.findElement(shareWishlistEmail).sendKeys(email);
        driver.findElement(submitShareButton).click();
        return new MyWishlistPage(driver);
    }
}
