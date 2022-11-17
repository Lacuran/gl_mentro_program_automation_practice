package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;
import utility.User;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class PageObjectVerifyWishlistTest extends BaseTestSetup {

    @Test(description = "5")
    public void verifyAccountCreationInECommerceAndShareWishlistTest() {
        //variables
        String addToEmail = String.valueOf(ThreadLocalRandom.current().nextInt(999999999));
        User user = new User("Kornel", "Maybe"
                , "Test", "lenrok.test" + addToEmail + "@gmail.com"
                , "test123");
        String expectedRegistrationMsg = "Thank you for registering with Main Website Store.";
        String assertErrorMSG = "Check registration MSG";
        String expectedMsg = "Your Wishlist has been shared.";
        String errorAssertSharingMSG = "Check share Msg";

        PandaHomePage homePage = new PandaHomePage(driver);

        homePage.clickMyAccountLink()
                .clickCreateAccountPage()
                .fillUserInformation(user)
                .clickRegisterButton()
                .assertRegistration(expectedRegistrationMsg, assertErrorMSG)
                .clickTVLink()
                .addToWishList()
                .clickShareWishListLink()
                .shareWishlist(user)
                .assertSuccessSharing(expectedMsg, errorAssertSharingMSG);

        log.info("2. Click on my account link");

        log.info("3. Click Create Account link and fill New User information except Email ID");

        log.info("4. Click Register");

        log.info("5. Verify Registration is done");

        log.info("6. Go to TV menu");

        log.info("7. Add product in your wish list");

        log.info("8. Click SHARE WISHLIST");

        log.info("9. In next page enter Email and a message and click SHARE WISHLIST");

        log.info("10. Check wishlist is shared");
    }

}
