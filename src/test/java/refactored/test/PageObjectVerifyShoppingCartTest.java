package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;

@Slf4j
public class PageObjectVerifyShoppingCartTest extends BaseTestSetup {

    @Test(description = "3")
    public void verifyThatYouCannotAddMoreProductInCartThanTheProductAvailableInTheStore() {
        //variables
        PandaHomePage homePage = new PandaHomePage(driver);
        String expectedErrorMsg = "Some of the products cannot be ordered in requested quantity.";
        String expectedEmptyCartMsg = "SHOPPING CART IS EMPTY";

        homePage.clickMobileLink()
                .addToCart()
                .changeQtyAndClickUpdate("1000")
                .assertErrorMSG(expectedErrorMsg, "Verify error message")
                .emptyShoppingCart()
                .verifyTheCartIsEmpty(expectedEmptyCartMsg, "Verify msg for empty cart");

    }
}
