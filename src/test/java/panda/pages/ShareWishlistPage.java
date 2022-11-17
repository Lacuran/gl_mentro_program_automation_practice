package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.User;

public class ShareWishlistPage {

    WebDriver driver;
    By submitShareButton = By.cssSelector("[title='Share Wishlist']");
    By shareWishlistEmail = By.id("email_address");


    public ShareWishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyWishlistPage shareWishlist(User user) {
        driver.findElement(shareWishlistEmail).sendKeys(user.getEmail());
        driver.findElement(submitShareButton).click();
        return new MyWishlistPage(driver);
    }

    public MyWishlistPage shareWishlist(String email) {
        driver.findElement(shareWishlistEmail).sendKeys(email);
        driver.findElement(submitShareButton).click();
        return new MyWishlistPage(driver);
    }

}
