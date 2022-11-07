package live.panda.testing;

import base.test.BaseTestSetup;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.User;

import java.time.Duration;
import java.util.stream.IntStream;

public class VerifyPurchaseOfAProductTest extends BaseTestSetup {
    User user = new User("Kornel", "Maybe"
            , "Test", "lenrok.test1@gmail.com", "test123");
    By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
    By myAccountCssSelector = RelativeLocator.with(By.cssSelector("[title='My Account']"))
            .below(accountCssSelector);
    By[] loginID = {By.id("email"), By.id("pass")};
    String[] loginData ={user.getEmail(), user.getPassword()};
    By loginButton = By.id("send2");


    @BeforeTest
    public void beforeTestSetUp(){
        By tvXpath = By.xpath("//*[text()='TV']");
        By addToWishListButton = RelativeLocator.with(By.cssSelector(".link-wishlist"))
                .below(By.cssSelector("[title='LG LCD']"));

        setUp();
        loginSteps();

        LOGGER.info("4. Clicking on TV link");
        driver.findElement(tvXpath).click();

        LOGGER.info("5. Adding TV to Wishlist");
        driver.findElement(addToWishListButton).click();
        cleanUp();

    }
    @Test(description = "6")
    public void verifyUserIsAbleToPurchaseProductUsingRegisteredEmailTest(){
        //variables
        SoftAssert softAssert = new SoftAssert();
        Faker faker = new Faker();
        //step 4 data
        By myWishlistSelector = RelativeLocator
                .with(By.xpath("//a[contains(text(), 'My Wishlist')]"))
                .below(By.cssSelector(".current"));
        //step 5 data
        By addToCartCssSelector = By.cssSelector(".btn-cart");
        //step 6 data
        By regionCart = By.id("region");
        By postcodeCart = By.id("postcode");
        //step 7 data
        By estimateButton = By.cssSelector("[title='Estimate']");
        //step 8 data
        String expectedPrice = "$5.00";
        By shippingPrice = RelativeLocator
                .with(By.cssSelector(".price"))
                .below(By.xpath("//dt[contains(text(), 'Flat Rate')]"));
        //step 9 data
        By radioButton = By.id("s_method_flatrate_flatrate");
        By updateTotalButton = By.cssSelector("[title='Update Total']");
        //step 10 data
        By totalPriceXPath = By.xpath("//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span");
        String expectedTotalPrice = "$620.00";
        //step 11 data
        By checkoutButton = RelativeLocator
                .with(By.cssSelector(".btn-checkout"))
                .below(By.cssSelector(".top"));
        //step 12 data
        By countrySelectID = By.id("billing:country_id");
        By[] shippingID = {By.id("billing:firstname"), By.id("billing:middlename")
                , By.id("billing:lastname"), By.id("billing:street1")
                , By.id("billing:city"), By.id("billing:postcode")
                , By.id("billing:telephone")};
        String streetAddress = faker.address().streetAddress();
        String city = faker.address().cityName();
        String zipcode = faker.address().zipCode();
        String telephone = faker.phoneNumber().phoneNumber();
        String[] shippingData = {user.getFirstName(), user.getMiddleName()
                , user.getLastName(), streetAddress, city, zipcode, telephone};
        //step 13 data
        By continueButtonBillingInformation = By.xpath("//*[@id='billing-buttons-container']/button");
        //step 14 data
        By continueButtonShippingMethod = By.xpath("//*[@id='shipping-method-buttons-container']/button");
        //step 15 data
        By continueButtonPaymentInformation = By.xpath("//*[@id='payment-buttons-container']/button");
        By checkMoneyRadioButton = By.id("p_method_checkmo");
        //step 16 data
        By placeOrderButton = By.cssSelector("[title='Place Order']");
        //step 17 data
        String expectedOrderMSG = "YOUR ORDER HAS BEEN RECEIVED.";
        By orderMSG = By.cssSelector(".page-title");

        loginSteps();

        LOGGER.info("4. Click on 'MY WISHLIST' link");
        driver.findElement(myWishlistSelector).click();

        LOGGER.info("5. In the next page, Click 'ADD TO CART' link");
        driver.findElement(addToCartCssSelector).click();

        LOGGER.info("6. Enter shipping information");
        Select countrySelect = new Select(driver.findElement(By.id("country")));
        countrySelect.selectByValue("PL");
        driver.findElement(regionCart).sendKeys("Malopolska");
        driver.findElement(postcodeCart).sendKeys(faker.address().zipCode());

        LOGGER.info("7. Click Estimate");
        driver.findElement(estimateButton).click();

        LOGGER.info("8. Verify Shipping cost generated");
        softAssert.assertEquals(driver.findElement(shippingPrice).getText(), expectedPrice, "Check shipping price");

        LOGGER.info("9. Select Shipping Cost, Update Total");
        driver.findElement(radioButton).click();
        driver.findElement(updateTotalButton).click();

        LOGGER.info("10. Verify shipping cost is added to total");
        softAssert.assertEquals(driver.findElement(totalPriceXPath).getText(), expectedTotalPrice, "Check total price");

        LOGGER.info("11. Click 'Proceed to Checkout'");
        driver.findElement(checkoutButton).click();

        LOGGER.info("12. Enter Billing Information");
        Select newAddressSelect = new Select(driver.findElement(By.id("billing-address-select")));
        Select shippingCountry = new Select(driver.findElement(countrySelectID));

        newAddressSelect.selectByVisibleText("New Address");
        IntStream.range(0, shippingData.length)
                .forEach(i -> driver.findElement(shippingID[i]).clear());
        IntStream.range(0, shippingData.length)
                .forEach(i -> driver.findElement(shippingID[i]).sendKeys(shippingData[i]));
        shippingCountry.selectByValue("PL");

        LOGGER.info("13. In Billing Information, Click Continue");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(continueButtonBillingInformation).click();

        LOGGER.info("14. In Shipping Method, Click Continue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButtonShippingMethod));
        driver.findElement(continueButtonShippingMethod).click();

        LOGGER.info("15. In Payment Information select 'Check/Money Order' radio button, Click Continue'");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkMoneyRadioButton));
        driver.findElement(checkMoneyRadioButton).click();
        driver.findElement(continueButtonPaymentInformation).click();

        LOGGER.info("16. Click 'Place ORDER' button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        driver.findElement(placeOrderButton).click();

        LOGGER.info("17. Verify Order is generated. Note the order number");
        wait.until(ExpectedConditions.titleIs("Magento Commerce"));
        softAssert.assertEquals(driver.findElement(orderMSG).getText(), expectedOrderMSG, "Check order msg");
        softAssert.assertAll();

    }

    private void loginSteps() {
        LOGGER.info("2. Click on my account link");
        driver.findElement(myAccountCssSelector).click();

        LOGGER.info("3. Login in application using previously created credentials");
        IntStream.range(0, loginData.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));
        driver.findElement(loginButton).click();
    }
}
