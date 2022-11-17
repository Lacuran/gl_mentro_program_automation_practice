package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;

@Slf4j
public class PageObjectVerifyShoppingCartTest extends BaseTestSetup {

    @Test(description = "3")
    public void verifyThatYouCannotAddMoreProductInCartThanTheProductAvailableInTheStore(){
        //variables
        PandaHomePage homePage = new PandaHomePage(driver);
        String expectedErrorMsg = "Some of the products cannot be ordered in requested quantity.";
        String expectedEmptyCartMsg = "SHOPPING CART IS EMPTY";


        log.info("2. Click on the 'MOBILE' menu.");
        homePage.clickMobileLink()
                .addToCart()
                .changeQtyAndClickUpdate("1000")
                .assertErrorMSG(expectedErrorMsg, "Verify error message")
                .emptyShoppingCart()
                .verifyTheCartIsEmpty(expectedEmptyCartMsg, "Verify msg for empty cart");

        log.info("3. In the list of all mobile, click on the 'ADD TO CART' for Sony Xperia mobile.");


        log.info("4. Change 'QTY' value to 1000 and click on the 'UPDATE' button.");


        log.info("5. Verify the error message.");


        log.info("6. Then click on the 'EMPTY CART' link in the footer of list of all mobiles");


        log.info("7. Verify the cart is empty");


    }
}
