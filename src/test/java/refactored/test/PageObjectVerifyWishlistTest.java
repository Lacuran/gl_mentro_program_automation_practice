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

        String addToEmail = String.valueOf(ThreadLocalRandom.current().nextInt(999999999));
        User user = User.createUserFrom("Userdata1.json").setEmail("lenrok.test" + addToEmail + "@gmail.com");
        String expectedRegistrationMsg = "Thank you for registering with Main Website Store.";
        String assertErrorMSG = "Check registration MSG";
        String expectedMsg = "Your Wishlist has been shared.";
        String errorAssertSharingMSG = "Check share Msg";

        PandaHomePage homePage = new PandaHomePage(decorated);

        homePage.clickMyAccountLink()
                .clickCreateAccountPage()
                .fillUserInformation(user)
                .clickRegisterButton()
                .assertRegistration(expectedRegistrationMsg, assertErrorMSG)
                .clickTVLink()
                .addToWishlist()
                .clickShareWishListLink()
                .shareWishlist(user)
                .assertSuccessSharing(expectedMsg, errorAssertSharingMSG);
    }
}