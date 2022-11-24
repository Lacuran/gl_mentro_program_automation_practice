package refactored.test;

import base.test.BaseTestSetup;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;
import utility.User;

@Slf4j
public class PageObjectVerifyChangeOrReorderPreviouslyAddedProductTest extends BaseTestSetup {

    @Test(description = "8")
    public void verifyYouAreAbleToChangeOrReorderPreviouslyAddedProductTest() {
        //variables
        PandaHomePage homePage = new PandaHomePage(driver);
        Faker faker = new Faker();
        User user = new User("Kornel", "Maybe"
                , "Test", "lenrok.test1@gmail.com", "test123");
        String expectedGrandTotalPrice = "$6,150.00";
        String newAddress = "New Address";
        String[] shippingData = {user.getFirstName(), user.getMiddleName()
                , user.getLastName(), faker.address().streetAddress()
                , faker.address().cityName(), faker.address().zipCode()
                , faker.phoneNumber().phoneNumber()};
        String expectedOrderMSG = "YOUR ORDER HAS BEEN RECEIVED.";

        homePage.clickMyAccountLink()
                .fillingLoginData(user)
                .clickLoginButton()
                .clickReorderLink()
                .changeQtyAndClickUpdate("10")
                .assertTotalShippingCost(expectedGrandTotalPrice, "Check Total Shipping Cost")
                .clickCheckoutButton()
                .selectNewBillingAddress(newAddress)
                .fillNewBillingInformation(shippingData, "PL")
                .clickBillingInformationContinueButton()
                .clickShippingInformationContinueButton()
                .selectCheckMoneyRadioButton()
                .clickPaymentInformationContinueButton()
                .clickPlaceOrderButton()
                .assertOrderIsGenerated(expectedOrderMSG, "Check order msg");

    }
}
