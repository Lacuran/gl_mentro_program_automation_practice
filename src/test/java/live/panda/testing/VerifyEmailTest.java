package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VerifyEmailTest extends BaseTestSetup {

    By addToWishListButton;

    @Test
    public void VerifyAccountCreationInECommerceAndShareWishlistTest(){
        //variables
        By accountXPath = By.cssSelector("[data-target-element='#header-account']");
        By myAccountLink = RelativeLocator.with(By.cssSelector("[title='My Account']")).below(accountXPath);
        By createAccButton = By.cssSelector("[title='Create an Account']");
        By submitAccButton = RelativeLocator.with(By.cssSelector("[title='Register']")).below(By.cssSelector(".button"));
        By[] registrationForm = {By.id("firstname"), By.id("middlename")
                , By.id("lastname"), By.id("email_address")
                , By.id("password"), By.id("confirmation")};
        String[] registrationData = {"Kornel", "Maybe"
                , "Test", "lenrok.test+gurutest1@gmail.com"
                , "test123", "test123"};
        By registrationDoneText = By.cssSelector(".success-msg");
        String expectedRegistrationMsg = "Thank you for registering with Main Website Store.";
        By tvXpath = By.xpath("//*[text()='TV']");
        addToWishListButton = By.cssSelector(".link-compare");


        LOGGER.info("2. Click on my account link");
        driver.findElement(accountXPath).click();
        driver.findElement(myAccountLink).click();

        LOGGER.info("3. Click Create Account link and fill New User information except Email ID");
        driver.findElement(createAccButton).click();
        for (int i = 0; i < registrationForm.length; i++) {
            driver.findElement(registrationForm[i]).sendKeys(registrationData[i]);
        }

        LOGGER.info("4. Click Register");
        driver.findElement(submitAccButton).click();

        LOGGER.info("5. Verify Registration is done");
        assertEquals(driver.findElement(registrationDoneText).getText(), expectedRegistrationMsg, "Check welcome msg");

        LOGGER.info("6. Go to TV menu");
        driver.findElement(tvXpath).click();

        LOGGER.info("7. Add product in your wish list");
        LOGGER.info("8. Click SHARE WISHLIST");
        LOGGER.info("9. In next page enter Email and a message and click SHARE WISHLIST");
        LOGGER.info("10. Check wishlist is shared");
    }
}
