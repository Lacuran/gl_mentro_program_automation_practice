package live.panda.testing;

import base.test.BaseTestSetup;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utility.User;

import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

public class VerifyPurchaseOfAProductTest extends BaseTestSetup {

    @Test(description = "6")
    public void verifyUserIsAbleToPurchaseProductUsingRegisteredEmailTest(){
        //variables
        User user = new User("Kornel", "Maybe"
                , "Test", "lenrok.test1@gmail.com", "test123");
        By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
        By myAccountCssSelector = RelativeLocator.with(By.cssSelector("[title='My Account']"))
                .below(accountCssSelector);
        By[] loginID = {By.id("email"), By.id("pass")};
        String[] loginData ={user.getEmail(), user.getPassword()};
        By loginButton = By.id("send2");
        By myWishlistSelector = RelativeLocator
                .with(By.xpath("//a[contains(text(), 'My Wishlist')]"))
                .below(By.cssSelector(".current"));
        By addToCartCssSelector = By.cssSelector(".btn-cart");
        By checkoutButton = RelativeLocator
                .with(By.cssSelector(".btn-checkout"))
                .below(By.cssSelector(".top"));
        By countrySelectID = By.id("billing:country_id");
        By[] shippingID = {By.id("billing:firstname"), By.id("billing:middlename")
                , By.id("billing:lastname"), By.id("billing:street1")
                , By.id("billing:city"), By.id("billing:postcode")
                , By.id("billing:telephone")};
        By continueButton = RelativeLocator
                .with(By.cssSelector("[title='Continue']"))
                .below(By.id("billing-buttons-container"));
        By shippingCost = RelativeLocator
                .with(By.cssSelector(".price"))
                .below(By.cssSelector("[for='s_method_flatrate_flatrate']"));
        String expectedShippingPrice = "$5.00";

        LOGGER.info("2. Click on my account link");
        driver.findElement(myAccountCssSelector).click();

        LOGGER.info("3. Login in application using previously created credentials");
        IntStream.range(0, loginData.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));
        driver.findElement(loginButton).click();

        LOGGER.info("4. Click on 'MY WISHLIST' link");
        driver.findElement(myWishlistSelector).click();

        LOGGER.info("5. In the next page, Click 'ADD TO CART' link");
        driver.findElement(addToCartCssSelector).click();

        LOGGER.info("6. Click 'PROCEED TO CHECKOUT'");
        driver.findElement(checkoutButton).click();

        LOGGER.info("7. Enter shipping information");
        Faker faker = new Faker();
        Select shippingCountry = new Select(driver.findElement(countrySelectID));
        String streetAddress = faker.address().streetAddress();
        String city = faker.address().cityName();
        String zipcode = faker.address().zipCode();
        String telephone = faker.phoneNumber().phoneNumber();
        String[] shippingData = {user.getFirstName(), user.getMiddleName()
                , user.getLastName(), streetAddress, city, zipcode, telephone};
        IntStream.range(0, loginData.length)
                .forEach(i -> driver.findElement(shippingID[i]).clear());
        IntStream.range(0, loginData.length)
                .forEach(i -> driver.findElement(shippingID[i]).sendKeys(shippingData[i]));
        shippingCountry.selectByValue("PL");

        LOGGER.info("8. Click Estimate");
        driver.findElement(continueButton).click();

        LOGGER.info("9. Verify Shipping cost generated");
        assertEquals(driver.findElement(shippingCost).getText(), expectedShippingPrice, "Check shipping price");

        LOGGER.info("10. Select Shipping Cost, Update Total");
        LOGGER.info("11. Verify shipping cost is added to total");
        LOGGER.info("12. Click 'Proceed to Checkout'");
        LOGGER.info("13. Enter Billing Information");
        LOGGER.info("14. In Shipping Method, Click Continue");
        LOGGER.info("15. In Payment Information select 'Check/Money Order' radio button, Click Continue'");
        LOGGER.info("16. Click 'Place ORDER' button");
        LOGGER.info("17. Verify Order is generated. Note the order number");

    }
}
