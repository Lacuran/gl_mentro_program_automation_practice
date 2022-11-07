package live.panda.testing;

import base.test.BaseTestSetup;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.User;

import java.time.Duration;
import java.util.stream.IntStream;

public class VerifyChangeOrReorderPreviouslyAddedProductTest extends BaseTestSetup {

    @Test(description = "8")
    public void verifyYouAreAbleToChangeOrReorderPreviouslyAddedProductTest(){
        //variables
        SoftAssert softAssert = new SoftAssert();
        Faker faker = new Faker();
        User user = new User("Kornel", "Maybe"
                , "Test", "lenrok.test1@gmail.com", "test123");
        //step 2
        By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
        By myAccountCssSelector = RelativeLocator.with(By.cssSelector("[title='My Account']"))
                .below(accountCssSelector);
        //step 3
        By[] loginID = {By.id("email"), By.id("pass")};
        String[] loginData ={user.getEmail(), user.getPassword()};
        By loginButton = By.id("send2");
        //step 4
        By reorderButton = RelativeLocator
                .with(By.cssSelector(".link-reorder"))
                .below(By.cssSelector(".first.odd"));
        By quantityField = By.cssSelector("[title='Qty']");
        By updateButton = By.cssSelector("[title='Update']");
        //step 5
        By grandTotalPriceXpath = By.xpath("//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span");
        String expectedGrandTotalPrice = "$6,150.00";
        //step 6
        By checkoutButton = RelativeLocator
                .with(By.cssSelector(".btn-checkout"))
                .below(By.cssSelector(".top"));
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
        By continueButtonBillingInformation = By.xpath("//*[@id='billing-buttons-container']/button");
        By continueButtonShippingMethod = By.xpath("//*[@id='shipping-method-buttons-container']/button");
        By continueButtonPaymentInformation = By.xpath("//*[@id='payment-buttons-container']/button");
        By checkMoneyRadioButton = By.id("p_method_checkmo");
        By placeOrderButton = By.cssSelector("[title='Place Order']");
        //step 7
        String expectedOrderMSG = "YOUR ORDER HAS BEEN RECEIVED.";
        By orderMSG = By.cssSelector(".page-title");

        LOGGER.info("2. Click on my account link");
        driver.findElement(myAccountCssSelector).click();

        LOGGER.info("3. Login in application using previously created credentials");
        IntStream.range(0, loginData.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));
        driver.findElement(loginButton).click();

        LOGGER.info("4. Click on 'REORDER' Link, change QTY(10) & click Update");
        driver.findElement(reorderButton).click();
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys("10");
        driver.findElement(updateButton).click();

        LOGGER.info("5. Verify Grand Total is changed");
        softAssert.assertEquals(driver.findElement(grandTotalPriceXpath).getText(), expectedGrandTotalPrice, "Check grand total price");

        LOGGER.info("6. Complete Billing & Shipping Information");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(checkoutButton).click();

        Select newAddressSelect = new Select(driver.findElement(By.id("billing-address-select")));
        Select shippingCountry = new Select(driver.findElement(countrySelectID));

        newAddressSelect.selectByVisibleText("New Address");
        IntStream.range(0, shippingData.length)
                .forEach(i -> driver.findElement(shippingID[i]).clear());
        IntStream.range(0, shippingData.length)
                .forEach(i -> driver.findElement(shippingID[i]).sendKeys(shippingData[i]));
        shippingCountry.selectByValue("PL");

        LOGGER.info("Click on the Continue Button");
        driver.findElement(continueButtonBillingInformation).click();

        LOGGER.info("Click on the continue button in Shipping method");
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButtonShippingMethod));
        driver.findElement(continueButtonShippingMethod).click();

        LOGGER.info("Click on the Check / Money radio button and clicking on the continue button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkMoneyRadioButton));
        driver.findElement(checkMoneyRadioButton).click();
        driver.findElement(continueButtonPaymentInformation).click();

        LOGGER.info("Click on the Place order button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        driver.findElement(placeOrderButton).click();

        LOGGER.info("7. Verify order is generated and note the order number");
        wait.until(ExpectedConditions.titleIs("Magento Commerce"));
        softAssert.assertEquals(driver.findElement(orderMSG).getText(), expectedOrderMSG, "Check order msg");
        softAssert.assertAll();
    }
}
