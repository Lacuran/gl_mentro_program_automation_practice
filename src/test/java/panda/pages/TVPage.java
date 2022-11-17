package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class TVPage {

    WebDriver driver;
    By addToWishListButton = RelativeLocator.with(By.cssSelector(".link-wishlist"))
            .below(By.cssSelector("[title='LG LCD']"));

    public TVPage(WebDriver driver) {
        this.driver = driver;
    }
    public MyWishlistPage addToWishList() {
        driver.findElement(addToWishListButton).click();
        return new MyWishlistPage(driver);
    }
}
