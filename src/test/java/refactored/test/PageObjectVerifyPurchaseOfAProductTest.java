package refactored.test;

import base.test.BaseTestSetup;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;
import utility.User;

@Slf4j
public class PageObjectVerifyPurchaseOfAProductTest extends BaseTestSetup {

    User user = User.createUserFrom("Userdata1.json").setEmail("lenrok.test1@gmail.com");

    @BeforeGroups("purchase")
    public void beforeTestSetUp() {

        setUp();
        PandaHomePage homePage = new PandaHomePage(driver1);
        homePage.clickMyAccountLink()
                .fillingLoginData(user)
                .clickLoginButton()
                .clickTVLink()
                .addToWishlist()
                .openCart()
                .emptyShoppingCart();
        cleanUp();

    }

    @Test(groups = "purchase", description = "6")
    public void verifyUserIsAbleToPurchaseProductUsingRegisteredEmailTest() {

        PandaHomePage homePage = new PandaHomePage(driver);
        Faker faker = new Faker();

        String assertionShippingPriceMSG = "Check shipping price";
        String expectedPrice = "$5.00";
        String expectedTotalPrice = "$620.00";
        String assertionTotalPriceMSG = "Check total price";
        String newAddress = "New Address";
        String streetAddress = faker.address().streetAddress();
        String city = faker.address().cityName();
        String zipcode = faker.address().zipCode();
        String telephone = faker.phoneNumber().phoneNumber();
        String[] shippingData = {user.getFirstName(), user.getMiddleName()
                , user.getLastName(), streetAddress, city, zipcode, telephone};
        String expectedOrderMSG = "YOUR ORDER HAS BEEN RECEIVED.";
        String pageOrderAssertionErrorMSG = "Check order msg";

        homePage.clickMyAccountLink()
                .fillingLoginData(user)
                .clickLoginButton()
                .clickMyWishListLink()
                .addToCartButton()
                .fillShippingAndTaxField("PL", "Malopolska", zipcode)
                .clickEstimateLink()
                .assertShippingCost(expectedPrice, assertionShippingPriceMSG)
                .selectRadioButton()
                .clickUpdateTotalLink()
                .assertTotalShippingCost(expectedTotalPrice, assertionTotalPriceMSG)
                .clickCheckoutButton()
                .selectNewBillingAddress(newAddress)
                .fillNewBillingInformation(shippingData, "PL")
                .clickBillingInformationContinueButton()
                .clickShippingInformationContinueButton()
                .selectCheckMoneyRadioButton()
                .clickPaymentInformationContinueButton()
                .clickPlaceOrderButton()
                .assertOrderIsGenerated(expectedOrderMSG, pageOrderAssertionErrorMSG);
    }
}
