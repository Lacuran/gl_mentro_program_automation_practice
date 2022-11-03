package live.panda.testing;

import base.test.BaseTestSetup;
import org.testng.annotations.Test;

public class VerifyChangeOrReorderPreviouslyAddedProductTest extends BaseTestSetup {

    @Test(description = "8")
    public void verifyYouAreAbleToChangeOrReorderPreviouslyAddedProductTest(){
        LOGGER.info("2. Click on my account link");
        LOGGER.info("3. Login in application using previously created credentials");
        LOGGER.info("4. Click on 'REORDER' Link, change QTY(10) & click Update");
        LOGGER.info("5. Verify Grand Total is changed");
        LOGGER.info("6. Complete Billing & Shipping Information");
        LOGGER.info("7. Verify order is generated and note the order number");
    }
}
