package live.panda.testing;

import base.test.BaseTestSetup;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VerifyShoppingCartTest extends BaseTestSetup {

    @Test
    public void verifyThatYouCannotAddMoreProductInCartThanTheProductAvailableInTheStore(){
        //variables

        LOGGER.info("2. Click on the 'MOBILE' menu.");

        LOGGER.info("3. In the list of all mobile, click on the 'ADD TO CART' for Sony Xperia mobile.");

        LOGGER.info("4. Change 'QTY' value to 1000 and click on the 'UPDATE' button.");

        LOGGER.info("5. Verify the error message.");

        LOGGER.info("6. Then click on the 'EMPTY CART' link in the footer of list of all mobiles");

        LOGGER.info("7. Verify the cart is empty");

    }
}
