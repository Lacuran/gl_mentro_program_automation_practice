package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VerifyShoppingCartTest extends BaseTestSetup {

    @Test(description = "3")
    public void verifyThatYouCannotAddMoreProductInCartThanTheProductAvailableInTheStore(){
        //variables
        String expectedErrorMsg = "Some of the products cannot be ordered in requested quantity.";
        String expectedEmptyCartMsg = "SHOPPING CART IS EMPTY";

        By mobileXpath = By.xpath("//*[text()='Mobile']");
        By addToCartCss = By.cssSelector(".btn-cart");
        By mobileQuantityInput = By.cssSelector("[title='Qty']");
        By updateButton = By.cssSelector("[title='Update']");
        By errorMsgPopUp = By.className("error-msg");
        By emptyCartButton = By.id("empty_cart_button");
        By emptyCartMsg = By.className("page-title");

        LOGGER.info("2. Click on the 'MOBILE' menu.");
        driver.findElement(mobileXpath).click();

        LOGGER.info("3. In the list of all mobile, click on the 'ADD TO CART' for Sony Xperia mobile.");
        driver.findElements(addToCartCss).stream().findAny().orElseThrow().click();

        LOGGER.info("4. Change 'QTY' value to 1000 and click on the 'UPDATE' button.");
        driver.findElement(mobileQuantityInput).clear();
        driver.findElement(mobileQuantityInput).sendKeys("1000");
        driver.findElement(updateButton).click();

        LOGGER.info("5. Verify the error message.");
        assertEquals(driver.findElement(errorMsgPopUp).getText(), expectedErrorMsg,"Verify error message");

        LOGGER.info("6. Then click on the 'EMPTY CART' link in the footer of list of all mobiles");
        driver.findElement(emptyCartButton).click();

        LOGGER.info("7. Verify the cart is empty");
        assertEquals(driver.findElement(emptyCartMsg).getText(), expectedEmptyCartMsg,"Verify msg for empty cart");

    }
}
