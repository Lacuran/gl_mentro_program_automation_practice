package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

@Slf4j
public class CommonPageElements {

    WebDriver driver;
    By addToWishListButton = RelativeLocator.with(By.cssSelector(".link-wishlist"))
            .below(By.cssSelector("[title='LG LCD']"));
    By myCartButton = By.cssSelector(".top-link-cart");
    By accountButton = By.cssSelector(".skip-account");

    public CommonPageElements(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public String getPageTitle() {
        log.info("Getting Home Page title");
        return driver.getTitle();
    }

    @Step
    public MyWishlistPage addToWishlist() {
        log.info("Add LG LCD TV to Wishlist");
        driver.findElement(addToWishListButton).click();
        return new MyWishlistPage(driver);
    }

    @Step
    public ShoppingCartPage openCart() {
        log.info("Opening Cart");
        driver.findElement(accountButton).click();
        driver.findElement(myCartButton).click();
        return new ShoppingCartPage(driver);
    }
}
