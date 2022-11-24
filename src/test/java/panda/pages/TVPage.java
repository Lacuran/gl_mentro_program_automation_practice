package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

@Slf4j
public class TVPage {

    WebDriver driver;
    By addToWishListButton = RelativeLocator.with(By.cssSelector(".link-wishlist"))
            .below(By.cssSelector("[title='LG LCD']"));

    public TVPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyWishlistPage addToWishlist() {
        log.info("Add LG LCD TV to Wishlist");
        driver.findElement(addToWishListButton).click();
        return new MyWishlistPage(driver);
    }
}
