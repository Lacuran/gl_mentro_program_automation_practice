package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class ShoppingCartPage {

    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    By quantityInput = By.cssSelector("[title='Qty']");
    By updateButton = By.cssSelector("[title='Update']");
    By errorMsgPopUp = By.className("error-msg");
    By emptyCartButton = By.id("empty_cart_button");
    By emptyCartMsg = By.className("page-title");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public ShoppingCartPage changeQtyAndClickUpdate(String Quantity){
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(Quantity);
        driver.findElement(updateButton).click();
        return this;
    }

    public ShoppingCartPage assertErrorMSG(String expectedMSG, String assertionErrorMessage){
        softAssert.assertEquals(expectedMSG, driver.findElement(errorMsgPopUp).getText(), assertionErrorMessage);
        return this;
    }

    public ShoppingCartPage emptyShoppingCart(){
        driver.findElement(emptyCartButton).click();
        return this;
    }

    public ShoppingCartPage verifyTheCartIsEmpty(String expectedMSG, String assertionErrorMessage){
        softAssert.assertEquals(expectedMSG, driver.findElement(emptyCartMsg).getText(), assertionErrorMessage);
        softAssert.assertAll();
        return this;
    }
}
